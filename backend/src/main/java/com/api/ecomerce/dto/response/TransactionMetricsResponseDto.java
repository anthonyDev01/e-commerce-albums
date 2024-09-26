package com.api.ecomerce.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionMetricsResponseDto {
    private BigDecimal total_sales_of_the_day;
    private Integer total_sales;
    private BigDecimal average_sales_value;
    private Integer total_points_given;
    private  BigDecimal average_points_earned;
    private Integer points_earned_of_the_day;
}
