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
 * This class is for creating GUI of Same-Day Delivery.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class SameDayPage extends JFrame implements ActionListener {

    ImageIcon icon;
    JRadioButton localT, crossT;
    JLabel l1, l2, l3;
    ButtonGroup bg = new ButtonGroup();
    JButton save, mainMenu, reset, print;
    JTable table;
    JScrollPane sp;
    JTextField weight, quantity;

    /**
     * This is constructor for SameDayPage.
     */
    public SameDayPage() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        l1 = new JLabel();
        l1.setBounds(20, 200, 180, 50);
        l1.setText("Please select a town : ");
        add(l1);

        localT = new JRadioButton("Local Town");
        localT.setBounds(180, 215, 150, 20);
        bg.add(localT);
        add(localT);

        crossT = new JRadioButton("Cross Town");
        crossT.setBounds(180, 235, 150, 20);
        bg.add(crossT);
        add(crossT);

        l2 = new JLabel();
        l2.setBounds(20, 270, 180, 50);
        l2.setText("Please enter weight (gm) : ");
        add(l2);

        weight = new JTextField();
        weight.setBounds(180, 285, 150, 25);
        add(weight);

        l3 = new JLabel();
        l3.setBounds(20, 320, 180, 50);
        l3.setText("Please enter quantity : ");
        add(l3);

        quantity = new JTextField();
        quantity.setBounds(180, 335, 150, 25);
        add(quantity);

        save = new JButton("Save");
        save.setBounds(260, 385, 110, 30);
        save.addActionListener(this);
        add(save);

        print = new JButton("Print");
        print.setBounds(650, 340, 125, 30);
        print.addActionListener(this);
        add(print);

        reset = new JButton("Reset");
        reset.setBounds(95, 385, 110, 30);
        reset.addActionListener(this);
        add(reset);

        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(650, 385, 125, 30);
        mainMenu.addActionListener(this);
        add(mainMenu);

        String[] packageType = {"", "", "Local Town", "", "", "Cross Town", ""};
        String[][] data =
                {
                        {"Weight", "Domestic Charge", "Surcharge", "Total", "Domestic Charge", "Surcharge", "Total"},
                        {"Below 500gm", "4.90", "6.00", "10.90", "5.40", "7.50", "12.90"},
                        {"501gm - 750gm", "5.70", "6.00", "11.70", "6.40", "7.50", "13.90"},
                        {"751gm - 1000gm  ", "6.50", "6.00", "12.50", "7.40", "7.50", "14.90"},
                        {"", "", "", "", "", "", "All charges in RM"},
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
        sp.setBounds(10, 10, 900, 186);
        add(sp);

        setTitle("Same-Day Delivery");
        setResizable(false);
        setLayout(null);
        setSize(930, 490);
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
                if (bg.getSelection() == null || weight.getText().isEmpty() || quantity.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Some questions have not been filled.", "Same-Day Delivery", JOptionPane.ERROR_MESSAGE);
                } else {
                    String sdType = "";
                    double sdPrice = 0;
                    int sdWeight = Integer.parseInt(weight.getText());
                    int sdQuantity = Integer.parseInt(quantity.getText());

                    if (sdWeight > 0 && sdWeight <= 1000) {
                        if (sdQuantity > 0) {
                            if (localT.isSelected())
                                sdType = "Local Town";
                            else if (crossT.isSelected())
                                sdType = "Cross Town";
                            SameDayDelivery sD = new SameDayDelivery(sdType, sdWeight, sdQuantity, sdPrice);
                            JOptionPane.showMessageDialog(this, "Data has been saved.\n" + sD.toString(), "Same-Day Delivery", JOptionPane.INFORMATION_MESSAGE);
                            bg.clearSelection();
                            weight.setText(null);
                            quantity.setText(null);
                        } else
                            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Same-Day Delivery", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(this, "Please enter a valid weight", "Same-Day Delivery", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter an integer.", "Same-Day Delivery", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == print) {
            new SameDayReceipt();
        }
        if (e.getSource() == mainMenu) {
            new MenuPage();
            this.dispose();
        }
        if (e.getSource() == reset) {
            bg.clearSelection();
            weight.setText(null);
            quantity.setText(null);
        }

    }

}
