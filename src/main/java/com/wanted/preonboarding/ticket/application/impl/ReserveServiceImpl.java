package com.wanted.preonboarding.ticket.application.impl;

import com.wanted.preonboarding.ticket.application.ReserveService;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfoResponse;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    public ReserveInfoResponse reserve(ReserveInfo reserveInfo) {
        log.info("reserveInfo ID => {}", reserveInfo.getPerformanceId());
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId())
                .orElseThrow(EntityNotFoundException::new);
        log.info("performanceInfo => {}", info.toString());
        String enableReserve = info.getIsReserve();
        if (enableReserve.equalsIgnoreCase("enable")) {
            // 1. 결제
            int price = info.getPrice();
            reserveInfo.setAmount(reserveInfo.getAmount() - price);
            // 2. 예매 진행
            Reservation savedreservation = reservationRepository.save(Reservation.of(reserveInfo));
            return ReserveInfoResponse.builder()
                    .round(savedreservation.getRound())
                    .performanceName(info.getName())
                    .line(savedreservation.getLine())
                    .seat(savedreservation.getSeat())
                    .performanceId(savedreservation.getPerformanceId())
                    .reservationName(savedreservation.getName())
                    .reservationPhoneNumber(savedreservation.getPhoneNumber())
                    .build();
        } else {
            throw new IllegalArgumentException("예약할 수 없는 공연입니다.");
        }
    }
}
