import java.util.Date;

public class Siparis {
    private Kullanici kullanici;
    private Sepet sepet;
    private Date siparisTarihi;
    // İleride sipariş numarası, adres vs. eklenebilir

    public Siparis(Kullanici kullanici, Sepet sepet) {
        this.kullanici = kullanici;
        this.sepet = sepet;
        this.siparisTarihi = new Date(); // Sipariş oluştuğu anın tarihi
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public Sepet getSepet() {
        return sepet;
    }

    public Date getSiparisTarihi() {
        return siparisTarihi;
    }

    // Toplam fiyatı döndüren bir metot
    public double toplamFiyat() {
        return sepet.toplamFiyat();
    }
}
