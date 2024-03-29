package model.dao.Impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
        private Connection conn;

        public DepartmentDaoJDBC(Connection conn){
            this.conn = conn;
        }


    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department" +
                            " WHERE department.Id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();
            if (rs.next()) {
                Department obj = instantiateDepartment(rs);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY ID");

            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();

            while (rs.next()){
                Department obj = instantiateDepartment(rs);
                list.add(obj);
            }return list;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }
}