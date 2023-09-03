package edu.kmaooad.capstone23.common;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class ValidatingHandler<T1,T2>  implements CommandHandler<T1,T2> {

    @Inject
    @Delegate
    private CommandHandler<T1,T2> decoratee;

    @Override
    public Result<T2> handle(T1 command) {
        return decoratee.handle(command);
    }

}