package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class main_class extends JFrame {

    main_class() {
        super("Main Window");
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/ebs.png"));
        Image bgIcon2 = bgIcon.getImage().getScaledInstance(1530, 830, Image.SCALE_DEFAULT);  // 1530, 830
        ImageIcon bgIcon3 = new ImageIcon(bgIcon2);
        JLabel bgLabel = new JLabel(bgIcon3);
        add(bgLabel);

        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.setBorderPainted(true);
        mainMenuBar.setBackground(Color.LIGHT_GRAY);
        setJMenuBar(mainMenuBar);

        JMenu menuTab = new JMenu("Menu");
        menuTab.setFont(new Font("serif", Font.BOLD, 20));
        mainMenuBar.add(menuTab);

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon customerIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/newCustomer.png"));
        Image customerIcon2 = customerIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon customerIcon3 = new ImageIcon(customerIcon2);
        newCustomer.setIcon(customerIcon3);
        menuTab.add(newCustomer);

        JMenuItem customerDetail = new JMenuItem("Customer Details");
        customerDetail.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon detailIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/customerDetails.png"));
        Image detailIcon2 = detailIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon detailIcon3 = new ImageIcon(detailIcon2);
        customerDetail.setIcon(detailIcon3);
        menuTab.add(customerDetail);

        JMenuItem depositDetail = new JMenuItem("Deposit Details");
        depositDetail.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon depositIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/depositDetails.png"));
        Image depositIcon2 = depositIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon depositIcon3 = new ImageIcon(depositIcon2);
        depositDetail.setIcon(depositIcon3);
        menuTab.add(depositDetail);

        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon calBillIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/calculateBill.png"));
        Image calBillIcon2 = calBillIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon calBillIcon3 = new ImageIcon(calBillIcon2);
        calculateBill.setIcon(calBillIcon3);
        menuTab.add(calculateBill);

        JMenu infoTab = new JMenu("Information");
        infoTab.setFont(new Font("serif", Font.BOLD, 20));
        mainMenuBar.add(infoTab);

        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon updateIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/refresh.png"));
        Image updateIcon2 = updateIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon updateIcon3 = new ImageIcon(updateIcon2);
        updateInfo.setIcon(updateIcon3);
        infoTab.add(updateInfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon viewIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/information.png"));
        Image viewIcon2 = viewIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon viewIcon3 = new ImageIcon(viewIcon2);
        viewInfo.setIcon(viewIcon3);
        infoTab.add(viewInfo);

        JMenu userTab = new JMenu("User");
        userTab.setFont((new Font("serif", Font.BOLD, 20)));
        mainMenuBar.add(userTab);

        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon payIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/pay.png"));
        Image payIcon2 = payIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon payIcon3 = new ImageIcon(payIcon2);
        payBill.setIcon(payIcon3);
        userTab.add(payBill);

        JMenuItem billDetail = new JMenuItem("Bill Details");
        billDetail.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon billDetailIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/detail.png"));
        Image billDetailIcon2 = billDetailIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon billDetailIcon3 = new ImageIcon(billDetailIcon2);
        billDetail.setIcon(billDetailIcon3);
        userTab.add(billDetail);

        JMenu billTab = new JMenu("Bill");
        billTab.setFont(new Font("serif", Font.BOLD, 20));
        mainMenuBar.add(billTab);

        JMenuItem genBill = new JMenuItem("Generate Bill");
        genBill.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon genBillIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/bill.png"));
        Image genBillIcon2 = genBillIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon genBillIcon3 = new ImageIcon(genBillIcon2);
        genBill.setIcon(genBillIcon3);
        billTab.add(genBill);

        JMenu utilityTab = new JMenu("Utility");
        utilityTab.setFont(new Font("serif", Font.BOLD, 20));
        mainMenuBar.add(utilityTab);

        JMenuItem notePad = new JMenuItem("Notepade");
        notePad.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon noteIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/notepad.png"));
        Image noteIcon2 = noteIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon noteIcon3 = new ImageIcon(noteIcon2);
        notePad.setIcon(noteIcon3);
        utilityTab.add(notePad);

        JMenuItem calc = new JMenuItem("Calculator");
        calc.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon calcIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/calculator.png"));
        Image calcIcon2 = calcIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon calcIcon3 = new ImageIcon(calcIcon2);
        calc.setIcon(calcIcon3);
        utilityTab.add(calc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new FlowLayout());
        setVisible(true);

        JMenu exitTab = new JMenu("Exit");
        exitTab.setFont(new Font("serif", Font.BOLD, 20));
        mainMenuBar.add(exitTab);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", Font.BOLD, 18));
        ImageIcon exitIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/exit.png"));
        Image exitIcon2 = exitIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon exitIcon3 = new ImageIcon(exitIcon2);
        exit.setIcon(exitIcon3);
        exitTab.add(exit);

    }

    public static void main(String[] args) {
        new main_class();
    }
}
