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

@WebServlet("/delete-group")
public class DeleteGroupServlet extends HttpServlet {
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

        String group_id = "";
        if (request.getParameter("group_id") != null){
            group_id = (request.getParameter("group_id"));
        }

        // getting a string add numbers to list
        // Remove trailing comma if present
        if (group_id.endsWith(",")) {
            group_id = group_id.substring(0, group_id.length() - 1);
        }

        String[] numberStrings = group_id.split(",");
        List<Integer> groupIds = new ArrayList<>();

        for (String numberString : numberStrings) {
            try {
                int number = Integer.parseInt(numberString);
                groupIds.add(number);
            } catch (NumberFormatException e) {
                // Handle invalid number format
                System.out.println("Invalid number format: " + numberString);
            }
        }



        System.out.println("group_id: "+ group_id);


        sql = "{ ? = call MYPROJECT.DELETE_GROUP(?) }";

        try {
            if (conn != null){
                call = conn.prepareCall(sql);

                // Registering the output parameter as REF_CURSOR
                call.registerOutParameter(1, OracleTypes.INTEGER);

                List<Integer> resultList = new ArrayList<>();
                for (int number : groupIds) {
                    call.setInt(2, number);

                    // Executing the function call
                    call.execute();

                    int result = call.getInt(1);
                    System.out.println("RESULT: " + result);
                    resultList.add(result);
                }
                boolean allSuccessfull = true;
                for (int result:resultList){
                    if (result == 0){
                        allSuccessfull = false;
                    }
                }
                String content = "";
                if(allSuccessfull){
                    content = "{\"result\":" + 1 + "}";
                }else{
                    content = "{\"result\":" + 0 + "}";
                }

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
