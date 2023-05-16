package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Locale;

public class DBFunction {
    protected final Log logger = LogFactory.getLog(getClass());

    public Connection connectDB() throws Exception {
//        System.out.println("This is connect db function");
//        System.out.println("Connect db is called"); // passed
        Locale oldLocale = Locale.getDefault();
        //   boolean is = oldLocale.equals(Locale.ENGLISH);
        //   if (!is){
        //       Locale.setDefault(Locale.ENGLISH);
        //    }
        Locale.setDefault(oldLocale);
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/ORCL");
        if (ds == null){
            System.out.println("Data source is null");
        }else{
            System.out.println("Data source is not null");
        }
        System.out.println(ds);
        Connection connection = ds.getConnection();
        if (connection != null) {
            connection.setAutoCommit(false);
            System.out.println("Connected");
        } else {
            logger.info("NOT CONNECTED");
            System.out.println("NOT connected");
        }

        return connection;
    }//ConnectDB



    public String encodePass(String orig) {
        byte[] encoded = Base64.encodeBase64(orig.getBytes());
        String res = new String(encoded);
        return res;
    }

    public String decodePass(String encVal) {
        byte[] decoded = Base64.decodeBase64(encVal.getBytes());
        String res = new String(decoded);
        return res;
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection c) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (c != null) {
            c.close();
        }
    }

    public static void close(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            ps.close();
        }
    }

    public static void close(ResultSet rs, CallableStatement cs, Connection c) throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("close" + e.toString());
        }
        try {
            if (cs != null) {
                cs.close();
            }

        } catch (Exception e) {
            System.out.println("close" + e.toString());
        }
        try {
            if (c != null) {
                c.close();
            }

        } catch (Exception e) {
            System.out.println("close" + e.toString());
        }

    }

    public static void close(ResultSet rs, PreparedStatement ps) throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("lose exception----" + e.toString());
        }

    }

    public static void close(Connection c) throws SQLException {
        try {
            if (c != null) {
                c.close();
                c = null;
            }
        } catch (Exception e) {
            System.out.println("close connection" + e.toString());
        }
    }

    public static void close(PreparedStatement ps, Connection c) throws SQLException {
        if (ps != null) {
            ps.close();
        }
        if (c != null) {
            c.close();
        }
    }

    public static void close(CallableStatement callableStatement, Connection connection) throws SQLException {
        if (callableStatement != null) {
            callableStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public static void close(CallableStatement callableStatement) throws SQLException {

        try {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
        } catch (Exception e) {
            System.out.println("close callablestatement" + e.toString());
        }
    }

    public static void close(ResultSet resultSet) throws SQLException {

        try {
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (Exception e) {
            System.out.println("close resultset" + e.toString());
        }
    }


}
