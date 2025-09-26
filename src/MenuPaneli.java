import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuPaneli extends JPanel {
    public MenuPaneli(AnaPencere anaPencere, Sepet sepet) {
        setLayout(new BorderLayout());

        // Başlık
        JLabel baslik = new JLabel("Pizza ve Yan Ürün Menüsü", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 28));
        baslik.setBorder(BorderFactory.createEmptyBorder(16,0,12,0));
        add(baslik, BorderLayout.NORTH);

        // Orta panel
        JPanel urunlerPaneli = new JPanel();
        urunlerPaneli.setLayout(new BoxLayout(urunlerPaneli, BoxLayout.Y_AXIS));
        urunlerPaneli.setBackground(new Color(245, 245, 245));

        // --- HAZIR PIZZALAR ---
        ArrayList<HazirPizza> hazirPizzalar = anaPencere.getHazirPizzalar();
        for (HazirPizza pizza : hazirPizzalar) {
            JPanel kart = new JPanel(new BorderLayout());
            kart.setMaximumSize(new Dimension(700, 60));
            kart.setBackground(Color.WHITE);
            kart.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(8, 16, 8, 16),
                    BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true)
            ));

            JLabel isim = new JLabel(pizza.getIsim());
            isim.setFont(new Font("Arial", Font.BOLD, 16));
            kart.add(isim, BorderLayout.WEST);

            JPanel sagPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 4));
            sagPanel.setOpaque(false);
            sagPanel.add(new JLabel(String.format("Fiyat: ₺%.2f", pizza.malzemeFiyatiHesapla())));
            JButton secBtn = new JButton("Seç");
            secBtn.addActionListener(e -> anaPencere.panelDegistir(new HazirPizzaSecimPaneli(anaPencere, sepet, pizza)));
            sagPanel.add(secBtn);
            kart.add(sagPanel, BorderLayout.EAST);

            urunlerPaneli.add(kart);
            urunlerPaneli.add(Box.createVerticalStrut(8));
        }

        // --- YAN ÜRÜNLER ---
        ArrayList<YanUrun> yanUrunler = anaPencere.getYanUrunler();
        if (!yanUrunler.isEmpty()) {
            JLabel yanUrunBaslik = new JLabel("Yan Ürünler");
            yanUrunBaslik.setFont(new Font("Arial", Font.BOLD, 19));
            yanUrunBaslik.setAlignmentX(Component.CENTER_ALIGNMENT);
            urunlerPaneli.add(Box.createVerticalStrut(8));
            urunlerPaneli.add(yanUrunBaslik);
            urunlerPaneli.add(Box.createVerticalStrut(5));

            for (YanUrun yanUrun : yanUrunler) {
                JPanel kart = new JPanel(new BorderLayout());
                kart.setMaximumSize(new Dimension(700, 60));
                kart.setBackground(Color.WHITE);
                kart.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(8, 16, 8, 16),
                        BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true)
                ));

                JLabel isim = new JLabel(yanUrun.getIsim());
                isim.setFont(new Font("Arial", Font.PLAIN, 15));
                kart.add(isim, BorderLayout.WEST);

                JPanel sagPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 4));
                sagPanel.setOpaque(false);
                sagPanel.add(new JLabel(String.format("Fiyat: ₺%.2f", yanUrun.getFiyat())));
                JButton sepeteEkleBtn = new JButton("Sepete Ekle");
                sepeteEkleBtn.addActionListener(e -> {
                    sepet.yanUrunEkle(yanUrun);
                    JOptionPane.showMessageDialog(this, yanUrun.getIsim() + " sepete eklendi!");
                    anaPencere.panelDegistir(new MenuPaneli(anaPencere, sepet));
                });
                sagPanel.add(sepeteEkleBtn);
                kart.add(sagPanel, BorderLayout.EAST);

                urunlerPaneli.add(kart);
                urunlerPaneli.add(Box.createVerticalStrut(8));
            }
        }

        // --- KENDİ PİZZANI OLUŞTUR ---
        JPanel customPanel = new JPanel();
        customPanel.setMaximumSize(new Dimension(700, 40));
        customPanel.setBackground(new Color(245,245,245));
        JButton customPizzaBtn = new JButton("Kendi Pizzanı Oluştur");
        customPizzaBtn.setFont(new Font("Arial", Font.BOLD, 14));
        customPizzaBtn.addActionListener(e -> anaPencere.panelDegistir(new OzelPizzaPaneli(anaPencere, sepet)));
        customPanel.add(customPizzaBtn);
        urunlerPaneli.add(customPanel);
        urunlerPaneli.add(Box.createVerticalStrut(8));

        // --- SCROLLABLE ---
        JScrollPane scrollPane = new JScrollPane(urunlerPaneli);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // --- SEPETİ GÖRÜNTÜLE ---
        JButton sepetiGoruntule = new JButton("Sepeti Görüntüle");
        sepetiGoruntule.setFont(new Font("Arial", Font.BOLD, 13));
        sepetiGoruntule.addActionListener(e -> anaPencere.panelDegistir(new SepetPaneli(anaPencere, sepet)));
        add(sepetiGoruntule, BorderLayout.SOUTH);
    }
}
