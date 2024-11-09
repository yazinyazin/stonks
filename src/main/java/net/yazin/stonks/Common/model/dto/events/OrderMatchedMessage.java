package net.yazin.stonks.Common.model.dto.events;

import lombok.Builder;
import lombok.Getter;
import net.yazin.stonks.Common.model.enums.OrderSide;

@Getter
@Builder
public class OrderMatchedMessage {

    String assetName;

    String assetAgainst;

    OrderSide orderSide;

    double requestedSize;

    double price;

    int customerId;

}
