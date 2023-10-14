package ru.diosespectro.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.diosespectro.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
