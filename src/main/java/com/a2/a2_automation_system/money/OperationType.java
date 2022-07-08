package com.a2.a2_automation_system.money;


public enum OperationType {
    SPORTSMAN_PAYMENT("Оплата от ученика"),

    OTHER_OPERATIONS("Прочие операции");

    public final String rusValue;

    OperationType(String rusValue) {
        this.rusValue = rusValue;
    }

    public String getType(){
        return this.rusValue;
    }

    public static OperationType getOperationTypeByRusValue(String rusValue){
        for (OperationType t: OperationType.values()) {
            if (t.getType().equalsIgnoreCase(rusValue)) return t;
        }
        return null;
    }
}
