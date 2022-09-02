package student.dao;


import student.domain.Admin;
import student.domain.Stu;

import java.util.List;
import java.util.Map;

/**
 * 学生信息操作的DAO
 */
public interface StuDao {

    /**
     * 查询所有学生信息
     * @return
     */
    List<Stu> findAll();

    /**
     * 判断登录用户名与密码
     * @param adminLogin
     * @return
     */
    Admin login(Admin adminLogin);

    /**
     * 添加学生信息
     * @param stu
     */
    void addStu(Stu stu);

    /**
     * 删除学生信息
     * @param id
     */
    void deleteStu(int id);

    /**
     * 通过id查询学生信息
     * @param id
     */
    Stu findStuById(int id);

    /**
     * 通过id修改学生信息
     * @param stu
     */
    void updateStu(Stu stu);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<Stu> findStuByPage(int start, int rows, Map<String, String[]> condition);
}
