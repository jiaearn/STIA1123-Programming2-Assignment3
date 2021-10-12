package my.husco;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for manipulating Next-Day Delivery Receipt.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class NextDayReceipt extends JFrame implements ActionListener {

    static List<BillInfo> billInfo;
    String[] header = new String[]{"Type", "Zone", "Weight", "Quantity", "Price"};

    ImageIcon icon;
    DefaultTableModel dtm;
    JTable table;
    JScrollPane jsp;
    JButton delete;
    int row;

    /**
     * This is constructor for NextDayReceipt.
     */
    public NextDayReceipt() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        runProgram();
        billInfo = new ArrayList<>();
        dtm = new DefaultTableModel(header, 0);
        table.setModel(dtm);
        dtm.setRowCount(0);
        for (BillInfo bI : NextDayDelivery.billInfo) {
            String price = String.format("%.2f", bI.ndPrice);
            Object[] objs = {bI.ndType, bI.ndZone, bI.ndWeight, bI.ndQuantity, price};
            dtm.addRow(objs);
        }
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is for displaying the receipt of Next-Day Delivery.
     */
    private void runProgram() {

        jsp = new JScrollPane();
        table = new JTable();

        delete = new JButton();
        delete.setText("Delete");
        delete.addActionListener(this);

        setTitle("Next-Day Delivery");
        setResizable(false);
        setVisible(true);
        jsp.setViewportView(table);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(NextDayReceipt.DISPOSE_ON_CLOSE);

        JTableHeader header = table.getTableHeader();
        int headerHeight = 28;
        header.setPreferredSize(new Dimension(50, headerHeight));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(20);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)

                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(49, 49, 49)//left gap
                                                .addComponent(jsp))

                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)//button location
                                                .addComponent(delete)))

                                .addContainerGap(49, Short.MAX_VALUE))//right gap


        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)//top gap

                                .addComponent(jsp, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)//button gap
                                .addComponent(delete, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)

                                .addContainerGap())
        );
        pack();
    }

    /**
     * This method is for button function.
     *
     * @param e Button function of delete.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        row = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (NextDayDelivery.billInfo.isEmpty()) {
            if (e.getSource() == delete) {
                JOptionPane.showMessageDialog(this, "List is empty", "Next Day Delivery", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (e.getSource() == delete) {
                if (table.getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select a row that you want to delete", "Next Day Delivery", JOptionPane.ERROR_MESSAGE);
                } else {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data?", "Delete", dialogButton);
                    if (dialogResult == 0) {
                        NextDayDelivery.billInfo.remove(row);
                        model.removeRow(row);
                        NextDayDelivery nd = new NextDayDelivery();
                        nd.addData();
                    }
                }
            }
        }
    }

}
