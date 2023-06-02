package web;

import domain.Column;
import domain.Row;
import domain.UsersInfo;
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

@WebServlet("/get-exam-questions")
public class GetExamQuestionsServlet extends HttpServlet {
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
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        UserService userService = new UserServiceImpl();
        String ACTION = "action";

        Connection conn = df.connectDB();
        CallableStatement call = null;
        ResultSet rs = null;

        int exam_id = Integer.parseInt(request.getParameter("exam_id"));

        System.out.println("EXAM_ID: " + exam_id);

        String sql = "{ ? = call MYPROJECT.GET_EXAM_QUESTIONS(?) }";

        try {
            if (conn != null){
                call = conn.prepareCall(sql);

                // Registering the output parameter as REF_CURSOR
                call.registerOutParameter(1, OracleTypes.CURSOR);

                call.setInt(2, exam_id);

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
                    System.out.println("ROWS: " + rows);
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
