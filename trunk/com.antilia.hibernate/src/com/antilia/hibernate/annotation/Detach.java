package com.antilia.hibernate.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * the <code>Detach</code> annotation marks a method in an entity
 * that gets called when the entity is detached. The serviceExecuter
 * will call this method to detach the entity from the persistence tier
 * before passing it to the presentation tier.
 * 
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Detach {
}
