package net.yazin.stonks.Asset.exception;

import lombok.Getter;

@Getter
public class AssetCannotBeReservedException extends IllegalStateException{

    private final int orderId;
    public AssetCannotBeReservedException(String message,int orderId){

        super(message);
        this.orderId = orderId;

    }

}
