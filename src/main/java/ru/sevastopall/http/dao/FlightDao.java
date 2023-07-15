package ru.sevastopall.http.dao;


import ru.sevastopall.http.entity.Flight;
import ru.sevastopall.http.entity.FlightStatus;
import ru.sevastopall.http.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private static final FlightDao INSTANCE = new FlightDao();

    private static final String FIND_ALL = """
            SELECT id, flight_no, departure_date, 
            departure_airport_code, arrival_date,
            arrival_airport_code, aircraft_id, status
            FROM flight
            """;

    private FlightDao() {}

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();
        try (var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(FIND_ALL)) {
        var resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            flights.add(buildFlight(resultSet));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return flights;
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {

        return new Flight(
        resultSet.getObject("id", Long.class),
        resultSet.getObject("flight_no", String.class),
        resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime(),
        resultSet.getObject("departure_airport_code", String.class),
        resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
        resultSet.getObject("arrival_airport_code", String.class),
        resultSet.getObject("aircraft_id", Integer.class),
        FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight flight) {

    }

    @Override
    public Flight save(Flight flight) {
        return null;
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
