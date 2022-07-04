package com.a2.a2_automation_system.tariff;

import com.a2.a2_automation_system.money.TypeOfFinance;

public enum OperationType {
    ACCRUED("Начислено "),

    PAID("Оплачено");

    public final String type;
    private OperationType(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static OperationType getTypeOfOperationType(String type){
        for (OperationType t:OperationType.values()) {
            if (t.getType().equalsIgnoreCase(type)) return t;
        }
        return null;
    }
}
