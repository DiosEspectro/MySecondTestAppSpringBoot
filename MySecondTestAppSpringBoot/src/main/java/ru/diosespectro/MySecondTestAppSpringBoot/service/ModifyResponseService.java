package ru.diosespectro.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.diosespectro.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
