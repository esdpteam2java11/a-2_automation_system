package com.a2.a2_automation_system.sportsmanpayments;

import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SportsmanPaymentService {
    private final SportsmanPaymentRepository sportsmanPaymentRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public List<SportsmanPaymentForPeriodDTO> getSportsmanPayments(YearMonth startYearMonth, YearMonth endYearMonth,
                                                                   Long groupId) {

        LocalDate startLocalDate = startYearMonth.atDay(1);
        LocalDate endLocalDate = endYearMonth.atEndOfMonth();
        Date startDate = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<SportsmanPaymentForPeriodDTO> sportsmanPaymentForPeriodDTOS = new ArrayList<>();
        Group group = groupRepository.findById(groupId).orElseThrow();
        List<User> sportsmen = userRepository.findByGroup(group.getId());
        for (User user : sportsmen) {
            Double startBalance = getStartBalance(user.getId(), startDate, startYearMonth);
            YearMonth nextYearMonth = startYearMonth;
            int monthsQuantity = 0;
            Double sportsmanPrice = 0.0;
            Double accruedTotal = 0.0;
            Double paidTotal = sportsmanPaymentRepository.findTotalPaidAmountForPeriod(user.getId(), startDate, endDate);

            Optional<SportsmanPayment> startSportsmanPayment =
                    sportsmanPaymentRepository.findUpToYearMonthAccruedAmount(user.getId(),
                            startYearMonth.getYear(), startYearMonth.getMonthValue());
            if (startSportsmanPayment.isPresent()) {
                sportsmanPrice = startSportsmanPayment.get().getAmount();
                accruedTotal += sportsmanPrice;
                monthsQuantity = endYearMonth.compareTo(startYearMonth) + 1;
            }
            nextYearMonth = nextYearMonth.plusMonths(1L);

            while ((endYearMonth.compareTo(nextYearMonth) + 1) != 0) {
                Optional<SportsmanPayment> sportsmanPayment =
                        sportsmanPaymentRepository.findAccruedAmountForPeriod(user.getId(),
                                nextYearMonth.getYear(), nextYearMonth.getMonthValue());
                if (sportsmanPayment.isPresent()) {
                    if (monthsQuantity == 0) monthsQuantity = endYearMonth.compareTo(nextYearMonth) + 1;
                    sportsmanPrice = sportsmanPayment.get().getAmount();
                }
                accruedTotal += sportsmanPrice;
                nextYearMonth = nextYearMonth.plusMonths(1L);
            }

            sportsmanPaymentForPeriodDTOS.add(SportsmanPaymentForPeriodDTO.from(user, group, monthsQuantity,
                    accruedTotal, paidTotal, startBalance));
        }

        Collections.sort(sportsmanPaymentForPeriodDTOS);
        return sportsmanPaymentForPeriodDTOS;
    }

    public List<SportsmanPaymentForMonthDTO> getMonthlySportsmanPayments(YearMonth startYearMonth,
                                                                         YearMonth endYearMonth, Long userId) {

        LocalDate startLocalDate = startYearMonth.atDay(1);
        Date startDate = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Double groupPrice = (double) userRepository.findById(userId).get().getGroup().getSum();
        Double startBalance = getStartBalance(userId, startDate, startYearMonth);

        YearMonth nextYearMonth = startYearMonth;
        Double sportsmanPriceInCurrentMonth = 0.0;
        Double paidTotalInCurrentMonth;


        List<SportsmanPaymentForMonthDTO> sportsmanPaymentForMonthDTOS = new ArrayList<>();

        Optional<SportsmanPayment> startSportsmanPayment =
                sportsmanPaymentRepository.findUpToYearMonthAccruedAmount(userId,
                        startYearMonth.getYear(), startYearMonth.getMonthValue());
        if (startSportsmanPayment.isPresent()) {
            sportsmanPriceInCurrentMonth = startSportsmanPayment.get().getAmount();
        }

        while ((endYearMonth.compareTo(nextYearMonth) + 1) != 0) {
            paidTotalInCurrentMonth = sportsmanPaymentRepository.findTotalPaidAmountForMonth(userId, nextYearMonth.getYear(),
                    nextYearMonth.getMonthValue());

            Optional<SportsmanPayment> sportsmanPayment =
                    sportsmanPaymentRepository.findAccruedAmountForPeriod(userId,
                            nextYearMonth.getYear(), nextYearMonth.getMonthValue());
            if (sportsmanPayment.isPresent()) {
                sportsmanPriceInCurrentMonth = sportsmanPayment.get().getAmount();
            }

            sportsmanPaymentForMonthDTOS.add(SportsmanPaymentForMonthDTO.from(userId, nextYearMonth, groupPrice,
                    sportsmanPriceInCurrentMonth, paidTotalInCurrentMonth, startBalance));

            startBalance += sportsmanPriceInCurrentMonth - paidTotalInCurrentMonth;
            nextYearMonth = nextYearMonth.plusMonths(1L);
        }

        Collections.sort(sportsmanPaymentForMonthDTOS);
        return sportsmanPaymentForMonthDTOS;
    }

    private Double getStartBalance(Long userId, Date startDate, YearMonth startYearMonth) {
        List<SportsmanPayment> sportsmanPayments = sportsmanPaymentRepository
                .findAccruedAmountsBeforeDate(userId, startDate);

        double payments = sportsmanPaymentRepository.findTotalPaidAmountToDate(userId, startDate);
        double startBalance = 0 - payments;

        if (sportsmanPayments.size() > 0) {
            YearMonth billingYearMonth = YearMonth.from(sportsmanPayments.get(0).getDate().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate());
            double price = sportsmanPayments.get(0).getAmount();

            for (SportsmanPayment sportsmanPayment : sportsmanPayments) {
                YearMonth currentYearMonth = YearMonth.from(sportsmanPayment.getDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate());
                while (currentYearMonth.compareTo(billingYearMonth) != 0) {
                    startBalance += price;
                    billingYearMonth = billingYearMonth.plusMonths(1L);
                }
                price = sportsmanPayment.getAmount();
            }
            while (startYearMonth.compareTo(billingYearMonth) != 0) {
                startBalance += price;
                billingYearMonth = billingYearMonth.plusMonths(1L);
            }
        }

        return startBalance;
    }
}
