package com.api.ecomerce.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionUserMetricsResponse {
    private BigDecimal total_purchases_of_the_day;
    private Integer total_purchases;
    private BigDecimal average_purchases_value;
    private Integer total_points_earned;
    private  BigDecimal average_points_earned;
    private Integer points_earned_of_the_day;
}
