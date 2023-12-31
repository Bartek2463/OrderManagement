package com.example.ordermanagement.model.order.dto;

import com.example.ordermanagement.model.order.JobOrder;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class JobOrderDetailsDtoUP {

    private BigDecimal price;
    private LocalDate dateJobOrder;
    private String description;


    public static JobOrderDetailsDtoUP mapToDto(JobOrder jobOrder) {
        return new JobOrderDetailsDtoUP()
                .setPrice(jobOrder.getPrice())
                .setDateJobOrder(jobOrder.getDateJobOrder())
                .setDescription(jobOrder.getDescription());
    }

    public static JobOrder mapToModel(JobOrderDetailsDTO jobOrderDetailsDTO) {
        return new JobOrder()
                .setPrice(jobOrderDetailsDTO.getPrice())
                .setDateJobOrder(jobOrderDetailsDTO.getDateJobOrder())
                .setDescription(jobOrderDetailsDTO.getDescription())
                .setUser(jobOrderDetailsDTO.getUser());

    }

}
