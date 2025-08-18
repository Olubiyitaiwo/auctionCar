package org.olubiyi.mycarauction.dtos.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ItemRequestDto {
    private String make;
    private String model;
    private int year;
    private String color;
    private Double mileage;
    private String imageUrl;
}
