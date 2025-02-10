package com.pragma.food_court.domain.use_cases;

import com.pragma.food_court.domain.api.IOrderServicePort;
import com.pragma.food_court.domain.exception.ClientHasAOrderException;
import com.pragma.food_court.domain.model.Order;
import com.pragma.food_court.domain.model.StateEnum;
import com.pragma.food_court.domain.spi.IOrderPersistencePort;
import com.pragma.food_court.domain.spi.ISecurityContextPort;
import com.pragma.food_court.domain.spi.ITraceabilityFeignClientPort;


public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort iOrderPersistencePort;
    private final ISecurityContextPort iSecurityContextPort;
    private final ITraceabilityFeignClientPort iTraceabilityFeignClientPort;
    public OrderUseCase(IOrderPersistencePort iOrderPersistencePort, ISecurityContextPort iSecurityContextPort, ITraceabilityFeignClientPort iTraceabilityFeignClientPort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
        this.iSecurityContextPort = iSecurityContextPort;
        this.iTraceabilityFeignClientPort = iTraceabilityFeignClientPort;
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

}
