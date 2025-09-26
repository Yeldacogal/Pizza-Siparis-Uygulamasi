import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;


public class GirisPaneli extends JPanel {
    private JTextField kullaniciAlani;
    private JPasswordField sifreAlani;
    private JButton girisButonu, kayitButonu;

    public GirisPaneli(AnaPencere anaPencere) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel kullaniciLabel = new JLabel("Kullanıcı Adı:");
        JLabel sifreLabel = new JLabel("Şifre:");

        kullaniciAlani = new JTextField(15);
        sifreAlani = new JPasswordField(15);

        girisButonu = new JButton("Giriş Yap");
        kayitButonu = new JButton("Kayıt Ol");

        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(5,5,5,5);
        add(kullaniciLabel, gbc);

        gbc.gridx = 1;
        add(kullaniciAlani, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(sifreLabel, gbc);

        gbc.gridx = 1;
        add(sifreAlani, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(girisButonu, gbc);

        gbc.gridx = 1;
        add(kayitButonu, gbc);

        // Giriş Butonu
        girisButonu.addActionListener(e -> {
            String kullaniciAdi = kullaniciAlani.getText();
            String sifre = new String(sifreAlani.getPassword());

            if (anaPencere.girisYap(kullaniciAdi, sifre)) {
                anaPencere.panelDegistir(new MenuPaneli(anaPencere, anaPencere.getSepet())); // menüye geçiş
            } else {
                JOptionPane.showMessageDialog(this, "Hatalı kullanıcı adı veya şifre!", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Kayıt Butonu
        kayitButonu.addActionListener(e -> {
            String kullaniciAdi = kullaniciAlani.getText();
            String sifre = new String(sifreAlani.getPassword());

            if (anaPencere.kayitOl(kullaniciAdi, sifre)) {
                JOptionPane.showMessageDialog(this, "Kayıt başarılı! Şimdi giriş yapabilirsiniz.");
            } else {
                JOptionPane.showMessageDialog(this, "Bu kullanıcı adı zaten alınmış.", "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });


    }

    public String getKullaniciAdi() {
        return kullaniciAlani.getText();
    }
    public String getSifre() {
        return new String(sifreAlani.getPassword());
    }
    public JButton getGirisButonu() { return girisButonu; }
    public JButton getKayitButonu() { return kayitButonu; }
}
