package net.yazin.stonks.Asset.exception;

import lombok.RequiredArgsConstructor;
import net.yazin.stonks.Common.model.dto.events.AssetReserveResponseMessage;
import net.yazin.stonks.Common.model.dto.ErrorMessage;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequiredArgsConstructor
public class AssetExceptionHandler {
    private final ApplicationEventPublisher publisher;

    @ExceptionHandler(AssetCannotBeReservedException.class)
    @Transactional
    protected ResponseEntity<ErrorMessage> handle(AssetCannotBeReservedException ex){

        System.out.println("hee hee");
        publisher.publishEvent(AssetReserveResponseMessage.builder().success(false).orderId(ex.getOrderId()));
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()),BAD_REQUEST);

    }
}
