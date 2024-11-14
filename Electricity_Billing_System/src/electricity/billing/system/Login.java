package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText, passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");
        getContentPane().setBackground(Color.LIGHT_GRAY);
        JLabel username = new JLabel("UserName");
        username.setBounds(300, 60, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        JLabel login = new JLabel("Login In As");
        login.setBounds(300, 140, 100, 20);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400, 140, 150, 20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setBounds(330, 180, 100, 20);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(460, 180, 100, 20);
        add(cancelButton);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        signupButton.setBounds(400, 215, 100, 20);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("Icons/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon profileThree = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(profileThree);
        profileLabel.setBounds(5, 5, 250, 250);
        add(profileLabel);

        setSize(640, 300);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Back-End data
            String userName = userText.getText();
            String userPassword = passwordText.getText();
            String userType = loginChoice.getSelectedItem();

            try {
                Database myDB = new Database();
                String query = "SELECT * FROM SignUp WHERE username = '" + userName + "' AND password = '" + userPassword + "' AND usertype = '" + userType + "';";
                ResultSet resultSet = myDB.myStatement.executeQuery(query);
//                System.out.printf("Result : %s\n", resultSet.toString());

                if (resultSet.next()) {
                    String meterNo = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(userType, meterNo);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Details");
                }
            } catch (Exception expobj) {
                System.out.printf("Error in Login data : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            // Close application
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new SignUp();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
