package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Choice;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {

    Choice searchMeterByChoice, searchMonthByChoice;
    JTable table;
    JButton searchButton, printButton, cancelButton;

    DepositDetails() {
        super("Deposit Details");
        getContentPane().setBackground(new Color(190, 186, 254));

        JLabel searchMeterNum = new JLabel("Search By Meter Number");
        searchMeterNum.setBounds(20, 20, 150, 20);
        add(searchMeterNum);

        searchMeterByChoice = new Choice();
        searchMeterByChoice.setBounds(180, 20, 150, 20);
        add(searchMeterByChoice);

        JLabel searchMonth = new JLabel("Search By Month");
        searchMonth.setBounds(400, 20, 100, 20);
        add(searchMonth);

        searchMonthByChoice = new Choice();
        searchMonthByChoice.add("January");
        searchMonthByChoice.add("February");
        searchMonthByChoice.add("March");
        searchMonthByChoice.add("April");
        searchMonthByChoice.add("May");
        searchMonthByChoice.add("June");
        searchMonthByChoice.add("July");
        searchMonthByChoice.add("August");
        searchMonthByChoice.add("September");
        searchMonthByChoice.add("October");
        searchMonthByChoice.add("November");
        searchMonthByChoice.add("December");
        searchMonthByChoice.setBounds(520, 20, 150, 20);
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
        scrollPane.setBounds(0, 100, 700, 500);
        add(scrollPane);

        searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(20, 70, 80, 25);
        searchButton.addActionListener(this);
        add(searchButton);

        printButton = new JButton("Print");
        printButton.setBackground(Color.WHITE);
        printButton.setBounds(120, 70, 80, 25);
        printButton.addActionListener(this);
        add(printButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setBounds(590, 70, 80, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

        setSize(700, 500);
        setLocation(400, 200);
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
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DepositDetails();
    }
}