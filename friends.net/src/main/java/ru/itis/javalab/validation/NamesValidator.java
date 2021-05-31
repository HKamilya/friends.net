package ru.itis.javalab.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NamesValidator implements ConstraintValidator<ValidNames, Object> {

    private String passwordPropertyName;
    private String confPassPropertyName;

    @Override
    public void initialize(ValidNames constraintAnnotation) {
        this.passwordPropertyName = constraintAnnotation.password();
        this.confPassPropertyName = constraintAnnotation.confPass();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object password = new BeanWrapperImpl(value).getPropertyValue(passwordPropertyName); //получили конкретные значения
        Object confPassword = new BeanWrapperImpl(value).getPropertyValue(confPassPropertyName);

        return password != null && password.equals(confPassword);
    }
}

