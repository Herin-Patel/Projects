import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.awt.event.ActionListener;

public class CalculateBill extends JFrame implements ActionListener {
    Choice meterNumChoice, monthChoice;
    TextField nameText, addressText, unitText;
    JButton submitButton, cancelButton;

    CalculateBill() {
        super("Calculate Bill");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel mainHeading = new JLabel("Calculate Electricity Bill");
        mainHeading.setBounds(70, 10, 300, 20);
        mainHeading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(mainHeading);

        JLabel meterNum = new JLabel("Meter Number");
        meterNum.setFont(new Font("senserif", Font.BOLD, 15));
        meterNum.setBounds(40, 80, 120, 20);
        panel.add(meterNum);

        meterNumChoice = new Choice();
        meterNumChoice.setBounds(180, 80, 150, 25);
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
        name.setFont(new Font("senserif", Font.BOLD, 15));
        name.setBounds(40, 120, 120, 20);
        panel.add(name);

        nameText = new TextField("");
        nameText.setEditable(false);
        nameText.setBounds(180, 120, 150, 20);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("senserif", Font.BOLD, 15));
        address.setBounds(40, 160, 120, 20);
        panel.add(address);

        addressText = new TextField("");
        addressText.setEditable(false);
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
        unitConsumed.setFont(new Font("senserif", Font.BOLD, 15));
        unitConsumed.setBounds(40, 200, 120, 20);
        panel.add(unitConsumed);

        unitText = new TextField();
        unitText.setBounds(180, 200, 150, 20);
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setFont(new Font("senserif", Font.BOLD, 15));
        month.setBounds(40, 240, 120, 20);
        panel.add(month);

        monthChoice = new Choice();
        CustomUtil.addMonths(monthChoice);
        monthChoice.setBounds(180, 240, 150, 25);
        panel.add(monthChoice);

        submitButton = CustomUtil.setButton("Submit", 16);
        submitButton.setBounds(70, 300, 110, 30);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        cancelButton = CustomUtil.setButton("Cancel", 16);
        cancelButton.setBounds(220, 300, 110, 30);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, "Center");
        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\budget.png";
        ImageIcon bgIcon = CustomUtil.setImage(imagePath, 250,200);
        JLabel bgLabel = new JLabel(bgIcon);
        add(bgLabel, "East");

        setSize(650, 400);
        setLocation(450, 200);
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
