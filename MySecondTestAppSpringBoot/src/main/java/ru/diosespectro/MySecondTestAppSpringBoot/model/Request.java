package ru.diosespectro.MySecondTestAppSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "Поле uid не заполнено")
    private String uid; // Уникальный идентификатор сообщение

    @NotBlank(message = "Поле operationUid не заполнено")
    private String operationUid; // Уникальный идентификатор операции

    private Systems systemName; // Имя системы отправителя

    @NotBlank(message = "Поле systemTime не заполнено")
    private String systemTime; // Время создания сообщения

    private String source; // Наименование ресурса

    private Positions position; // Должность
    private Double salary; // Зарплата
    private Double bonus; // Коэффициент
    private Integer workDays; // Рабочие дни

    @Min(value = 1, message = "Значение communicationId не должно быть меньше 1")
    @Max(value = 100000, message = "Значение communicationId не должно быть больше 100000")
    private int communicationId; // Уникальный идентификатор коммуникации

    private int templateId; // Уникальный идентификатор шаблона
    private int productCode; // Код продукта
    private int smsCode; // Смс код

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}
