package edu.kmaooad.capstone23.common;

import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Decorator
public class ValidatingHandler<T1, T2> implements CommandHandler<T1, T2> {

    @Inject
    @Delegate
    private CommandHandler<T1, T2> decoratee;

    @Inject
    private Validator validator;

    @Override
    public Result<T2> handle(T1 command) {

        Set<ConstraintViolation<T1>> violations = validator.validate(command);

        if (!violations.isEmpty()) {
            return new Result<T2>(violations);
        }

        return decoratee.handle(command);
    }

}