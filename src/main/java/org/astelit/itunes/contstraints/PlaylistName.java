package org.astelit.itunes.contstraints;

import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NotEmpty
@Pattern(regexp = "^[а-яА-Я0-9- ]*$", message = "содержит недопустимые символы")
@Size(min = 4, max = 255)
public @interface PlaylistName {
    String message() default "Cодержит недопустимые символы";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
