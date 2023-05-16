package web;
import domain.ArchiveInfo;
import domain.ExamInfo;
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
        PrintWriter out = response.getWriter();
        Connection conn = df.connectDB();
        CallableStatement call = null;
        ResultSet rs = null;

        String sql = "{ ? = call MYPROJECT.ARCIVE_EXAMS() }";

        try {
            if (conn != null){
                call = conn.prepareCall(sql);

                // Registering the output parameter as REF_CURSOR
                call.registerOutParameter(1, OracleTypes.CURSOR);

                // Executing the function call
                call.execute();

                rs = (ResultSet) call.getObject(1);

                List<ArchiveInfo> archives = new ArrayList<>();
                while (rs.next()) {
                    String supervisorName = rs.getString(1);
                    String examDate = rs.getString(2);
                    String subject = rs.getString(3);
                    int score = rs.getInt(4);

                    ArchiveInfo archiveInfo = new ArchiveInfo();

                    archiveInfo.setSupervisorName(supervisorName);
                    archiveInfo.setExamDate(examDate);
                    archiveInfo.setSubjectName(subject);
                    archiveInfo.setScore(score);

                    archives.add(archiveInfo);
                }

                response.setContentType("application/json");

                Gson gson = new Gson();
                String json = gson.toJson(archives);
                out.println(json);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/student.jsp");
//                dispatcher.forward(request, response);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
