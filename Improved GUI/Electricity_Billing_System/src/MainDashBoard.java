import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainDashBoard extends JFrame implements ActionListener {
    String accountType, meter_pass, imagePath;

    MainDashBoard(String accountType, String meter_pass) {
        super("Main Window");
        this.accountType = accountType;
        this.meter_pass = meter_pass;

        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\ebs.png";
        ImageIcon bgIcon = CustomUtil.setImage(imagePath, 1530, 830);
        JLabel bgLabel = new JLabel(bgIcon);
        add(bgLabel);

        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.setBorderPainted(true);
        mainMenuBar.setBackground(Color.LIGHT_GRAY);
        setJMenuBar(mainMenuBar);

        JMenu menuTab = new JMenu("Menu");
        menuTab.setFont(new Font("serif", Font.BOLD, 20));
//        mainMenuBar.add(menuTab);

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\newCustomer.png";
        ImageIcon customerIcon = CustomUtil.setImage(imagePath, 30, 30);
        newCustomer.setIcon(customerIcon);
        newCustomer.addActionListener(this);
        menuTab.add(newCustomer);

        JMenuItem customerDetail = new JMenuItem("Customer Details");
        customerDetail.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\customerDetails.png";
        ImageIcon detailIcon = CustomUtil.setImage(imagePath, 30, 30);
        customerDetail.setIcon(detailIcon);
        customerDetail.addActionListener(this);
        menuTab.add(customerDetail);

        JMenuItem depositDetail = new JMenuItem("Deposit Details");
        depositDetail.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\depositDetails.png";
        ImageIcon depositIcon = CustomUtil.setImage(imagePath, 30, 30);
        depositDetail.setIcon(depositIcon);
        depositDetail.addActionListener(this);
        menuTab.add(depositDetail);

        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\calculateBill.png";
        ImageIcon calBillIcon = CustomUtil.setImage(imagePath, 30, 30);
        calculateBill.setIcon(calBillIcon);
        calculateBill.addActionListener(this);
        menuTab.add(calculateBill);

        JMenu infoTab = new JMenu("Information");
        infoTab.setFont(new Font("serif", Font.BOLD, 20));
//        mainMenuBar.add(infoTab);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\information.png";
        ImageIcon viewIcon = CustomUtil.setImage(imagePath, 30, 30);
        viewInfo.setIcon(viewIcon);
        viewInfo.addActionListener(this);
        infoTab.add(viewInfo);

        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\refresh.png";
        ImageIcon updateIcon = CustomUtil.setImage(imagePath, 30, 30);
        updateInfo.setIcon(updateIcon);
        updateInfo.addActionListener(this);
        infoTab.add(updateInfo);

        JMenu userTab = new JMenu("User");
        userTab.setFont((new Font("serif", Font.BOLD, 20)));
//        mainMenuBar.add(userTab);

        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\pay.png";
        ImageIcon payIcon = CustomUtil.setImage(imagePath, 30, 30);
        payBill.setIcon(payIcon);
        payBill.addActionListener(this);
        userTab.add(payBill);

        JMenuItem billDetail = new JMenuItem("Bill Details");
        billDetail.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\detail.png";
        ImageIcon billDetailIcon = CustomUtil.setImage(imagePath, 30, 30);
        billDetail.setIcon(billDetailIcon);
        billDetail.addActionListener(this);
        userTab.add(billDetail);

        JMenu billTab = new JMenu("Bill");
        billTab.setFont(new Font("serif", Font.BOLD, 20));
//        mainMenuBar.add(billTab);

        JMenuItem genBill = new JMenuItem("Generate Bill");
        genBill.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\bill.png";
        ImageIcon genBillIcon = CustomUtil.setImage(imagePath, 30, 30);
        genBill.setIcon(genBillIcon);
        genBill.addActionListener(this);
        billTab.add(genBill);

        JMenu utilityTab = new JMenu("Utility");
        utilityTab.setFont(new Font("serif", Font.BOLD, 20));
//        mainMenuBar.add(utilityTab);

        JMenuItem notePad = new JMenuItem("Notepad");
        notePad.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\notepad.png";
        ImageIcon noteIcon = CustomUtil.setImage(imagePath, 30, 30);
        notePad.setIcon(noteIcon);
        notePad.addActionListener(this);
        utilityTab.add(notePad);

        JMenuItem calc = new JMenuItem("Calculator");
        calc.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\calculator.png";
        ImageIcon calcIcon = CustomUtil.setImage(imagePath, 30, 30);
        calc.setIcon(calcIcon);
        calc.addActionListener(this);
        utilityTab.add(calc);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new FlowLayout());
        setVisible(true);

        JMenu exitTab = new JMenu("Exit");
        exitTab.setFont(new Font("serif", Font.BOLD, 20));
//        mainMenuBar.add(exitTab);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", Font.BOLD, 18));
        imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\exit.png";
        ImageIcon exitIcon = CustomUtil.setImage(imagePath, 30, 30);
        exit.setIcon(exitIcon);
        exit.addActionListener(this);
        exitTab.add(exit);

        if (accountType.equals("Admin")) {
            mainMenuBar.add(menuTab);
        } else if (accountType.equals("Customer")) {
            mainMenuBar.add(infoTab);
            mainMenuBar.add(userTab);
            mainMenuBar.add(billTab);
        }
        mainMenuBar.add(utilityTab);
        mainMenuBar.add(exitTab);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("New Customer")) {
            new NewCustomer();
        } else if (action.equals("Customer Details")) {
            new CustomerDetails();
        } else if (action.equals("Deposit Details")) {
            new DepositDetails();
        } else if (action.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (action.equals("View Information")) {
            new ViewInformation(meter_pass);
        } else if (action.equals("Update Information")) {
            new UpdateInformation(meter_pass);
        } else if (action.equals("Bill Details")) {
            new BillDetails(meter_pass);
        } else if (action.equals("Pay Bill")) {
            new PayBill(meter_pass);
        } else if (action.equals("Generate Bill")) {
            new GenerateBill(meter_pass);
        } else if (action.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception expobj) {
                System.out.printf("Runtime Error in opening calculator.exe : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (action.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception expobj) {
                System.out.printf("Runtime Error in opening notepad.exe : %s\n", expobj.getMessage());
                expobj.printStackTrace();
            }
        } else if (action.equals("Exit")) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, accountType + " has logged out successfully.");
            new Login();
        }
    }

    public static void main(String[] args) {
        new MainDashBoard("", "");
    }
}
