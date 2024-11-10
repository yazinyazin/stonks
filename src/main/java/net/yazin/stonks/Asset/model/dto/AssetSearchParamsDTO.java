package net.yazin.stonks.Asset.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.yazin.stonks.Common.security.CustomerInfo;

@AllArgsConstructor
@Getter
@Setter
public class AssetSearchParamsDTO implements CustomerInfo {
    private String customerId;
    private int pageNumber;
    private int itemCount;

}
