package ru.sevastopall.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sevastopall.http.dto.FlightDto;
import ru.sevastopall.http.service.FlightService;
import ru.sevastopall.http.util.JSPHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flightDtos = flightService.findAll();
        req.setAttribute("flights", flightDtos);
        req.getSession().setAttribute("flightsMap", flightDtos.stream().collect(toMap(FlightDto::getId, FlightDto::getDescription)));
        req.getRequestDispatcher(JSPHelper.getPath("content"))
                .forward(req, resp);
    }
}
