package net.yazin.stonks.Asset.service;

import net.yazin.stonks.Asset.model.dto.CashRequestDTO;
import net.yazin.stonks.Asset.model.entity.Asset;
import net.yazin.stonks.Common.model.dto.AssetReserveRequestMessage;
import net.yazin.stonks.Common.model.dto.OrderCancelledMessage;
import net.yazin.stonks.Common.model.dto.OrderMatchedMessage;

public interface AssetService {

    void depositCash(CashRequestDTO cashRequest);

    void withdrawCash(CashRequestDTO cashRequest);

    void reserveAsset(AssetReserveRequestMessage msg);

    void updateAssets(OrderCancelledMessage msg);

    void updateAssets(OrderMatchedMessage msg);

    Asset getAsset(int assetId);

}
