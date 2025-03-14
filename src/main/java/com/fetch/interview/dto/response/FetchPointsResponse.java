package com.fetch.interview.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class FetchPointsResponse extends BaseSuccessResponse{
    private long points;
}
