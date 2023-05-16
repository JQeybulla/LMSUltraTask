package web;

import domain.UsersInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Log logger = LogFactory.getLog(getClass());
        String address = null;
        Boolean forward = true;
        UsersInfo userInfo = null;
        HttpSession session = request.getSession();

        try {
            userInfo = (UsersInfo) session.getAttribute(Constant.USER);

            if (userInfo == null) {
                response.setHeader(Constant.REQUIRES_AUTH, String.valueOf(1));
                address = Constant.PAGE_LOGIN;
                forward = true;
            } else {
                String ACTION = request.getParameter("action");
                switch (ACTION) {
                    case Constant.COMBO_CONTENT:
                        address = "/cs?action=" + Constant.COMBO_CONTENT;
                        break;
                    case Constant.GET_OPERATION_BUTTONS1:
                        address = "/cs?action=" + Constant.GET_OPERATION_BUTTONS1;
                        break;
                    case Constant.GET_OPERATION_BUTTONS2:
                        address = "/cs?action=" + Constant.GET_OPERATION_BUTTONS2;
                        break;
                    case Constant.GET_OPERATION_BUTTONS3:
                        address = "/cs?action=" + Constant.GET_OPERATION_BUTTONS3;
                        break;

                    case Constant.ACTION_GET_SECURITY_GRID:
                        address = "/cs?action=" + Constant.ACTION_GET_SECURITY_GRID;
                        break;

                    case Constant.ACTION_OPEN_DIALOG_FOR_USER_NEW:
                        address = "/MMIS/dialog/dialogsJSP/pUsers.jsp";
                        break;

                    case Constant.ACTION_OPEN_DIALOG_FOR_USER_EDIT:
                        address = "/MMIS/dialog/dialogsJSP/pUsers.jsp";
                        break;

                    case Constant.ACTION_OPEN_DIALOG_FOR_STAFF_NEW:
                        address = "/MMIS/dialog/dialogsJSP/pHeyet.jsp";
                        ;
                        break;

                    case Constant.ACTION_OPEN_DIALOG_FOR_SCHOOL_EDIT:
                        address = "/MMIS/dialog/dialogsJSP/schools.jsp";
                        break;

                    case Constant.ACTION_OPEN_DIALOG_FOR_STUDENT_EDIT:
                        address = "/MMIS/dialog/dialogsJSP/students.jsp";
                        break;

                    case Constant.ACTION_OPEN_DIALOG_FOR_STAFF_EDIT:
                        address = "/MMIS/dialog/dialogsJSP/pHeyet.jsp";
                        break;
                    case Constant.ACTION_SAVE_GROUP_HOUR:
                        address="/cs1?action="+Constant.ACTION_SAVE_GROUP_HOUR;
                        break;

                    case Constant.ACTION_OPEN_DIALOG_FOR_DELETE:
                        address = "/modals/delForm.jsp";
                        break;

                    case Constant.ACTION_LOAD_STRUKTUR:
                        address = "/cs?action" + Constant.ACTION_LOAD_STRUKTUR;
                        break;

                    case Constant.ACTION_CHECK_USER_ACCESS:
                        address = "/cs?action" + Constant.ACTION_CHECK_USER_ACCESS;
                        break;

                    case Constant.ACTION_LOAD_DICT_LIST:
                        address = "/cs?action" + Constant.ACTION_LOAD_DICT_LIST;
                        break;

                    case Constant.ACTION_LOAD_MAIN_GRID:
                        address = "/cs?action" + Constant.ACTION_LOAD_MAIN_GRID;
                        break;

                    case Constant.ACTION_GET_PERSON_IDENTITY_SEARCH:
                        address = "/cs?action" + Constant.ACTION_GET_PERSON_IDENTITY_SEARCH;
                        break;

                    case Constant.ACTION_GET_PERSON_IDENTITY_SEARCHUTIS:
                        address = "/cs?action" + Constant.ACTION_GET_PERSON_IDENTITY_SEARCHUTIS;
                        break;

                    case Constant.ACTION_PIN_SERVICE:
                        address = "/cs?action" + Constant.ACTION_PIN_SERVICE;
                        break;

                    case Constant.ACTION_MIG_SERVICE:
                        address = "/cs?action" + Constant.ACTION_MIG_SERVICE;
                        break;

                    case Constant.ACTION_MET_SERVICE:
                        address = "/cs?action" + Constant.ACTION_MET_SERVICE;
                        break;


                    case Constant.ACTION_GET_SCHOOL_COMMON_INFO:
                        address = "/cs?action" + Constant.ACTION_GET_SCHOOL_COMMON_INFO;
                        break;

                    case Constant.ACTION_LOAD_SCHOOL_GRID_INFO:
                        address = "/cs?action" + Constant.ACTION_LOAD_SCHOOL_GRID_INFO;
                        break;

                    case Constant.ACTION_GET_PERSON_COMMON_INFO:
                        address = "/cs?action" + Constant.ACTION_GET_PERSON_COMMON_INFO;
                        break;

                    case Constant.ACTION_GET_STUDENT_COMMON_INFO:
                        address = "/cs?action" + Constant.ACTION_GET_STUDENT_COMMON_INFO;
                        break;

                    case Constant.ACTION_GET_STUDENT_RIGHT_PANEL_INFO:
                        address = "/cs?action" + Constant.ACTION_GET_STUDENT_RIGHT_PANEL_INFO;
                        break;

                    case Constant.ACTION_GET_GRID_CONTENT:
                        address = "/cs?action" + Constant.ACTION_GET_GRID_CONTENT;
                        break;

                    case Constant.ACTION_GET_TEACHING_GRID_CONTENT:
                        address = "/cs?action" + Constant.ACTION_GET_TEACHING_GRID_CONTENT ;
                        break;
                    case Constant.ACTION_GET_FAMILY_GRID_CONTENT:
                        address = "/cs?action" + Constant.ACTION_GET_FAMILY_GRID_CONTENT ;
                        break;
                    case Constant.ACTION_GET_SPEC_GRID_CONTENT:
                        address = "/cs?action" + Constant.ACTION_GET_SPEC_GRID_CONTENT ;
                        break;


                    case Constant.ACTION_GET_DYNAMIC_GRID_CONTENT:
                        address = "/cs?action" + Constant.ACTION_GET_DYNAMIC_GRID_CONTENT;
                        break;

                    case Constant.ACTION_DOWN_FILE:
                        address = "/cs?action" + Constant.ACTION_DOWN_FILE;
                        break;

                    case Constant.ACTION_GET_STRUKTUR_ORG_INFO:
                        address = "/cs?action" + Constant.ACTION_GET_STRUKTUR_ORG_INFO;
                        break;

                    case Constant.ACTION_GET_USER_GROUP_OPERATIONS:
                        address = "/cs?action" + Constant.ACTION_GET_USER_GROUP_OPERATIONS;
                        break;

                    case Constant.ACTION_LOAD_USERINFO:
                        address = "/cs?action" + Constant.ACTION_LOAD_USERINFO;
                        break;

                    case Constant.ACTION_SET_SEARCH_TEXT:
                        address = "/cs?action" + Constant.ACTION_SET_SEARCH_TEXT;
                        break;

                    case Constant.ACTION_CHECK_TEACHER_CURRENT_WORK:
                        address = "/cs?action" + Constant.ACTION_CHECK_TEACHER_CURRENT_WORK;
                        break;


                    case Constant.ACTION_DOWNLOAD_FILE:
                        address = "/cs?action" + Constant.ACTION_DOWNLOAD_FILE;
                        break;


                    //cs1 contSaveServlet
                    case Constant.ACTION_SAVE_STRUKTUR:
                        address = "/cs1?action" + Constant.ACTION_SAVE_STRUKTUR;
                        break;

                    case Constant.ACTION_CHECK_USERNAME:
                        address = "/cs1?action" + Constant.ACTION_CHECK_USERNAME;
                        break;

                    case Constant.ACTION_SAVE_USERS:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_USERS;
                        break;

                    case Constant.ACTION_SAVE_DICTIONARY:
                        address = "/cs1?action" + Constant.ACTION_SAVE_DICTIONARY;
                        break;

                    case Constant.ACTION_SAVE_SCHOOL_COMMON_INFO:
                        address = "/cs1?action" + Constant.ACTION_SAVE_SCHOOL_COMMON_INFO;
                        break;

                    case Constant.ACTION_SAVE_STUFF_AS_NEW_PERSON:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_STUFF_AS_NEW_PERSON;
                        break;

                    case Constant.ACTION_SAVE_STUFF_COMMON_INFO:
                        address = "/cs1?action" + Constant.ACTION_SAVE_STUFF_COMMON_INFO;
                        break;

                    case Constant.ACTION_SAVE_STUDENT_AS_NEW_PERSON:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_STUDENT_AS_NEW_PERSON;
                        break;

                    case Constant.ACTION_SAVE_STUDENT_COMMON_INFO:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_STUDENT_COMMON_INFO;
                        break;

                    case Constant.ACTION_DELETE_FROM_MAIN_GRID:
                        address = "/cs1?action=" + Constant.ACTION_DELETE_FROM_MAIN_GRID;
                        break;

                    case Constant.ACTION_SAVE_USER_HISTORY:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_USER_HISTORY;
                        break;

                    case Constant.ACTION_GET_COUNT_STUD_CLASS:
                        address = "/cs1?action=" + Constant.ACTION_GET_COUNT_STUD_CLASS;
                        break;

                    case Constant.ACTION_SAVE_PHOTO:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_PHOTO;
                        break;

                    case Constant.ACTION_GET_PERSON_FILE:
                        address = "/cs1?action=" + Constant.ACTION_GET_PERSON_FILE;
                        break;

                    case Constant.ACTION_SAVE_MAINDOCS:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_MAINDOCS;
                        break;

                    case Constant.ACTION_SAVE_SCHOOL_ADDRESS_INFO:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_SCHOOL_ADDRESS_INFO;
                        break;

                    case Constant.ACTION_SAVE_SCHOOL_CONTACT_INFO:
                        address = "/cs1?action=" + Constant.ACTION_SAVE_SCHOOL_CONTACT_INFO;
                        break;

                    case Constant.ACTION_SET_SESSION:
                        address = "/cs1?action=" + Constant.ACTION_SET_SESSION;
                        break;

                    case Constant.ACTION_CHANGE_USER_PASSWORD:
                        address = "/cs1?action=" + Constant.ACTION_CHANGE_USER_PASSWORD;
                        break;


                    case Constant.ACTION_GET_CHECK_CONTACT_SCH:
                        address = "/cs1?action=" + Constant.ACTION_GET_CHECK_CONTACT_SCH;
                        break;

                    case Constant.ACTION_UPDATE_PERON_INFO_BY_IAMAS:
                        address = "/cs1?action=" + Constant.ACTION_UPDATE_PERON_INFO_BY_IAMAS;
                        break;

                    //jspler
                    case Constant.ACTION_OPEN_DIALOG_FOR_IDENTITY:
                        address = "/MMIS/dialog/dialogsJSP/identitySearch.jsp";
                        break;

                    default:
                        request.setAttribute(Constant.INFO_ATTRIBUTE, "Bu əməliyyata icazə verilmir!!!");
                        address = "/WEB-INF/pages/parse/info.jsp";
                        forward = true;
                        logger.info("Bu emeliyyata icaze yoxdur!!!");
                        break;
                }

            }
        } catch (Exception e) {
            logger.info("error fromtcontroller " + e.toString());
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            if (forward) {
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(address);

            }
        }

    }
}