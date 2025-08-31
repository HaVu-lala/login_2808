package web_2808.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import web_2808.configs.DBConnectMySQL;
import web_2808.dao.IUserDao;
import web_2808.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao{

	// thuc thi
	// ke thua extends
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<UserModel> findAll() {
		// TODO Auto-generated method stub
		String sql = "select * from users";
		
		List<UserModel> list = new ArrayList<>();	// Tao 1 list truyen du lieu
		
		try {
			conn = super.getDatabaseConnection();	// ket noi database
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {	// next tung dong toi cuoi bang
				list.add(
						new UserModel(
							rs.getInt("id"),
							rs.getString("username"),
							rs.getString("email"),
							rs.getString("password"),
							rs.getString("fullname"),
							rs.getString("images")
							)
						);	// add vao
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserModel findById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users WHERE id = ?";
	    try {
	        conn = super.getDatabaseConnection();   // kết nối database
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return new UserModel(
	                rs.getInt("id"),
	                rs.getString("username"),
	                rs.getString("email"),
	                rs.getString("password"),
	                rs.getString("fullname"),
	                rs.getString("images")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // nếu không tìm thấy
	}

	public UserModel findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            conn = super.getDatabaseConnection();   // dùng DBConnectMySQL như các hàm khác
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                return new UserModel(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("fullname"),
                    rs.getString("images")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // không tìm thấy
    }
	
	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO users(id, username, email, password, fullname, images) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			conn = super.getDatabaseConnection();	// ket noi database
			
			ps = conn.prepareStatement(sql);	// nem cau sql vao cho thuc thi
			
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getFullname());
			ps.setString(6, user.getImages());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		
		userDao.insert(new UserModel(2, "Truong", "truongnv@gmail.com", "123", "nguyen", ""));
		
		List<UserModel> list = userDao.findAll();
		
		for (UserModel user : list) {
			System.out.println(user);
		}
	}
	
	

}
