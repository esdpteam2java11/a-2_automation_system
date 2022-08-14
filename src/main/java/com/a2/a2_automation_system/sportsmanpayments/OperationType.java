package com.a2.a2_automation_system.sportsmanpayments;

public enum OperationType {
    ACCRUED("Начислено "),
    PAID("Оплачено");

    public final String type;

    OperationType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static OperationType getTypeOfOperationType(String type) {
        for (OperationType t : OperationType.values()) {
            if (t.getType().equalsIgnoreCase(type)) return t;
        }
        return null;
    }
}
