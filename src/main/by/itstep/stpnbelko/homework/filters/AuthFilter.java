package by.itstep.stpnbelko.homework.filters;

import by.itstep.stpnbelko.homework.model.Role;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.ServletUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter extends HttpFilter {


    List<String> allowedPath = Arrays.asList("/jsp/index.jsp", "/welcome", "/login", "/registration");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Auth filter");

        HttpServletRequest httpReq = (HttpServletRequest) req;
        String path = httpReq.getServletPath();
        String context = httpReq.getContextPath();
        String url = String.valueOf(httpReq.getRequestURL());
        System.out.println("Servlet path : " + path);
        System.out.println("Parameter : " + httpReq.getParameter("action"));
        System.out.println("Allowed path : " + allowedPath.toString());
        System.out.println("Servlet context : " + context);
        System.out.println("URL : " + url);
        User user = ServletUtils.getSessionUser(httpReq);

        if (allowedPath.contains(path)) {
            chain.doFilter(req, res);
            return;
        } else {
            if (user == null) {
                req.setAttribute("msg", "User == null. Please login");
                RequestDispatcher rd = req.getRequestDispatcher("jsp/msg.jsp");
                rd.forward(req, res);
                return;
            }
        }


        if (user.getRole() == null || user.getRole().size() == 0) {
            req.setAttribute("msg", "User does not have any role. Please login");
            RequestDispatcher rd = req.getRequestDispatcher("jsp/msg.jsp");
            rd.forward(req, res);
        } else {
            for (Role role : user.getRole()) {
                for (String allowedPath : ServletUtils.getRolesMapping().get(role)) {
                    if (allowedPath.trim().equals(path)) {
                        chain.doFilter(req, res);
                        return;
                    }
                }
            }
        }

        req.setAttribute("msg", "You haven't suitable roles" );
        RequestDispatcher rd = req.getRequestDispatcher("jsp/msg.jsp");
        rd.forward(req, res);
    }
}
