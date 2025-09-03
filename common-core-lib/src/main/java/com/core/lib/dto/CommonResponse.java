package com.core.lib.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponse {

    private int code;

    private String message;

    private String errorMessage;

}
