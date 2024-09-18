package com.example.laboratory.ExceptionHandler.Factory;

import com.example.laboratory.ExceptionHandler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class UserExceptionFactory {

    private static final Map<ExceptionType, Supplier<UserException>> exceptionRegistry = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(UserExceptionFactory.class);

    static {
        exceptionRegistry.put(ExceptionType.USER_ALREADY_EXISTS,
                () -> new UserAlreadyExistsException("User already exists"));
        exceptionRegistry.put(ExceptionType.INVALID_FCM_TOKEN,
                () -> new InvalidFCMTokenException("Invalid FCM token"));
        exceptionRegistry.put(ExceptionType.USER_NOT_FOUND,
                () -> new UserNotFoundException("User not found"));
    }

    public static UserException createException(ExceptionType type, String message) {
        logger.info("Creating exception of type: {} with message: {}", type, message);

        Supplier<UserException> exceptionSupplier = exceptionRegistry.get(type);
        if (exceptionSupplier != null) {
            return exceptionSupplier.get();
        }
        logger.error("Unknown exception type: {}", type);
        throw new IllegalArgumentException("Unknown exception type: " + type);
    }

    public static void registerException(ExceptionType type, Supplier<UserException> supplier) {
        logger.info("Registering new exception type: {}", type);
        exceptionRegistry.put(type, supplier);
    }
}

