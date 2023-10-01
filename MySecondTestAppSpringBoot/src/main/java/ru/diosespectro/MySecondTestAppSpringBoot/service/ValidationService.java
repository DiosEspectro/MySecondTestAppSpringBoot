package ru.diosespectro.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.diosespectro.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.diosespectro.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
}
