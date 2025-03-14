package com.fetch.interview.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ProcessReceiptResponse extends BaseSuccessResponse{
    private String id;
}
