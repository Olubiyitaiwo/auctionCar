package org.olubiyi.mycarauction.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
    private UUID id;
    private String make;
    private String model;
    private int year;
    private String color;
    private Double mileage;
    private String imageUrl;
}
