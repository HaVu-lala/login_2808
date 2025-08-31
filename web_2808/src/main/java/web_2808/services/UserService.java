package web_2808.services;

import web_2808.models.UserModel;

public interface UserService {

	UserModel login(String username, String password);
}
