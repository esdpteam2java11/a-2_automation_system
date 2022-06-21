package com.a2.a2_automation_system.schedule;


public enum DaysOfTheWeek {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");
    private final String value;

    DaysOfTheWeek(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DaysOfTheWeek getDaysOfTheWeek(String name){
        for (DaysOfTheWeek r:DaysOfTheWeek.values()) {
            if (r.getValue().equalsIgnoreCase(name)) return r;
        }
        return null;
    }
}
