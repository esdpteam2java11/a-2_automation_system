package com.a2.a2_automation_system.money;


public enum TypeOfFinance {
    INCOME("Приход"),

    DISCHARGE("Расход");

    public final String rusValue;

    TypeOfFinance(String rusValue) {
        this.rusValue = rusValue;
    }

    public String getType(){
        return this.rusValue;
    }

    public static TypeOfFinance getTypeOfFinanceByRusValue(String rusValue){
        for (TypeOfFinance t:TypeOfFinance.values()) {
            if (t.getType().equalsIgnoreCase(rusValue)) return t;
        }
        return null;
    }
}
