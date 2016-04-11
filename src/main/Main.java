package main;

import gui.AdminForm;
import gui.EmployeeForm;
import gui.LoginForm;
import bl.UserService;

public class Main {

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        AdminForm adminForm = new AdminForm();
        EmployeeForm employeeForm = new EmployeeForm();
        adminForm.setVisible(false);
        employeeForm.setVisible(false);
        adminForm.init(loginForm);
        loginForm.initListeners(adminForm, employeeForm);
        //UserService userService = new UserService(loginForm);
    }

}
