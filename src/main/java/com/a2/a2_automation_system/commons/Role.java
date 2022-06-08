package com.a2.a2_automation_system.commons;

public enum Role {
    ADMIN("Администратор"),
    EMPLOYEE("Сотрудник"),
    CLIENT("Клиент");
    public final String role;
   private Role(String role) {
        this.role = role;
    }
}
