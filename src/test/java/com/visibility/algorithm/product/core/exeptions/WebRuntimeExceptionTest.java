package com.visibility.algorithm.product.core.exeptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class WebRuntimeExceptionTest {


    private static final String TEST_MESSAGE = "Test Message";
    private static final ErrorCode TEST_ERROR_CODE = new ErrorCode() {
        @Override
        public String getCode() {
            return "TEST_ERROR_CODE";
        }
    };

    @Test
    @DisplayName("Test Context Exception With Message")
    void testExceptionWithMessage() {
        WebRuntimeException exception = new WebRuntimeException(TEST_MESSAGE);
        assertEquals(TEST_MESSAGE, exception.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Test Context Exception With Message And Cause")
    void testExceptionWithMessageAndCause() {
        Throwable cause = new Throwable("Test Cause");
        WebRuntimeException exception = new WebRuntimeException(TEST_MESSAGE, cause);
        assertEquals(TEST_MESSAGE, exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(cause, exception.getCause());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Test Context Exception With Cause")
    void testExceptionWithCause() {
        Throwable cause = new Throwable("Test Cause");
        WebRuntimeException exception = new WebRuntimeException(cause);
        assertNotNull(exception.getCause());
        assertEquals(cause, exception.getCause());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Test Context Exception With Message Cause Error Code And HttpStatus")
    void testExceptionWithMessageCauseErrorCodeAndHttpStatus() {
        Throwable cause = new Throwable("Test Cause");
        WebRuntimeException exception = new WebRuntimeException(TEST_MESSAGE, cause, TEST_ERROR_CODE, HttpStatus.BAD_REQUEST);
        assertEquals(TEST_MESSAGE, exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(cause, exception.getCause());
        assertEquals(TEST_ERROR_CODE, exception.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Test Context Exception With Error Code And HttpStatus")
    void testExceptionWithErrorCodeAndHttpStatus() {
        WebRuntimeException exception = new WebRuntimeException(TEST_ERROR_CODE, HttpStatus.BAD_REQUEST);
        assertEquals(TEST_ERROR_CODE, exception.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
