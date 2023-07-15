package ru.sevastopall.http.service;

import ru.sevastopall.http.dao.FlightDao;
import ru.sevastopall.http.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {

        return flightDao.findAll().stream()
                .map(flight -> FlightDto.builder()
                                        .id(flight.getId())
                        .description(
                        """
                            %s - %s - %s
                        """.formatted(flight.getDeparture_airport_code(),
                                flight.getArrival_airport_code(),
                                flight.getStatus())
                ).build())
                .collect(toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
