package org.olubiyi.mycarauction.data.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Bid {
    private UUID id;
    private double price;
    private String message;
}
