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
                UsersInfo user = userService.getUser(0L, userName, password);
                System.out.println("Userin username-i: " + user.getUsername());
                System.out.println("Userin ID-si: " + user.getId());
                if (user.getId() != 0) {
                    session = request.getSession(true);
                    session.setAttribute("sesID", session.getId());
                    session.setAttribute(Constant.USER, user);
                    session.removeAttribute(Constant.MESSAGE);
//                    session.setAttribute(Constant.USER_ALLOW, 1);

  //                  String logDate = format2.format(new Date());
//                    user.setLogDate(logDate);
//                    user.setLoginDate(new Date());
//                    user.setUserRemote(ipAddress);
//                    user.setTomcatIp(tomcatIp);
//                    user.setLogHistoryStatus("login");
//                    user.setUserPassword(password);


                   /* if (user.getUserGroupID() == 1029) {
                        session.setAttribute("checkOrgId", user.getRandomOrgCode());
                    } else {*/
                    //session.setAttribute("checkOrgId", "0");
                    //  }

                    address = Constant.PAGE_MAIN_STUDENT;
//                    address = "login.jsp";
                    forward = false;

                } else {
                    System.out.println("Parol sehv");
                    address = Constant.PAGE_LOGIN;
                    request.getSession().setAttribute(Constant.MESSAGE, "İstifadəçi adı və ya şifrə səhvdir");
                    //  request.getSession().setAttribute(Constant.MESSAGE, "Sistemə giriş müvəqqəti olaraq bloklanmışdır!");
                    forward = true;
                }
            }else {
                System.out.println("Action is NOT Login Hehehe");
            }
//            else if (ACTION.equalsIgnoreCase(Constant.LOGOUT)) {
//                UsersInfo user = (UsersInfo) session.getAttribute(Constant.USER);
//                logger.info("Session is destroyed - " + session.getId() + " - " + user.getUserName() + " - " + user.getFullName());
//                String logDate = format2.format(new Date());
//                user.setLogDate(logDate);
//                user.setLogHistoryStatus("logout");
//                long logHistoryId = userService.userLogHistoryOperation(user);
//                try {
//                    session.removeAttribute(Constant.USER);
//                    session.invalidate();
//                    session.setMaxInactiveInterval(1);
//                    address = "login.jsp";
//                    forward = false;
//                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//                    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//                    response.setDateHeader("Expires", 0); // Proxies.
//                    response.addHeader("session_timeout", "1");
//                } catch (Exception e) {
//                    logger.info("exception at  logout");
//                }

           // }
        }
        catch (Exception ex) {
//            PrintWriter out = response.getWriter();
//            out.println("Hello world");
//            System.out.println("Hellloo");

            logger.error(" Error in loginservlete action login" + ex.toString());
            System.out.println("This happened");
            forward = true;
            address = Constant.PAGE_LOGIN;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        if (forward) {
            dispatcher.forward(request, response);
        } else {
//            logger.info("main page redirect login success!!! = " + address);
            response.sendRedirect(response.encodeRedirectURL(address));
        }
    }

}