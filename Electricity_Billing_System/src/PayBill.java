import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import java.sql.ResultSet;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

public class PayBill extends JFrame implements ActionListener {
    String meterNumber;
    Choice monthByChoice;
    JButton payButton, backButton;
    JTextField meterNoText, nameText, unitText, totalBillText, statusText;

    PayBill(String meterNumber) {
        super("Pay Bill");
        this.meterNumber = meterNumber;

        JLabel mainHeading = new JLabel("Pay Bill");
        mainHeading.setFont(new Font("tahoma", Font.BOLD, 25));
        mainHeading.setBounds(160, 10, 400, 30);
        add(mainHeading);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setFont(new Font("senserif", Font.BOLD, 15));
        meterNo.setBounds(35, 80, 200, 20);
        add(meterNo);

        meterNoText = new JTextField();
        meterNoText.setFont(new Font("senserif", Font.BOLD, 15));
        meterNoText.setBounds(200, 80, 200, 20);
        meterNoText.setEditable(false);
        add(meterNoText);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("senserif", Font.BOLD, 15));
        name.setBounds(35, 140, 200, 20);
        add(name);

        nameText = new JTextField();
        nameText.setFont(new Font("senserif", Font.BOLD, 15));
        nameText.setBounds(200, 140, 200, 20);
        nameText.setEditable(false);
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setFont(new Font("senserif", Font.BOLD, 15));
        month.setBounds(35, 200, 150, 20);
        add(month);

        monthByChoice = new Choice();
        CustomUtil.addMonths(monthByChoice);
        monthByChoice.setFont(new Font("senserif", Font.BOLD, 15));
        monthByChoice.setBounds(200, 200, 200, 20);
        add(monthByChoice);

        JLabel unit = new JLabel("Unit");
        unit.setFont(new Font("senserif", Font.BOLD, 15));
        unit.setBounds(35, 260, 200, 20);
        add(unit);

        unitText = new JTextField();
        unitText.setFont(new Font("senserif", Font.BOLD, 15));
        unitText.setBounds(200, 260, 200, 20);
        unitText.setEditable(false);
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setFont(new Font("senserif", Font.BOLD, 15));
        totalBill.setBounds(35, 320, 200, 20);
        add(totalBill);

        totalBillText = new JTextField();
        totalBillText.setFont(new Font("senserif", Font.BOLD, 15));
        totalBillText.setBounds(200, 320, 200, 20);
        totalBillText.setEditable(false);
        add(totalBillText);

        JLabel status = new JLabel("Status");
        status.setFont(new Font("senserif", Font.BOLD, 15));
        status.setBounds(35, 380, 200, 20);
        add(status);

        statusText = new JTextField();
        statusText.setFont(new Font("senserif", Font.BOLD, 15));
        statusText.setBounds(200, 380, 200, 20);
        statusText.setEditable(false);
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

        payButton = CustomUtil.setButton("Pay", 16);
        payButton.setBounds(100, 440, 100, 30);
        payButton.addActionListener(this);
        add(payButton);

        backButton = CustomUtil.setButton("Back", 16);
        backButton.setBounds(300, 440, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\pay.png";
        ImageIcon bgImg = CustomUtil.setImage(imagePath, 380, 365);
        JLabel bgLabel = new JLabel(bgImg);
        bgLabel.setBounds(400, 20, 500, 480);
        add(bgLabel);

        getContentPane().setBackground(new Color(250, 202, 240));
        setSize(900, 550);
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
            } catch (Exception expobj) {
                System.out.printf("Error in updating 'bill' table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
            setVisible(false);
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
