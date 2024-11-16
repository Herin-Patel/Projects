import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import java.awt.BorderLayout;
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
        panel.setBackground(new Color(250, 201, 77));
        add(panel);

        JLabel mainHeading = new JLabel("Meter Information");
        mainHeading.setBounds(130, 10, 200, 20);
        mainHeading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(mainHeading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setFont(new Font("senserif", Font.BOLD, 15));
        meterNumber.setBounds(50, 80, 120, 20);
        panel.add(meterNumber);

        JLabel meterText = new JLabel(givenMeterNum);
        meterText.setBounds(200, 80, 150, 25);
        panel.add(meterText);

        JLabel meterLoc = new JLabel("Meter Location");
        meterLoc.setFont(new Font("senserif", Font.BOLD, 15));
        meterLoc.setBounds(50, 120, 120, 20);
        panel.add(meterLoc);

        meterLocChoice = new Choice();
        meterLocChoice.setBounds(200, 120, 150, 25);
        meterLocChoice.add("Outside");
        meterLocChoice.add("Inside");
        panel.add(meterLocChoice);

        JLabel meterType = new JLabel("Meter Type");
        meterType.setFont(new Font("senserif", Font.BOLD, 15));
        meterType.setBounds(50, 160, 120, 20);
        panel.add(meterType);

        meterTypeChoice = new Choice();
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        meterTypeChoice.setBounds(200, 160, 150, 25);
        panel.add(meterTypeChoice);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setFont(new Font("senserif", Font.BOLD, 15));
        phaseCode.setBounds(50, 200, 120, 20);
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
        phaseCodeChoice.setBounds(200, 200, 150, 25);
        panel.add(phaseCodeChoice);

        JLabel billType = new JLabel("Bill Type");
        billType.setFont(new Font("senserif", Font.BOLD, 15));
        billType.setBounds(50, 240, 120, 20);
        panel.add(billType);

        billTypeChoice = new Choice();
        billTypeChoice.add("Normal");
        billTypeChoice.add("Industrial");
        billTypeChoice.setBounds(200, 240, 150, 25);
        panel.add(billTypeChoice);

        JLabel day = new JLabel("30 Days Billing Time");
        day.setFont(new Font("senserif", Font.BOLD, 15));
        day.setBounds(50, 280, 150, 20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setFont(new Font("senserif", Font.BOLD, 15));
        note.setBounds(50, 320, 100, 20);
        panel.add(note);

        JLabel subNote = new JLabel("By default, the bill is calculated for 30 days only.");
        subNote.setFont(new Font("senserif", Font.BOLD, 15));
        subNote.setBounds(50, 340, 370, 20);
        panel.add(subNote);

        submitButton = CustomUtil.setButton("Submit", 16);
        submitButton.setBounds(230, 390, 120, 30);
        submitButton.setBackground(Color.BLACK);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        setLayout(new BorderLayout());
        add(panel, "Center");

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\details.png";
        ImageIcon detailIcon3 = CustomUtil.setImage(imagePath, 230,200);
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

                JOptionPane.showMessageDialog(this, "Meter Information submitted successfully.");
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
