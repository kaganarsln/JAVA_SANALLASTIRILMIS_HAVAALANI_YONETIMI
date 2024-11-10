public class Main {
    public static void main(String[] args) {

        kuleYonetimi kuleYonetimi = new kuleYonetimi();
        ucakiciIslemler ucakiciIslemler = new ucakiciIslemler();
        yerHizmetleri yerHizmetleri = new yerHizmetleri();


        yerHizmetleri.biletSatisSistemi();
        System.out.println("\nYolcular uçağa doğru yol almıştır. Son kontrol noktasına yaklaşıyorlar...");
        ucakiciIslemler.checkinListesineEkle();
        ucakiciIslemler.checkInIslemiYap();
        System.out.println("\nSon kontrollerin ardından yolcular koltuklarına yerleşti ve hostes emniyet talimatlarını vermeye başladı...");
        ucakiciIslemler.emniyetTalimatlari();
        System.out.println("\nPilot kule ile iletişime geçti...");
        kuleYonetimi.ruzgarDuzeltmesikalkis();
        kuleYonetimi.havaDurumuKalkis();
        kuleYonetimi.kalkisBilgisi();
        System.out.println("\nUçak havalandıktan bir süre sonra hostesler yolculara yemek servisi yapmaya başladı...");
        ucakiciIslemler.yiyecekIcecekHizmetiYap(yerHizmetleri);
        System.out.println("\nUçak ineceği konuma yaklaştığında pilot tekrar kule ile iletişime geçti...\n");
        kuleYonetimi.ruzgarDuzeltmesiInis();
        kuleYonetimi.havaDurumInis();
        kuleYonetimi.inisBilgisi();
        System.out.println("\nUçak başarılı bir şekilde inişi gerçekleştirmiştir!");
        ucakiciIslemler.yolcuUcusDegerlendirmesi();
    }
}