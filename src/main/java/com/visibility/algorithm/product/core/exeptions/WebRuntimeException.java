package com.visibility.algorithm.product.core.exeptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * The {@code WebRuntimeException} class extends {@code RuntimeException} and represents a standard error structure for web-based applications.
 * In addition to the standard error message and cause, it includes an {@code ErrorCode} and {@code HttpStatus} to provide more information about the error.
 * The class uses the Lombok {@code @Getter} annotation to automatically generate getter methods for its fields.
 */
@Getter
public class WebRuntimeException extends RuntimeException{

    private static final long serialVersionUID = 6757077657798234323L;

    /**
     * An error code that provides more specific information about the type of error that occurred.
     */
    private final ErrorCode errorCode;

    /**
     * The HTTP status that should be returned as a response to the request that resulted in this error.
     */
    private final HttpStatus httpStatus;

    /**
     * Constructs a new web runtime exception with a specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@code Throwable.getMessage()} method).
     */
    public WebRuntimeException(String message) {
        super(message);
        this.errorCode = null;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Constructs a new web runtime exception with a specified detail message, cause, error code, and HTTP status.
     *
     * @param cause the cause (which is saved for later retrieval by the {@code Throwable.getCause()} method).
     */
    public WebRuntimeException(String message, Throwable cause) {
        super(message, cause);
        errorCode = null;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Constructs a new web runtime exception with a specified detail message, cause, error code, and HTTP status.
     *
     * @param cause the cause (which is saved for later retrieval by the {@code Throwable.getCause()} method).
     */
    public WebRuntimeException(Throwable cause) {
        super(cause);
        errorCode = null;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Constructs a new web runtime exception with a specified detail message, cause, error code, and HTTP status.
     *
     * @param message the detail message (which is saved for later retrieval by the {@code Throwable.getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@code Throwable.getCause()} method).
     * @param errorCode the error code.
     * @param httpStatus the HTTP status.
     */
    public WebRuntimeException(String message, Throwable cause, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    /**
     * Constructs a new web runtime exception with a specified error code and HTTP status.
     *
     * @param errorCode the error code.
     * @param httpStatus the HTTP status.
     */
    public WebRuntimeException(ErrorCode errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}

