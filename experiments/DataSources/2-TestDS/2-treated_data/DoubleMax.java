package org.imogene.web.server.validation ; @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER } )@Retention(RUNTIME)@Documented@Constraint(validatedBy = DoubleMaxValidator.class)public @interface DoubleMax {   String message() default "{org.imogene.web.server.validation.DoubleMax.message}" ;  Class<?>[] groups() default { }  ;  Class<? extends Payload>[] payload() default {} ;  * @return value the element must be inferior or equal to double value() ; }