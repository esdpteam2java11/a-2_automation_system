package com.a2.a2_automation_system.tariff;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportsmanPaymentRepository extends PagingAndSortingRepository<SportsmanPayment, Long> {

    @Query(value = "select * from sportsman_payments sp where sp.user_id = ?1 " +
            "and sp.operation_type = ?2 order by sp.date desc limit 1", nativeQuery = true)
    Optional<SportsmanPayment> findUpToDateAmount(Long user, String type);
}
