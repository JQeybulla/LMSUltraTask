package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Get the current session, but do not create a new one if it doesn't exist

        if (session != null) {
            session.invalidate(); // Invalidate the session, effectively logging out the user
        }

        // Redirect the user to a logged-out page or the login page
        response.sendRedirect(request.getContextPath() + "/" + Constant.PAGE_LOGIN);
    }
}
