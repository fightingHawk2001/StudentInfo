package student.service;

import student.domain.Admin;
import student.domain.PageBean;
import student.domain.Stu;

import java.util.List;
import java.util.Map;

/**
 * 学生管理的业务接口
 */
public interface StuService {
    /**
     * 查询所有的学生信息
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
    void deleteStu(String id);

    /**
     * 通过id查询学生信息
     * @param id
     */
    Stu findStuById(String id);

    /**
     * 通过id修改学生信息
     * @param stu
     */
    void updateStu(Stu stu);

    /**
     * 通过id数组删除多个学生记录
     * @param checkIds
     */
    void deleteStus(String[] checkIds);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<Stu> findStuByPage(String currentPage, String rows, Map<String, String[]> condition);
}
