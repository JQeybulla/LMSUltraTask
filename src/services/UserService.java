package services;


import domain.Operation;
import domain.UsersInfo;

import java.util.Map;

public interface UserService {
    public UsersInfo getUser(Long id, String username, String password) throws Exception;
    public Map<Long, Operation> getOperationMap() throws Exception;

    public Map<Long, domain.Module> getModuleMap() throws Exception;
}
