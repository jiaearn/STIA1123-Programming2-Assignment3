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
 * This class is for creating GUI of Pos Ekspres.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PosEkspresPage extends JFrame implements ActionListener {

    ImageIcon icon;
    JRadioButton lG, lE, lD, lK;
    JLabel l1, l2;
    ButtonGroup bg = new ButtonGroup();
    JButton save, mainMenu, reset, print;
    JTable table;
    JScrollPane sp;
    JTextField quantity;

    /**
     * This is constructor for PosEkspresPage.
     */
    public PosEkspresPage() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        l1 = new JLabel();
        l1.setBounds(20, 170, 160, 50);
        l1.setText("Please select a type : ");
        add(l1);

        lG = new JRadioButton("LG");
        lG.setBounds(160, 185, 150, 20);
        bg.add(lG);
        add(lG);

        lE = new JRadioButton("LE (C5)");
        lE.setBounds(160, 205, 150, 20);
        bg.add(lE);
        add(lE);

        lD = new JRadioButton("LD (B4)");
        lD.setBounds(160, 225, 150, 20);
        bg.add(lD);
        add(lD);

        lK = new JRadioButton("LK");
        lK.setBounds(160, 245, 150, 20);
        bg.add(lK);
        add(lK);

        l2 = new JLabel();
        l2.setBounds(20, 280, 160, 50);
        l2.setText("Please enter quantity : ");
        add(l2);

        quantity = new JTextField();
        quantity.setBounds(160, 295, 150, 25);
        add(quantity);

        save = new JButton("Save");
        save.setBounds(260, 345, 110, 30);
        save.addActionListener(this);
        add(save);

        reset = new JButton("Reset");
        reset.setBounds(95, 345, 110, 30);
        reset.addActionListener(this);
        add(reset);

        print = new JButton("Print");
        print.setBounds(650, 300, 125, 30);
        print.addActionListener(this);
        add(print);

        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(650, 345, 125, 30);
        mainMenu.addActionListener(this);
        add(mainMenu);

        String[] standardType = {"Type", "LG", "LE(C5)", "LD(B4)", "LK"};
        String[][] data =
                {
                        {"Size", "220mm x 110mm", "229mm x 162mm", "353mm x 250mm", "340mm x 250mm"},
                        {"Max Weight", "100gm", "250gm", "500gm", "1000gm"},
                        {"Max Thickness", "3mm", "5mm", "10mm", "25mm"},
                        {"Price", "RM 3.18", "RM 3.71", "RM 4.77", "RM 7.42"}
                };

        table = new JTable(data, standardType);

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
        sp.setBounds(10, 10, 900, 156);
        add(sp);

        setTitle("Pos Ekspres");
        setResizable(false);
        setLayout(null);
        setSize(930, 450);
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
                if (bg.getSelection() == null || quantity.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Some questions have not been filled.", "Pos Ekspres", JOptionPane.ERROR_MESSAGE);
                } else {
                    String peType = "", peSize = "";
                    double pePrice = 0;
                    int peWeight = 0;
                    int peThickness = 0;
                    int peQuantity = Integer.parseInt(quantity.getText());

                    if (peQuantity > 0) {
                        if (lG.isSelected())
                            peType = "LG";
                        else if (lE.isSelected())
                            peType = "LE(C5)";
                        else if (lD.isSelected())
                            peType = "LD(B4)";
                        else if (lK.isSelected())
                            peType = "LK";
                        PosEkspres posEkspres = new PosEkspres(peType, peSize, peWeight, peThickness, peQuantity, pePrice);
                        JOptionPane.showMessageDialog(this, "Data has been saved.\n" + posEkspres.toString(), "Pos Ekspres", JOptionPane.INFORMATION_MESSAGE);
                        bg.clearSelection();
                        quantity.setText(null);
                    } else
                        JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Pos Ekspres", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter an integer.", "Pos Ekspres", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == mainMenu) {
            new MenuPage();
            this.dispose();
        }
        if (e.getSource() == print) {
            new PeReceipt();
        }
        if (e.getSource() == reset) {
            bg.clearSelection();
            quantity.setText(null);
        }

    }

}
