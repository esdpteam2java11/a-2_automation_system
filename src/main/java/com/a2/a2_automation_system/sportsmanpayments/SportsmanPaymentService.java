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

    public List<SportsmanPaymentForPeriodDTO> getSportsmanPayments(YearMonth startDate, YearMonth endDate) {

        LocalDate startLocalDate = startDate.atDay(1);
        LocalDate endLocalDate = endDate.atEndOfMonth();
        Date start = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<SportsmanPaymentForPeriodDTO> sportsmanPaymentForPeriodDTOS = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            List<User> sportsmen = userRepository.findByGroup(group.getId());
            for (User user : sportsmen) {
                YearMonth nextYearMonth = startDate;
                int monthsQuantity = 0;
                Double sportsmanPrice = 0.0;
                Double accruedTotal = 0.0;
                Double paidTotal = sportsmanPaymentRepository.findTotalPaidAmountForPeriod(user.getId(), start, end);

                Optional<SportsmanPayment> startSportsmanPayment =
                        sportsmanPaymentRepository.findUpToYearMonthAccruedAmount(user.getId(),
                                startDate.getYear(), startDate.getMonthValue());
                if (startSportsmanPayment.isPresent()) {
                    sportsmanPrice = startSportsmanPayment.get().getAmount();
                    accruedTotal += sportsmanPrice;
                    monthsQuantity = endDate.compareTo(startDate) + 1;
                }
                nextYearMonth = nextYearMonth.plusMonths(1L);

                while ((endDate.compareTo(nextYearMonth) + 1) != 0) {
                    Optional<SportsmanPayment> sportsmanPayment =
                            sportsmanPaymentRepository.findAccruedAmountForPeriod(user.getId(),
                                    nextYearMonth.getYear(), nextYearMonth.getMonthValue());
                    if (sportsmanPayment.isPresent()) {
                        if (monthsQuantity == 0) monthsQuantity = endDate.compareTo(nextYearMonth) + 1;
                        sportsmanPrice = sportsmanPayment.get().getAmount();
                    }
                    accruedTotal += sportsmanPrice;
                    nextYearMonth = nextYearMonth.plusMonths(1L);
                }

                sportsmanPaymentForPeriodDTOS.add(SportsmanPaymentForPeriodDTO.from(user, group, monthsQuantity,
                        accruedTotal, paidTotal));
            }
        }

        return sportsmanPaymentForPeriodDTOS;
    }
}
