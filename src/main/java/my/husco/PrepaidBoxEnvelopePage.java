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
 * This class is for creating GUI of Prepaid Box and Envelope.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PrepaidBoxEnvelopePage extends JFrame implements ActionListener {

    ImageIcon icon;
    JRadioButton eS, eL, pBS, pBM, pBL;
    JLabel l1, l2;
    ButtonGroup bg = new ButtonGroup();
    JButton save, mainMenu, reset, print;
    JTable table;
    JScrollPane sp;
    JTextField quantity;

    /**
     * This is constructor for PrepaidBoxEnvelopePage.
     */
    public PrepaidBoxEnvelopePage() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        l1 = new JLabel();
        l1.setBounds(20, 140, 160, 50);
        l1.setText("Please select a type : ");
        add(l1);

        eS = new JRadioButton("Envelope S (ES)");
        eS.setBounds(160, 155, 150, 20);
        bg.add(eS);
        add(eS);

        eL = new JRadioButton("Envelope L (EL)");
        eL.setBounds(160, 175, 150, 20);
        bg.add(eL);
        add(eL);

        pBS = new JRadioButton("Prepaid Box S (PBS)");
        pBS.setBounds(160, 195, 150, 20);
        bg.add(pBS);
        add(pBS);

        pBM = new JRadioButton("Prepaid Box M (PBM)");
        pBM.setBounds(160, 215, 150, 20);
        bg.add(pBM);
        add(pBM);

        pBL = new JRadioButton("Prepaid Box L (PBL)");
        pBL.setBounds(160, 235, 150, 20);
        bg.add(pBL);
        add(pBL);

        l2 = new JLabel();
        l2.setBounds(20, 270, 160, 50);
        l2.setText("Please enter quantity : ");
        add(l2);

        quantity = new JTextField();
        quantity.setBounds(160, 285, 150, 25);
        add(quantity);

        save = new JButton("Save");
        save.setBounds(260, 335, 110, 30);
        save.addActionListener(this);
        add(save);

        reset = new JButton("Reset");
        reset.setBounds(95, 335, 110, 30);
        reset.addActionListener(this);
        add(reset);

        print = new JButton("Print");
        print.setBounds(650, 290, 125, 30);
        print.addActionListener(this);
        add(print);

        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(650, 335, 125, 30);
        mainMenu.addActionListener(this);
        add(mainMenu);

        String[] standardType = {"Type", "Envelope S (ES)", "Envelope L (EL)", "Prepaid Box S (PBS)", "Prepaid Box M (PBM)", "Prepaid Box L (PBL)"};
        String[][] data =
                {
                        {"Size", "280mm x 200mm", "380mm x 320mm", "380mm x 250mm x 80mm", "340mm x 250mm x 150mm", "380mm x 320mm x 200mm"},
                        {"Max Weight", "500g", "1kg", "2kg", "5kg", "10kg"},
                        {"Price", "RM 7.31", "RM 10.49", "RM 13.78", "RM 21.20", "RM 31.80"}
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
        sp.setBounds(10, 10, 920, 126);
        add(sp);

        setTitle("Prepaid Box & Envelope");
        setResizable(false);
        setLayout(null);
        setSize(950, 440);
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
                    JOptionPane.showMessageDialog(this, "Some questions have not been filled.", "Prepaid Box & Envelope", JOptionPane.ERROR_MESSAGE);
                } else {
                    String pbeType = "", pbeSize = "";
                    double pbePrice = 0;
                    int pbeWeight = 0;
                    int pbeQuantity = Integer.parseInt(quantity.getText());

                    if (pbeQuantity > 0) {

                        if (eS.isSelected())
                            pbeType = "Envelope S (ES)";
                        else if (eL.isSelected())
                            pbeType = "Envelope L (EL)";
                        else if (pBS.isSelected())
                            pbeType = "Prepaid Box S (PBS)";
                        else if (pBM.isSelected())
                            pbeType = "Prepaid Box M (PBM)";
                        else if (pBL.isSelected())
                            pbeType = "Prepaid Box L (PBL)";
                        PrepaidBoxEnvelope prepaidBoxEnvelope = new PrepaidBoxEnvelope(pbeType, pbeSize, pbeWeight, pbeQuantity, pbePrice);
                        JOptionPane.showMessageDialog(this, "Data has been saved.\n" + prepaidBoxEnvelope.toString(), "Prepaid Box & Envelope", JOptionPane.INFORMATION_MESSAGE);
                        bg.clearSelection();
                        quantity.setText(null);
                    } else
                        JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Prepaid Box & Envelope", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter an integer.", "Prepaid Box & Envelope", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == print) {
            new PbeReceipt();
        }
        if (e.getSource() == mainMenu) {
            new MenuPage();
            this.dispose();
        }
        if (e.getSource() == reset) {
            bg.clearSelection();
            quantity.setText(null);
        }

    }

}
