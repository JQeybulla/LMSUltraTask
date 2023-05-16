package web;

import domain.Module;
import domain.Operation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import services.UserService;
import services.UserServiceImpl;
import utils.Helper;

import javax.naming.Context;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CleanContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    public CleanContextListener() {

    }

    Log logger = LogFactory.getLog(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserService userService = new UserServiceImpl();


        Map<Long, Operation> operationMap = new HashMap<Long, Operation>();
        Map<Long, Module> moduleMap = new HashMap<Long, Module>();
        try {

//             operationMap = userService.getOperationMap();
//             moduleMap = userService.getModuleMap();
        } catch (Exception e) {
            logger.info("error initialize context " + e.toString());
        }

        sce.getServletContext().setAttribute(Constant.OPERATION_BUTTONS, operationMap);
        sce.getServletContext().setAttribute(Constant.MODULS, moduleMap);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Logger logger = Logger.getLogger("CleanupContextListener");
        Context initContext;


        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            ClassLoader driverclassLoader = driver.getClass().getClassLoader();
            ClassLoader thisClassLoader = this.getClass().getClassLoader();
            if (driverclassLoader != null && thisClassLoader != null && driverclassLoader.equals(thisClassLoader)) {
                try {
                    logger.log(Level.INFO, "Deregistering: {0}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    logger.info("error cleancontext " + e.toString());
                }
            }
        }
    }

    public void sessionCreated(HttpSessionEvent se) {
        try {
            se.getSession().setAttribute(Constant.AUTHENTICATION_TOKEN, Helper.toMD5(new Date().toString() + RandomStringUtils.randomAlphanumeric(13)));
            System.out.println("token11= " + Helper.toMD5("19/12/1990" + RandomStringUtils.randomAlphanumeric(13)));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void sessionDestroyed(HttpSessionEvent se) {


    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}