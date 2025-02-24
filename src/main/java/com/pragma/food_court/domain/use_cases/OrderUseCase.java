package com.pragma.food_court.domain.use_cases;

import com.pragma.food_court.domain.api.IOrderServicePort;
import com.pragma.food_court.domain.api.IRestaurantServicePort;
import com.pragma.food_court.domain.exception.ClientHasAOrderException;
import com.pragma.food_court.domain.exception.InvalidOrderException;
import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.model.StateEnum;
import com.pragma.food_court.domain.spi.IMessageFeignClientPort;
import com.pragma.food_court.domain.spi.IOrderPersistencePort;
import com.pragma.food_court.domain.spi.ISecurityContextPort;
import com.pragma.food_court.domain.spi.ITraceabilityFeignClientPort;
import com.pragma.food_court.domain.util.PagedResponse;

import java.util.List;
import java.util.Map;


public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort iOrderPersistencePort;
    private final ISecurityContextPort iSecurityContextPort;
    private final ITraceabilityFeignClientPort iTraceabilityFeignClientPort;
    private final IRestaurantServicePort iRestaurantServicePort;
    private final IMessageFeignClientPort iMessageFeignClientPort;

    public OrderUseCase(IOrderPersistencePort iOrderPersistencePort, ISecurityContextPort iSecurityContextPort, ITraceabilityFeignClientPort iTraceabilityFeignClientPort, IRestaurantServicePort iRestaurantServicePort, IMessageFeignClientPort iMessageFeignClientPort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
        this.iSecurityContextPort = iSecurityContextPort;
        this.iTraceabilityFeignClientPort = iTraceabilityFeignClientPort;
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.iMessageFeignClientPort = iMessageFeignClientPort;
    }


    @Override
    public void saveOrder(Order order) {
        order.setState(StateEnum.PENDING.toString());
        order.setClientId(iSecurityContextPort.getUserId());
        boolean hasAOrder = iOrderPersistencePort.hasOrder(order.getClientId());
        if(hasAOrder){
            throw new ClientHasAOrderException("The client has a order");
        }
       long orderId=  iOrderPersistencePort.saveOrder(order);
        iTraceabilityFeignClientPort.saveState(StateEnum.PENDING.toString(),orderId);

    }



    @Override
    public PagedResponse<Order> getAllOrders(int page, int size, String state) {
        long restaurantId = iRestaurantServicePort.getRestaurantIdByEmployeeId(iSecurityContextPort.getUserId());
        return iOrderPersistencePort.getAllOrders(page,size,state, restaurantId);
    }

    @Override
    public void  assignOrder(long orderId) {
       Order order = iOrderPersistencePort.getOrder(orderId);
        if(!order.getState().equals(StateEnum.PENDING.toString())){
            throw new InvalidOrderException("The order is not pending");
        }
        Long employeeId = iSecurityContextPort.getUserId();
        iOrderPersistencePort.assignOrder(orderId,employeeId,StateEnum.PREPARING.toString());
        iTraceabilityFeignClientPort.saveState(StateEnum.PREPARING.toString(),orderId);
    }

    @Override
    public void notifyOrderReady(long orderId) {
        Order order = iOrderPersistencePort.getOrder(orderId);
        if(!order.getState().equals(StateEnum.PREPARING.toString())){
            throw new InvalidOrderException("The order is not being preparing");
        }
        order.setState(StateEnum.READY.toString());
        iOrderPersistencePort.changeState(orderId,StateEnum.READY.toString());
        iTraceabilityFeignClientPort.saveState(StateEnum.READY.toString(),orderId);
        iMessageFeignClientPort.sendMessage(orderId,order.getClientId(),"Your order is ready");
    }

    @Override
    public void deliverOrder(long orderId,String securityCode) {

        Order order = iOrderPersistencePort.getOrder(orderId);
        if(!order.getState().equals(StateEnum.READY.toString())){
            throw new InvalidOrderException("The order is not ready");
        }
        if(!iMessageFeignClientPort.verifySecurityCode(orderId,securityCode)){
            throw new InvalidOrderException("The security code is invalid");
        }


        order.setState(StateEnum.DELIVERED.toString());
        iOrderPersistencePort.changeState(orderId,StateEnum.DELIVERED.toString());
        iTraceabilityFeignClientPort.saveState(StateEnum.DELIVERED.toString(),orderId);
    }

    @Override
    public void cancelOrder(long orderId) {
        Order order = iOrderPersistencePort.getOrder(orderId);
        if(!order.getState().equals(StateEnum.PENDING.toString())){
            iMessageFeignClientPort.sendMessage(orderId,order.getClientId(),"The order is not pending, it can not be canceled");
            throw new InvalidOrderException("The order is not pending, it can not be canceled");
        }
        order.setState(StateEnum.CANCELED.toString());
        iOrderPersistencePort.changeState(orderId,StateEnum.CANCELED.toString());
        iTraceabilityFeignClientPort.saveState(StateEnum.CANCELED.toString(),orderId);
    }

    @Override
    public List<Long> getOrdersIdByClientId(long clientId) {
        return iOrderPersistencePort.getOrdersIdByClientId(clientId);
    }

    @Override
    public List<Long> getAllOrdersId() {
        return iOrderPersistencePort.getAllOrdersId();
    }

    @Override
    public Map<Long, Long> getOrdersIdAndEmployeeId() {
        return iOrderPersistencePort.getOrdersIdAndEmployeeId();
    }

}
