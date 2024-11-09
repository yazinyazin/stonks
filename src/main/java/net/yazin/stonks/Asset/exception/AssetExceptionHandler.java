package net.yazin.stonks.Asset.exception;

import lombok.RequiredArgsConstructor;
import net.yazin.stonks.Common.model.dto.AssetReserveResponseMessage;
import net.yazin.stonks.Common.model.dto.ErrorMessage;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequiredArgsConstructor
public class AssetExceptionHandler {
    private final ApplicationEventPublisher publisher;

    @ExceptionHandler(AssetCannotBeReservedException.class)
    protected ResponseEntity<ErrorMessage> handle(AssetCannotBeReservedException ex){

        publisher.publishEvent(AssetReserveResponseMessage.builder().success(false).orderId(ex.getOrderId()));
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(ex.getMessage()),BAD_REQUEST);

    }
}
