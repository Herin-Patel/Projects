package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Choice;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {

    Choice searchMeterByChoice, searchNameByChoice;
    JTable table;
    JButton searchButton, printButton, closeButton;

    CustomerDetails() {
        super("Customer Details");
        getContentPane().setBackground(new Color(192, 186, 254));

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterByChoice = new Choice();
        searchMeterByChoice.setBounds(180, 20, 150, 20);
        add(searchMeterByChoice);

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(400, 20, 100, 20);
        add(searchName);

        searchNameByChoice = new Choice();
        searchNameByChoice.setBounds(520, 20, 150, 20);
        add(searchNameByChoice);

        table = new JTable();

        try {
            Database myDB = new Database();
            ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer;");

            while (myResultSet.next()) {
                searchMeterByChoice.add(myResultSet.getString("meter_no"));
                searchNameByChoice.add(myResultSet.getString("name"));
            }

            myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer;");
            table.setModel(DbUtils.resultSetToTableModel(myResultSet));
        } catch (Exception expobj) {
            System.out.printf("Error in fetching meter_no and name from new_customer table : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 700, 500);
        scrollPane.setBackground(Color.WHITE);
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

        closeButton = new JButton("Close");
        closeButton.setBackground(Color.WHITE);
        closeButton.setBounds(590, 70, 80, 25);
        closeButton.addActionListener(this);
        add(closeButton);

        setSize(700, 500);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String search_query = "SELECT * FROM new_customer WHERE meter_no='" + searchMeterByChoice.getSelectedItem() + "' AND name='" + searchNameByChoice.getSelectedItem() + "';";

            try {
                Database myDB = new Database();
                ResultSet myResultSet = myDB.myStatement.executeQuery(search_query);

                table.setModel(DbUtils.resultSetToTableModel(myResultSet));
            } catch (Exception expobj) {
                System.out.printf("Error in searching name and meter_no from new_customer table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }

        } else if (e.getSource() == printButton) {
            try {
                table.print();
            } catch (PrinterException expobj) {
                System.out.printf("Error in printing data in table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (e.getSource() == closeButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}