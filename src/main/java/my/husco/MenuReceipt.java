package my.husco;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is for manipulating the receipt of all services.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class MenuReceipt extends JFrame implements ActionListener {

    static ArrayList<BillInfo> billInfo;
    String[] header1 = new String[]{"Type", "Zone", "Weight", "Quantity", "Price"};
    String[] header2 = new String[]{"Type", "Weight", "Quantity", "Price"};
    String[] header3 = new String[]{"Type", "Size", "Max Weight (gm)", "Quantity", "Price"};
    String[] header4 = new String[]{"Type", "Size", "Max Weight (gm)", "Max Thickness (mm)", "Quantity", "Price"};

    ImageIcon icon;
    DefaultTableModel dtm1, dtm2, dtm3, dtm4;
    JTable table1, table2, table3, table4;
    JScrollPane jsp1, jsp2, jsp3, jsp4, jsp5, jsp6;
    JLabel l1, l2, l3, l4, receipt;
    JPanel menuReceipt, fullScreen;
    JTextPane textPane;
    JButton delete, mainMenu;

    /**
     * This is constructor for MenuReceipt.
     */
    public MenuReceipt() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        runProgram();
        billInfo = new ArrayList<>();

        dtm1 = new DefaultTableModel(header1, 0);
        dtm2 = new DefaultTableModel(header2, 0);
        dtm3 = new DefaultTableModel(header3, 0);
        dtm4 = new DefaultTableModel(header4, 0);

        table1.setModel(dtm1);
        table1.setDefaultEditor(Object.class, null);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table2.setModel(dtm2);
        table2.setDefaultEditor(Object.class, null);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table3.setModel(dtm3);
        table3.setDefaultEditor(Object.class, null);
        table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table4.setModel(dtm4);
        table4.setDefaultEditor(Object.class, null);
        table4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        dtm1.setRowCount(0);
        dtm2.setRowCount(0);
        dtm3.setRowCount(0);
        dtm4.setRowCount(0);

        for (BillInfo bI : NextDayDelivery.billInfo) {
            String price = String.format("%.2f", bI.ndPrice);
            Object[] objs = {bI.ndType, bI.ndZone, bI.ndWeight, bI.ndQuantity, price};
            dtm1.addRow(objs);
        }
        for (BillInfo bI : SameDayDelivery.billInfo) {
            String price = String.format("%.2f", bI.sdPrice);
            Object[] objs = {bI.sdType, bI.sdWeight, bI.sdQuantity, price};
            dtm2.addRow(objs);
        }
        for (BillInfo bI : PrepaidBoxEnvelope.billInfo) {
            String price = String.format("%.2f", bI.pbePrice);
            Object[] objs = {bI.pbeType, bI.pbeSize, bI.pbeWeight, bI.pbeQuantity, price};
            table3.getColumnModel().getColumn(0).setPreferredWidth(100);
            table3.getColumnModel().getColumn(1).setPreferredWidth(100);
            dtm3.addRow(objs);
        }
        for (BillInfo bI : PosEkspres.billInfo) {
            String price = String.format("%.2f", bI.pePrice);
            Object[] objs = {bI.peType, bI.peSize, bI.peWeight, bI.peThickness, bI.peQuantity, price};
            table4.getColumnModel().getColumn(1).setPreferredWidth(100);
            table4.getColumnModel().getColumn(3).setPreferredWidth(90);
            dtm4.addRow(objs);
        }
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is for displaying the receipt of all services.
     */
    private void runProgram() {

        jsp6 = new JScrollPane();
        jsp6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fullScreen = new JPanel();
        fullScreen.setPreferredSize(new Dimension(800, 1200));

        receipt = new JLabel();
        receipt.setText("Receipt ");
        Font r = receipt.getFont();
        receipt.setFont(r.deriveFont(Font.BOLD, r.getSize() * 1.2f));

        menuReceipt = new JPanel();
        menuReceipt.setBorder(BorderFactory.createEtchedBorder());
        menuReceipt.setPreferredSize(new Dimension(750, 250));

        textPane = new JTextPane();
        textPane.setEditable(false);
        StyledDocument style = textPane.getStyledDocument();
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_CENTER);//set text in middle
        style.setParagraphAttributes(0, style.getLength(), align, false);
        textPane.setText(toString());
        jsp5 = new JScrollPane();
        jsp5.setViewportView(textPane);

        l1 = new JLabel();
        l1.setText("Next-Day Delivery");
        Font a = l1.getFont();
        l1.setFont(a.deriveFont(Font.BOLD, a.getSize() * 1.2f));

        l2 = new JLabel();
        l2.setText("Same-Day Delivery");
        Font b = l2.getFont();
        l2.setFont(b.deriveFont(Font.BOLD, b.getSize() * 1.2f));

        l3 = new JLabel();
        l3.setText("Prepaid Box & Envelope");
        Font c = l3.getFont();
        l3.setFont(c.deriveFont(Font.BOLD, c.getSize() * 1.2f));

        l4 = new JLabel();
        l4.setText("Pos Ekspres");
        Font d = l4.getFont();
        l4.setFont(d.deriveFont(Font.BOLD, d.getSize() * 1.2f));

        jsp1 = new JScrollPane();
        jsp2 = new JScrollPane();
        jsp3 = new JScrollPane();
        jsp4 = new JScrollPane();

        table1 = new JTable();
        table2 = new JTable();
        table3 = new JTable();
        table4 = new JTable();

        delete = new JButton();
        delete.setText("Delete");
        delete.addActionListener(this);

        mainMenu = new JButton();
        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(this);

        setTitle("Receipt");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(MenuReceipt.DISPOSE_ON_CLOSE);

        jsp1.setViewportView(table1);
        jsp2.setViewportView(table2);
        jsp3.setViewportView(table3);
        jsp4.setViewportView(table4);

        int headerHeight = 28;
        JTableHeader header1 = table1.getTableHeader();
        header1.setPreferredSize(new Dimension(50, headerHeight));
        ((DefaultTableCellRenderer) table1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader header2 = table2.getTableHeader();
        header2.setPreferredSize(new Dimension(50, headerHeight));
        ((DefaultTableCellRenderer) table2.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader header3 = table3.getTableHeader();
        header3.setPreferredSize(new Dimension(50, headerHeight));
        ((DefaultTableCellRenderer) table3.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JTableHeader header4 = table4.getTableHeader();
        header4.setPreferredSize(new Dimension(50, headerHeight));
        ((DefaultTableCellRenderer) table4.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table1.setDefaultRenderer(Object.class, centerRenderer);
        table2.setDefaultRenderer(Object.class, centerRenderer);
        table3.setDefaultRenderer(Object.class, centerRenderer);
        table4.setDefaultRenderer(Object.class, centerRenderer);

        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setResizingAllowed(false);
        table1.setRowHeight(20);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                table2.clearSelection();
                table3.clearSelection();
                table4.clearSelection();
            }
        });

        table2.getTableHeader().setReorderingAllowed(false);
        table2.getTableHeader().setResizingAllowed(false);
        table2.setRowHeight(20);
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                table1.clearSelection();
                table3.clearSelection();
                table4.clearSelection();
            }
        });

        table3.getTableHeader().setReorderingAllowed(false);
        table3.getTableHeader().setResizingAllowed(false);
        table3.setRowHeight(20);
        table3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                table1.clearSelection();
                table2.clearSelection();
                table4.clearSelection();
            }
        });

        table4.getTableHeader().setReorderingAllowed(false);
        table4.getTableHeader().setResizingAllowed(false);
        table4.setRowHeight(20);
        table4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                table1.clearSelection();
                table2.clearSelection();
                table3.clearSelection();
            }
        });

        GroupLayout jPanelLayout1 = new GroupLayout(menuReceipt);
        menuReceipt.setLayout(jPanelLayout1);

        jPanelLayout1.setHorizontalGroup(
                jPanelLayout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout1.createSequentialGroup()
                                .addGroup(jPanelLayout1.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addGroup(jPanelLayout1.createSequentialGroup()
                                                .addGap(340, 340, 340)//location label receipt
                                                .addComponent(receipt))

                                        .addGroup(jPanelLayout1.createSequentialGroup()
                                                .addContainerGap()
                                                .addGap(200, 200, 200)//location scroll bar menuReceipt
                                                .addComponent(jsp5)))

                                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanelLayout1.setVerticalGroup(
                jPanelLayout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout1.createSequentialGroup()

                                .addGap(10)//top gap
                                .addComponent(receipt)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)//jsp5 gap
                                .addComponent(jsp5)

                                .addContainerGap())
        );

        GroupLayout jPanelLayout2 = new GroupLayout(fullScreen);
        fullScreen.setLayout(jPanelLayout2);

        jPanelLayout2.setHorizontalGroup(
                jPanelLayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout2.createSequentialGroup()

                                .addGroup(jPanelLayout2.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addGroup(jPanelLayout2.createSequentialGroup()
                                                .addGap(49, 49, 49)//left gap
                                                .addComponent(menuReceipt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )

                                        .addGroup(jPanelLayout2.createSequentialGroup()
                                                .addGap(49, 49, 49)//location of compenent

                                                .addGroup(jPanelLayout2.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                        .addComponent(l1)
                                                        .addComponent(l2)
                                                        .addComponent(l3)
                                                        .addComponent(l4)
                                                        .addComponent(jsp1)
                                                        .addComponent(jsp2)
                                                        .addComponent(jsp3)
                                                        .addComponent(jsp4)
                                                ))
                                        .addGroup(jPanelLayout2.createSequentialGroup()
                                                .addGap(300, 300, 300)
                                                .addComponent(delete, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(mainMenu, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        )
                                )

                                .addContainerGap(49, 49))//right gap
        );

        jPanelLayout2.setVerticalGroup(
                jPanelLayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout2.createSequentialGroup()
                                .addGap(30)//top gap
                                .addComponent(l1)
                                .addGap(15)
                                .addComponent(jsp1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)//SIZE
                                .addGap(30)
                                .addComponent(l2)
                                .addGap(15)
                                .addComponent(jsp2, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(l3)
                                .addGap(15)
                                .addComponent(jsp3, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(l4)
                                .addGap(15)
                                .addComponent(jsp4, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addGap(25)
                                .addGroup(jPanelLayout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(delete)
                                        .addComponent(mainMenu)
                                )
                                .addGap(30)
                                .addComponent(menuReceipt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addContainerGap())
        );

        jsp6.setViewportView(fullScreen);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//get screen size
        getContentPane().setPreferredSize(new Dimension(850, screenSize.height));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jsp6, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jsp6, GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)

        );
        pack();
    }

    /**
     * This method is for button function.
     *
     * @param e Button function of Main Menu and Delete.
     */
    public void actionPerformed(ActionEvent e) {
        int row;
        if (e.getSource() == mainMenu) {
            this.dispose();
        } else {

            if (table1.getSelectionModel().isSelectionEmpty() && table2.getSelectionModel().isSelectionEmpty() && table3.getSelectionModel().isSelectionEmpty() && table4.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a row that you want to delete", "Pos Laju Malaysia", JOptionPane.ERROR_MESSAGE);
            }

            if (table1.getSelectedRow() != -1) {
                if (e.getSource() == delete) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data?", "Delete", dialogButton);
                    if (dialogResult == 0) {
                        row = table1.getSelectedRow();
                        NextDayDelivery.billInfo.remove(row);

                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                        model.removeRow(row);

                        NextDayDelivery nd = new NextDayDelivery();
                        nd.addData();
                    }

                }
            } else if (table2.getSelectedRow() != -1) {
                if (e.getSource() == delete) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data?", "Delete", dialogButton);
                    if (dialogResult == 0) {
                        row = table2.getSelectedRow();
                        SameDayDelivery.billInfo.remove(row);

                        DefaultTableModel model = (DefaultTableModel) table2.getModel();
                        model.removeRow(row);

                        SameDayDelivery sd = new SameDayDelivery();
                        sd.addData();
                    }
                }
            } else if (table3.getSelectedRow() != -1) {
                if (e.getSource() == delete) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data?", "Delete", dialogButton);
                    if (dialogResult == 0) {
                        row = table3.getSelectedRow();
                        PrepaidBoxEnvelope.billInfo.remove(row);

                        DefaultTableModel model = (DefaultTableModel) table3.getModel();
                        model.removeRow(row);

                        PrepaidBoxEnvelope pbe = new PrepaidBoxEnvelope();
                        pbe.addData();
                    }
                }
            } else if (table4.getSelectedRow() != -1) {
                if (e.getSource() == delete) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data?", "Delete", dialogButton);
                    if (dialogResult == 0) {
                        row = table4.getSelectedRow();
                        PosEkspres.billInfo.remove(row);

                        DefaultTableModel model = (DefaultTableModel) table4.getModel();
                        model.removeRow(row);

                        PosEkspres pe = new PosEkspres();
                        pe.addData();
                    }
                }
            }
        }

        CalculateTotalPrice calculateTotalPrice = new CalculateTotalPrice();
        textPane.setText("---------------------------------------------------- "
                + "\n" + "Receipt:"
                + "\n" + "---------------------------------------------------- "
                + "\n" + "Next Day Delivery:               RM  " + String.format("%.2f", calculateTotalPrice.getNdTotalPrice())
                + "\n" + "Same Day Delivery:            RM  " + String.format("%.2f", calculateTotalPrice.getSdTotalPrice())
                + "\n" + "Prepaid Box & Envelope:    RM  " + String.format("%.2f", calculateTotalPrice.getPbeTotalPrice())
                + "\n" + "Pos Ekspres:                      RM  " + String.format("%.2f", calculateTotalPrice.getPeTotalPrice())
                + "\n" + "---------------------------------------------------- "
                + "\n" + "Total Price:                         RM  " + String.format("%.2f", calculateTotalPrice.getTotalPrice())
                + "\n" + "---------------------------------------------------- ");

    }

    /**
     * This method is for manipulating the string.
     *
     * @return The total price of all services.
     */
    public String toString() {
        String dataSave;
        CalculateTotalPrice calculateTotalPrice = new CalculateTotalPrice();
        dataSave = "---------------------------------------------------- "
                + "\n" + "Receipt:"
                + "\n" + "---------------------------------------------------- "
                + "\n" + "Next Day Delivery:               RM  " + String.format("%.2f", calculateTotalPrice.getNdTotalPrice())
                + "\n" + "Same Day Delivery:            RM  " + String.format("%.2f", calculateTotalPrice.getSdTotalPrice())
                + "\n" + "Prepaid Box & Envelope:    RM  " + String.format("%.2f", calculateTotalPrice.getPbeTotalPrice())
                + "\n" + "Pos Ekspres:                      RM  " + String.format("%.2f", calculateTotalPrice.getPeTotalPrice())
                + "\n" + "---------------------------------------------------- "
                + "\n" + "Total Price:                         RM  " + String.format("%.2f", calculateTotalPrice.getTotalPrice())
                + "\n" + "---------------------------------------------------- "
        ;
        return dataSave;
    }

}
