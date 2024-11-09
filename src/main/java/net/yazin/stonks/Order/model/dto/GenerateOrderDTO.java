package net.yazin.stonks.Order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.yazin.stonks.Common.model.enums.OrderSide;

@AllArgsConstructor
@Getter
public class GenerateOrderDTO {

    private int customerId;

    private String assetName;

    private String assetAgainst;

    private OrderSide side;

    private long size;

    private double price;

}
