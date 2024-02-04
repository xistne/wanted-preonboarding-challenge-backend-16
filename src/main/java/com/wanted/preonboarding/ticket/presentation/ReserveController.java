package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.ticket.application.ReserveService;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;

    @PostMapping("")
    public ResponseEntity<ReserveInfoResponse> reservation(@RequestBody ReserveInfo reserveInfo) {
        System.out.println("reservation");
        log.info("reserveInfo : " + reserveInfo.toString());
        ReserveInfoResponse savedReserve = reserveService.reserve(reserveInfo);
        return ResponseEntity.status(HttpStatus.OK).body(savedReserve);
//        return ticketSeller.reserve(ReserveInfo.builder()
//            .performanceId(UUID.fromString("4438a3e6-b01c-11ee-9426-0242ac180002"))
//            .reservationName("유진호")
//            .reservationPhoneNumber("010-1234-1234")
//            .reservationStatus("reserve")
//            .amount(200000)
//            .round(1)
//            .line('A')
//            .seat(1)
//            .build()
//        );
    }
}
