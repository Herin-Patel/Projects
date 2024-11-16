import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateInformation extends JFrame implements ActionListener {
    JTextField nameText, meterText, addressText, cityText, stateText, emailText, phoneText;
    String meterNumber;
    JButton updateButton, cancelButton;

    UpdateInformation(String meterNumber) {
        super("Update Information");
        this.meterNumber = meterNumber;

        JLabel mainHeading = new JLabel("Update Customer Information");
        mainHeading.setFont(new Font("serif", Font.BOLD, 25));
        mainHeading.setBounds(35, 10, 400, 40);
        add(mainHeading);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("senserif", Font.BOLD, 15));
        name.setBounds(30, 70, 100, 20);
        add(name);

        nameText = new JTextField();
        nameText.setFont(new Font("senserif", Font.BOLD, 15));
        nameText.setBounds(165, 70, 200, 20);
        nameText.setEditable(false);
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setFont(new Font("senserif", Font.BOLD, 15));
        meterNo.setBounds(30, 110, 120, 20);
        add(meterNo);

        meterText = new JTextField();
        meterText.setFont(new Font("senserif", Font.BOLD, 15));
        meterText.setBounds(165, 110, 100, 20);
        meterText.setEditable(false);
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("senserif", Font.BOLD, 15));
        address.setBounds(30, 150, 100, 20);
        add(address);

        addressText = new JTextField();
        addressText.setFont(new Font("senserif", Font.BOLD, 15));
        addressText.setBounds(165, 150, 200, 22);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setFont(new Font("senserif", Font.BOLD, 15));
        city.setBounds(30, 190, 100, 20);
        add(city);

        cityText = new JTextField();
        cityText.setFont(new Font("senserif", Font.BOLD, 15));
        cityText.setBounds(165, 190, 200, 20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setFont(new Font("senserif", Font.BOLD, 15));
        state.setBounds(30, 230, 100, 20);
        add(state);

        stateText = new JTextField();
        stateText.setFont(new Font("senserif", Font.BOLD, 15));
        stateText.setBounds(165, 230, 200, 20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setFont(new Font("senserif", Font.BOLD, 15));
        email.setBounds(30, 270, 100, 20);
        add(email);

        emailText = new JTextField();
        emailText.setFont(new Font("senserif", Font.BOLD, 15));
        emailText.setBounds(165, 270, 200, 22);
        add(emailText);

        JLabel phone = new JLabel("Phone Number");
        phone.setFont(new Font("senserif", Font.BOLD, 15));
        phone.setBounds(30, 310, 120, 20);
        add(phone);

        phoneText = new JTextField();
        phoneText.setFont(new Font("senserif", Font.BOLD, 15));
        phoneText.setBounds(165, 310, 200, 20);
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

        updateButton = CustomUtil.setButton("Update", 16);
        updateButton.setBounds(50, 360, 120, 30);
        updateButton.addActionListener(this);
        add(updateButton);

        cancelButton = CustomUtil.setButton("Cancel", 16);
        cancelButton.setBounds(200, 360, 120, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\update.png";
        ImageIcon bgImg = CustomUtil.setImage(imagePath, 380, 365);
        JLabel bgLabel = new JLabel(bgImg);
        bgLabel.setBounds(380, 0, 400, 410);
        add(bgLabel);

        getContentPane().setBackground(new Color(229, 255, 227));
        setBounds(400, 150, 800, 450);
        setLayout(null);
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
