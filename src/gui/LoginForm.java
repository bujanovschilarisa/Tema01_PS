package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import models.User;
import bl.UserService;

public class LoginForm extends JFrame {
    private static final String ADMIN = "admin";
    private static final String EMPLOYEE = "employee";

    private JLabel userMessageLabel;
    private JTextField userTextField;
    private JLabel passwordMessageLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel imagineFundal;
    private JButton forgotPasswordButton;

    private UserService userService = new UserService();
    private User user;
    private JLabel forgotPasswordLabel;

    public LoginForm() {
        // Permiterea amplasarii unui obiect unde doresti
        setLayout(null);

        // O afisare draguta a componentelor grafice
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Vizibilitatea aplicatiei
        setVisible(true);

        // Setarea dimensiunilor aplicatiei
        setBounds(200, 5, 900, 700);

        setTitle("Teatrul National - Cluj Napoca <-- Bujanovschi Beatrice Larisa -->"); // Numele
        // aplicatiei

        // Inchiderea procesului Java la inchiderea aplicatiei
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        userMessageLabel = new JLabel("Utilizator:");
        userMessageLabel.setBounds(10, 50, 200, 40);
        userMessageLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        userMessageLabel.setForeground(Color.BLACK);
        userMessageLabel.setVisible(true);
        add(userMessageLabel);

        userTextField = new JTextField();
        userTextField.setToolTipText("Introduceti user name-ul!");
        userTextField.setBounds(120, 55, 300, 30);
        userTextField.setOpaque(true);
        userTextField.setVisible(true);
        add(userTextField);

        passwordMessageLabel = new JLabel("Parola:");
        passwordMessageLabel.setBounds(10, 90, 200, 40);
        passwordMessageLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        passwordMessageLabel.setForeground(Color.BLACK);
        passwordMessageLabel.setVisible(true);
        add(passwordMessageLabel);

        passwordField = new JPasswordField();
        passwordField.setToolTipText("Introduceti parola!");
        passwordField.setBounds(120, 95, 300, 30);
        passwordField.setOpaque(true);
        passwordField.setVisible(true);
        add(passwordField);

        loginButton = new JButton("Logheaza-te");
        loginButton.setBounds(270, 135, 150, 30);
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginButton.setVisible(true);
        add(loginButton);

        forgotPasswordButton = new JButton("Recuperare parola");
        forgotPasswordButton.setBounds(600, 600, 200, 30);
        forgotPasswordButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        forgotPasswordButton.setVisible(true);
        add(forgotPasswordButton);

        forgotPasswordLabel = new JLabel(
                "Pentru a afla parola introduceti username-ul si apasati butonul > recuperare parola!");
        forgotPasswordLabel.setBounds(10, 550, 700, 40);
        forgotPasswordLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        forgotPasswordLabel.setForeground(Color.BLACK);
        forgotPasswordLabel.setVisible(true);
        add(forgotPasswordLabel);

        imagineFundal = new JLabel(new ImageIcon("teatru.png"));
        imagineFundal.setBounds(0, 0, 900, 700);
        imagineFundal.setOpaque(true);
        imagineFundal.setVisible(true);
        add(imagineFundal);

        repaint();
    }

    public void initListeners(AdminForm adminForm, EmployeeForm employeeForm) {
        LoginForm loginForm = this;
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                user = userService.isUser(getUsername(), getPassword());
                if (user == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Username sau parola gresita!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    setVisible(false);
                    if (user.getRol().equals(ADMIN)) {
                        adminForm.setVisible(true);
                    } else if (user.getRol().equals(EMPLOYEE)) {
                        employeeForm.setVisible(true);
                        employeeForm.init(loginForm);
                    }
                }

            }
        });

        forgotPasswordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isUsername = userService.findUsername(getUsername());
                if (isUsername) {
                    String password = userService.getForgotPassword(getUsername());
                    if (password == null) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nu s-a putut recupera parola!", "Eroare",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "PAROLA ESTE: " + password, "Informatie",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Acest username nu exista", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public String getUsername() {
        return userTextField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void cleanUsernameAndPassword() {
        userTextField.setText("");
        passwordField.setText("");

    }
}
