package com.a2.a2_automation_system.money;


public enum TypeOfFinance {
    INCOME("доход"),

    DISCHARGE("расход");

    public final String type;

    private TypeOfFinance(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static TypeOfFinance getTypeOfFinanceByTypeOfFinanceType(String type){
        for (TypeOfFinance t:TypeOfFinance.values()) {
            if (t.getType().equalsIgnoreCase(type)) return t;
        }
        return null;
    }
}
