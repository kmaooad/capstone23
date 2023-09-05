package edu.kmaooad.capstone23.common;

public interface CommandHandler<T1, T2> {
    Result<T2> handle(T1 command);
}