import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {
    String meterNumber;
    Choice monthByChoice;
    JTextArea area;
    JButton billButton, printButton, backButton;

    GenerateBill(String meterNumber) {
        super("Generate Bill");
        this.meterNumber = meterNumber;

        setSize(500, 700);
        setLocation(500, 30);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JLabel mainHeading = new JLabel("Generate Bill for ");
        JLabel meterNo = new JLabel("Meter Number : " + meterNumber);

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

        area = new JTextArea(50, 15);
        area.setText("\n\n\t --------------- Click on the --------------- \n\t ---------- Generate Bill Button ---------");
        area.setFont(new Font("serif", Font.ITALIC, 15));

        JScrollPane paneScroll = new JScrollPane(area);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        billButton = new JButton("Generate Bill");
        billButton.addActionListener(this);

        printButton = new JButton("Print");
        printButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        buttonsPanel.add(billButton);
        buttonsPanel.add(printButton);
        buttonsPanel.add(backButton);

        panel.add(mainHeading);
        panel.add(meterNo);
        panel.add(monthByChoice);
        add(panel, "North");
        add(buttonsPanel, "South"); // Add buttons panel instead of individual buttons
        add(paneScroll);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==billButton) {
            try {
                Database myDB = new Database();
                String monthData = monthByChoice.getSelectedItem();
                area.setText("\n    Java Power Limited \n    Electricity Bill for month of '" + monthData + "', 2024\n\n\n");
                ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer WHERE meter_no='" + meterNumber + "';");

                if (myResultSet.next()) {
                    area.append("\n    Customer Name                  : " + myResultSet.getString("name"));
                    area.append("\n    Customer Meter Number     : " + myResultSet.getString("meter_no"));
                    area.append("\n    Customer Address               : " + myResultSet.getString("address"));
                    area.append("\n    Customer City                     : " + myResultSet.getString("city"));
                    area.append("\n    Customer State                   : " + myResultSet.getString("state"));
                    area.append("\n    Customer Email                 : " + myResultSet.getString("email"));
                    area.append("\n    Customer Phone Number   : " + myResultSet.getString("phone_no"));
                }

//            myResultSet = myDB.myStatement.executeQuery("select * from meter_info where meter_number ='"+meter+"'");
                myResultSet = myDB.myStatement.executeQuery("SELECT * FROM meter_info WHERE meter_number='" + meterNumber + "';");
                if (myResultSet.next()) {
                    area.append("\n    Customer Meter Location  : " + myResultSet.getString("meter_location"));
                    area.append("\n    Customer Meter Type        : " + myResultSet.getString("meter_type"));
                    area.append("\n    Customer Phase Code       : " + myResultSet.getString("phase_code"));
                    area.append("\n    Customer Bill Type           : " + myResultSet.getString("bill_type"));
                    area.append("\n    Customer Days                 : " + myResultSet.getString("Days"));

                }

                myResultSet = myDB.myStatement.executeQuery("SELECT * FROM tax;");
                if (myResultSet.next()) {
                    area.append("\n   Cost Per Unit                    : " + myResultSet.getString("cost_per_unit"));
                    area.append("\n   Meter Rent                        : " + myResultSet.getString("meter_rent"));
                    area.append("\n   Service Charge                 : " + myResultSet.getString("service_charge"));
                    area.append("\n   Service Tax                       : " + myResultSet.getString("service_tax"));
                    area.append("\n   Swacch Bharat                 : " + myResultSet.getString("swacch_bharat"));
                    area.append("\n   Fixed Tax                         : " + myResultSet.getString("fixed_tax"));

                }

                myResultSet = myDB.myStatement.executeQuery("SELECT * FROM bill WHERE meter_no='" + meterNumber + "' AND month='" + monthByChoice.getSelectedItem() + "';");
                if (myResultSet.next()) {
                    area.append("\n   Current Month                 : " + myResultSet.getString("month"));
                    area.append("\n   Units Consumed              : " + myResultSet.getString("unit"));
                    area.append("\n   Total Charges                 : " + myResultSet.getString("total_bill"));
                    area.append("\n   Total Payable                 : " + myResultSet.getString("total_bill"));
                }
            } catch (Exception expobj) {
                System.out.printf("Error from fetching data : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        }
        else if(e.getSource()==printButton){
            try {
                area.print();
            } catch (PrinterException expobj){
                System.out.printf("Error in printing the bill receipt : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (e.getSource()==backButton){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new GenerateBill("41795");
    }
}
