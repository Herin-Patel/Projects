import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {

    Choice searchMeterByChoice, searchMonthByChoice;
    JTable table;
    JButton searchButton, printButton, closeButton;

    DepositDetails() {
        super("Deposit Details");
        getContentPane().setBackground(new Color(190, 186, 254));

        JLabel searchMeterNum = new JLabel("Search By Meter Number :");
        searchMeterNum.setFont(new Font("senserif", Font.BOLD, 15));
        searchMeterNum.setBounds(20, 20, 200, 20);
        add(searchMeterNum);

        searchMeterByChoice = new Choice();
        searchMeterByChoice.setBounds(230, 20, 150, 25);
        add(searchMeterByChoice);

        JLabel searchMonth = new JLabel("Search By Month :");
        searchMonth.setFont(new Font("senserif", Font.BOLD, 15));
        searchMonth.setBounds(440, 20, 150, 20);
        add(searchMonth);

        searchMonthByChoice = new Choice();
        CustomUtil.addMonths(searchMonthByChoice);
        searchMonthByChoice.setBounds(590, 20, 150, 25);
        add(searchMonthByChoice);

        table = new JTable();
        try {
            Database myDB = new Database();
            ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT  * FROM bill;");

            while (myResultSet.next()) {
                searchMeterByChoice.add(myResultSet.getString("meter_no"));
            }

            myResultSet = myDB.myStatement.executeQuery("SELECT  * FROM bill;");
            table.setModel(DbUtils.resultSetToTableModel(myResultSet));

        } catch (Exception expobj) {
            System.out.printf("Error in fetching data from 'new_customer' table : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBounds(0, 110, 800, 600);
        add(scrollPane);

        searchButton = CustomUtil.setButton("Search", 16);
        searchButton.setBounds(20, 70, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        printButton = CustomUtil.setButton("Print", 16);
        printButton.setBounds(160, 70, 100, 30);
        printButton.addActionListener(this);
        add(printButton);

        closeButton = CustomUtil.setButton("Close", 16);
        closeButton.setBounds(640, 70, 100, 30);
        closeButton.addActionListener(this);
        add(closeButton);

        setSize(800, 600);
        setLocation(400, 150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String search_query = "SELECT * FROM bill WHERE meter_no='" + searchMeterByChoice.getSelectedItem() + "' AND month='" + searchMonthByChoice.getSelectedItem() + "';";

            try {
                Database myDB = new Database();
                ResultSet myResultSet = myDB.myStatement.executeQuery(search_query);

                table.setModel(DbUtils.resultSetToTableModel(myResultSet));
            } catch (Exception expobj) {
                System.out.printf("Error in fetching search_query details from bill table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (e.getSource() == printButton) {
            try {
                table.print();
            } catch (PrinterException expobj) {
                System.out.printf("Error in printing table data : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (e.getSource() == closeButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DepositDetails();
    }
}