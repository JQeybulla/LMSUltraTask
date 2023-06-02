package web;
import domain.*;
import com.google.gson.Gson;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import services.UserService;
import services.UserServiceImpl;
import utils.DBFunction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

@WebServlet("/archive")
public class ArchiveServlet extends HttpServlet {
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

        String distinctAction = null;

        if (request.getParameter("distinctAction") != null){
            distinctAction = request.getParameter("distinctAction");
        }

        String sql = null;

        int teacher_id = 0;
        if (request.getParameter("teacher_id") != null){
            teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
        }

        UsersInfo user = (UsersInfo) request.getSession().getAttribute(Constant.USER);
        long user_id = user.getId();

        System.out.println("teacher_id: "+ teacher_id);

        if (distinctAction != null){
            if (distinctAction.equals("supervisor")){
                sql = "{ ? = call MYPROJECT.ARCIVE_EXAMS_SUPERVISOR_COMBO() }";
            }
        }else{
            sql = "{ ? = call MYPROJECT.ARCIVE_EXAMS(?,?) }";
        }

        try {
            if (conn != null){
                call = conn.prepareCall(sql);

                // Registering the output parameter as REF_CURSOR
                call.registerOutParameter(1, OracleTypes.CURSOR);

                if (distinctAction == null){
                    call.setInt(2, teacher_id);
                    call.setLong(3, user_id);
                }


                // Executing the function call
                call.execute();

                rs = (ResultSet) call.getObject(1);

                if (rs != null){
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    List<Row> params = new ArrayList<Row>();

                    while (rs.next()) {
                        Row gp = new Row();
                        List<Column> columnList = new ArrayList<>();
                        for(int i = 1; i <= columnCount; i++){
                            Column column = new Column();
                            column.setKey(rsmd.getColumnName(i));
                            column.setValue(rs.getString(rsmd.getColumnName(i)));

                            columnList.add(column);
                        }
                        gp.setColumns(columnList);
                        params.add(gp);
                    }

                    JSONObject content = new JSONObject();
                    JSONArray rows = new JSONArray();
                    for (Row row : params){
                        JSONObject r = new JSONObject();
                        for (Column column : row.getColumns()){
                            r.put(column.getKey().toLowerCase(), column.getValue());
                        }
                        rows.put(r);
                    }
                    content.put("options", rows);
                    System.out.println(content);
                    request.setAttribute("subjects", content);

                    // Set the content type of the response to JSON
                    response.setContentType("application/json");

                    // Write the JSON data to the response
                    PrintWriter out = response.getWriter();
                    out.print(content);
                    out.flush();
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
