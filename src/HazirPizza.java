import java.util.ArrayList;

public class HazirPizza extends Pizza {
    public HazirPizza(String isim, PizzaBoyutu boyut, HamurTipi hamur, ArrayList<Malzeme> malzemeler, double tabanFiyat) {
        super(isim, boyut, hamur, malzemeler, tabanFiyat);
    }
    public HazirPizza(String isim, ArrayList<Malzeme> malzemeler, double tabanFiyat) {
        super(isim, null, null, malzemeler, tabanFiyat); // boyut ve hamur daha sonra seçilecek
    }

    public HazirPizza(String isim, ArrayList<Malzeme> malzemeler) {
        super(isim, null, null, malzemeler, 0); // Taban fiyat = 0
    }


    public void setBoyut(PizzaBoyutu boyut) {
        super.setBoyut(boyut);
    }

    public void setHamur(HamurTipi hamur) {
        super.setHamur(hamur);
    }


    // Kullanıcı ekstra malzeme ekleyip/çıkarabilir
    public void malzemeEkle(Malzeme m) { getMalzemeler().add(m); }
    public void malzemeCikar(Malzeme m) { getMalzemeler().remove(m); }

    // Sadece malzeme fiyatını döndürür (menüde gösterim için)
    public double malzemeFiyatiHesapla() {
        double toplam = 0;
        for (Malzeme m : getMalzemeler()) {
            toplam += m.getFiyat();
        }
        return toplam;
    }

    // Tam fiyatı döndürür (sepette, siparişte, her şey dahil)
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
