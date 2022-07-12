package com.a2.a2_automation_system.money;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MoneyMovementRepository extends JpaRepository<MoneyMovement, Long> {

    @Query(value = "select * from moneys m where " +
            "(?1 is null or m.counterparty_id = ?1) and\n" +
            "(?2 is null or trim(?2)='' or m.type_of_finance = ?2) and\n" +
            "(?3 is null or trim(?3)='' or m.operation_type = ?3) and\n" +
            "m.date>=?4 and m.date<=?5", nativeQuery = true)
    List<MoneyMovement> getMoneysByFilters(Long userId,
                                           String typeOfFinance,
                                           String operationType,
                                           LocalDate periodStart, LocalDate periodEnd);
}
