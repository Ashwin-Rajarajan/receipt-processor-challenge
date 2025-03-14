package com.fetch.interview.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class Receipt {

    @NotNull @Pattern( regexp = "^[\\w\\s\\-&]+$")
    private String retailer;

    @NotNull @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @NotNull @JsonFormat(pattern = "HH:mm")
    private LocalTime purchaseTime;

    @NotEmpty
    private List<@Valid Item> items;

    @NotNull @Pattern( regexp = "^\\d+\\.\\d{2}$")
    private String total;
}
