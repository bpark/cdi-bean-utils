package com.github.bpark.cdi.ordered;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Documented
@Retention(RUNTIME)
@Target( { TYPE, METHOD, FIELD, PARAMETER })
public @interface Ordered {

    /** the order value */
    @Nonbinding
    int value() default 0;
}
