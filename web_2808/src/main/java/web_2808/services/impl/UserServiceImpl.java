package web_2808.services.impl;

import web_2808.dao.IUserDao;
import web_2808.dao.impl.UserDaoImpl;
import web_2808.models.UserModel;
import web_2808.services.UserService;

public class UserServiceImpl implements UserService {

    private IUserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public UserModel login(String username, String password) {
        // gọi dao để check user
        UserModel user = userDao.findByUsernameAndPassword(username, password);
        if (user != null) {
            return user;  // đăng nhập thành công
        }
        return null; // đăng nhập thất bại
    }
}
