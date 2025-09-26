import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HazirPizzaSecimPaneli extends JPanel {
    public HazirPizzaSecimPaneli(AnaPencere anaPencere, Sepet sepet, HazirPizza seciliPizza) {
        setLayout(new BorderLayout(20, 20));

        // Başlık
        JLabel baslik = new JLabel("Pizza Seçenekleri - " + seciliPizza.getIsim(), JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 22));
        add(baslik, BorderLayout.NORTH);

        // Tüm malzeme listesi ve hazır pizzanın mevcut malzemeleri
        ArrayList<Malzeme> tumMalzemeler = anaPencere.getTumMalzemeler();
        ArrayList<Malzeme> aktifMalzemeler = new ArrayList<>(seciliPizza.getMalzemeler());

        // SOL: Malzeme seçim paneli (checkbox'lar)
        JPanel solPanel = new JPanel();
        solPanel.setLayout(new BoxLayout(solPanel, BoxLayout.Y_AXIS));
        solPanel.setBorder(BorderFactory.createTitledBorder("Malzeme Seçimi"));
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();

        for (Malzeme m : tumMalzemeler) {
            JCheckBox box = new JCheckBox(m.getIsim() + " (₺" + m.getFiyat() + ")");
            box.setSelected(aktifMalzemeler.contains(m));
            checkBoxes.add(box);
            solPanel.add(box);
        }

        // ORTA: Boyut ve hamur seçim paneli
        JPanel ortaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Boyut
        gbc.gridx = 0; gbc.gridy = 0;
        ortaPanel.add(new JLabel("Boyut:"), gbc);

        JComboBox<PizzaBoyutu> boyutBox = new JComboBox<>(PizzaBoyutu.values());
        boyutBox.setSelectedItem(seciliPizza.getBoyut() != null ? seciliPizza.getBoyut() : PizzaBoyutu.KUCUK);
        gbc.gridx = 1; gbc.gridy = 0;
        ortaPanel.add(boyutBox, gbc);

        // Hamur tipi
        gbc.gridx = 0; gbc.gridy = 1;
        ortaPanel.add(new JLabel("Hamur Tipi:"), gbc);

        JComboBox<HamurTipi> hamurBox = new JComboBox<>(HamurTipi.values());
        hamurBox.setSelectedItem(seciliPizza.getHamur() != null ? seciliPizza.getHamur() : HamurTipi.KLASIK);
        gbc.gridx = 1; gbc.gridy = 1;
        ortaPanel.add(hamurBox, gbc);

        // Fiyat label
        gbc.gridx = 0; gbc.gridy = 2;
        ortaPanel.add(new JLabel("Toplam Fiyat:"), gbc);
        JLabel fiyatLabel = new JLabel();
        gbc.gridx = 1; gbc.gridy = 2;
        ortaPanel.add(fiyatLabel, gbc);

        // Fiyatı güncelleyen fonksiyon
        Runnable fiyatGuncelle = () -> {
            ArrayList<Malzeme> seciliMalzemeler = new ArrayList<>();
            for (int i = 0; i < tumMalzemeler.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    seciliMalzemeler.add(tumMalzemeler.get(i));
                }
            }
            HazirPizza tempPizza = new HazirPizza(
                    seciliPizza.getIsim(),
                    seciliMalzemeler
            );
            tempPizza.setBoyut((PizzaBoyutu) boyutBox.getSelectedItem());
            tempPizza.setHamur((HamurTipi) hamurBox.getSelectedItem());
            fiyatLabel.setText(String.format("₺%.2f", tempPizza.fiyatHesapla()));
        };

        fiyatGuncelle.run(); // Başlangıçta çağır

        // Checkbox, boyut ve hamur seçimi değiştiğinde fiyat güncelle
        for (JCheckBox cb : checkBoxes) cb.addActionListener(e -> fiyatGuncelle.run());
        boyutBox.addActionListener(e -> fiyatGuncelle.run());
        hamurBox.addActionListener(e -> fiyatGuncelle.run());

        // Sepete ekle butonu
        JButton sepeteEkleBtn = new JButton("Sepete Ekle");
        sepeteEkleBtn.setFont(new Font("Arial", Font.BOLD, 17));
        sepeteEkleBtn.addActionListener(e -> {
            ArrayList<Malzeme> seciliMalzemeler = new ArrayList<>();
            for (int i = 0; i < tumMalzemeler.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    seciliMalzemeler.add(tumMalzemeler.get(i));
                }
            }
            HazirPizza yeniPizza = new HazirPizza(
                    seciliPizza.getIsim(),
                    seciliMalzemeler
            );
            yeniPizza.setBoyut((PizzaBoyutu) boyutBox.getSelectedItem());
            yeniPizza.setHamur((HamurTipi) hamurBox.getSelectedItem());
            sepet.pizzaEkle(yeniPizza);
            JOptionPane.showMessageDialog(this, "Pizza sepete eklendi!");
            anaPencere.panelDegistir(new MenuPaneli(anaPencere, sepet));
        });

        // Geri dön butonu
        JButton geriDonBtn = new JButton("Menüye Dön");
        geriDonBtn.addActionListener(e -> anaPencere.panelDegistir(new MenuPaneli(anaPencere, sepet)));
        JPanel butonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        butonPanel.add(sepeteEkleBtn);
        butonPanel.add(geriDonBtn);

        // Layout yerleşimi
        JPanel ortaWrapper = new JPanel(new BorderLayout());
        ortaWrapper.add(ortaPanel, BorderLayout.CENTER);
        ortaWrapper.add(butonPanel, BorderLayout.SOUTH);

        add(solPanel, BorderLayout.WEST);
        add(ortaWrapper, BorderLayout.CENTER);
    }
}
