package web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

public class UnicodeFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Log logger = LogFactory.getLog(getClass());
        Throwable problem = null;
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            chain.doFilter(req, resp);
        } catch (Exception t) {
            problem = t;
            logger.info("error unicode " + t.toString());
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }
}