package web;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute(Constant.AUTHENTICATION_TOKEN);
        String authTkn = request.getParameter("authentificationToken");
        filterChain.doFilter(req, resp);
       /* if ( authTkn == null ){
        }
        else{
            if (!token.equals( authTkn )&& !authTkn.equals("192A719DC9CB2F9C992FE54BBE69D6B7") ){

            }
            else{
                filterChain.doFilter( req , resp);
            }
        }*/
    }

    @Override
    public void destroy() {

    }
}