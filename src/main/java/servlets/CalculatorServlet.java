package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("op");
        double n1 = Double.parseDouble(req.getParameter("n1"));
        double n2 = Double.parseDouble(req.getParameter("n2"));
        double result = 0;
        String sign = null;
        switch (operation) {
            case "add":
                sign = "+";
                result = n1 + n2;
                break;
            case "sub":
                sign = "-";
                result = n1 - n2;
                break;
            case "multiply":
                sign = "*";
                result = n1 * n2;
                break;
            case "div":
                if (n2 != 0) {
                    sign = "/";
                    result = n1 / n2;
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Division by zero is not allowed");
                    return;
                }
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid operation");
                return;
        }
        resp.getWriter().println(n1 + sign + n2 + "=" + result);
    }
}

