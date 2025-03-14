package com.fetch.interview.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base class for all types of Success Response bodies returned by this Application.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseSuccessResponse extends BaseResponse{
}
