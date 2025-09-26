import java.util.ArrayList;

public abstract class Pizza {
    private String isim;
    private PizzaBoyutu boyut;
    private HamurTipi hamur;
    private ArrayList<Malzeme> malzemeler;
    private double tabanFiyat;

    public Pizza(String isim, PizzaBoyutu boyut, HamurTipi hamur, ArrayList<Malzeme> malzemeler, double tabanFiyat) {
        this.isim = isim;
        this.boyut = boyut;
        this.hamur = hamur;
        this.malzemeler = malzemeler;
        this.tabanFiyat = tabanFiyat;
    }

    public String getIsim() { return isim; }
    public PizzaBoyutu getBoyut() { return boyut; }
    public HamurTipi getHamur() { return hamur; }
    public ArrayList<Malzeme> getMalzemeler() { return malzemeler; }
    public double getTabanFiyat() { return tabanFiyat; }

    public void setBoyut(PizzaBoyutu boyut) {
        this.boyut = boyut;
    }

    public void setHamur(HamurTipi hamur) {
        this.hamur = hamur;
    }

    public void setMalzemeler(ArrayList<Malzeme> malzemeler) {
        this.malzemeler = malzemeler;
    }

    public abstract double fiyatHesapla();

    public double malzemeFiyatiHesapla() {
        double toplam = 0;
        for (Malzeme m : malzemeler) {
            toplam += m.getFiyat();
        }
        return toplam;
    }

}
