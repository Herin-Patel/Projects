package electricity.billing.system;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;

public class UpdateInformation extends JFrame implements ActionListener {

    JLabel nameText;
    JTextField addressText, cityText, stateText, emailText, phoneText;
    String meterNumber;
    JButton updateButton, cancelButton;

    UpdateInformation(String meterNumber) {
        super("Update Information");
        this.meterNumber = meterNumber;
        getContentPane().setBackground(new Color(229, 255, 227));
        setBounds(400, 150, 777, 450);
        setLayout(null);

        JLabel mainHeading = new JLabel("Update Customer Information");
        mainHeading.setFont(new Font("serif", Font.BOLD, 20));
        mainHeading.setBounds(50, 10, 400, 40);
        add(mainHeading);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 70, 100, 20);
        add(name);

        nameText = new JLabel("");
        nameText.setBounds(150, 70, 200, 20);
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 110, 100, 20);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(150, 110, 100, 20);
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setBounds(30, 150, 100, 20);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(150, 150, 200, 20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(30, 190, 100, 20);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(150, 190, 200, 20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(30, 230, 100, 20);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(150, 230, 200, 20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(30, 270, 100, 20);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(150, 270, 200, 20);
        add(emailText);

        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(30, 310, 100, 20);
        add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(150, 310, 200, 20);
        add(phoneText);

        try {
            Database myDB = new Database();
            ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer WHERE meter_no='" + meterNumber + "';");

            if (myResultSet.next()) {
                nameText.setText(myResultSet.getString("name"));
                meterText.setText(meterNumber);
                addressText.setText(myResultSet.getString("address"));
                cityText.setText(myResultSet.getString("city"));
                stateText.setText(myResultSet.getString("state"));
                emailText.setText(myResultSet.getString("email"));
                phoneText.setText(myResultSet.getString("phone_no"));
            }
        } catch (Exception expobj) {
            System.out.printf("Error in fetching data from new_customer table : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        }

        updateButton = new JButton("Update");
        updateButton.setBackground(new Color(33, 106, 145));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBounds(50, 360, 120, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(33, 106, 145));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(200, 360, 120, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/update.png"));
        Image bgIcon2 = bgIcon.getImage().getScaledInstance(380, 365, Image.SCALE_DEFAULT);
        ImageIcon bgIcon3 = new ImageIcon(bgIcon2);
        JLabel bgLabel = new JLabel(bgIcon3);
        bgLabel.setBounds(360, 0, 400, 410);
        add(bgLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String addressData = addressText.getText();
            String cityData = cityText.getText();
            String stateData = stateText.getText();
            String emailData = emailText.getText();
            String phoneData = phoneText.getText();
            String update_query = "UPDATE new_customer SET address='" + addressData + "', city='" + cityData + "', state='" + stateData + "', email='" + emailData + "', phone_no='" + phoneData + "' WHERE meter_no='" + meterNumber + "';";

            try {
                Database myDB = new Database();
                myDB.myStatement.executeUpdate(update_query);

                JOptionPane.showMessageDialog(null, "Customer Information Updated Successfully.");
                setVisible(false);
            } catch (Exception expobj) {
                System.out.printf("Error in inserting data into new_customer table : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }

        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
