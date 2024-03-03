package com.emailmicroservice.core.util;

public class DefaultResponse {

    public static final String ERROR = "Error";
    public static final String SUCESS = "Success";

    private final String status;
    private final String message;

    /**
     * This constructor will be deprecated soon. New factory approach will be soon implemented.
     * Please, consider using DefaultResponseImpl method 'asResponse()' for constant messages, or DefaultResponseFactory.create().
     */
    public DefaultResponse(final String status, final String message) {
        this.status = status;
        this.message = message;
    }

}
