package web;
import domain.ExamInfo;
import domain.FutureExamInfo;
import domain.UsersInfo;
import com.google.gson.Gson;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

@WebServlet("/exams")
public class ExamsServlet extends HttpServlet {
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

        String sql = "{ ? = call MYPROJECT.FUTURE_EXAMS() }";

        try {
            if (conn != null){
                call = conn.prepareCall(sql);

                // Registering the output parameter as REF_CURSOR
                call.registerOutParameter(1, OracleTypes.CURSOR);

                // Executing the function call
                call.execute();

                rs = (ResultSet) call.getObject(1);

                List<FutureExamInfo> exams = new ArrayList<>();

                while (rs.next()) {
                    String subjectName = rs.getString(1);
                    String examDate = rs.getString(2);

                    FutureExamInfo examInfo = new FutureExamInfo();
                    examInfo.setSubjectName(subjectName);
                    examInfo.setExamDate(examDate);

                    exams.add(examInfo);
                }
                Gson gson = new Gson();
                String examsJson = gson.toJson(exams);
                request.setAttribute("exams", exams);

                // Set the content type of the response to JSON
                response.setContentType("application/json");

                // Write the JSON data to the response
                PrintWriter out = response.getWriter();
                out.print(examsJson);
                out.flush();

//                RequestDispatcher dispatcher = request.getRequestDispatcher("/student.jsp");
//                dispatcher.forward(request, response);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
