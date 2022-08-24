package com.a2.a2_automation_system.sportsmanpayments;

import com.a2.a2_automation_system.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SportsmanPaymentRepository extends PagingAndSortingRepository<SportsmanPayment, Long> {

    @Query(value = "select * from sportsman_payments sp where sp.user_id = ?1 " +
            "and sp.operation_type = ?2 order by sp.date desc limit 1", nativeQuery = true)
    Optional<SportsmanPayment> findUpToDateAmount(Long user, String type);

    @Query(value = "select * from sportsman_payments sp " +
            "where sp.id in (select max(id) from sportsman_payments " +
            "where operation_type = 'ACCRUED'and user_id = ?1 " +
            "group by date_part('year', date), date_part('month', date)) " +
            "and sp.date < ?2 " +
            "order by sp.date", nativeQuery = true)
    List<SportsmanPayment> findAccruedAmountsBeforeDate(Long user, Date date);

    @Query(value = "select * from sportsman_payments sp where sp.user_id = ?1 " +
            "and sp.operation_type = 'ACCRUED' and date_part('year',sp.date) <= ?2 " +
            "and date_part('month',sp.date) <= ?3 order by sp.date desc limit 1", nativeQuery = true)
    Optional<SportsmanPayment> findUpToYearMonthAccruedAmount(Long user, Integer year, Integer month);

    @Query(value = "select * from sportsman_payments sp where sp.user_id = ?1 " +
            "and sp.operation_type = 'ACCRUED' and date_part('year',sp.date) = ?2 " +
            "and date_part('month',sp.date) = ?3 order by sp.date desc limit 1", nativeQuery = true)
    Optional<SportsmanPayment> findAccruedAmountForPeriod(Long user, Integer year, Integer month);

    @Query(value = "select coalesce(sum(sp.amount), 0) from sportsman_payments sp where sp.user_id = ?1 " +
            "and sp.operation_type = 'PAID' and date_part('year',sp.date) = ?2 " +
            "and date_part('month',sp.date) = ?3", nativeQuery = true)
    Double findTotalPaidAmountForMonth(Long user, Integer year, Integer month);

    @Query(value = "select coalesce(sum(sp.amount), 0) from SportsmanPayment sp where sp.user.id = ?1 " +
            "and sp.operationType = 'PAID' and sp.date >= ?2 and sp.date <= ?3")
    Double findTotalPaidAmountForPeriod(Long user, Date startDate, Date endDate);

    @Query(value = "select coalesce(sum(sp.amount), 0) from SportsmanPayment sp where sp.user.id = ?1 " +
            "and sp.operationType = 'PAID' and sp.date < ?2")
    Double findTotalPaidAmountToDate(Long user, Date date);

    List<SportsmanPayment> findByUserId(Long id);

    Boolean existsByUserAndAmountAndDateAndOperationType(@NotNull User user,
                                                         @NotNull Double amount,
                                                         @NotNull Date date,
                                                         @NotNull OperationType operationType);

    List<SportsmanPayment> findAllByMoneyMovementId(Long id);

    void deleteAllByMoneyMovementId(Long id);
}
