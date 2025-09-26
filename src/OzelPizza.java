import java.util.ArrayList;

public class OzelPizza extends Pizza {
    public OzelPizza(String isim, ArrayList<Malzeme> malzemeler) {
        super(isim, null, null, malzemeler, 0); // tabanFiyat artık 0, gerekirse hiç kullanılmaz
    }

    // Diğer metotlar: malzeme ekle/çıkar gibi
    public void malzemeEkle(Malzeme m) {
        getMalzemeler().add(m);
    }
    public void malzemeCikar(Malzeme m) {
        getMalzemeler().remove(m);
    }

    // Sadece malzeme fiyatı (menüde gösterim için)
    public double malzemeFiyatiHesapla() {
        double toplam = 0;
        for (Malzeme m : getMalzemeler()) {
            toplam += m.getFiyat();
        }
        return toplam;
    }

    @Override
    public double fiyatHesapla() {
        double fiyat = malzemeFiyatiHesapla();

        if (getBoyut() != null) {
            switch (getBoyut()) {
                case ORTA: fiyat += 30; break;
                case BUYUK: fiyat += 60; break;
            }
        }
        if (getHamur() == HamurTipi.DUBLEKS) fiyat += 25;

        return fiyat;
    }

}
