package net.yazin.stonks.Order.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yazin.stonks.Common.model.enums.OrderSide;
import net.yazin.stonks.Common.model.enums.OrderStatus;
import net.yazin.stonks.Order.model.dto.GenerateOrderDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int orderId;

    private int customerId;

    private String assetName;

    private String assetAgainst;

    private OrderSide side;

    private OrderStatus status;

    private long size;

    private double price;

    private long createdDate;



    public static Order generateNewOrder(GenerateOrderDTO orderDTO){
        Order order = new Order(orderDTO.getCustomerId(), orderDTO.getAssetName(), orderDTO.getAssetAgainst(), orderDTO.getSide(), orderDTO.getSize(), orderDTO.getPrice());
        order.setStatus(OrderStatus.TENTATIVE);
        order.setCreatedDate(System.currentTimeMillis());
        return order;
    }

    public Order(int customerId, String assetName, String assetAgainst, OrderSide side, long size, double price) {
        this.customerId = customerId;
        this.assetName = assetName;
        this.assetAgainst = assetAgainst;
        this.side = side;
        this.size = size;
        this.price = price;
    }

    public void checkIfMatchable(){
        if(this.status != OrderStatus.PENDING){
            throw new IllegalStateException("Cannot match an order in this state.");
        }
    }

    public void match(){
        checkIfMatchable();
        status = OrderStatus.MATCHED;
    }

    public void checkIfCancelable(){
        if(this.status != OrderStatus.PENDING){
            throw new IllegalStateException("Cannot cancel an order in this state.");
        }
    }

    public void cancel(){
        checkIfCancelable();
        status = OrderStatus.CANCELLED;
    }

}
