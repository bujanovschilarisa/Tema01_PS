package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import models.Event;
import models.User;
import bl.EventService;
import bl.UserService;

public class AdminForm extends JFrame {

    private JLabel showsListLabel;
    private JComboBox<Event> comboBoxShowsList;
    private JLabel titleShowLabel;
    private JTextField titleShowTextField;
    private JButton createShowButton;
    private JLabel regieShowLabel;
    private JTextField regieShowTextField;
    private JLabel distributieShowLabel;
    private JTextField distributieShowTextField;
    private JLabel dataPremieraShowLabel;
    private JTextField dataPremieraShowTextField;
    private JLabel numberOfTiketsLabel;
    private JTextField numberOfTiketsTextField;
    private JLabel nameOfEmployeeLabel;
    private JTextField nameOfEmployeeTextField;
    private JLabel usernameOfEmployeeLabel;
    private JTextField usernameOfEmployeeTextField;
    private JLabel passwordOfEmployeeLabel;
    private JTextField passwordOfEmployeeTextField;
    private JButton createEmployeeButton;
    private JButton deleteShowButton;
    private JButton readShowButton;
    private JButton updateShowButton;
    private JButton logoutButton;

    private EventService eventService = new EventService();
    private UserService userService = new UserService();
    private JButton selectShowButton;

    public AdminForm() {
        setVisible(false);
        // Permiterea amplasarii unui obiect unde doresti
        setLayout(null);

        // O afisare draguta a componentelor grafice
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Setarea dimensiunilor aplicatiei
        setBounds(200, 5, 900, 700);

        setTitle("Teatrul National - Cluj Napoca <-- Bujanovschi Beatrice Larisa -->"); // Numele
        // aplicatiei

        // Inchiderea procesului Java la inchiderea aplicatiei
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        showsListLabel = new JLabel("Lista spectacolelor:");
        showsListLabel.setBounds(5, 5, 200, 40);
        showsListLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        showsListLabel.setForeground(Color.BLACK);
        showsListLabel.setVisible(true);
        add(showsListLabel);

        comboBoxShowsList = new JComboBox<Event>();
        comboBoxShowsList.setBounds(170, 5, 700, 40);
        comboBoxShowsList.setVisible(true);
        comboBoxShowsList.setAutoscrolls(true);
        comboBoxShowsList.repaint();
        add(comboBoxShowsList);

        titleShowLabel = new JLabel("Titlul:");
        titleShowLabel.setBounds(10, 50, 200, 40);
        titleShowLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        titleShowLabel.setForeground(Color.BLACK);
        titleShowLabel.setVisible(true);
        add(titleShowLabel);

        titleShowTextField = new JTextField();
        titleShowTextField.setToolTipText("Introduceti titlul spectacolului!");
        titleShowTextField.setBounds(250, 55, 300, 30);
        titleShowTextField.setOpaque(true);
        titleShowTextField.setVisible(true);
        add(titleShowTextField);

        regieShowLabel = new JLabel("Regia:");
        regieShowLabel.setBounds(10, 90, 200, 40);
        regieShowLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        regieShowLabel.setForeground(Color.BLACK);
        regieShowLabel.setVisible(true);
        add(regieShowLabel);

        regieShowTextField = new JTextField();
        regieShowTextField.setToolTipText("Introduceti regia spectacolului!");
        regieShowTextField.setBounds(250, 90, 300, 30);
        regieShowTextField.setOpaque(true);
        regieShowTextField.setVisible(true);
        add(regieShowTextField);

        distributieShowLabel = new JLabel("Distributia:");
        distributieShowLabel.setBounds(10, 130, 200, 40);
        distributieShowLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        distributieShowLabel.setForeground(Color.BLACK);
        distributieShowLabel.setVisible(true);
        add(distributieShowLabel);

        distributieShowTextField = new JTextField();
        distributieShowTextField.setToolTipText("Introduceti distributia pentru spectacol!");
        distributieShowTextField.setBounds(250, 130, 300, 30);
        distributieShowTextField.setOpaque(true);
        distributieShowTextField.setVisible(true);
        add(distributieShowTextField);

        dataPremieraShowLabel = new JLabel("Data premierei:");
        dataPremieraShowLabel.setBounds(10, 170, 200, 40);
        dataPremieraShowLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        dataPremieraShowLabel.setForeground(Color.BLACK);
        dataPremieraShowLabel.setVisible(true);
        add(dataPremieraShowLabel);

        dataPremieraShowTextField = new JTextField();
        dataPremieraShowTextField.setToolTipText("Introduceti data premierei pentru spectecol!");
        dataPremieraShowTextField.setBounds(250, 170, 300, 30);
        dataPremieraShowTextField.setOpaque(true);
        dataPremieraShowTextField.setVisible(true);
        add(dataPremieraShowTextField);

        numberOfTiketsLabel = new JLabel("Numarul de bilete disponibil:");
        numberOfTiketsLabel.setBounds(10, 210, 250, 40);
        numberOfTiketsLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        numberOfTiketsLabel.setForeground(Color.BLACK);
        numberOfTiketsLabel.setVisible(true);
        add(numberOfTiketsLabel);

        numberOfTiketsTextField = new JTextField();
        numberOfTiketsTextField.setToolTipText("Introduceti numarul de bilete disponibile pentru vanzare!");
        numberOfTiketsTextField.setBounds(250, 210, 300, 30);
        numberOfTiketsTextField.setOpaque(true);
        numberOfTiketsTextField.setVisible(true);
        add(numberOfTiketsTextField);

        createShowButton = new JButton("Creaza spectacol");
        createShowButton.setBounds(350, 250, 200, 30);
        createShowButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        createShowButton.setVisible(true);
        add(createShowButton);

        readShowButton = new JButton("Citeste evenimentele");
        readShowButton.setBounds(600, 55, 250, 30);
        readShowButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        readShowButton.setVisible(true);
        add(readShowButton);

        deleteShowButton = new JButton("Sterge eveniment");
        deleteShowButton.setBounds(600, 90, 250, 30);
        deleteShowButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        deleteShowButton.setVisible(true);
        add(deleteShowButton);

        updateShowButton = new JButton("Update eveniment");
        updateShowButton.setBounds(600, 170, 250, 30);
        updateShowButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updateShowButton.setVisible(true);
        add(updateShowButton);

        selectShowButton = new JButton("Seleteaza eveniment");
        selectShowButton.setBounds(600, 130, 250, 30);
        selectShowButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        selectShowButton.setVisible(true);
        add(selectShowButton);

        nameOfEmployeeLabel = new JLabel("Numele noului angajat:");
        nameOfEmployeeLabel.setBounds(10, 300, 200, 40);
        nameOfEmployeeLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        nameOfEmployeeLabel.setForeground(Color.BLACK);
        nameOfEmployeeLabel.setVisible(true);
        add(nameOfEmployeeLabel);

        nameOfEmployeeTextField = new JTextField();
        nameOfEmployeeTextField.setToolTipText("Introduceti numele angajatului!");
        nameOfEmployeeTextField.setBounds(250, 300, 300, 30);
        nameOfEmployeeTextField.setOpaque(true);
        nameOfEmployeeTextField.setVisible(true);
        add(nameOfEmployeeTextField);

        usernameOfEmployeeLabel = new JLabel("Username-ul angajatului:");
        usernameOfEmployeeLabel.setBounds(10, 340, 250, 40);
        usernameOfEmployeeLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        usernameOfEmployeeLabel.setForeground(Color.BLACK);
        usernameOfEmployeeLabel.setVisible(true);
        add(usernameOfEmployeeLabel);

        usernameOfEmployeeTextField = new JTextField();
        usernameOfEmployeeTextField.setToolTipText("Introduceti user name-ul pentru noul angajat!");
        usernameOfEmployeeTextField.setBounds(250, 340, 300, 30);
        usernameOfEmployeeTextField.setOpaque(true);
        usernameOfEmployeeTextField.setVisible(true);
        add(usernameOfEmployeeTextField);

        passwordOfEmployeeLabel = new JLabel("Parola :");
        passwordOfEmployeeLabel.setBounds(10, 380, 200, 40);
        passwordOfEmployeeLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        passwordOfEmployeeLabel.setForeground(Color.BLACK);
        passwordOfEmployeeLabel.setVisible(true);
        add(passwordOfEmployeeLabel);

        passwordOfEmployeeTextField = new JTextField();
        passwordOfEmployeeTextField.setToolTipText("Introduceti parola noului angajat!");
        passwordOfEmployeeTextField.setBounds(250, 380, 300, 30);
        passwordOfEmployeeTextField.setOpaque(true);
        passwordOfEmployeeTextField.setVisible(true);
        add(passwordOfEmployeeTextField);

        createEmployeeButton = new JButton("Deschide cont angajat");
        createEmployeeButton.setBounds(300, 420, 250, 30);
        createEmployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        createEmployeeButton.setVisible(true);
        add(createEmployeeButton);

        logoutButton = new JButton("Delogheaza-te");
        logoutButton.setBounds(650, 600, 200, 30);
        logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        logoutButton.setVisible(true);
        add(logoutButton);

        repaint();
    }

    public void init(LoginForm loginForm) {

        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginForm.setVisible(true);
                loginForm.repaint();
                loginForm.cleanUsernameAndPassword();
            }
        });

        readShowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                writeInCombobox();
            }
        });

        deleteShowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Event event = (Event) comboBoxShowsList.getSelectedItem();
                boolean isAdd = eventService.deleteEvent(event);
                if (isAdd == true) {
                    JOptionPane.showMessageDialog(new JFrame(), "Spectacol sters cu succes!", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Spectacolul nu a putut fi sters!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }
                comboBoxShowsList.removeAllItems();
                writeInCombobox();
            }
        });

        selectShowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Event uploadEvent;
                uploadEvent = (Event) comboBoxShowsList.getSelectedItem();
                titleShowTextField.setText(uploadEvent.getName());
                regieShowTextField.setText(uploadEvent.getRegie());
                distributieShowTextField.setText(uploadEvent.getDistributie());
                dataPremieraShowTextField.setText(uploadEvent.getDataPremierei());
                numberOfTiketsTextField.setText(uploadEvent.getNumberOfTockets() + "");
            }
        });

        updateShowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Event uploadEvent = new Event();
                uploadEvent.setName(titleShowTextField.getText());
                uploadEvent.setRegie(regieShowTextField.getText());
                uploadEvent.setDistributie(distributieShowTextField.getText());
                String dateString = dataPremieraShowTextField.getText();
                uploadEvent.setDataPremierei(dateString);

                uploadEvent.setNumberOfTockets(Integer.valueOf(numberOfTiketsTextField.getText()));

                boolean isUpdate = userService.uploadatEvent(uploadEvent);
                if (isUpdate == true) {
                    JOptionPane.showMessageDialog(new JFrame(), "Spectacol updatat cu succes!", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Spectacolul nu a putut fi updatat!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        createShowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Event event = new Event();
                event.setName(titleShowTextField.getText());
                event.setRegie(regieShowTextField.getText());
                event.setDistributie(distributieShowTextField.getText());
                event.setDataPremierei(dataPremieraShowTextField.getText());
                event.setNumberOfTockets(Integer.valueOf(numberOfTiketsTextField.getText()));
                boolean isAdd = eventService.addEvent(event);
                if (isAdd == true) {
                    JOptionPane.showMessageDialog(new JFrame(), "Spectacol a fost adaugat cu succes!", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Spectacolul nu a putut fi adaugat!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        createEmployeeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(getNewEmployeeName());
                user.setPassword(getNewEmployeePassword());
                user.setUsername(getNewEmployeeUsername());
                boolean isAdd = userService.addUser(user);
                if (isAdd == true) {
                    JOptionPane.showMessageDialog(new JFrame(), "Noul angajat a fost adaugat cu succes!", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Noul angajat nu a putut fi adaugat!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    protected void writeInCombobox() {
        comboBoxShowsList.removeAllItems();
        ArrayList<Event> events = eventService.getEvents();
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            comboBoxShowsList.addItem(event);
        }

    }

    protected ArrayList<Integer> convertStringToDate(String dataPremiereiString) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int i = 0;
        int numarDeCratime = 0;
        for (int j = 0; j < dataPremiereiString.length() - 1; j++) {
            if (dataPremiereiString.substring(j, j + 1).equals("-") && numarDeCratime < 2) {
                list.add(Integer.valueOf(dataPremiereiString.substring(i, j)));
                numarDeCratime++;
                i = j + 1;
            }
        }
        list.add(Integer.valueOf(dataPremiereiString.substring(i, dataPremiereiString.length())));
        return list;
    }

    public void setVisibleButtonsAdmin(boolean isVisible) {
        showsListLabel.setVisible(isVisible);
        comboBoxShowsList.setVisible(isVisible);

    }

    public String getTitleShow() {
        return titleShowTextField.getText();
    }

    public String getRegieShow() {
        return regieShowTextField.getText();
    }

    public String getDistributieShow() {
        return distributieShowTextField.getText();
    }

    public String getDataPremiereiShow() {
        return dataPremieraShowTextField.getText();
    }

    public String getNumberOfTiketsShow() {
        return numberOfTiketsTextField.getText();
    }

    public String getNewEmployeeName() {
        return nameOfEmployeeTextField.getText();
    }

    public String getNewEmployeeUsername() {
        return usernameOfEmployeeTextField.getText();
    }

    public String getNewEmployeePassword() {
        return passwordOfEmployeeTextField.getText();
    }
}
