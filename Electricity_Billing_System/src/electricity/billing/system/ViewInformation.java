package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewInformation extends JFrame implements ActionListener {
    String meterNumber;
    JButton cancelButton;

    ViewInformation(String meterNumber) {
        super("View Information");
        this.meterNumber = meterNumber;

        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70, 80, 100, 20);
        add(nameLabel);

        JTextField nameLabelText = new JTextField("");
        nameLabelText.setBounds(200, 80, 150, 20);
        nameLabelText.setEditable(false);
        add(nameLabelText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(70, 140, 100, 20);
        add(meterNo);

        JTextField meterNoText = new JTextField("");
        meterNoText.setBounds(200, 140, 150, 20);
        meterNoText.setEditable(false);
        add(meterNoText);

        JLabel address = new JLabel("Address");
        address.setBounds(70, 200, 100, 20);
        add(address);

        JTextField addressText = new JTextField("");
        addressText.setBounds(200, 200, 150, 20);
        addressText.setEditable(false);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70, 260, 100, 20);
        add(city);

        JTextField cityText = new JTextField("");
        cityText.setBounds(200, 260, 150, 20);
        cityText.setEditable(false);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500, 80, 100, 20);
        add(state);

        JTextField stateText = new JTextField("");
        stateText.setBounds(600, 80, 150, 20);
        stateText.setEditable(false);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(500, 140, 100, 20);
        add(email);

        JTextField emailText = new JTextField("");
        emailText.setBounds(600, 140, 150, 20);
        emailText.setEditable(false);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500, 200, 100, 20);
        add(phone);

        JTextField phoneText = new JTextField("");
        phoneText.setBounds(600, 200, 150, 20);
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

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(24, 118, 242));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(220, 350, 120, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/viewInfo.png"));
        Image bgIcon2 = bgIcon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon bgIcon3 = new ImageIcon(bgIcon2);
        JLabel bgLabel = new JLabel(bgIcon3);
        bgLabel.setBounds(100, 320, 600, 300);
        add(bgLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
