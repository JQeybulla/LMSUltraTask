package web;

import domain.UsersInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherPageFilter implements Filter {

    private Log logger = LogFactory.getLog(getClass());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter.super.init(filterConfig);
                //super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the user is logged in
        if (httpRequest.getSession().getAttribute("user") == null) {
            // User is not logged in, redirect to the login page or show access denied message
            httpRequest.getSession().setAttribute(Constant.MESSAGE, "Sistemə giriş edin və ya qeydiyyatdan keçin");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/"+ Constant.PAGE_LOGIN);
        } else {
            UsersInfo usersInfo = (UsersInfo) httpRequest.getSession().getAttribute("user");
            if (usersInfo.getIs_teacher() == 1){
                // User is logged in and is a teacher, allow the request to proceed
                filterChain.doFilter(request, response);
            }else {
                httpRequest.getSession().setAttribute(Constant.MESSAGE, "Muellim deyilsiniz");
//                httpResponse.sendRedirect(httpRequest.getContextPath() + "/"+ Constant.PAGE_LOGIN);
            }
        }
    }

    @Override
    public void destroy() {
        //Filter.super.destroy();
    }
}
