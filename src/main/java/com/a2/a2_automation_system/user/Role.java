package com.a2.a2_automation_system.user;

public enum Role {
    ADMIN("Администратор"),
    EMPLOYEE("Сотрудник"),
    CLIENT("Клиент");
    public final String role;
   private Role(String role) {
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }

    public static Role getRoleByRoleName(String name){
        for (Role r:Role.values()) {
            if (r.getRole().equalsIgnoreCase(name)) return r;
        }
        return null;
    }
}
