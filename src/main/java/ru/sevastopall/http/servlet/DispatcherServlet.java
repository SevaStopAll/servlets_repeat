package ru.sevastopall.http.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/flights");

        /*        req.getRequestDispatcher("/flights")
                *//*.forward(req, resp);*//*
                .include(req, resp);
            PrintWriter writer = resp.getWriter();
        writer.write("Hello 2");*/


     /*   req.setAttribute("1", "234");*/
        /*requestDispatcher.forward(req, resp);*/
    }
}
