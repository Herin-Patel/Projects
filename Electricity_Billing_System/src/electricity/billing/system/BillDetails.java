package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.Color;
import java.sql.ResultSet;

public class BillDetails extends JFrame {
    String meterNumber;

    BillDetails(String meterNumber) {
        super("Bill Details");
        this.meterNumber = meterNumber;

        setSize(700, 650);
        setLocation(400, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();

        try {

            Database myDB = new Database();
            String bill_query = "SELECT * FROM bill WHERE meter_no='" + meterNumber + "';";

            ResultSet myResultSet = myDB.myStatement.executeQuery(bill_query);
            table.setModel(DbUtils.resultSetToTableModel(myResultSet));

        } catch (Exception expobj) {
            System.out.printf("Error in fetching details into table from bills table : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,700,650);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails("");
    }
}
