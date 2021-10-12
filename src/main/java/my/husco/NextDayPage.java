package my.husco;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class is for creating GUI of Next-Day Delivery.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class NextDayPage extends JFrame implements ActionListener {

    ImageIcon icon;
    JRadioButton document, parcel, z1, z2, z3, z4, z5;
    JLabel l1, l2, l3, l4;
    ButtonGroup bg1, bg2;
    JButton save, mainMenu, reset, print;
    JTable table;
    JScrollPane sp;
    JTextField weight, quantity;

    /**
     * This is constructor for NextDayPage.
     */
    public NextDayPage() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();

        l1 = new JLabel();
        l1.setBounds(20, 260, 180, 50);
        l1.setText("Please select a type : ");
        add(l1);

        document = new JRadioButton("Document");
        document.setBounds(180, 275, 150, 20);
        bg1.add(document);
        add(document);

        parcel = new JRadioButton("Parcel");
        parcel.setBounds(180, 295, 150, 20);
        bg1.add(parcel);
        add(parcel);

        l2 = new JLabel();
        l2.setBounds(20, 330, 180, 50);
        l2.setText("Please select a zone : ");
        add(l2);

        z1 = new JRadioButton("Zone 1: City");
        z1.setBounds(180, 345, 400, 20);
        bg2.add(z1);
        add(z1);

        z2 = new JRadioButton("Zone 2: In Peninsular Malaysia, Sarawak and Sabah");
        z2.setBounds(180, 365, 400, 20);
        bg2.add(z2);
        add(z2);

        z3 = new JRadioButton("Zone 3: Between Sabah and Sarawak");
        z3.setBounds(180, 385, 400, 20);
        bg2.add(z3);
        add(z3);

        z4 = new JRadioButton("Zone 4: Between Peninsular Malaysia and Sarawak");
        z4.setBounds(180, 405, 400, 20);
        bg2.add(z4);
        add(z4);

        z5 = new JRadioButton("Zone 5: Between Peninsular Malaysia and Sabah");
        z5.setBounds(180, 425, 400, 20);
        bg2.add(z5);
        add(z5);

        l3 = new JLabel();
        l3.setBounds(20, 460, 180, 50);
        l3.setText("Please enter weight (gm) : ");
        add(l3);

        weight = new JTextField();
        weight.setBounds(180, 475, 150, 25);
        add(weight);

        l4 = new JLabel();
        l4.setBounds(20, 510, 180, 50);
        l4.setText("Please enter quantity : ");
        add(l4);

        quantity = new JTextField();
        quantity.setBounds(180, 525, 150, 25);
        add(quantity);

        reset = new JButton("Reset");
        reset.setBounds(95, 575, 110, 30);
        reset.addActionListener(this);
        add(reset);

        save = new JButton("Save");
        save.setBounds(260, 575, 110, 30);
        save.addActionListener(this);
        add(save);

        print = new JButton("Print");
        print.setBounds(650, 530, 125, 30);
        print.addActionListener(this);
        add(print);

        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(650, 575, 125, 30);
        mainMenu.addActionListener(this);
        add(mainMenu);

        String[] packageType = {"", "Document (below 2kg)", "", "Parcel (above 2kg)", ""};
        String[][] data =
                {
                        {"Zone", "First 500gm", "Subsequent 250gm", "2.001-2.5kg", "Subsequent 500gm"},
                        {"Zone 1", "4.90", "0.80", "10.50", "0.50"},
                        {"Zone 2", "5.40", "1.00", "16.00", "2.00"},
                        {"Zone 3", "6.90", "1.50", "21.00", "3.00"},
                        {"Zone 4", "7.40", "1.50", "26.00", "3.50"},
                        {"Zone 5", "7.90", "2.00", "31.00", "4.00"},
                        {"", "", "", "", "All charges in RM"},
                };

        table = new JTable(data, packageType);

        JTableHeader header = table.getTableHeader();
        int headerHeight = 30;
        header.setPreferredSize(new Dimension(100, headerHeight));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(30);
        table.setEnabled(false);
        sp = new JScrollPane(table);
        sp.setBounds(10, 10, 900, 246);
        add(sp);

        setTitle("Next-Day Delivery");
        setResizable(false);
        setLayout(null);
        setSize(930, 680);
        setLocationRelativeTo(null);
        setVisible(true);
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
    }

    /**
     * This method is for button function.
     *
     * @param e Button function of Save, Print, Reset and Main Menu.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == save) {
            try {
                if (bg1.getSelection() == null || bg2.getSelection() == null || weight.getText().isEmpty() || quantity.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Some questions have not been filled.", "Next-Day Delivery", JOptionPane.ERROR_MESSAGE);
                } else {
                    String ndType = "";
                    int ndZone = 0;
                    double ndPrice = 0;
                    int ndWeight = Integer.parseInt(weight.getText());
                    int ndQuantity = Integer.parseInt(quantity.getText());

                    if ((document.isSelected() && ndWeight > 0 && ndWeight <= 2000) || (parcel.isSelected() && ndWeight > 2000)) {
                        if (ndQuantity > 0) {
                            if (document.isSelected())
                                ndType = "Document";
                            else if (parcel.isSelected())
                                ndType = "Parcel";
                            if (z1.isSelected())
                                ndZone = 1;
                            else if (z2.isSelected())
                                ndZone = 2;
                            else if (z3.isSelected())
                                ndZone = 3;
                            else if (z4.isSelected())
                                ndZone = 4;
                            else if (z5.isSelected())
                                ndZone = 5;

                            NextDayDelivery nD = new NextDayDelivery(ndType, ndZone, ndWeight, ndQuantity, ndPrice);
                            JOptionPane.showMessageDialog(this, "Data has been saved.\n" + nD.toString(), "Next-Day Delivery", JOptionPane.INFORMATION_MESSAGE);
                            bg1.clearSelection();
                            bg2.clearSelection();
                            weight.setText(null);
                            quantity.setText(null);
                        } else
                            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Next-Day Delivery", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(this, "Please enter a valid weight", "Next-Day Delivery", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter an integer.", "Next-Day Delivery", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == print) {
            new NextDayReceipt();
        }
        if (e.getSource() == mainMenu) {
            new NextDayReceipt().dispose();
            new MenuPage();
            this.dispose();
        }
        if (e.getSource() == reset) {
            bg1.clearSelection();
            bg2.clearSelection();
            weight.setText(null);
            quantity.setText(null);
        }
    }

}
