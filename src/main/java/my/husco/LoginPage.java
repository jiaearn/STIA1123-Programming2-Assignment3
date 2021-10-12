package my.husco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is for user login.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class LoginPage extends JFrame implements ActionListener, FocusListener {

    ImageIcon icon;
    JButton login, cancel;
    JLabel label1, label2;
    JTextField userName;
    JPasswordField password;
    JCheckBox checkBox;

    /**
     * This is constructor for class LoginPage.
     */
    public LoginPage() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        label1 = new JLabel();
        label1.setText("Username : ");
        label1.setBounds(25, 10, 150, 50);
        add(label1);

        userName = new JTextField();
        userName.setText("Name & Pass: 123");
        userName.setBounds(95, 25, 150, 25);
        userName.setForeground(Color.LIGHT_GRAY);
        userName.addFocusListener(this);
        add(userName);

        label2 = new JLabel();
        label2.setBounds(25, 40, 150, 50);
        label2.setText("Password  : ");
        add(label2);

        password = new JPasswordField();
        password.setBounds(95, 55, 150, 25);
        add(password);

        checkBox = new JCheckBox("Show Password");
        checkBox.setBounds(25, 80, 150, 20);
        checkBox.addActionListener(this);
        add(checkBox);

        login = new JButton("LOGIN");
        login.setBounds(25, 110, 100, 30);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("CANCEL");
        cancel.setBounds(145, 110, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        setResizable(false);
        setLayout(null);
        setTitle("Sign In");
        setSize(290, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Pos Laju Malaysia", dialogButton);
                if (dialogResult == 0) {
                    System.exit(0);
                }
            }
        });
        setVisible(true);
        requestFocusInWindow();
    }

    /**
     * This method is for button function.
     *
     * @param e Button function of login and cancel in LoginPage.
     */
    public void actionPerformed(ActionEvent e) {

        String name = userName.getText();
        String passwordText = String.valueOf(password.getPassword());

        if (e.getSource() == login) {
            if (name.equals("123") && passwordText.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login Successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                new MenuPage();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == cancel) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to Exit?", "Pos Laju Malaysia", dialogButton);
            if (dialogResult == 0) {
                System.exit(0);
            }
        }

        if (checkBox.isSelected()) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }

    }

    /**
     * This method is for creating a nimbus design for the main class.
     *
     * @param args The String[] args parameter is an array of Strings passed as parameters when you are running your application through command line in the OS.
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }

    /**
     * This is method is for calling when a component gains the keyboard focus, the textfield will set text null.
     *
     * @param e Invoked when a component gains the keyboard focus.
     */
    @Override
    public void focusGained(FocusEvent e) {
        String hint = userName.getText();
        if (hint.equals("Name & Pass: 123")) {
            userName.setText("");
            userName.setForeground(Color.BLACK);
        }
    }

    /**
     * This is method is for calling when a component loses the keyboard focus, the textfield will set text name and password.
     *
     * @param e Invoked when a component loses the keyboard focus.
     */
    @Override
    public void focusLost(FocusEvent e) {
        String hint = userName.getText();
        if (hint.equals("")) {
            userName.setText("Name & Pass: 123");
            userName.setForeground(Color.GRAY);
        }
    }

}
