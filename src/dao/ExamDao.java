package dao;

import domain.ExamInfo;
import domain.Operation;
import domain.UsersInfo;

import java.util.Map;

public interface ExamDao {
    public ExamInfo getExam(Long userId, String username, String password) throws Exception;
    public Map<Long, Operation> getOperationMap() throws Exception;

    public Map<Long, domain.Module> getModuleMap() throws Exception;
}
