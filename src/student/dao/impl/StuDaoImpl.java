package student.dao.impl;


import com.sun.javafx.util.Logging;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import student.dao.StuDao;
import student.domain.Admin;
import student.domain.Stu;
import student.util.DruidUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StuDaoImpl implements StuDao {
    private JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());
    @Override
    public List<Stu> findAll() {
        // 使用JDBC完成操作
        String sql = "select * from students";
        List<Stu> stus = template.query(sql, new BeanPropertyRowMapper<Stu>(Stu.class));
        return stus;
    }

    @Override
    public Admin login(Admin adminLogin) {
        String sql = "select * from admin where username=? and password=?";
        Admin admin = null;
        try {
            admin = template.queryForObject(sql,
                    new BeanPropertyRowMapper<Admin>(Admin.class),
                    adminLogin.getUsername(),
                    adminLogin.getPassword());
        } catch (Exception e) {
            // 如果发生异常也就是用户名与密码和数据库中的不匹配，就返回空
            return null;
        }
        return admin;
    }

    @Override
    public void addStu(Stu stu) {
        String sql = "insert into students values(null, ?, ?, ?, ?, ?, ?)";
        template.update(sql,
                stu.getName(),
                stu.getGender(),
                stu.getAge(),
                stu.getAddress(),
                stu.getQq(),
                stu.getEmail());
    }

    @Override
    public void deleteStu(int id) {
        String sql = "delete from students where id=?";
        template.update(sql, id);
    }

    @Override
    public Stu findStuById(int id) {
        String sql = "select * from students where id=?";
        List<Stu> stus = template.query(sql, new BeanPropertyRowMapper<Stu>(Stu.class), id);
        Stu stu = stus.get(0);
        return stu;
    }

    @Override
    public void updateStu(Stu stu) {
        String sql = "update students set name=?, gender=?, age=?, address=?, qq=?, email=? where id=?";
        template.update(sql,
                stu.getName(),
                stu.getGender(),
                stu.getAge(),
                stu.getAddress(),
                stu.getQq(),
                stu.getEmail(),
                stu.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        // 定义初始化模板sql
        StringBuilder sql = new StringBuilder("select count(*) from students where 1=1");
        // 遍历map集合，拼接查询条件
        Set<String> keySet = condition.keySet();
        // 定义参数的集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 排除分页查询的参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            // 获取value
            String value = condition.get(key)[0];
            // 判断value是否有值
            if (value != null && !"".equals(value)) {
                // 有值
                if ("gender".equals(key)) {
                    sql.append(" and "+ key +" = ?");
                    params.add(value); // 加参数?的值
                } else {
                    sql.append(" and "+ key +" like ?");
                    params.add("%" + value + "%"); // 加参数?的值
                }
            }
        }
        int totalCount = template.queryForObject(sql.toString(), Integer.class, params.toArray());
        return totalCount;
    }

    @Override
    public List<Stu> findStuByPage(int start, int rows, Map<String, String[]> condition) {
        // 定义初始化模板sql
        StringBuilder sql = new StringBuilder("select * from students where 1=1");
        // 遍历map集合，拼接查询条件
        Set<String> keySet = condition.keySet();
        // 定义参数的集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 排除分页查询的参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            // 获取value
            String value = condition.get(key)[0];
            // 判断value是否有值
            if (value != null && !"".equals(value)) {
                // 有值
                if ("gender".equals(key)) {
                    sql.append(" and "+ key +" = ?");
                    params.add(value); // 加参数?的值
                } else {
                    sql.append(" and "+ key +" like ?");
                    params.add("%" + value + "%"); // 加参数?的值
                }
            }
        }
        // 添加分页查询
        sql.append(" limit ?,?");
        // 添加分页查询的参数
        params.add(start);
        params.add(rows);
        List<Stu> list = template.query(sql.toString(), new BeanPropertyRowMapper<Stu>(Stu.class), params.toArray());
        return list;
    }
}
