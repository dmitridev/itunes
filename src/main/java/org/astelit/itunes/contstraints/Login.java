package org.astelit.itunes.contstraints;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NotNull
@Length(min = 4, max = 64)
@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "cодержит недопустимые символы")
@Constraint(validatedBy = { })
public @interface Login {
    String message() default "{javax.validation.constraints.NotNull.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
