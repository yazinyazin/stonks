package net.yazin.stonks.Order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.yazin.stonks.Common.model.dto.CustomerInfo;

@AllArgsConstructor
@Getter
public class OrderSearchParamsDTO implements CustomerInfo {

   private String customerId;
   private long startDate;
   private long endDate;
   private int pageNumber;
   private int itemCount;

   @Override
   public void setCustomerId(String customerId) {
      this.customerId = customerId;
   }
}
