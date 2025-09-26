import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

public class AnaPencere extends JFrame {
    private GirisPaneli girisPaneli;
    private ArrayList<Kullanici> kullaniciListesi = new ArrayList<>();
    private ArrayList<HazirPizza> hazirPizzalar = new ArrayList<>();
    private Sepet sepet = new Sepet();
    private ArrayList<Malzeme> tumMalzemeler = new ArrayList<>();
    private ArrayList<YanUrun> yanUrunler = new ArrayList<>();  // YAN ÜRÜNLER!

    public AnaPencere() {
        // Pencere başlığı
        setTitle("Pizza Siparis Sistemi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);

        //Temel listeleri oluştur
        tumMalzemeleriBaslat();
        yanUrunleriBaslat();      // yan ürünler oluşturuldu
        hazirPizzalariBaslat();   // Hazır pizzalar oluşturuldu

        girisPaneli = new GirisPaneli(this);
        setContentPane(girisPaneli);

        setVisible(true);
    }

    // Tüm malzeme listesi
    private void tumMalzemeleriBaslat() {
        tumMalzemeler.add(new Malzeme("Mozarella", 15));
        tumMalzemeler.add(new Malzeme("Domates Sos", 10));
        tumMalzemeler.add(new Malzeme("Feslegen", 8));
        tumMalzemeler.add(new Malzeme("Sucuk", 20));
        tumMalzemeler.add(new Malzeme("Zeytin", 7));
        tumMalzemeler.add(new Malzeme("Mantar", 12));
        tumMalzemeler.add(new Malzeme("Mısır", 8));
        tumMalzemeler.add(new Malzeme("Biber", 6));
        // ... eklemeye devam edebilirsin
    }

    // YAN ÜRÜNLERİ BAŞLAT!
    private void yanUrunleriBaslat() {
        yanUrunler.add(new YanUrun("Kola", 18));
        yanUrunler.add(new YanUrun("Ayran", 8));
        yanUrunler.add(new YanUrun("Patates Kızartması", 20));
        yanUrunler.add(new YanUrun("Sarımsaklı Ekmek", 14));
        yanUrunler.add(new YanUrun("Salata", 17));
        // ... diğer yan ürünler
    }

    // Hazır pizzalar için fonksiyonun varsa burada çağır
    private void hazirPizzalariBaslat() {
        // Hazır pizzaları kendi malzeme listeleriyle oluştur
        ArrayList<Malzeme> margarita = new ArrayList<>();
        margarita.add(tumMalzemeler.get(0)); // Mozarella
        margarita.add(tumMalzemeler.get(1)); // Domates Sos
        margarita.add(tumMalzemeler.get(2)); // Feslegen
        hazirPizzalar.add(new HazirPizza("Margarita", margarita));

        ArrayList<Malzeme> sucuklu = new ArrayList<>();
        sucuklu.add(tumMalzemeler.get(0)); // Mozarella
        sucuklu.add(tumMalzemeler.get(1)); // Domates Sos
        sucuklu.add(tumMalzemeler.get(3)); // Sucuk-
        sucuklu.add(tumMalzemeler.get(5)); // Mantar
        hazirPizzalar.add(new HazirPizza("Sucuklu", sucuklu));

        ArrayList<Malzeme> karisik = new ArrayList<>();
        karisik.add(tumMalzemeler.get(0));
        karisik.add(tumMalzemeler.get(1));
        karisik.add(tumMalzemeler.get(3));
        karisik.add(tumMalzemeler.get(4));
        karisik.add(tumMalzemeler.get(5));
        karisik.add(tumMalzemeler.get(6));
        karisik.add(tumMalzemeler.get(7));
        hazirPizzalar.add(new HazirPizza("Karışık", karisik));

        // dilediğimiz kadar hazır pizza ekleyebilirz!
    }


    // GETTER'LAR
    public ArrayList<Malzeme> getTumMalzemeler() { return tumMalzemeler; }
    public ArrayList<YanUrun> getYanUrunler() { return yanUrunler; }
    public ArrayList<HazirPizza> getHazirPizzalar() { return hazirPizzalar; }
    public Sepet getSepet() { return sepet; }

    public void panelDegistir(JPanel yeniPanel) {
        setContentPane(yeniPanel);
        revalidate();
        repaint();
    }

    public boolean girisYap(String kullaniciAdi, String sifre) {
        for (Kullanici k : kullaniciListesi) {
            if (k.getKullaniciAdi().equals(kullaniciAdi) && k.getSifre().equals(sifre)) {
                return true;
            }
        }
        return false;
    }

    public boolean kayitOl(String kullaniciAdi, String sifre) {
        for (Kullanici k : kullaniciListesi) {
            if (k.getKullaniciAdi().equals(kullaniciAdi)) {
                return false;
            }
        }
        kullaniciListesi.add(new Kullanici(kullaniciAdi, sifre));
        return true;
    }

    // MAIN
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new AnaPencere());
    }
}
