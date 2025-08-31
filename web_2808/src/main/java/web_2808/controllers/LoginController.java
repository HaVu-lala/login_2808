package web_2808.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import web_2808.models.UserModel;
import web_2808.services.UserService;
import web_2808.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Chuyển hướng sang trang login.jsp
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserModel user = userService.login(username, password);

        if (user != null) {
            // Lưu user vào session
            HttpSession session = req.getSession();
            session.setAttribute("account", user);

            // Đăng nhập thành công → chuyển về trang chủ
            //resp.sendRedirect(req.getContextPath() + "/home");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/views/success.jsp").forward(req, resp);
            
        } else {
            // Sai username/password → quay lại login.jsp kèm thông báo
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            //req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }
}
