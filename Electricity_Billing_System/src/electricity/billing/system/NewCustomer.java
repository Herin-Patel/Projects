package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {
    JLabel heading, customerName, meterNumber, meterText, address, city, state, email, phone;
    TextField nameText, addressText, cityText, stateText, emailText, phoneText;

    JButton nextButton, cancelButton;

    NewCustomer() {
        super("New Customer");
        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        panel.setBackground(new Color(252, 186, 3));

        heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("Customer Name");
        customerName.setBounds(50, 80, 100, 20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(180, 80, 150, 20);
        panel.add(nameText);

        meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50, 120, 100, 20);
        panel.add(meterNumber);

        meterText = new JLabel();
        meterText.setBounds(180, 120, 150, 20);
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterText.setText("" + Math.abs(number));
        panel.add(meterText);

        address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setBounds(50, 200, 100, 20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(50, 240, 100, 20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setBounds(50, 280, 100, 20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);

        phone = new JLabel("Phone");
        phone.setBounds(50, 320, 100, 20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        nextButton = new JButton("Next");
        nextButton.setBounds(120, 390, 100, 25);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        panel.add(nextButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 390, 100, 25);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel, "West");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            String userName = nameText.getText();
            String userMeter = meterText.getText();
            String userAddress = addressText.getText();
            String userCity = cityText.getText();
            String userState = stateText.getText();
            String userEmail = emailText.getText();
            String userPhone = phoneText.getText();

            String query_customer = "INSERT INTO new_customer VALUES('" + userName + "', '" + userMeter + "', '" + userAddress + "', '" + userCity + "', '" + userState + "', '" + userEmail + "', '" + userPhone + "');";
            String query_signup = "INSERT INTO SignUp VALUES('" + userMeter + "', '', '" + userName + "', '','');";

            try {
                Database myDB = new Database();
                myDB.myStatement.executeUpdate(query_customer);
                myDB.myStatement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null, "Customer details added successfully.");
                setVisible(false);
                new MeterInfo(userMeter);
            } catch (Exception expobj) {
                System.out.printf("Error in inserting data from NewCustomer data : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        }
        else if (e.getSource()==cancelButton){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
