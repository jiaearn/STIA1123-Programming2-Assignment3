package my.husco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is for creating GUI for Menu.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class MenuPage extends JFrame implements ActionListener {

    ImageIcon icon;
    JLabel l1;
    JButton nextDay, sameDay, prepaidBox, posEkspres, printR;
    JMenu menu, submenu;
    JMenuBar menuBar;
    JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6;
    JCheckBox checkBox;

    /**
     * This is constructor for MenuPage.
     */
    public MenuPage() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        l1 = new JLabel();
        l1.setBounds(30, 25, 450, 50);
        l1.setText("Welcome to Pos Laju Malaysia");
        l1.setFont(new Font(l1.getFont().getName(), l1.getFont().getStyle(), 15));
        add(l1);

        menuBar = new JMenuBar();

        checkBox = new JCheckBox("Hide/Unhide Menu Bar");
        checkBox.setBounds(5, 5, 200, 20);
        checkBox.addActionListener(this);
        add(checkBox);

        menu = new JMenu("Menu");
        menuBar.add(menu);

        menuItem1 = new JMenuItem("Log Out");
        menuItem1.addActionListener(this);
        menu.add(menuItem1);

        menuItem2 = new JMenuItem("Exit");
        menuItem2.addActionListener(this);
        menu.add(menuItem2);

        menu = new JMenu("Help");
        menuBar.add(menu);

        submenu = new JMenu("Help?");
        menuItem3 = new JMenuItem("How to run this program?");
        menuItem3.addActionListener(this);
        submenu.add(menuItem3);
        menu.add(submenu);

        menuItem4 = new JMenuItem("Check for Updates");
        menuItem4.addActionListener(this);
        menu.add(menuItem4);

        menuItem5 = new JMenuItem("Contact Us");
        menuItem5.addActionListener(this);
        menu.add(menuItem5);

        menuItem6 = new JMenuItem("About Us");
        menuItem6.addActionListener(this);
        menu.add(menuItem6);

        nextDay = new JButton("Next-Day Delivery");
        nextDay.setBounds(55, 85, 180, 40);
        add(nextDay);
        nextDay.addActionListener(this);

        sameDay = new JButton("Same-Day Delivery");
        sameDay.setBounds(55, 135, 180, 40);
        add(sameDay);
        sameDay.addActionListener(this);

        prepaidBox = new JButton("Prepaid Box & Envelope");
        prepaidBox.setBounds(55, 185, 180, 40);
        add(prepaidBox);
        prepaidBox.addActionListener(this);

        posEkspres = new JButton("Pos Ekspres");
        posEkspres.setBounds(55, 235, 180, 40);
        add(posEkspres);
        posEkspres.addActionListener(this);

        printR = new JButton("Receipt");
        printR.setBounds(55, 285, 180, 40);
        add(printR);
        printR.addActionListener(this);

        readData1();
        readData2();
        readData3();
        readData4();
        setResizable(false);
        setTitle("Pos Laju Malaysia");
        setLayout(null);
        setLocation(650, 250); //frame location
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
        setJMenuBar(menuBar);
        setSize(300, 410); //size of frame

    }

    /**
     * This method is for button function.
     *
     * @param e Button function in MenuPage.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        JTextArea jta = new JTextArea(5, 15);
        jta.setEditable(false);

        menuBar.setVisible(!checkBox.isSelected());

        if (e.getSource() == nextDay) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure want to enter Next-Day Delivery?", "Next-Day Delivery", dialogButton);
            if (dialogResult == 0) {
                new NextDayPage();
                this.dispose();
            }
        }

        if (e.getSource() == sameDay) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure want to enter Same-Day Delivery?", "Same-Day Delivery", dialogButton);
            if (dialogResult == 0) {
                new SameDayPage();
                this.dispose();
            }
        }

        if (e.getSource() == prepaidBox) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure want to enter Prepaid Box & Envelope?", "Prepaid Box & Envelope", dialogButton);
            if (dialogResult == 0) {
                new PrepaidBoxEnvelopePage();
                this.dispose();
            }
        }

        if (e.getSource() == posEkspres) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure want to enter Pos Ekspres?", "Pos Ekspres", dialogButton);
            if (dialogResult == 0) {
                new PosEkspresPage();
                this.dispose();
            }
        }

        if (e.getSource() == printR) {
            if (NextDayDelivery.billInfo.isEmpty() && SameDayDelivery.billInfo.isEmpty() && PrepaidBoxEnvelope.billInfo.isEmpty() && PosEkspres.billInfo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "List is empty", "Pos Laju Malaysia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                new MenuReceipt();
            }
        }

        if (e.getSource() == menuItem1) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to Log Out?", "Pos Laju Malaysia", dialogButton);
            if (dialogResult == 0) {
                new LoginPage();
                this.dispose();
            }
        }

        if (e.getSource() == menuItem2) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to Exit?", "Pos Laju Malaysia", dialogButton);
            if (dialogResult == 0) {
                System.exit(0);
            }
        }

        if (e.getSource() == menuItem3) {
            String msg =
                    "Step : " +
                            "\n1. Choose your service by click the button that you want to proceed." +
                            "\n2. Fulfil the form that have been provided for the service." +
                            "\n3. When you key in wrongly, you can click \"Reset\" to clear the data." +
                            "\n4. Click \"Save\". Then the data will be saved into system and system will calculate the total price for each service." +
                            "\n5. Click \"Print\" to show the receipt of service and you can also click \"Delete\" to delete data." +
                            "\n6. Back to \"Main Menu\". Choose another service if you want otherwise you can click the button \"Receipt\" to print the receipt." +
                            "\n7. In the receipt, you can click \"Delete\" to delete data or Back to \"Main Menu\"." +
                            "\n8. After finish, you can log out your account at the menu bar." +
                            "\nThank you for choosing our system.";
            jta.setText(msg);
            JOptionPane.showMessageDialog(null, jta, "How to run this program?", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == menuItem4) {
            JOptionPane.showMessageDialog(this, "You are on the latest version.", "You're up to date!", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == menuItem5) {
            JOptionPane.showMessageDialog(this, "Call Our Service Centre. \n 017-*******.", "Contact Us", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == menuItem6) {
            String msg = "This system is designed by team Husco. \n\n Our Team Members: \n 1. Tan Jia Earn \n 2. Wong Fang Man \n\n";
            jta.setText(msg);
            JOptionPane.showMessageDialog(null, jta, "About Us", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * This method is for reading the data of Next-Day Delivery from file.
     */
    public void readData1() {

        try {
            boolean create;
            File newFile = new File("Next-Day Delivery.txt");
            create = newFile.createNewFile();

            if (create) {
                System.out.println("\nCreate Next-Day Delivery.txt");
            }

            NextDayDelivery.billInfo.clear();
            FileReader nextDay = new FileReader("Next-Day Delivery.txt");

            StringBuffer sb = new StringBuffer();
            while (nextDay.ready()) {
                char c = (char) nextDay.read();
                if (c == '\n') {
                    String[] billArr = sb.toString().split(",");
                    NextDayDelivery.billInfo.add(new BillInfo(billArr[0], Integer.parseInt(billArr[1]), Integer.parseInt(billArr[2]), Integer.parseInt(billArr[3]), Double.parseDouble(billArr[4])));
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is for reading the data of Same-Day Delivery from file.
     */
    public void readData2() {

        try {
            boolean create;
            File newFile = new File("Same-Day Delivery.txt");
            create = newFile.createNewFile();

            if (create) {
                System.out.println("\nCreate Same-Day Delivery.txt");
            }

            SameDayDelivery.billInfo.clear();
            FileReader sameDay = new FileReader("Same-Day Delivery.txt");

            StringBuffer sb = new StringBuffer();
            while (sameDay.ready()) {
                char c = (char) sameDay.read();
                if (c == '\n') {
                    String[] billArr = sb.toString().split(",");
                    SameDayDelivery.billInfo.add(new BillInfo(billArr[0], Integer.parseInt(billArr[1]), Integer.parseInt(billArr[2]), Double.parseDouble(billArr[3])));
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is for reading the data of Prepaid Box and Envelope from file.
     */
    public void readData3() {

        try {
            boolean create;
            File newFile = new File("Prepaid Box & Envelope.txt");
            create = newFile.createNewFile();

            if (create) {
                System.out.println("\nCreate Prepaid Box & Envelope.txt");
            }

            PrepaidBoxEnvelope.billInfo.clear();
            FileReader pbe = new FileReader("Prepaid Box & Envelope.txt");

            StringBuffer sb = new StringBuffer();
            while (pbe.ready()) {
                char c = (char) pbe.read();
                if (c == '\n') {
                    String[] billArr = sb.toString().split(",");
                    PrepaidBoxEnvelope.billInfo.add(new BillInfo(billArr[0], billArr[1], Integer.parseInt(billArr[2]), Integer.parseInt(billArr[3]), Double.parseDouble(billArr[4])));
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is for reading the data of Pos Ekspres from file.
     */
    public void readData4() {

        try {
            boolean create;
            File newFile = new File("Pos Ekspres.txt");
            create = newFile.createNewFile();

            if (create) {
                System.out.println("\nCreate Pos Ekspres.txt");
            }

            PosEkspres.billInfo.clear();
            FileReader pe = new FileReader("Pos Ekspres.txt");

            StringBuffer sb = new StringBuffer();
            while (pe.ready()) {
                char c = (char) pe.read();
                if (c == '\n') {
                    String[] billArr = sb.toString().split(",");
                    PosEkspres.billInfo.add(new BillInfo(billArr[0], billArr[1], Integer.parseInt(billArr[2]), Integer.parseInt(billArr[3]), Integer.parseInt(billArr[4]), Double.parseDouble(billArr[5])));
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
