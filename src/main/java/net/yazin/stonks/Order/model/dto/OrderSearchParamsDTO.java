package net.yazin.stonks.Order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderSearchParamsDTO {

   private int customerId;
   private long startDate;
   private long endDate;
   private int pageNumber;
   private int itemCount;

}
