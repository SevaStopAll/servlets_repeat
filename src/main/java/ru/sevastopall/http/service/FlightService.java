package ru.sevastopall.http.service;

import ru.sevastopall.http.dao.FlightDao;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {

        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(
                    flight.getId(),
                        """
                            %s - %s - %s
                        """.formatted(flight.getDeparture_airport_code(),
                                flight.getArrival_airport_code(),
                                flight.getStatus())
                ))
                .collect(toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
