package ru.diosespectro.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.diosespectro.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate (Positions positions, double salary, double bonus, int workDays);
    double calculateQuarter (Positions positions, double salary, double bonus, int workDays);
}
