package com.a2.a2_automation_system.money;
import com.a2.a2_automation_system.news.*;
import com.a2.a2_automation_system.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {News.class})
@ExtendWith(SpringExtension.class)
public class MoneyMovementsServiceTest {
    @InjectMocks
    private MoneyMovementsService movementsService;

    @Mock
    private MoneyMovementRepository movementRepository;



    private MoneyMovement movement;

    @BeforeEach
    void setupBeforeEach() {
        movement = MoneyMovement.builder()
                .id(1L)
                .cashier(new User())
                .typeOfFinance(TypeOfFinance.getTypeOfFinanceByRusValue(""))
                .purpose("")
                .date(LocalDate.now())
                .moneyOperationType(MoneyOperationType.getOperationTypeByRusValue(""))
                .amount(100.0)
                .counterparty(new User())
                .build();

    }



    @Test
    void addMoneyMovement() {
        when(movementRepository.save(ArgumentMatchers.any(MoneyMovement.class))).thenReturn(movement);

    }

}
