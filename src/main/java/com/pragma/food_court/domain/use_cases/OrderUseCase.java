package com.pragma.food_court.domain.use_cases;

import com.pragma.food_court.domain.api.IOrderServicePort;
import com.pragma.food_court.domain.api.IRestaurantServicePort;
import com.pragma.food_court.domain.exception.ClientHasAOrderException;
import com.pragma.food_court.domain.exception.InvalidOrderException;
import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.model.StateEnum;
import com.pragma.food_court.domain.spi.IOrderPersistencePort;
import com.pragma.food_court.domain.spi.ISecurityContextPort;
import com.pragma.food_court.domain.spi.ITraceabilityFeignClientPort;
import com.pragma.food_court.domain.util.PagedResponse;


public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort iOrderPersistencePort;
    private final ISecurityContextPort iSecurityContextPort;
    private final ITraceabilityFeignClientPort iTraceabilityFeignClientPort;
    private final IRestaurantServicePort iRestaurantServicePort;
    public OrderUseCase(IOrderPersistencePort iOrderPersistencePort, ISecurityContextPort iSecurityContextPort, ITraceabilityFeignClientPort iTraceabilityFeignClientPort, IRestaurantServicePort iRestaurantServicePort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
        this.iSecurityContextPort = iSecurityContextPort;
        this.iTraceabilityFeignClientPort = iTraceabilityFeignClientPort;
        this.iRestaurantServicePort = iRestaurantServicePort;
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
    public PagedResponse<Order> getAllOrdersByClientId( int page, int size) {
        long restaurantId = iRestaurantServicePort.getRestaurantIdByEmployeeId(iSecurityContextPort.getUserId());
        return iOrderPersistencePort.getAllOrdersByClientId(page,size, restaurantId);
    }

    @Override
    public void  assignOrder(long orderId) {
       Order order = iOrderPersistencePort.getOrder(orderId);
        if(!order.getState().equals(StateEnum.PENDING.toString())){
            throw new InvalidOrderException("The order is not pending");
        }
        Long employeeId = iSecurityContextPort.getUserId();
        order.setEmployeeId(employeeId);
        order.setState(StateEnum.PREPARING.toString());
        iOrderPersistencePort.updateState(order);
        iTraceabilityFeignClientPort.saveState(StateEnum.PREPARING.toString(),orderId);
    }

    @Override
    public void notifyOrderReady(long orderId) {
        Order order = iOrderPersistencePort.getOrder(orderId);
        if(!order.getState().equals(StateEnum.PREPARING.toString())){
            throw new InvalidOrderException("The order is not preparing");
        }
        order.setState(StateEnum.READY.toString());
        iOrderPersistencePort.updateState(order);
        iTraceabilityFeignClientPort.saveState(StateEnum.READY.toString(),orderId);
    }

}
