package web;

import domain.UsersInfo;
import utils.DBFunction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    protected DBFunction df = new DBFunction();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data from request parameters
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        // Call the PL/SQL function to register the user
        int registrationSuccessful = callRegistrationFunction(name, surname, username, password, password2, request);
        System.out.println("This is registrationSuccessful: " + registrationSuccessful);
        if (registrationSuccessful != 0) {
            UsersInfo usersInfo = new UsersInfo();

            usersInfo.setId((long) registrationSuccessful);
            usersInfo.setUsername(username);
            usersInfo.setPassword(password);
            usersInfo.setFullname(name + " " + surname);

            request.getSession().setAttribute(Constant.USER, usersInfo);
            // Registration successful, redirect to a success page
            response.sendRedirect(request.getContextPath() + "/" + Constant.PAGE_MAIN_STUDENT);
        } else {
            // Registration failed, redirect to an error page
            response.sendRedirect(request.getContextPath() + "/registration.jsp");
        }
    }

    private int callRegistrationFunction(String name, String surname, String username, String password, String password2, HttpServletRequest request) {
        if (!password.equals(password2)){
            request.getSession().setAttribute(Constant.MESSAGE, "Shifreler us-uste dushmur");
            return 0;
        }
        try {
            // Establish a database connection
            Connection conn = df.connectDB();

            // Prepare the PL/SQL function call
            CallableStatement call = conn.prepareCall("{ ? = call MYPROJECT.register_user(?, ?, ?, ?) }");
            call.registerOutParameter(1, java.sql.Types.INTEGER);

            call.setString(2, name);
            call.setString(3, surname);
            call.setString(4, username);
            call.setString(5, password);

            // Execute the PL/SQL function
            call.execute();

            // Retrieve the result from the PL/SQL function
            int result = call.getInt(1);
            System.out.println("This is result:"  + result);

            // Close the statement and connection
            call.close();
            conn.close();

            if (result == 0){
                request.getSession().setAttribute(Constant.MESSAGE, "Bu adda istifadeci qeydiyyatdadir!");
            }

            return (result); // Assuming the PL/SQL function returns 1 on successful registration

        } catch (Exception e) {
            // Handle any database errors
            e.printStackTrace();
            return 0;
        }
    }
}

