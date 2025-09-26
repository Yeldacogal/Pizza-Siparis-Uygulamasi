import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SepetPaneli extends JPanel {
    public SepetPaneli(AnaPencere anaPencere, Sepet sepet) {
        setLayout(new BorderLayout(15, 15));

        JLabel baslik = new JLabel("Sepetiniz", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 22));
        add(baslik, BorderLayout.NORTH);

        // Ana panel
        JPanel icerikPanel = new JPanel();
        icerikPanel.setLayout(new BoxLayout(icerikPanel, BoxLayout.Y_AXIS));

        // Sepette pizza var mı?
        ArrayList<Pizza> pizzalar = sepet.getPizzalar();
        ArrayList<YanUrun> yanUrunler = sepet.getYanUrunler();

        if (pizzalar.isEmpty() && yanUrunler.isEmpty()) {
            JLabel bosLabel = new JLabel("Sepetiniz boş.");
            bosLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            bosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            icerikPanel.add(Box.createVerticalStrut(30));
            icerikPanel.add(bosLabel);
        } else {
            // Pizzaları listele
            if (!pizzalar.isEmpty()) {
                JLabel pizzaBaslik = new JLabel("Pizzalar:");
                pizzaBaslik.setFont(new Font("Arial", Font.BOLD, 18));
                icerikPanel.add(pizzaBaslik);
                for (int i = 0; i < pizzalar.size(); i++) {
                    Pizza p = pizzalar.get(i);
                    JPanel kart = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    kart.setBorder(BorderFactory.createTitledBorder(p.getIsim() + " (" + p.getBoyut() + ", " + p.getHamur() + ")"));
                    kart.add(new JLabel(String.format("₺%.2f", p.fiyatHesapla())));
                    JButton silBtn = new JButton("Çıkar");
                    int idx = i;
                    silBtn.addActionListener(e -> {
                        sepet.pizzaCikar(idx);
                        anaPencere.panelDegistir(new SepetPaneli(anaPencere, sepet));
                    });
                    kart.add(silBtn);
                    icerikPanel.add(kart);
                }
            }

            // Yan ürünleri listele
            if (!yanUrunler.isEmpty()) {
                JLabel yanUrunBaslik = new JLabel("Yan Ürünler:");
                yanUrunBaslik.setFont(new Font("Arial", Font.BOLD, 18));
                icerikPanel.add(yanUrunBaslik);
                for (int i = 0; i < yanUrunler.size(); i++) {
                    YanUrun y = yanUrunler.get(i);
                    JPanel kart = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    kart.setBorder(BorderFactory.createTitledBorder(y.getIsim()));
                    kart.add(new JLabel(String.format("₺%.2f", y.getFiyat())));
                    JButton silBtn = new JButton("Çıkar");
                    int idx = i;
                    silBtn.addActionListener(e -> {
                        sepet.yanUrunCikar(idx);
                        anaPencere.panelDegistir(new SepetPaneli(anaPencere, sepet));
                    });
                    kart.add(silBtn);
                    icerikPanel.add(kart);
                }
            }

            // Toplam fiyat
            JLabel toplamLabel = new JLabel(String.format("Toplam Fiyat: ₺%.2f", sepet.toplamFiyat()));
            toplamLabel.setFont(new Font("Arial", Font.BOLD, 18));
            toplamLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            icerikPanel.add(Box.createVerticalStrut(20));
            icerikPanel.add(toplamLabel);
        }

        JScrollPane scroll = new JScrollPane(icerikPanel);
        add(scroll, BorderLayout.CENTER);

        // Butonlar paneli
        JButton onayBtn = new JButton("Siparişi Onayla");
        onayBtn.setFont(new Font("Arial", Font.BOLD, 18));
        onayBtn.addActionListener(e -> {
            if (pizzalar.isEmpty() && yanUrunler.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sepetiniz boş!", "Uyarı", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Siparişiniz başarıyla alındı!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                sepet.temizle();
                anaPencere.panelDegistir(new MenuPaneli(anaPencere, sepet));
            }
        });

        JButton geriBtn = new JButton("Menüye Dön");
        geriBtn.addActionListener(e -> anaPencere.panelDegistir(new MenuPaneli(anaPencere, sepet)));

        JPanel butonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        butonPanel.add(onayBtn);
        butonPanel.add(geriBtn);

        add(butonPanel, BorderLayout.SOUTH);
    }
}
