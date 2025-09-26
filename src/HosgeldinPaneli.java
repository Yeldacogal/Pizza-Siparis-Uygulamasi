import javax.swing.*;
import java.awt.*;

public class HosgeldinPaneli extends JPanel {
    public HosgeldinPaneli(String kullaniciAdi) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Hoş geldin, " + kullaniciAdi + "!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
}
