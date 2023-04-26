package com.pfecigma.banking.validators;


import java.util.Set;
import java.util.stream.Collectors;

import com.pfecigma.banking.exception.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

/**
 * @author Ali Bouali
 * @since 15.09.22
 */

@Component
public class ObjectsValidator<T> {


    /// This method creates an instance of the default
    // validator factory with default configuration settings for validation
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    //The second line uses the ValidatorFactory instance to get an instance of Validator, which is used to
    // validate objects.
    // The getValidator() method from ValidatorFactory returns an instance of Validator that you can
    // use to validate objects.
    private final Validator validator = factory.getValidator();////

    public void validate(T objectToValidate) {
        //The method returns a Set of ConstraintViolation objects, which represent the validation
        // errors found during the validation process. If no errors are found, an empty Set is returned.
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()) {
            //In summary, the code uses Java 8 streams to extract the
            // error messages from a set of ConstraintViolation objects, and collects them into a Set of String
            // objects, which can be used to display or handle the validation errors in a user-friendly way.
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationException(errorMessages, objectToValidate.getClass().getName());
        }
    }

}

