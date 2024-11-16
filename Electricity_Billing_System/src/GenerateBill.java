import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import java.sql.ResultSet;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class GenerateBill extends JFrame implements ActionListener {
    String meterNumber;
    Choice monthByChoice;
    JTextArea area;
    JButton billButton, printButton, backButton;

    GenerateBill(String meterNumber) {
        super("Generate Bill");
        this.meterNumber = meterNumber;

        // Set the layout for the main frame
        setLayout(new BorderLayout());

        // Top panel for the header and month selection
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        JLabel mainHeading = new JLabel("Generate Bill for ");
        mainHeading.setForeground(Color.WHITE);
        mainHeading.setFont(new Font("senserif", Font.BOLD, 15));
        panel.add(mainHeading);

        JLabel meterNo = new JLabel("Meter Number : " + meterNumber);
        meterNo.setForeground(Color.WHITE);
        meterNo.setFont(new Font("senserif", Font.BOLD, 15));
        panel.add(meterNo);

        monthByChoice = new Choice();
        CustomUtil.addMonths(monthByChoice); // Populate months using your utility method
        monthByChoice.setFont(new Font("senserif", Font.BOLD, 15));
        panel.add(monthByChoice);

        add(panel, BorderLayout.NORTH); // Add the panel to the north section

        // Center area for the text display
        area = new JTextArea(50, 15);
        area.setText("\n\n\t --------------- Click on the --------------- \n\t ---------- Generate Bill Button ---------");
        area.setFont(new Font("serif", Font.ITALIC, 15));
        area.setEditable(false); // Make it read-only

        JScrollPane paneScroll = new JScrollPane(area); // Add scrolling capability
        add(paneScroll, BorderLayout.CENTER); // Add the scroll pane to the center

        // Bottom panel for the buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // Set a grid layout for buttons
        billButton = CustomUtil.setButton("Generate Bill", 15);
        billButton.addActionListener(this);
        buttonsPanel.add(billButton);

        printButton = CustomUtil.setButton("Print", 15);
        printButton.addActionListener(this);
        buttonsPanel.add(printButton);

        backButton = CustomUtil.setButton("Back", 15);
        backButton.addActionListener(this);
        buttonsPanel.add(backButton);

        buttonsPanel.setBackground(Color.BLACK);
        add(buttonsPanel, BorderLayout.SOUTH); // Add the buttons panel to the south

        // Set frame properties
        setSize(500, 700);
        setLocation(500, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == billButton) {
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
        } else if (e.getSource() == printButton) {
            try {
                area.print();
            } catch (PrinterException expobj) {
                System.out.printf("Error in printing the bill receipt : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
