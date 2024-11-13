package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SignUp extends JFrame implements ActionListener {
    Choice loginAs;
    TextField meterText, employerText, userText, nameText, passwordText;
    JButton createButton, backButton;

    SignUp() {
        super("SignUp Page");
        getContentPane().setBackground(new Color(168, 203, 255));

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30, 50, 125, 20);
        add(createAs);

        loginAs = new Choice();
        loginAs.add("Admin");
        loginAs.add("Customer");
        loginAs.setBounds(170, 50, 125, 20);
        add(loginAs);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);
        add(meterText);

        JLabel employerID = new JLabel("Employer ID");
        employerID.setBounds(30, 100, 125, 20);
        employerID.setVisible(true);
        add(employerID);

        employerText = new TextField();
        employerText.setBounds(170, 100, 125, 20);
        employerText.setVisible(true);
        add(employerText);

        JLabel userName = new JLabel("User Name");
        userName.setBounds(30, 140, 125, 20);
        add(userName);

        userText = new TextField();
        userText.setBounds(170, 140, 125, 20);
        add(userText);

        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        nameText = new TextField();
        nameText.setBounds(170, 180,125,20);
        add(nameText);

        JLabel password = new JLabel("Password");
        password.setBounds(30, 220, 125, 20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170, 220, 125, 20);
        add(passwordText);

        loginAs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginAs.getSelectedItem();
                if (user.equals("Customer")){
                    employerID.setVisible(false);
                    employerText.setVisible(false);

                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }
                else {
                    employerID.setVisible(true);
                    employerText.setVisible(true);

                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        createButton = new JButton("Create");
        createButton.setBackground(new Color(66, 127, 219));
        createButton.setForeground(Color.WHITE);
        createButton.setBounds(50,285, 100, 25);
        createButton.addActionListener(this);
        add(createButton);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(66, 127, 219));
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(180, 285, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        ImageIcon signIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/boy.png"));
        Image signIcon2 = signIcon.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
        ImageIcon signIcon3 = new ImageIcon(signIcon2);
        JLabel signLabel = new JLabel(signIcon3);
        signLabel.setBounds(320,30,250,250);
        add(signLabel);


        setSize(600, 388 );
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==createButton){
            // Back-end data stored in MySQL Database

            String userTypeData = loginAs.getSelectedItem();
            String userNameData = userText.getText();
            String nameData = nameText.getText();
            String passwordData = passwordText.getText();
            String meterData = meterText.getText();

            try {
                Database myDB = new Database();
                String query = null;
                query = "INSERT INTO SignUp VALUE ('" + meterData + "', '" + userNameData + "', '" + nameData + "', '" + passwordData + "', '" + userTypeData + "');";

                myDB.myStatement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();
            }
            catch(Exception expobj){
                System.out.printf("Error in inserting data into database : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }

        }else if(e.getSource()==backButton){
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
