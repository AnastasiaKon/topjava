package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "MealServlet")
public class MealServlet extends HttpServlet {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Logger log = getLogger(UserServlet.class);
    private final int limitCaloriesPerDay = 2000;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("get all meals");

        List<MealTo> meals = MealsUtil.filteredByStreams(MealsUtil.getMeals(), LocalTime.MIN, LocalTime.MAX, limitCaloriesPerDay);
        request.setAttribute("userMeals", meals);

        request.setAttribute("dateTimeFormatter", dateTimeFormatter);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("meals.jsp");
        requestDispatcher.forward(request, response);
    }
}
