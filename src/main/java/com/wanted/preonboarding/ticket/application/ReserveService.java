package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfoResponse;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ReserveService {
    ReserveInfoResponse reserve(ReserveInfo reserveInfo);
}
