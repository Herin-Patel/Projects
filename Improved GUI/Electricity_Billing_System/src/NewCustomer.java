import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {
    JLabel mainHeading, customerName, meterNumber, address, city, state, email, phone;
    TextField nameText, meterText, addressText, cityText, stateText, emailText, phoneText;
    JButton nextButton, cancelButton;

    NewCustomer() {
        super("New Customer");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        panel.setBackground(new Color(250, 201, 77));

        mainHeading = new JLabel("New Customer");
        mainHeading.setBounds(150, 10, 200, 20);
        mainHeading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(mainHeading);

        customerName = new JLabel("Customer Name");
        customerName.setFont(new Font("senserif", Font.BOLD, 15));
        customerName.setBounds(50, 80, 120, 20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(200, 80, 150, 20);
        panel.add(nameText);

        meterNumber = new JLabel("Meter Number");
        meterNumber.setFont(new Font("senserif", Font.BOLD, 15));
        meterNumber.setBounds(50, 120, 120, 20);
        panel.add(meterNumber);

        meterText = new TextField();
        meterText.setBounds(200, 120, 150, 20);
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterText.setText("" + Math.abs(number));
        meterText.setEditable(false);
        panel.add(meterText);

        address = new JLabel("Address");
        address.setFont(new Font("senserif", Font.BOLD, 15));
        address.setBounds(50, 160, 120, 20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(200, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setFont(new Font("senserif", Font.BOLD, 15));
        city.setBounds(50, 200, 120, 20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(200, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setFont(new Font("senserif", Font.BOLD, 15));
        state.setBounds(50, 240, 120, 20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(200, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setFont(new Font("senserif", Font.BOLD, 15));
        email.setBounds(50, 280, 120, 20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(200, 280, 150, 20);
        panel.add(emailText);

        phone = new JLabel("Phone");
        phone.setFont(new Font("senserif", Font.BOLD, 15));
        phone.setBounds(50, 320, 120, 20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(200, 320, 150, 20);
        panel.add(phoneText);

        nextButton = CustomUtil.setButton("Next", 16);
        nextButton.setBackground(Color.BLACK);
        nextButton.setBounds(100, 390, 100, 30);
        nextButton.addActionListener(this);
        panel.add(nextButton);

        cancelButton = CustomUtil.setButton("Cancel", 16);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setBounds(250, 390, 100, 30);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, "Center");

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\boy.png";
        ImageIcon bgImg = CustomUtil.setImage(imagePath, 230, 230);
        JLabel imgLabel = new JLabel(bgImg);
        add(imgLabel, "West");

        setSize(650, 475);
        setLocation(500, 200);
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
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
