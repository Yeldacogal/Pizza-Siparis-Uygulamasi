import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OzelPizzaPaneli extends JPanel {
    public OzelPizzaPaneli(AnaPencere anaPencere, Sepet sepet) {
        setLayout(new BorderLayout(15, 15));

        // Başlık
        JLabel baslik = new JLabel("Kendi Pizzanı Oluştur", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 22));
        add(baslik, BorderLayout.NORTH);

        JPanel ortaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // 1. Pizza İsmi (isteğe bağlı)
        gbc.gridx = 0; gbc.gridy = 0;
        ortaPanel.add(new JLabel("Pizza İsmi (isteğe bağlı):"), gbc);
        JTextField isimAlani = new JTextField(15);
        gbc.gridx = 1;
        ortaPanel.add(isimAlani, gbc);

        // 2. Boyut seçimi
        gbc.gridx = 0; gbc.gridy = 1;
        ortaPanel.add(new JLabel("Boyut:"), gbc);
        JComboBox<PizzaBoyutu> boyutBox = new JComboBox<>(PizzaBoyutu.values());
        gbc.gridx = 1;
        ortaPanel.add(boyutBox, gbc);

        // 3. Hamur tipi seçimi
        gbc.gridx = 0; gbc.gridy = 2;
        ortaPanel.add(new JLabel("Hamur Tipi:"), gbc);
        JComboBox<HamurTipi> hamurBox = new JComboBox<>(HamurTipi.values());
        gbc.gridx = 1;
        ortaPanel.add(hamurBox, gbc);

        // 4. Malzeme seçimi (checkbox listesi)
        gbc.gridx = 0; gbc.gridy = 3;
        ortaPanel.add(new JLabel("Malzemeler:"), gbc);
        JPanel malzemePanel = new JPanel();
        malzemePanel.setLayout(new BoxLayout(malzemePanel, BoxLayout.Y_AXIS));

        ArrayList<Malzeme> tumMalzemeler = anaPencere.getTumMalzemeler(); // AnaPencere'de sabit malzeme listesi olsun!
        ArrayList<JCheckBox> malzemeBoxlar = new ArrayList<>();

        for (Malzeme m : tumMalzemeler) {
            JCheckBox checkBox = new JCheckBox(m.getIsim() + " (₺" + m.getFiyat() + ")");
            malzemePanel.add(checkBox);
            malzemeBoxlar.add(checkBox);
        }
        JScrollPane malzemeScroll = new JScrollPane(malzemePanel);
        malzemeScroll.setPreferredSize(new Dimension(200, 120));
        gbc.gridx = 1;
        ortaPanel.add(malzemeScroll, gbc);

        // 5. Fiyat alanı (dinamik)
        gbc.gridx = 0; gbc.gridy = 4;
        ortaPanel.add(new JLabel("Toplam Fiyat:"), gbc);
        JLabel fiyatLabel = new JLabel();
        gbc.gridx = 1;
        ortaPanel.add(fiyatLabel, gbc);

        // Dinamik fiyat hesaplama fonksiyonu
        Runnable fiyatGuncelle = () -> {
            ArrayList<Malzeme> seciliMalzemeler = new ArrayList<>();
            for (int i = 0; i < tumMalzemeler.size(); i++) {
                if (malzemeBoxlar.get(i).isSelected()) {
                    seciliMalzemeler.add(tumMalzemeler.get(i));
                }
            }
            OzelPizza tempPizza = new OzelPizza(
                    isimAlani.getText().isEmpty() ? "Özel Pizza" : isimAlani.getText(),
                    seciliMalzemeler
            );
            tempPizza.setBoyut((PizzaBoyutu) boyutBox.getSelectedItem());
            tempPizza.setHamur((HamurTipi) hamurBox.getSelectedItem());
            fiyatLabel.setText(String.format("₺%.2f", tempPizza.fiyatHesapla()));
        };

        // Bütün giriş alanlarına listener ekle
        for (JCheckBox cb : malzemeBoxlar)
            cb.addActionListener(e -> fiyatGuncelle.run());
        boyutBox.addActionListener(e -> fiyatGuncelle.run());
        hamurBox.addActionListener(e -> fiyatGuncelle.run());
        isimAlani.addActionListener(e -> fiyatGuncelle.run());

        fiyatGuncelle.run();

        // 6. Sepete ekle butonu
        JButton sepeteEkleBtn = new JButton("Sepete Ekle");
        sepeteEkleBtn.setFont(new Font("Arial", Font.BOLD, 18));
        sepeteEkleBtn.addActionListener(e -> {
            ArrayList<Malzeme> seciliMalzemeler = new ArrayList<>();
            for (int i = 0; i < tumMalzemeler.size(); i++) {
                if (malzemeBoxlar.get(i).isSelected()) {
                    seciliMalzemeler.add(tumMalzemeler.get(i));
                }
            }
            if (seciliMalzemeler.isEmpty()) {
                JOptionPane.showMessageDialog(this, "En az bir malzeme seçmelisiniz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }
            OzelPizza sepetePizza = new OzelPizza(
                    isimAlani.getText().isEmpty() ? "Özel Pizza" : isimAlani.getText(),
                    seciliMalzemeler
            );
            sepetePizza.setBoyut((PizzaBoyutu) boyutBox.getSelectedItem());
            sepetePizza.setHamur((HamurTipi) hamurBox.getSelectedItem());
            sepet.pizzaEkle(sepetePizza);
            JOptionPane.showMessageDialog(this, "Pizza sepete eklendi!");
            anaPencere.panelDegistir(new MenuPaneli(anaPencere, sepet));
        });

        // 7. Menüye dön (geri) butonu
        JButton geriDonBtn = new JButton("Menüye Dön");
        geriDonBtn.addActionListener(e -> anaPencere.panelDegistir(new MenuPaneli(anaPencere, sepet)));

        JPanel butonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        butonPanel.add(sepeteEkleBtn);
        butonPanel.add(geriDonBtn);

        add(ortaPanel, BorderLayout.CENTER);
        add(butonPanel, BorderLayout.SOUTH);
    }
}
