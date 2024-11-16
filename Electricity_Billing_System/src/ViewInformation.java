import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewInformation extends JFrame implements ActionListener {
    String meterNumber;
    JButton printButton, closeButton;
    JTextField nameLabelText, meterNoText, addressText, cityText, stateText, phoneText, emailText;

    ViewInformation(String meterNumber) {
        super("View Information");
        this.meterNumber = meterNumber;

        JLabel mainHeading = new JLabel("View Customer Information");
        mainHeading.setBounds(300, 0, 500, 40);
        mainHeading.setFont(new Font("serif", Font.BOLD, 25));
        add(mainHeading);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("senserif", Font.BOLD, 16));
        nameLabel.setBounds(70, 80, 120, 20);
        add(nameLabel);

        nameLabelText = new JTextField("");
        nameLabelText.setFont(new Font("senserif", Font.BOLD, 16));
        nameLabelText.setBounds(200, 80, 150, 25);
        nameLabelText.setEditable(false);
        add(nameLabelText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setFont(new Font("senserif", Font.BOLD, 16));
        meterNo.setBounds(70, 140, 120, 20);
        add(meterNo);

        meterNoText = new JTextField("");
        meterNoText.setFont(new Font("senserif", Font.BOLD, 16));
        meterNoText.setBounds(200, 140, 150, 25);
        meterNoText.setEditable(false);
        add(meterNoText);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("senserif", Font.BOLD, 16));
        address.setBounds(70, 200, 100, 20);
        add(address);

        addressText = new JTextField("");
        addressText.setFont(new Font("senserif", Font.BOLD, 16));
        addressText.setBounds(200, 200, 150, 25);
        addressText.setEditable(false);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setFont(new Font("senserif", Font.BOLD, 16));
        city.setBounds(70, 260, 100, 20);
        add(city);

        cityText = new JTextField("");
        cityText.setFont(new Font("senserif", Font.BOLD, 16));
        cityText.setBounds(200, 260, 150, 25);
        cityText.setEditable(false);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setFont(new Font("senserif", Font.BOLD, 16));
        state.setBounds(500, 80, 100, 20);
        add(state);

        stateText = new JTextField("");
        stateText.setFont(new Font("senserif", Font.BOLD, 16));
        stateText.setBounds(600, 80, 150, 25);
        stateText.setEditable(false);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setFont(new Font("senserif", Font.BOLD, 16));
        email.setBounds(500, 140, 100, 20);
        add(email);

        emailText = new JTextField("");
        emailText.setFont(new Font("senserif", Font.BOLD, 16));
        emailText.setBounds(600, 140, 150, 25);
        emailText.setEditable(false);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setFont(new Font("senserif", Font.BOLD, 16));
        phone.setBounds(500, 200, 100, 20);
        add(phone);

        phoneText = new JTextField("");
        phoneText.setFont(new Font("senserif", Font.BOLD, 16));
        phoneText.setBounds(600, 200, 150, 25);
        phoneText.setEditable(false);
        add(phoneText);

        try {
            Database myDB = new Database();
            ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM new_customer WHERE meter_no='" + meterNumber + "';");

            if (myResultSet.next()) {
                nameLabelText.setText(myResultSet.getString("name"));
                meterNoText.setText(myResultSet.getString("meter_no"));
                addressText.setText(myResultSet.getString("address"));
                cityText.setText(myResultSet.getString("city"));
                stateText.setText(myResultSet.getString("state"));
                emailText.setText(myResultSet.getString("email"));
                phoneText.setText(myResultSet.getString("phone_no"));
            }
        } catch (Exception expobj) {
            System.out.printf("Error in fetching data from : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        }

        closeButton = CustomUtil.setButton("Close", 16);
        closeButton.setBounds(630, 260, 120, 30);
        closeButton.addActionListener(this);
        add(closeButton);

        printButton = CustomUtil.setButton("Print", 16);
        printButton.setBounds(480, 260, 120, 30);
        printButton.addActionListener(this);
        add(printButton);

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\viewInfo.png";
        ImageIcon viewImg = CustomUtil.setImage(imagePath, 600, 300);
        JLabel bgLabel = new JLabel(viewImg);
        bgLabel.setBounds(50, 320, 600, 300);
        add(bgLabel);

        setBounds(350, 50, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            setVisible(false);
        } else if (e.getSource() == printButton) {
            try {
                JTextArea printArea = new JTextArea();
                printArea.setText(
                        "Customer Details : -\n\n" +
                                "Name      : " + nameLabelText.getText() + "\n" +
                                "Meter No  : " + meterNoText.getText() + "\n" +
                                "Address   : " + addressText.getText() + "\n" +
                                "City      : " + cityText.getText() + "\n" +
                                "State     : " + stateText.getText() + "\n" +
                                "Email     : " + emailText.getText() + "\n" +
                                "Phone     : " + phoneText.getText() + "\n"
                );

                // Set the text area to be non-editable
                printArea.setEditable(false);

                // Print the JTextArea
                if (printArea.print()) {
                    JOptionPane.showMessageDialog(null, "Customer Details sent to printing successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Printing Cancelled!");
                }
            } catch (Exception expobj) {
                System.out.printf("Error during printing : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
