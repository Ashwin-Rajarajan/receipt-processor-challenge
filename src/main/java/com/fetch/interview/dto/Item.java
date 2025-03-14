package com.fetch.interview.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Item {

    @NotNull @Pattern( regexp = "^[\\w\\s\\-]+$")
    private String shortDescription;

    @NotNull @Pattern( regexp = "^\\d+\\.\\d{2}$")
    private String price;
}
