import javax.swing.*;
import java.awt.*;
import java.io.File;

import static java.lang.System.exit;

public class CustomUtil {

    public static ImageIcon setImage(String imagePath, int width, int height){
        // Check if the file exists or not
        File checkFile = new File(imagePath);
        if (!checkFile.exists()){
            System.out.println("File not found at: " + imagePath);
            exit(1);
        }

        // Load and Scale the image
        ImageIcon bgImg = new ImageIcon(imagePath);
        if(bgImg.getIconWidth()==-1){
            System.out.println("Failed to load image.");
            exit(1);
        }
        Image scaledImg = bgImg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return (new ImageIcon(scaledImg));
    }

    public static JButton setButton(String buttonName, int size){
        JButton button = new JButton(buttonName);
        button.setBackground(new Color(103, 99, 224));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("senserif", Font.BOLD, size));
        return button;
    }

    public static void addMonths(Choice element){
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        for(String currentMonth : months)
            element.add(currentMonth);
    }
}
