import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {
    Choice searchMeterByChoice, searchNameByChoice;
    JTable table;
    JButton searchButton, printButton, closeButton;

    CustomerDetails() {
        super("Customer Details");
        getContentPane().setBackground(new Color(192, 186, 254));

        JLabel searchMeter = new JLabel("Search By Meter Number :");
        searchMeter.setFont(new Font("senserif", Font.BOLD, 15));
        searchMeter.setBounds(20, 20, 200, 20);
        add(searchMeter);

        searchMeterByChoice = new Choice();
        searchMeterByChoice.setBounds(230, 20, 150, 25);
        add(searchMeterByChoice);

        JLabel searchName = new JLabel("Search By Name :");
        searchName.setFont(new Font("senserif", Font.BOLD, 15));
        searchName.setBounds(440, 20, 150, 20);
        add(searchName);

        searchNameByChoice = new Choice();
        searchNameByChoice.setBounds(590, 20, 150, 25);
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
        scrollPane.setBounds(0, 110, 800, 600);
        scrollPane.setBackground(Color.WHITE);
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