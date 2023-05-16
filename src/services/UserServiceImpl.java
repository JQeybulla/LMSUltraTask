package services;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.Operation;
import domain.UsersInfo;

import java.util.Map;

public class UserServiceImpl implements UserService{
    UserDao uDao = new UserDaoImpl();
    @Override
    public UsersInfo getUser(Long id, String username, String password) throws Exception {
//        System.out.println("UserInfo is called"); // passed
        return uDao.getUser(id, username, password);
    }

    @Override
    public Map<Long, Operation> getOperationMap() throws Exception {
        return uDao.getOperationMap();
    }

    @Override
    public Map<Long, domain.Module> getModuleMap() throws Exception {
        return uDao.getModuleMap();
    }
}
