import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;
import java.sql.ResultSet;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {
    Choice loginAs;
    TextField meterText, employerText, userText, nameText;
    JPasswordField passwordText;
    JButton createButton, backButton;

    SignUp() {
        super("SignUp Page");
        getContentPane().setBackground(new Color(168, 203, 255));

        JLabel createAs = new JLabel("Create Account As :");
        createAs.setFont(new Font("senserif", Font.BOLD, 13));
        createAs.setBounds(30, 50, 125, 20);
        add(createAs);

        loginAs = new Choice();
        loginAs.add("Admin");
        loginAs.add("Customer");
        loginAs.setBounds(170, 50, 125, 20);
        add(loginAs);

        JLabel meterNo = new JLabel("Meter Number :");
        meterNo.setFont(new Font("senserif", Font.BOLD, 13));
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);
        add(meterText);

        JLabel employerID = new JLabel("Employer ID :");
        employerID.setFont(new Font("senserif", Font.BOLD, 13));
        employerID.setBounds(30, 100, 125, 20);
        employerID.setVisible(true);
        add(employerID);

        employerText = new TextField();
        employerText.setBounds(170, 100, 125, 20);
        employerText.setVisible(true);
        add(employerText);

        JLabel userName = new JLabel("User Name :");
        userName.setFont(new Font("senserif", Font.BOLD, 13));
        userName.setBounds(30, 140, 125, 20);
        add(userName);

        userText = new TextField();
        userText.setBounds(170, 140, 125, 20);
        add(userText);

        JLabel name = new JLabel("Name :");
        name.setFont(new Font("senserif", Font.BOLD, 13));
        name.setBounds(30, 180, 125, 20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170, 180, 125, 20);
        add(nameText);

        JLabel password = new JLabel("Password :");
        password.setFont(new Font("senserif", Font.BOLD, 13));
        password.setBounds(30, 220, 125, 20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(170, 220, 125, 20);
        add(passwordText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Database myDB = new Database();
                    ResultSet myResultSet = myDB.myStatement.executeQuery("SELECT * FROM SignUp WHERE meter_no='" + meterText.getText() + "';");

                    if (myResultSet.next()) {
                        nameText.setText(myResultSet.getString("name"));
                    }
                } catch (Exception expobj) {
                    System.out.printf("Error in fetching data from SignUp table : %s\n", expobj.getMessage());
                    expobj.printStackTrace();
                }
            }
        });

        loginAs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginAs.getSelectedItem();
                if (user.equals("Customer")) {
                    employerID.setVisible(false);
                    employerText.setVisible(false);

                    meterNo.setVisible(true);
                    meterText.setVisible(true);

                    nameText.setEditable(false);
                } else {
                    employerID.setVisible(true);
                    employerText.setVisible(true);

                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        createButton = CustomUtil.setButton("Create", 16);
        createButton.setBounds(50, 285, 100, 30);
        createButton.addActionListener(this);
        add(createButton);

        backButton = CustomUtil.setButton("Back", 16);
        backButton.setBounds(180, 285, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\boy.png";
        ImageIcon scaledProfile = CustomUtil.setImage(imagePath, 250, 250);
        JLabel imgLabel = new JLabel(scaledProfile);
        imgLabel.setBounds(320, 30, 250, 250);
        add(imgLabel);

        setSize(600, 388);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            // Back-end data stored in MySQL Database
            String userTypeData = loginAs.getSelectedItem();
            String userNameData = userText.getText();
            String nameData = nameText.getText();
            String passwordData = passwordText.getText();
            String meterData = meterText.getText();

            if (userNameData.isEmpty() || nameData.isEmpty() || passwordData.isEmpty() || meterData.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all details !", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            try {
                Database myDB = new Database();
                String query = null;

                if (userTypeData.equals("Admin")) {
                    query = "INSERT INTO SignUp VALUE ('" + meterData + "', '" + userNameData + "', '" + nameData + "', '" + passwordData + "', '" + userTypeData + "');";
                } else if (userTypeData.equals("Customer")) {
                    query = "UPDATE SignUp SET username='" + userNameData + "', password='" + passwordData + "', usertype='" + userTypeData + "' WHERE meter_no='" + meterData + "';";
                }

                myDB.myStatement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();

            } catch (Exception expobj) {
                System.out.printf("Error in inserting data into database : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
