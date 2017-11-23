package fr.pizzeria.model;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * The @ToString annotation indicates that the attribute has to be taken into
 * account when we do Model.toString()
 * 
 * @author ETY0002
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME) // can be used at run time
@Target({ ElementType.FIELD }) // can only be used on attributes
public @interface ToString {
	boolean uppercase() default false;

	String symbol() default "";

	String surroundedBefore() default " ";

	String surroundedAfter() default " ";
}