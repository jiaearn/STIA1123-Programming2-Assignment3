package my.husco;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is for manipulating Prepaid Box and Envelope Receipt.
 *
 * @author Tan Jia Earn
 * @author Wong Fang Man
 */
public class PbeReceipt extends JFrame implements ActionListener {

    static ArrayList<BillInfo> billInfo;
    String[] header = new String[]{"Type", "Size", "Max Weight (gm)", "Quantity", "Price"};

    ImageIcon icon;
    DefaultTableModel dtm;
    JTable table;
    JScrollPane jsp;
    JButton delete;
    int row;

    /**
     * This is constructor for PbeReceipt.
     */
    public PbeReceipt() {

        icon = new ImageIcon(getClass().getResource("/PosLogo.png"));
        setIconImage(icon.getImage());

        runProgram();
        billInfo = new ArrayList<>();
        dtm = new DefaultTableModel(header, 0);
        table.setModel(dtm);
        dtm.setRowCount(0);
        for (BillInfo bI : PrepaidBoxEnvelope.billInfo) {
            String price = String.format("%.2f", bI.pbePrice);
            Object[] objs = {bI.pbeType, bI.pbeSize, bI.pbeWeight, bI.pbeQuantity, price};
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            dtm.addRow(objs);
        }
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is for displaying the receipt of Prepaid Box & Envelope.
     */
    private void runProgram() {
        jsp = new JScrollPane();
        table = new JTable();

        delete = new JButton();
        delete.setText("Delete");
        delete.addActionListener(this);

        setTitle("Prepaid Box & Envelope");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(PbeReceipt.DISPOSE_ON_CLOSE);

        JTableHeader header = table.getTableHeader();
        int headerHeight = 28;
        header.setPreferredSize(new Dimension(150, headerHeight));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        jsp.setViewportView(table);

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
        getContentPane().setPreferredSize(new Dimension(799, 300));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(49, 49, 49)//left gap
                                                .addComponent(jsp, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE))

                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(350, 350, 350)//button location
                                                .addComponent(delete)))

                                .addContainerGap(49, Short.MAX_VALUE))//right gap
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)//top gap
                                .addComponent(jsp, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)//button gap
                                .addComponent(delete)
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
        if (PrepaidBoxEnvelope.billInfo.isEmpty()) {
            if (e.getSource() == delete) {
                JOptionPane.showMessageDialog(this, "List is empty", "Prepaid Box & Envelope", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (e.getSource() == delete) {
                if (table.getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select a row that you want to delete", "Prepaid Box & Envelope", JOptionPane.ERROR_MESSAGE);
                } else {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the data?", "Delete", dialogButton);
                    if (dialogResult == 0) {
                        PrepaidBoxEnvelope.billInfo.remove(row);
                        model.removeRow(row);
                        PrepaidBoxEnvelope pbe = new PrepaidBoxEnvelope();
                        pbe.addData();
                    }
                }
            }
        }
    }

}
