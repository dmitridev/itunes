package org.astelit.itunes.contstraint;


import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotNull
@Length(min = 1, max = 4)
@Pattern(regexp = "^[0-9]*$", message = "cодержит недопустимые символы")
@Min(value = -1,message="Значение не может быть отрицательным")
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface NotNegative{

    String message() default "содержит недопустимые символы" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

