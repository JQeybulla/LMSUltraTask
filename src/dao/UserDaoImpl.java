package dao;

import domain.Module;
import domain.Operation;
import domain.UsersInfo;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.DBFunction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

public class UserDaoImpl implements UserDao{
    private Log logger = LogFactory.getLog(getClass());
    protected DBFunction df = new DBFunction();


    @Override
    public UsersInfo getUser(Long userId, String username, String password) throws Exception {
        UsersInfo user = new UsersInfo();
        user.setId(userId);

        Connection conn = df.connectDB();
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call MYPROJECT.USERS_PACKAGE.GET_USER(?,?,?,?,?)}";
        String encPas = df.encodePass(password);
        try {
            if (conn != null) {
                logger.info("Connection ");
                try {
                    call = conn.prepareCall(sql);
                    call.setLong(1 ,userId);
                    call.setString(2, username);
                    call.setString(3, password);
                    call.registerOutParameter(4, OracleTypes.INTEGER);
                    call.registerOutParameter(5, OracleTypes.INTEGER);

                    call.execute();

                    int login = (Integer) call.getObject(4);

                    System.out.println("Login: " + login);
                    logger.info("Login corresponding to the username and password: " + login);

                    if (login == 1) {
                        try {
                            System.out.println(call);
//                            Long userId = call.getObject(5);
                            System.out.println(call.getObject(4));
//                            System.out.println(call.getLong(5));
//                            System.out.println(call.getLong(2));
//                            System.out.println(call.getLong(3));
//                            System.out.println(call.getLong(4));
//                            rs = (ResultSet) call.getObject(4);
                            user.setId(call.getLong(5));
                            user.setUsername(username);
                            user.setPassword(password);
                        }
                        catch (Exception ex) {
                            logger.info(ex.toString());
                            System.out.println("Error while fetching data");
                        } finally {
                            DBFunction.close(rs);
                        }
                    }
                }
                catch (Exception ex) {
                    logger.info(ex.toString());
                    System.out.println("Error while executing call");
                } finally {
                    DBFunction.close(call);
                }
            }else {
                System.out.println("No connection");
            }
        } catch (Exception ex) {
            logger.info(ex.toString());
            System.out.println("Error while connecting to database");
        } finally {
            DBFunction.close(conn);

        }

       return  user;
    }

    @Override
    public Map<Long, Operation> getOperationMap() throws Exception {
        return null;
    }

    @Override
    public Map<Long, Module> getModuleMap() throws Exception {
        return null;
    }
}
