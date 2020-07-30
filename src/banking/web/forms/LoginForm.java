package banking.web.forms;

import banking.model.LoginModel;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
    private String userNameAsString;
    private String passwordAsString;

    public static LoginForm fromRequest(HttpServletRequest req) {
        LoginForm loginForm = new LoginForm();

        loginForm.userNameAsString = req.getParameter("userName");
        loginForm.passwordAsString = req.getParameter("password");

        return loginForm;
    }
    public LoginModel toModel() {
        LoginModel loginModel = new LoginModel();

        String userName = userNameAsString;
        loginModel.setUserName(userName);

        String password = passwordAsString;
        loginModel.setPassword(password);

        return loginModel;
    }
}
