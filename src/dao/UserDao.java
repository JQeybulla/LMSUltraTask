package dao;

import domain.Operation;
import domain.UsersInfo;

import java.util.Map;

public interface UserDao {
    public UsersInfo getUser(Long userId, String username, String password, int is_teacher) throws Exception;
    public Map<Long, Operation> getOperationMap() throws Exception;

    public Map<Long, domain.Module> getModuleMap() throws Exception;

}
