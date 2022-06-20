package com.a2.a2_automation_system.parent;


public enum Kinship {
    FATHER("Отец"),
    MOTHER("Мать"),
    GRANDMOTHER("Бабушка"),
    GRANDFATHER("Дедушка"),
    SISTER("Сестра"),
    BROTHER("Брат"),
    GUARDIAN("Опекун");

    public final String kinship;

    private Kinship(String kinship) {
        this.kinship = kinship;
    }

    public String getKinship(){
        return this.kinship;
    }

    public static Kinship getKinshipByKinshipName(String name){
        for (Kinship k:Kinship.values()) {
            if (k.getKinship().equalsIgnoreCase(name)) return k;
        }
        return null;
    }
}
