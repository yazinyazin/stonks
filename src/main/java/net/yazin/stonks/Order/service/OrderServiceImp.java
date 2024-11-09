package net.yazin.stonks.Order.service;

import lombok.RequiredArgsConstructor;
import net.yazin.stonks.Common.model.dto.AssetReserveRequestMessage;
import net.yazin.stonks.Common.model.dto.AssetReserveResponseMessage;
import net.yazin.stonks.Common.model.dto.OrderMatchedMessage;
import net.yazin.stonks.Common.model.enums.OrderSide;
import net.yazin.stonks.Common.model.enums.OrderStatus;
import net.yazin.stonks.Order.data.repository.OrderRepository;
import net.yazin.stonks.Order.model.dto.GenerateOrderDTO;
import net.yazin.stonks.Order.model.entity.Order;
import net.yazin.stonks.Order.model.mapper.OrderMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;
    private final OrderMapper orderMapper;

    private AssetReserveRequestMessage getReservationMessage(Order order){

        return order.getSide() == OrderSide.BUY ?
                //Buying stock. Reserve cash.
                AssetReserveRequestMessage.builder()
                                            .orderId(order.getOrderId())
                                            .requestedSize(order.getSize()*order.getPrice())
                                            .assetName(order.getAssetAgainst())
                                            .build()
                //Selling stock. Reserve stock.
                :AssetReserveRequestMessage.builder()
                                            .orderId(order.getOrderId())
                                            .requestedSize(order.getSize())
                                            .assetName(order.getAssetName())
                                            .build();

    }

    @Override
    public void generateOrder(GenerateOrderDTO orderDTO) {

        var order = orderRepository.save(Order.generateNewOrder(orderDTO));

        publisher.publishEvent(getReservationMessage(order));

    }

    @Override
    @ApplicationModuleListener
    public void updateOrderAfterAssetReserved(AssetReserveResponseMessage res) {
        if(res.isSuccess()){
            orderRepository.updateOrderStatus(OrderStatus.PENDING,res.getOrderId());
        }
        else{
            orderRepository.deleteById(res.getOrderId());
        }
    }

    @Override
    public void matchOrder(int orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        order.match();
        orderRepository.save(order);
        publisher.publishEvent(orderMapper.getOrderMatchedMessage(order));

    }

    @Override
    public void cancelOrder(int orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        order.cancel();
        orderRepository.save(order);
        publisher.publishEvent(orderMapper.getOrderCancelledMessage(order));

    }
}
