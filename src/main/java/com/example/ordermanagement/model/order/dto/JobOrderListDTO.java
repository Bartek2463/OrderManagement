package com.example.ordermanagement.model.order.dto;

import com.example.ordermanagement.model.order.JobOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class JobOrderListDTO {


    private Long id;
    private LocalDate startJobOrder;
    private LocalDate endJobOrder;
    private Integer price;

    public static JobOrderListDTO mapToDto(JobOrder jobOrder) {
        return new JobOrderListDTO()
                .setId(jobOrder.getId())
                .setStartJobOrder(jobOrder.getStartJobOrder())
                .setEndJobOrder(jobOrder.getEndJobOrder())
                .setPrice(jobOrder.getPrice());
    }

    public static JobOrder mapToModel(JobOrderListDTO jobOrderListDTO){
        return new JobOrder()
                .setId(jobOrderListDTO.getId())
                .setStartJobOrder(jobOrderListDTO.getStartJobOrder())
                .setEndJobOrder(jobOrderListDTO.getEndJobOrder())
                .setPrice(jobOrderListDTO.getPrice());
    }

    public static List<JobOrderListDTO> mapToDto(List<JobOrder>jobOrders){
        return jobOrders.stream()
                .map(JobOrderListDTO::mapToDto)
                .collect(Collectors.toList());
    }

}
