package com.a2.a2_automation_system.money;


import java.util.ArrayList;
import java.util.List;

public enum ManyOperationType {
    SPORTSMAN_PAYMENT("Оплата от ученика"),

    OTHER_OPERATIONS("Прочие операции");

    public final String rusValue;

    ManyOperationType(String rusValue) {
        this.rusValue = rusValue;
    }

    public String getRusValue() {
        return this.rusValue;
    }

    public static List<String> getRusValues() {
        List<String> rusValues = new ArrayList<>();
        for (ManyOperationType t : ManyOperationType.values()) {
            rusValues.add(t.getRusValue());
        }
        return rusValues;
    }

    public static ManyOperationType getOperationTypeByRusValue(String rusValue) {
        for (ManyOperationType t : ManyOperationType.values()) {
            if (t.getRusValue().equalsIgnoreCase(rusValue)) return t;
        }
        return null;
    }
}
