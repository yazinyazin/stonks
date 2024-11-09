package net.yazin.stonks.Order.service;

import net.yazin.stonks.Common.model.dto.events.AssetReserveResponseMessage;
import net.yazin.stonks.Order.model.dto.GenerateOrderDTO;

public interface OrderService {

    void generateOrder(GenerateOrderDTO orderDTO);

    void updateOrderAfterAssetReserved(AssetReserveResponseMessage res);

    void matchOrder(int orderId);

    void cancelOrder(int orderId);


}
