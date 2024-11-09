package net.yazin.stonks.Common.model.dto;

import lombok.Builder;
import lombok.Getter;
import net.yazin.stonks.Common.model.enums.OrderSide;

@Getter
@Builder
public class AssetReserveResponseMessage {
    int orderId;
    boolean success;
}
