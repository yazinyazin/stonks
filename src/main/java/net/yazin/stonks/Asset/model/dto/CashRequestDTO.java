package net.yazin.stonks.Asset.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.yazin.stonks.Common.model.dto.CustomerInfo;

@Getter
@Setter
@AllArgsConstructor
public class CashRequestDTO implements CustomerInfo {
    private String assetName;
    private double amount;
    private String customerId;
}
