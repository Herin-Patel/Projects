import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentGateway extends JFrame implements ActionListener {
    String meterNumber;
    JButton backButton;

    PaymentGateway(String meterNumber) {
        super("Payment Bill");
        this.meterNumber = meterNumber;

        JEditorPane paneEditor = new JEditorPane();
        paneEditor.setEditable(false);

        try {
//            paneEditor.setPage("https://www.google.co.in/?gfe_rd=cr&ei=LDGrWIj6A8Hy8Aejj5GACQ&gws_rd=ssl");
            paneEditor.setPage("https://paytm.com/online-payments");
            paneEditor.setBounds(0, 0, 1000, 800);

        } catch (Exception expobj) {
            System.out.printf("Error in loading data to HTML pafe : %s\n", expobj.getMessage());
            expobj.printStackTrace();
            paneEditor.setContentType("text/html");
            paneEditor.setText("<html><head><h1>Error ! Could not load the data. </html>");
        }
        add(paneEditor);

        JScrollPane paneScroll = new JScrollPane();
        add(paneScroll);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(10, 0, 80, 30);
        backButton.addActionListener(this);
        paneEditor.add(backButton);

        setSize(1000, 800);
        setLocation(350, 0);
//        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PayBill(meterNumber);
    }

    public static void main(String[] args) {
        new PaymentGateway("");
    }
}
