package com.a2.a2_automation_system.money;

import java.util.ArrayList;
import java.util.List;

public enum TypeOfFinance {
    INCOME("Приход"),
    DISCHARGE("Расход");

    public final String rusValue;

    TypeOfFinance(String rusValue) {
        this.rusValue = rusValue;
    }

    public String getRusValue() {
        return this.rusValue;
    }

    public static List<String> getRusValues() {
        List<String> rusValues = new ArrayList<>();
        for (TypeOfFinance t : TypeOfFinance.values()) {
            rusValues.add(t.getRusValue());
        }
        return rusValues;
    }

    public static TypeOfFinance getTypeOfFinanceByRusValue(String rusValue) {
        for (TypeOfFinance t : TypeOfFinance.values()) {
            if (t.getRusValue().equalsIgnoreCase(rusValue)) return t;
        }
        return null;
    }
}
