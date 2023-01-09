package by.itstep.stpnbelko.homework.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;

@WebFilter("/*")
public class LogFilter extends HttpFilter {

    private static final String PRINT_PATTERN = "%s : %s [%s] {%s} %s %s %s";

    @Override
    public void init() throws ServletException {
        System.out.println("Init LogFilter");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy LogFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String servletPath = httpReq.getServletPath();
        String uri = httpReq.getRequestURI();
        String url = httpReq.getRequestURL().toString();
        String sessionId = httpReq.getSession().getId();

        System.out.println(String.format(PRINT_PATTERN, new Date().toString(), "INFO", Thread.currentThread().getName(), sessionId, servletPath, uri, url));

        chain.doFilter(req, res);

    }
}
