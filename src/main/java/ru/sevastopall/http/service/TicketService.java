package ru.sevastopall.http.service;

import ru.sevastopall.http.dao.TicketDao;
import ru.sevastopall.http.dto.TicketDto;

import java.util.List;

import static java.util.stream.Collectors.*;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();
    private TicketService() {}

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlight_id(Long flight_id) {
        return ticketDao.findAllByFlightId(flight_id).stream()
                .map(ticket -> TicketDto.builder()
                        .id(ticket.getId())
                        .flightId(ticket.getFlightId())
                        .seatNo(ticket.getSeatNo())
                        .build()
                )
                .collect(toList());
    }
}
