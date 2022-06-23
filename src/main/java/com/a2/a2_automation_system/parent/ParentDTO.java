package com.a2.a2_automation_system.parent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentDTO {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String kinship;
    private String whatsapp;
    private String telegram;

    public static ParentDTO from(Parent parent) {
        return builder()
                .id(parent.getId())
                .name(parent.getName())
                .surname(parent.getSurname())
                .patronymic(parent.getPatronymic())
                .phone(parent.getPhone())
                .kinship(parent.getKinship().getKinship())
                .whatsapp(parent.getWhatsapp())
                .telegram(parent.getTelegram())
                .build();
    }
}
