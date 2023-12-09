package edu.kmaooad.capstone23.notifications.utils;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Used to resolve conflict between NotifyingCreateCVHandler
 * and CreateCVHandler (both implement CommandHandler<CreateCV, CVCreated>)
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, FIELD})
public @interface Notifying {
}
