import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Choice;
import java.sql.ResultSet;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PayBill extends JFrame implements ActionListener {
    String meterNumber;
    Choice monthByChoice;
    JButton payButton, backButton;

    PayBill(String meterNumber) {
        super("Pay Bill");
        this.meterNumber = meterNumber;

        JLabel mainHeading = new JLabel("Pay Bill");
        mainHeading.setFont(new Font("tahoma", Font.BOLD, 24));
        mainHeading.setBounds(120, 5, 400, 30);
        add(mainHeading);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(35, 80, 200, 20);
        add(meterNo);

        JLabel meterNoText = new JLabel("");
        meterNoText.setBounds(300, 80, 200, 20);
        add(meterNoText);

        JLabel name = new JLabel("Name");
        name.setBounds(35, 140, 200, 20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(300, 140, 200, 20);
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setBounds(35, 200, 200, 20);
        add(month);

        monthByChoice = new Choice();
        monthByChoice.add("January");
        monthByChoice.add("February");
        monthByChoice.add("March");
        monthByChoice.add("April");
        monthByChoice.add("May");
        monthByChoice.add("June");
        monthByChoice.add("July");
        monthByChoice.add("August");
        monthByChoice.add("September");
        monthByChoice.add("October");
        monthByChoice.add("November");
        monthByChoice.add("December");
        monthByChoice.setBounds(300, 200, 200, 20);
        add(monthByChoice);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35, 260, 200, 20);
        add(unit);

        JLabel unitText = new JLabel("");
        unitText.setBounds(300, 260, 200, 20);
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setBounds(35, 320, 200, 20);
        add(totalBill);

        JLabel totalBillText = new JLabel("");
        totalBillText.setBounds(300, 320, 200, 20);
        add(totalBillText);

        JLabel status = new JLabel("Status");
        status.setBounds(35, 380, 200, 20);
        add(status);

        JLabel statusText = new JLabel("");
        statusText.setBounds(300, 380, 200, 20);
        statusText.setForeground(Color.RED);
        add(statusText);

        try {
            Database myDB = new Database();
            ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer WHERE meter_no='" + meterNumber + "';");

            while (myResultSet.next()) {
                meterNoText.setText(meterNumber);
                nameText.setText(myResultSet.getString("name"));
            }
        } catch (Exception expobj) {
            System.out.printf("Error in fetching data from 'new_customer' table : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        }

        monthByChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Database myDB = new Database();
                    ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM bill WHERE meter_no='" + meterNumber + "' AND month='" + monthByChoice.getSelectedItem() + "';");

                    if (myResultSet.next()) {
                        unitText.setText(myResultSet.getString("unit"));
                        totalBillText.setText(myResultSet.getString("total_bill"));
                        statusText.setText(myResultSet.getString("status"));
                    } else {
                        unitText.setText("");
                        totalBillText.setText("");
                        statusText.setText("");
                    }

                } catch (Exception expobj) {
                    System.out.printf("Error in fetching data from 'bill' table : %s\n", expobj.getMessage());
                    expobj.printStackTrace();
                }
            }
        });

        payButton = new JButton("Pay");
        payButton.setBackground(Color.BLACK);
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(this);
        payButton.setBounds(100, 460, 100, 25);
        add(payButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.setBounds(300, 460, 100, 25);
        add(backButton);

        setSize(900, 600);
        setLocation(300, 150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            try {
                new PaymentGateway(meterNumber);
                Database myDB = new Database();
                myDB.myStatement.executeUpdate("UPDATE bill SET status='Paid' WHERE meter_no='" + meterNumber + "' AND month='" + monthByChoice.getSelectedItem() + "';");
//                JOptionPane.showMessageDialog(null, "Bill paid for " + monthByChoice.getSelectedItem() + " month successfully.");
            } catch (Exception expobj) {
                System.out.printf("Error in updating 'bill' table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
            setVisible(false);

        } else if (e.getSource() == backButton) {
//            JOptionPane.showMessageDialog(null, "Bill not paid.");
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
