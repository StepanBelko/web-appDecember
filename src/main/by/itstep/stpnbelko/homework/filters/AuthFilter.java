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
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static by.itstep.stpnbelko.homework.util.ServletUtils.USER;
import static by.itstep.stpnbelko.homework.util.ServletUtils.getRolesMapping;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter extends HttpFilter {


    List<String> allowedPath = Arrays.asList("/login");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Auth filter");

        HttpServletRequest httpReq = (HttpServletRequest) req;
        String path = httpReq.getServletPath();

        if(allowedPath.contains(path)){
            chain.doFilter(req, res);
            return;
        } else {
            User user = ServletUtils.getSessionUser(httpReq);
            if (user == null) {
                req.setAttribute("msg", "Please login");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/msg.jsp");
                requestDispatcher.forward(httpReq, (HttpServletResponse) res);
                return;
            }
        }


        Map<Role, List<String>> map = ServletUtils.getRolesMapping();
        System.out.println("Auth filter map size : " + map.size());
        User user = (User) httpReq.getAttribute("user");
        for (Role role : user.getRole()) {
            List<String> roles = map.get(role);
        }





        System.out.println("PATH!!!!!!!!! " + path);

        chain.doFilter(req, res);


    }
}
