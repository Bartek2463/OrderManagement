package com.example.ordermanagement.model.order.dto;

import com.example.ordermanagement.model.order.JobOrder;
import com.example.ordermanagement.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class JobOrderDetailsDTO {

    private Long id;
    private BigDecimal price;
    private LocalDate startJobOrder;
    private LocalDate endJobOrder;
    private String description;
    @JsonIgnore
    private User user;

    public static JobOrderDetailsDTO mapToDto(JobOrder jobOrder) {
        return new JobOrderDetailsDTO()
                .setId(jobOrder.getId())
                .setPrice(jobOrder.getPrice())
                .setStartJobOrder(jobOrder.getStartJobOrder())
                .setEndJobOrder(jobOrder.getEndJobOrder())
                .setDescription(jobOrder.getDescription())
                .setUser(jobOrder.getUser());
    }

    public static JobOrder mapToModel(JobOrderDetailsDTO jobOrderDetailsDTO) {
        return new JobOrder()
                .setId(jobOrderDetailsDTO.getId())
                .setPrice(jobOrderDetailsDTO.getPrice())
                .setStartJobOrder(jobOrderDetailsDTO.getStartJobOrder())
                .setEndJobOrder(jobOrderDetailsDTO.getEndJobOrder())
                .setDescription(jobOrderDetailsDTO.getDescription())
                .setUser(jobOrderDetailsDTO.getUser());

    }
}
