package net.yazin.stonks.Asset.data.repository;

import net.yazin.stonks.Asset.model.entity.Asset;
import net.yazin.stonks.Asset.model.entity.CashAsset;
import net.yazin.stonks.Order.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset,Integer> {
    @Query("SELECT a FROM Asset a WHERE TYPE(a) = CashAsset AND a.assetName = :var")
    Optional<CashAsset> findCashAssetByName(@Param("var")String assetName);

    @Query("SELECT a FROM Asset a WHERE a.assetName = :var")
    Optional<Asset> findAssetByName(@Param("var")String assetName);

    Page<Asset> findByCustomerId(String customerId, Pageable pageable);

}
