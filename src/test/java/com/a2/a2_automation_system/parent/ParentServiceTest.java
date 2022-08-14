package com.a2.a2_automation_system.parent;

import com.a2.a2_automation_system.relationship.RelationshipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest(classes = {Parent.class})
@ExtendWith(SpringExtension.class)
class ParentServiceTest {

    @InjectMocks
    private ParentService parentService;

    @Mock
    private ParentRepository parentRepository;

    private ParentDTO parent;

    @Mock
    private RelationshipRepository relationshipRepository;

    @BeforeEach
    void setupBeforeEach() {
        parent = ParentDTO.builder()
                .id(1L)
                .name("parent")
                .surname("parent_surname")
                .patronymic("patronymic")
                .phone("9965553005555")
                .kinship("Brother")
                .whatsapp("99654448883")
                .telegram("99654448883")
                .build();
    }

    @Test
    void getParentsBySurname() {
        List<ParentDTO> parentsBySurname = parentService.getParentsBySurname(parent.getSurname());
        parentsBySurname.add(parent);
        Assertions.assertThat(parentsBySurname.get(0).getSurname()).isEqualTo("parent_surname");
    }

    @Test
    void getParentsBySportsmanId() {
        List<ParentDTO> parentsBySportsmanId = parentService.getParentsBySportsmanId(1L);
        parentsBySportsmanId.add(parent);
        Assertions.assertThat(parentsBySportsmanId.get(0).getId()).isEqualTo(1L);
    }
}