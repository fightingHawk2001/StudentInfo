package student.service.impl;

import student.dao.StuDao;
import student.dao.impl.StuDaoImpl;
import student.domain.Admin;
import student.domain.PageBean;
import student.domain.Stu;
import student.service.StuService;

import java.util.List;
import java.util.Map;

public class StuServiceImpl implements StuService {
    private StuDao dao = new StuDaoImpl();
    @Override
    public List<Stu> findAll() {
        // 调用DAO完成查询
        return dao.findAll();
    }

    @Override
    public Admin login(Admin adminLogin) {
        // 调用DAO完成判断
        return dao.login(adminLogin);
    }

    @Override
    public void addStu(Stu stu) {
        // 调用DAO完成添加
        dao.addStu(stu);
    }

    @Override
    public void deleteStu(String id) {
        // 调用DAO完成删除
        dao.deleteStu(Integer.parseInt(id));
    }

    @Override
    public Stu findStuById(String id) {
        return dao.findStuById(Integer.parseInt(id));
    }

    @Override
    public void updateStu(Stu stu) {
        dao.updateStu(stu);
    }

    @Override
    public void deleteStus(String[] checkIds) {
        // 循环调用dao的delete方法
        if (checkIds != null && checkIds.length > 0) {
            for (String checkId : checkIds) {
                dao.deleteStu(Integer.parseInt(checkId));
            }
        }
    }

    @Override
    public PageBean<Stu> findStuByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        if (_currentPage == null || "".equals(_currentPage)) {
            _currentPage = "1";
        }
        if (_rows == null || "".equals(_rows)) {
            _rows = "10";
        }
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<Stu> pb = new PageBean<Stu>();
        // 设置值
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        // 调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        int totalPage = (int) Math.ceil((double)totalCount / rows); // 向上取整
        pb.setTotalPage(totalPage);
        // 调用dao查询每页显示的数据
        List<Stu> list = dao.findStuByPage((currentPage - 1) * rows, rows, condition);
        pb.setList(list);
        return pb;
    }
}
