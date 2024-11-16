import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JTextField userText;
    JPasswordField passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel username = new JLabel("Username");
        username.setFont(new Font("senserif", Font.BOLD, 18));
        username.setBounds(300, 60, 120, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(420, 60, 150, 25);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setFont(new Font("senserif", Font.BOLD, 18));
        password.setBounds(300, 100, 120, 20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(420, 100, 150, 25);
        add(passwordText);

        JLabel login = new JLabel("Login As");
        login.setFont(new Font("senserif", Font.BOLD, 18));
        login.setBounds(300, 140, 120, 20);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(420, 140, 150, 30);
        add(loginChoice);

        loginButton = CustomUtil.setButton("Login", 16);
        loginButton.addActionListener(this);
        loginButton.setBounds(330, 180, 100, 30);
        add(loginButton);

        cancelButton = CustomUtil.setButton("Cancel", 16);
        cancelButton.addActionListener(this);
        cancelButton.setBounds(460, 180, 100, 30);
        add(cancelButton);

        signupButton = CustomUtil.setButton("Sign Up", 16);
        signupButton.addActionListener(this);
        signupButton.setBounds(400, 220, 100, 30);
        add(signupButton);

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\profile.png";
        ImageIcon scaledProfile = CustomUtil.setImage(imagePath, 250, 250);
        JLabel profileLabel = new JLabel(scaledProfile);
        profileLabel.setBounds(5, 5, 250, 250);
        add(profileLabel);

        setSize(630, 300);
        setLocation(450, 200);
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

            // Check if username or password is empty
            if (userName.isEmpty() || userPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all credentials", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method
            }

            try {
                Database myDB = new Database();
                String query = "SELECT * FROM SignUp WHERE username = '" + userName + "' AND password = '" + userPassword + "' AND usertype = '" + userType + "';";
                ResultSet resultSet = myDB.myStatement.executeQuery(query);

                if (resultSet.next()) {
                    String meterNo = resultSet.getString("meter_no");
                    setVisible(false);
                    new MainDashBoard(userType, meterNo);
                    JOptionPane.showMessageDialog(this, userType + " logged-in successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Login Details");
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
