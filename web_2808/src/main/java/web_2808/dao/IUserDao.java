package web_2808.dao;

import java.util.List;

import web_2808.models.UserModel;

public interface IUserDao {
	// dinh nghia danh sach phuong thuc, cac ham va cac thu tuc
	
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	UserModel findByUsernameAndPassword(String username, String password);


}
