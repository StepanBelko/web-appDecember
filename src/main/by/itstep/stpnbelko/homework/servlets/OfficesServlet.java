package by.itstep.stpnbelko.homework.servlets;

import by.itstep.stpnbelko.homework.dao.impl.OfficesDAO;
import by.itstep.stpnbelko.homework.model.Office;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

public class OfficesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Offices servlet doGET");
        Set<Office> offices = new OfficesDAO().getAll();
        req.setAttribute("offices", offices);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/offices.jsp");


        requestDispatcher.forward(req, resp);
    }
}
