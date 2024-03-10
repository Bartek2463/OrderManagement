package com.example.ordermanagement.model.order.dto;

import com.example.ordermanagement.model.order.JobOrder;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class JobOrderDetailsDtoUP {

    private Integer price;
    private LocalDate startJobOrder;
    private LocalDate endJobOrder;
    private String description;


    public static JobOrderDetailsDtoUP mapToDto(JobOrder jobOrder) {
        return new JobOrderDetailsDtoUP()
                .setPrice(jobOrder.getPrice())
                .setStartJobOrder(jobOrder.getStartJobOrder())
                .setEndJobOrder(jobOrder.getEndJobOrder())
                .setDescription(jobOrder.getDescription());
    }

    public static JobOrder mapToModel(JobOrderDetailsDtoUP jobOrderDetailsDtoUP) {
        return new JobOrder()
                .setPrice(jobOrderDetailsDtoUP.getPrice())
                .setStartJobOrder(jobOrderDetailsDtoUP.getStartJobOrder())
                .setEndJobOrder(jobOrderDetailsDtoUP.getEndJobOrder())
                .setDescription(jobOrderDetailsDtoUP.getDescription());

    }

}
