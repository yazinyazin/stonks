package net.yazin.stonks.Asset.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yazin.stonks.Common.model.enums.OrderSide;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "asset_type",
        discriminatorType = DiscriminatorType.INTEGER)
@NoArgsConstructor
public abstract class Asset {
    public static final String ASSET_NAME_TURKISH_LIRA = "TRY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assetId;

    private int customerId;

    String assetName;

    protected double size;

    protected double usableSize;

    public double reservedSize() {
        return size - usableSize;
    }

    public boolean reserve(double requestedSize) {


        if (requestedSize > usableSize) {
            return false;
        }

        usableSize -= requestedSize;

        return true;
    }

    public abstract void updateAfterMatchedOrder(OrderSide side, double requestedSize);

    public abstract void updateAfterCancelledOrder(OrderSide side, double requestedSize);

    public static boolean isCashAsset(String assetName) {
        //change this if we ever support other currencies
        return assetName.equalsIgnoreCase(ASSET_NAME_TURKISH_LIRA);
    }


}
