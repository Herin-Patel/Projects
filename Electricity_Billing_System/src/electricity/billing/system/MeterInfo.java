package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MeterInfo extends JFrame implements ActionListener {

    Choice meterLocChoice, meterTypeChoice, phaseCodeChoice, billTypeChoice;
    JButton submitButton;
    String givenMeterNum;

    MeterInfo(String givenMeterNum) {
        this.givenMeterNum = givenMeterNum;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 186, 3));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50, 80, 100, 20);
        panel.add(meterNumber);

        JLabel meterText = new JLabel(givenMeterNum);
        meterText.setBounds(180, 80, 150, 20);
        panel.add(meterText);

        JLabel meterLoc = new JLabel("Meter Location");
        meterLoc.setBounds(50, 120, 100, 20);
        panel.add(meterLoc);

        meterLocChoice = new Choice();
        meterLocChoice.setBounds(180, 120, 150, 20);
        meterLocChoice.add("Outside");
        meterLocChoice.add("Inside");
        panel.add(meterLocChoice);

        JLabel meterType = new JLabel("Meter Type");
        meterType.setBounds(50, 160, 100, 20);
        panel.add(meterType);

        meterTypeChoice = new Choice();
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        meterTypeChoice.setBounds(180, 160, 150, 20);
        panel.add(meterTypeChoice);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50, 200, 100, 20);
        panel.add(phaseCode);

        phaseCodeChoice = new Choice();
        phaseCodeChoice.add("011");
        phaseCodeChoice.add("022");
        phaseCodeChoice.add("033");
        phaseCodeChoice.add("044");
        phaseCodeChoice.add("055");
        phaseCodeChoice.add("066");
        phaseCodeChoice.add("077");
        phaseCodeChoice.add("088");
        phaseCodeChoice.add("099");
        phaseCodeChoice.setBounds(180, 200, 150, 20);
        panel.add(phaseCodeChoice);

        JLabel billType = new JLabel("Bill Type");
        billType.setBounds(50, 240, 100, 20);
        panel.add(billType);

        billTypeChoice = new Choice();
        billTypeChoice.add("Normal");
        billTypeChoice.add("Industrial");
        billTypeChoice.setBounds(180, 240, 150, 20);
        panel.add(billTypeChoice);

        JLabel day = new JLabel("30 Days Billing Time");
        day.setBounds(50, 280, 150, 20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50, 320, 100, 20);
        panel.add(note);

        JLabel note1 = new JLabel("By default, the bill is calculated for 30 days only.");
        note1.setBounds(50, 340, 300, 20);
        panel.add(note1);

        submitButton = new JButton("Submit");
        submitButton.setBounds(220, 390, 100, 25);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon detailIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/details.png"));
        Image detailIcon2 = detailIcon.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon detailIcon3 = new ImageIcon(detailIcon2);
        JLabel imgLabel = new JLabel(detailIcon3);
        add(imgLabel, "East");

        setSize(700, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String userMeterNum = givenMeterNum;
            String userMeterLoc = meterLocChoice.getSelectedItem();
            String userMeterType = meterTypeChoice.getSelectedItem();
            String userPhaseCode = phaseCodeChoice.getSelectedItem();
            String userBillType = billTypeChoice.getSelectedItem();
            String userDay = "30";

            String query_meterinfo = "INSERT INTO meter_info VALUES('" + userMeterNum + "', '" + userMeterLoc + "', '" + userMeterType + "', '" + userPhaseCode + "', '" + userBillType + "', '" + userDay + "');";

            try {
                Database myDB = new Database();
                myDB.myStatement.executeUpdate(query_meterinfo);

                JOptionPane.showMessageDialog(null, "Meter Information submitted successfully.");
                setVisible(false);
            } catch (Exception expobj) {
                System.out.printf("Error in inserting MeterInfo data to database : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        }
        else {
         setVisible(false);
        }

    }

    public static void main(String[] args) {
        new MeterInfo("");
    }
}
