import java.util.ArrayList;

public class Sepet {
    private ArrayList<Pizza> pizzalar;
    private ArrayList<YanUrun> yanUrunler;

    public Sepet() {
        this.pizzalar = new ArrayList<>();
        this.yanUrunler = new ArrayList<>();
    }

    public void pizzaEkle(Pizza pizza) {
        pizzalar.add(pizza);
    }

    public void pizzaCikar(Pizza pizza) {
        pizzalar.remove(pizza);
    }

    // Indeksle çıkarma (SepetPaneli için gerekli!)
    public void pizzaCikar(int index) {
        if (index >= 0 && index < pizzalar.size()) {
            pizzalar.remove(index);
        }
    }

    public ArrayList<Pizza> getPizzalar() {
        return pizzalar;
    }

    public void yanUrunEkle(YanUrun urun) {
        yanUrunler.add(urun);
    }

    public void yanUrunCikar(YanUrun urun) {
        yanUrunler.remove(urun);
    }

    // Indeksle çıkarma (SepetPaneli için gerekli!)
    public void yanUrunCikar(int index) {
        if (index >= 0 && index < yanUrunler.size()) {
            yanUrunler.remove(index);
        }
    }

    public ArrayList<YanUrun> getYanUrunler() {
        return yanUrunler;
    }

    // Tüm sepeti temizle (sipariş onaylandığında)
    public void temizle() {
        pizzalar.clear();
        yanUrunler.clear();
    }

    // Toplam fiyat (tüm panelde kullanabilmek için kısa isimle)
    public double toplamFiyat() {
        double toplam = 0;
        for (Pizza pizza : pizzalar) {
            toplam += pizza.fiyatHesapla();
        }
        for (YanUrun urun : yanUrunler) {
            toplam += urun.getFiyat();
        }
        return toplam;
    }
}
