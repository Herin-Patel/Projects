import javax.swing.*;

public class Splash extends JFrame {
    Splash() {
        super("Loading...");

        String imagePath = "C:\\TCS\\Projects\\Electricity_Billing_System\\Utils\\Icons\\Splash.jpg";
        ImageIcon scaledIcon = CustomUtil.setImage(imagePath, 500,400);
        JLabel imageLabel = new JLabel(scaledIcon);
        add(imageLabel);

        // Configure JFrame settings
        setSize(500, 400); // Match image dimensions
        setLocation(500, 200);
        setVisible(true);

        // Show splash for 3 seconds
        try {
            Thread.sleep(3000);
            setVisible(false);
            new Login();
        } catch (Exception e) {
            System.out.printf("Error in Splash Image: %s\n", e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
