package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.awt.event.ActionListener;

public class CalculateBill extends JFrame implements ActionListener {

    JLabel nameText, addressText;
    Choice meterNumChoice, monthChoice;
    TextField unitText;
    JButton submitButton, cancelButton;

    CalculateBill() {
        super("Calculate Bill");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70, 10, 300, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50, 80, 100, 20);
        panel.add(meterNum);

        meterNumChoice = new Choice();
        meterNumChoice.setBounds(180, 80, 150, 20);
        try {
            Database myDB = new Database();
            ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer;");

            while (myResultSet.next()) {
                meterNumChoice.add(myResultSet.getString("meter_no"));
            }
        } catch (Exception expobj) {
            System.out.printf("Error in fetching meter number choices : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        }
        panel.add(meterNumChoice);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 120, 100, 20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180, 120, 150, 20);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        try {
            Database myDB = new Database();
            ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer WHERE meter_no='" + meterNumChoice.getSelectedItem() + "';");
            while (myResultSet.next()) {
                nameText.setText(myResultSet.getString("name"));
                addressText.setText(myResultSet.getString("address"));
            }
        } catch (Exception expboj) {
            System.out.printf("Error in fetching data for CalculateBill : %s\n", expboj.getMessage());
            expboj.printStackTrace();
        }

        meterNumChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Database myDB = new Database();
                    ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer WHERE meter_no='" + meterNumChoice.getSelectedItem() + "';");
                    while (myResultSet.next()) {
                        nameText.setText(myResultSet.getString("name"));
                        addressText.setText(myResultSet.getString("address"));
                    }
                } catch (Exception expboj) {
                    System.out.printf("Error in fetching data for CalculateBill : %s\n", expboj.getMessage());
                    expboj.printStackTrace();
                }
            }
        });

        JLabel unitConsumed = new JLabel("Unit Consumed");
        unitConsumed.setBounds(50, 200, 100, 20);
        panel.add(unitConsumed);

        unitText = new TextField();
        unitText.setBounds(180, 200, 150, 20);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setBounds(50, 240, 100, 20);
        panel.add(month);

        monthChoice = new Choice();
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("May");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");
        monthChoice.setBounds(180, 240, 150, 20);
        panel.add(monthChoice);

        submitButton = new JButton("Submit");
        submitButton.setBounds(80, 300, 100, 25);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 300, 100, 25);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, "Center");
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/budget.png"));
        Image bgIcon2 = bgIcon.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        ImageIcon bgIcon3 = new ImageIcon(bgIcon2);
        JLabel bgLabel = new JLabel(bgIcon3);
        add(bgLabel, "East");

        setSize(650, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String userMeterNum = meterNumChoice.getSelectedItem();
            String userUnit = unitText.getText();
            String userMonth = monthChoice.getSelectedItem();

            int totalBill = 0;
            int units = Integer.parseInt(userUnit);
            String query_tax = "SELECT * FROM tax;";

            try {
                Database myDB = new Database();
                ResultSet myResultSet = myDB.myStatement.executeQuery(query_tax);
                while (myResultSet.next()) {
                    totalBill += units * Integer.parseInt(myResultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(myResultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(myResultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(myResultSet.getString("swacch_bharat"));
                    totalBill += Integer.parseInt(myResultSet.getString("fixed_tax"));
                }
            } catch (Exception expobj) {
                System.out.printf("Error in fetching data from tax table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }

            String query_total_bill = "INSERT INTO bill VALUES('" + userMeterNum + "', '" + userMonth + "', '" + userUnit + "', '" + totalBill + "', 'Not Paid');";
            try {
                Database myDB = new Database();
                myDB.myStatement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully.");
                setVisible(false);
            } catch (Exception expobj) {
                System.out.printf("Error in inserting data to bill table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }

        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new CalculateBill();
    }
}
