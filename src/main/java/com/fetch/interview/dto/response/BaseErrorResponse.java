package com.fetch.interview.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base class for all types of Error Response bodies returned by this Application.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class BaseErrorResponse extends BaseResponse{
    private String description;
}
