package net.yazin.stonks.Asset.service;

import lombok.RequiredArgsConstructor;
import net.yazin.stonks.Asset.data.repository.AssetRepository;
import net.yazin.stonks.Asset.model.dto.CashRequestDTO;
import net.yazin.stonks.Asset.model.entity.Asset;
import net.yazin.stonks.Asset.model.entity.CashAsset;
import net.yazin.stonks.Asset.model.entity.StockAsset;
import net.yazin.stonks.Common.model.dto.events.AssetReserveRequestMessage;
import net.yazin.stonks.Common.model.dto.events.AssetReserveResponseMessage;
import net.yazin.stonks.Common.model.dto.events.OrderCancelledMessage;
import net.yazin.stonks.Common.model.dto.events.OrderMatchedMessage;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.yazin.stonks.Asset.model.entity.Asset.isCashAsset;

@Service
@RequiredArgsConstructor
public class AssetServiceImp implements AssetService {

    private final AssetRepository assetRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public void depositCash(CashRequestDTO cashRequest) {

        CashAsset cashAsset = assetRepository.findCashAssetByName(cashRequest.getAssetName()).orElseGet(() -> new CashAsset(cashRequest.getAssetName(),cashRequest.getCustomerId()));

        cashAsset.deposit(cashAsset.getSize());

        assetRepository.save(cashAsset);

    }

    @Override
    public void withdrawCash(CashRequestDTO cashRequest) {

        CashAsset cashAsset = assetRepository.findCashAssetByName(cashRequest.getAssetName()).orElseGet(() -> new CashAsset(cashRequest.getAssetName(),cashRequest.getCustomerId()));

        cashAsset.withdraw(cashAsset.getSize());

        assetRepository.save(cashAsset);

    }

    private Asset generateNewAsset(String assetName,int customerId){
        return isCashAsset(assetName) ? new CashAsset(assetName,customerId) : new StockAsset(assetName,customerId);
    }

    @Override
    @ApplicationModuleListener
    public void reserveAsset(AssetReserveRequestMessage msg) {

        Asset asset = assetRepository.findAssetByName(msg.getAssetName()).orElseThrow(()->new RuntimeException("Asset not found"));

        asset.reserve(msg.getOrderId(),msg.getRequestedSize());

        assetRepository.save(asset);

        publisher.publishEvent(AssetReserveResponseMessage.builder().success(true).orderId(msg.getOrderId()).build());

    }

    @Override
    @ApplicationModuleListener
    public void updateAssets(OrderCancelledMessage msg) {

        Asset asset = assetRepository.findAssetByName(msg.getAssetName()).orElseGet(()->generateNewAsset(msg.getAssetName(),msg.getCustomerId()));
        CashAsset cashAsset = assetRepository.findCashAssetByName(msg.getAssetAgainst()).orElseGet(()->new CashAsset(msg.getAssetAgainst(),msg.getCustomerId()));

        asset.updateAfterCancelledOrder(msg.getOrderSide(), msg.getRequestedSize());
        cashAsset.updateAfterCancelledOrder(msg.getOrderSide(), msg.getRequestedSize() * msg.getPrice());

        assetRepository.saveAll(List.of(asset,cashAsset));
    }

    @Override
    @ApplicationModuleListener
    public void updateAssets(OrderMatchedMessage msg) {

        Asset asset = assetRepository.findAssetByName(msg.getAssetName()).orElseGet(()->generateNewAsset(msg.getAssetName(), msg.getCustomerId()));
        CashAsset cashAsset = assetRepository.findCashAssetByName(msg.getAssetAgainst()).orElseGet(()->new CashAsset(msg.getAssetAgainst(),msg.getCustomerId()));

        asset.updateAfterMatchedOrder(msg.getOrderSide(), msg.getRequestedSize());
        cashAsset.updateAfterMatchedOrder(msg.getOrderSide(), msg.getRequestedSize() * msg.getPrice());

        assetRepository.saveAll(List.of(asset,cashAsset));

    }

    @Override
    public Asset getAsset(int assetId) {
        return assetRepository.findById(assetId).orElseThrow(() -> new RuntimeException("Asset not found"));
    }
}
