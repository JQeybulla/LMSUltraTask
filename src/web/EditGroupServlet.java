package web;

import domain.Column;
import domain.Row;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import services.UserService;
import services.UserServiceImpl;
import utils.DBFunction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/edit-group")
public class EditGroupServlet extends HttpServlet {
    Log logger = LogFactory.getLog(getClass());
    protected DBFunction df = new DBFunction();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String address = Constant.PAGE_MAIN_STUDENT;
        boolean forward = false;
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        UserService userService = new UserServiceImpl();
        String ACTION = "action";
        Connection conn = df.connectDB();
        CallableStatement call = null;
        ResultSet rs = null;

        String sql = null;

        int group_id = -1;
        if (request.getParameter("id") != null){
            group_id = Integer.parseInt((request.getParameter("id")));
        }

        String name = "";
        if (request.getParameter("name") != null){
            name = (request.getParameter("name"));
        }

        int faculty_id = -1;
        if (request.getParameter("faculty_id") != null){
            faculty_id = Integer.parseInt((request.getParameter("faculty_id")));
        }


        System.out.println("group_id: "+ group_id);
        System.out.println("faculty_id: "+ faculty_id);

        sql = "{ ? = call MYPROJECT.EDIT_GROUP (?,?,?) }";

        try {
            if (conn != null){
                call = conn.prepareCall(sql);

                // Registering the output parameter as REF_CURSOR
                call.registerOutParameter(1, OracleTypes.INTEGER);
                call.setInt(2, group_id);
                call.setString(3, name);
                call.setInt(4, faculty_id);

                call.execute();

                int result = call.getInt(1);
                System.out.println("RESULT: " + result);

                String content = "{\"result\":" + request + "}";


                System.out.println(content);
                // Set the content type of the response to JSON
                response.setContentType("application/json");

                // Write the JSON data to the response
                PrintWriter out = response.getWriter();
                out.print(content);
                out.flush();
//                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
