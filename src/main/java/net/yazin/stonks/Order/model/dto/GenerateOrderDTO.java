package net.yazin.stonks.Order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.yazin.stonks.Common.model.enums.OrderSide;
import net.yazin.stonks.Common.security.CustomerInfo;

@AllArgsConstructor
@Getter
public class GenerateOrderDTO implements CustomerInfo {

    private String customerId;

    private String assetName;

    private String assetAgainst;

    private OrderSide side;

    private long size;

    private double price;

    @Override
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
