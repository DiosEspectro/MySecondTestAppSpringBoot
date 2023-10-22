package ru.diosespectro.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.diosespectro.MySecondTestAppSpringBoot.model.Positions;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.IsoFields;

@Service
public class AnnualBounsServiceImpl implements AnnualBonusService{
    @Override
    public double calculate (Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().length();
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarter (Positions positions, double salary, double bonus, int workDays) {
        if(!positions.isManager()) return 0;

        int quarter = LocalDate.now().get(IsoFields.QUARTER_OF_YEAR);
        int daysInQuart = switch (quarter) {
            case 1 -> Year.now().isLeap() ? 91 : 90;
            case 2 -> 91;
            case 3, 4 -> 92;
            default -> 0;
        };

        return salary * bonus * daysInQuart * positions.getPositionCoefficient() / workDays;
    }
}
