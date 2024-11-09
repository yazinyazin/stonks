package net.yazin.stonks.Asset.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CashRequestDTO {
    private String assetName;
    private double amount;
    private int customerId;
}
