package com.a2.a2_automation_system.money;

import java.util.ArrayList;
import java.util.List;

public enum MoneyOperationType {
    SPORTSMAN_PAYMENT("Оплата ученика"),
    RETURN_SPORTSMAN_PAYMENT("Возврат оплаты ученику"),
    OTHER_OPERATIONS("Прочие операции");

    public final String rusValue;

    MoneyOperationType(String rusValue) {
        this.rusValue = rusValue;
    }

    public String getRusValue() {
        return this.rusValue;
    }

    public static List<String> getRusValues() {
        List<String> rusValues = new ArrayList<>();
        for (MoneyOperationType t : MoneyOperationType.values()) {
            rusValues.add(t.getRusValue());
        }
        return rusValues;
    }

    public static MoneyOperationType getOperationTypeByRusValue(String rusValue) {
        for (MoneyOperationType t : MoneyOperationType.values()) {
            if (t.getRusValue().equalsIgnoreCase(rusValue)) return t;
        }
        return null;
    }
}
