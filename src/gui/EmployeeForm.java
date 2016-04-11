package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import models.Ticket;
import bl.EventService;
import bl.TicketService;

public class EmployeeForm extends JFrame {

    private JLabel rowsShowLabel;
    private JTextField rowsShowTextField;
    private JLabel showChooseLabel;
    private JComboBox<Event> comboBoxShowsList;
    private JLabel numberShowLabel;
    private JTextField numberShowTextField;
    private JButton inregTicketButton;
    private JButton exportTicketButton;
    private JButton logoutButton;

    private TicketService ticketService = new TicketService();
    private EventService eventService = new EventService();

    public EmployeeForm() {
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

        showChooseLabel = new JLabel("Alege spectacolul:");
        showChooseLabel.setBounds(5, 5, 200, 40);
        showChooseLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        showChooseLabel.setForeground(Color.BLACK);
        showChooseLabel.setVisible(true);
        add(showChooseLabel);

        comboBoxShowsList = new JComboBox<Event>();
        comboBoxShowsList.setBounds(170, 5, 700, 40);
        comboBoxShowsList.setVisible(true);
        comboBoxShowsList.setAutoscrolls(true);
        comboBoxShowsList.repaint();
        add(comboBoxShowsList);

        rowsShowLabel = new JLabel("Alege randul:");
        rowsShowLabel.setBounds(10, 90, 200, 40);
        rowsShowLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        rowsShowLabel.setForeground(Color.BLACK);
        rowsShowLabel.setVisible(true);
        add(rowsShowLabel);

        rowsShowTextField = new JTextField();
        rowsShowTextField.setToolTipText("Introduceti randul ales de client!");
        rowsShowTextField.setBounds(250, 90, 300, 30);
        rowsShowTextField.setOpaque(true);
        rowsShowTextField.setVisible(true);
        add(rowsShowTextField);

        numberShowLabel = new JLabel("Alege numarul:");
        numberShowLabel.setBounds(10, 130, 200, 40);
        numberShowLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
        numberShowLabel.setForeground(Color.BLACK);
        numberShowLabel.setVisible(true);
        add(numberShowLabel);

        numberShowTextField = new JTextField();
        numberShowTextField.setToolTipText("Introduceti numarul ales de client!");
        numberShowTextField.setBounds(250, 130, 300, 30);
        numberShowTextField.setOpaque(true);
        numberShowTextField.setVisible(true);
        add(numberShowTextField);

        inregTicketButton = new JButton("Cumpara bilet");
        inregTicketButton.setBounds(400, 170, 150, 30);
        inregTicketButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inregTicketButton.setVisible(true);
        add(inregTicketButton);

        exportTicketButton = new JButton("Exporta bilete vandute (csv)");
        exportTicketButton.setBounds(250, 210, 300, 30);
        exportTicketButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        exportTicketButton.setVisible(true);
        add(exportTicketButton);

        logoutButton = new JButton("Delogheaza-te");
        logoutButton.setBounds(650, 600, 200, 30);
        logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        logoutButton.setVisible(true);
        add(logoutButton);

        repaint();
    }

    public void init(LoginForm loginForm) {
        writeInComboBox();

        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxShowsList.removeAllItems();
                setVisible(false);
                loginForm.setVisible(true);
                loginForm.repaint();

            }
        });

        inregTicketButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket ticket = new Ticket();
                ticket.setNumber(Integer.valueOf(numberShowTextField.getText()));
                ticket.setRow(Integer.valueOf(rowsShowTextField.getText()));
                Event eventSelected = (Event) comboBoxShowsList.getSelectedItem();
                if (eventSelected.getNumberOfTockets() > 0) {
                    ticket.setNameShow(eventSelected.getName());
                    boolean isAdd = ticketService.addTicket(ticket);
                    if (isAdd == true) {
                        int numberOfTockets = eventSelected.getNumberOfTockets();
                        eventSelected.setNumberOfTockets(numberOfTockets - 1);
                        eventService.updateEvent(eventSelected);
                        comboBoxShowsList.removeAllItems();
                        writeInComboBox();
                        JOptionPane.showMessageDialog(new JFrame(), "Bilet cumparat cu succes", "Succes",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Biletul nu se poate cumpara! Incercati alt rand si numar", "Eroare",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Nu mai exista bilete pentru acest spectacol!",
                            "Eroare", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        exportTicketButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ticketService.exportCSV();
                    JOptionPane.showMessageDialog(new JFrame(), "Fisierul a fost creat cu succes!", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Fisierul nu s-a putut crea!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
    }

    private void writeInComboBox() {
        ArrayList<Event> events = eventService.getEvents();
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            comboBoxShowsList.addItem(event);
        }

    }
}
