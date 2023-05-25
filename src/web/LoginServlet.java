package web;

import domain.UsersInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginServlet extends HttpServlet {
    Log logger = LogFactory.getLog(getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = Constant.PAGE_MAIN_STUDENT;
        boolean forward = false;
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        UserService userService = new UserServiceImpl();
        String ACTION = "action";


        if (request.getParameter("action") != null) {
            ACTION = request.getParameter("action");
        }else {
            System.out.println("Action is null");
        }
        System.out.println("Action = "+ ACTION);

        try {
            HttpSession session = request.getSession(true);
            if (ACTION.equals(Constant.ACTION_LOGIN)) {
                String realIP = request.getHeader("X-Real-IP");
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                String tomcatIp = request.getLocalAddr();


                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                if (!ipAddress.equals(realIP)) {
                    ipAddress = ipAddress + "----" + realIP;
                }

                System.out.println("IP ADDRESS" + ipAddress);

                String userName = request.getParameter("username");
                String password = request.getParameter("password");

                logger.info("Login Process -login IP=" + ipAddress + "- Username=" + userName + " session_id=" + session.getId() + " - login time=" + format2.format(new Date()));
                UsersInfo user = userService.getUser(0L, userName, password, 0);
                System.out.println("Userin username-i: " + user.getUsername());
                System.out.println("Userin ID-si: " + user.getId());
                if (user.getId() != 0) {
                    session = request.getSession(true);
                    session.setAttribute("sesID", session.getId());
                    session.setAttribute(Constant.USER, user);
                    session.removeAttribute(Constant.MESSAGE);

                    if (user.getIs_teacher() != 0){
                        address = "teacher.jsp";
                    }else{
                        address = Constant.PAGE_MAIN_STUDENT;
                        forward = false;
                    }

//                    address = "login.jsp";


                } else {
                    System.out.println("Parol sehv");
                    address = Constant.PAGE_LOGIN;
                    request.getSession().setAttribute(Constant.MESSAGE, "İstifadəçi adı və ya şifrə səhvdir");
                    forward = true;
                }
            }else {
                System.out.println("Action is NOT Login Hehehe");
            }

        }
        catch (Exception ex) {

            logger.error(" Error in loginservlete action login" + ex.toString());
            System.out.println("This happened");
            forward = true;
            address = Constant.PAGE_LOGIN;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        if (forward) {
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(response.encodeRedirectURL(address));
        }
    }

}