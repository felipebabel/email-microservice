package com.emailmicroservice.core.util;

import lombok.Getter;

@Getter
public class DefaultResponse {

    public static final String ERROR = "Error";

    public static final String SUCESS = "Success";

    private final String status;

    private final String message;

    public DefaultResponse(final String status,
                           final String message) {
        this.status = status;
        this.message = message;
    }

}
