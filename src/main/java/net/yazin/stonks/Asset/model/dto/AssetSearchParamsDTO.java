package net.yazin.stonks.Asset.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssetSearchParamsDTO {
    private int customerId;
    private int pageNumber;
    private int itemCount;
}
