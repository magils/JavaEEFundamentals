package com.mgil.labs.java.ee.qualifiers;


import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.METHOD,
    ElementType.FIELD,
    ElementType.LOCAL_VARIABLE,
    ElementType.TYPE,
    ElementType.PARAMETER
})
public @interface NumberValue {
}
