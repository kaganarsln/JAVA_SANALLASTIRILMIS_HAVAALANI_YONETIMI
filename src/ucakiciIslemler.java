import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ucakiciIslemler extends yerHizmetleri {
    // Sınıfta kullandığımız tüm değişkenleri genellikle burada atadık.
    String yolcuAd;
    String yolcuSoyad;
    int bagajAgirligi;
    boolean checkInIslemiYapildiMi;
    ArrayList<String> checkListesi;
    ArrayList<Double> puanDegerlendirmesiTopla;
    String DOSYA_ADI = "yolcuDegerlendirmeleri.txt";
    double ortalama;
    Scanner scanner;

    public ucakiciIslemler() {
        // Kullanacağımız listeleri constructor classım'da tanımladık.
        this.checkListesi = new ArrayList<>();
        this.puanDegerlendirmesiTopla = new ArrayList<>();
        this.checkInIslemiYapildiMi = false;
        this.scanner = new Scanner(System.in);
    }

    public void checkinListesineEkle() { // YerHizmetleri sınıfından alınan yolcu bilgilerini buffered reader ile check-in işlemi için listeye ekledik.
        try (BufferedReader reader = new BufferedReader(new FileReader("yolcuBilgisi.txt"))) {
            String arananYolcu;
            while ((arananYolcu = reader.readLine()) != null) {
                checkListesi.add(arananYolcu);
            }
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu.");
            e.printStackTrace();
        }
    }

    public void checkInIslemiYap() { // Son yolcu kontrolü için bir sınıf oluşturuldu. Bu sınıfta yolcunun bilgileri tekrardan istenecek ve oluşturulmuş yolcu bilgisi dosyasından kontrol sağlanacak.
        System.out.println("Yolcunun adı: ");
        this.yolcuAd = scanner.nextLine();

        System.out.println("Yolcunun soyadı: ");
        this.yolcuSoyad = scanner.nextLine();

        String arananYolcu = yolcuAd + " " + yolcuSoyad;
        if (!checkListesi.contains(arananYolcu)) {
            System.out.println("Yolcu Check-in listesinde bulunmamaktadır. İşlemleri tamamlanamamıştır!");
            return;
        } else {
            System.out.println("Yolcu Check-in listesinde bulunmaktadır.");
        }

        // Basit bir bagaj kontrolü sağlandı.
        System.out.println("\nİç hat uçuşlarında bagaj ağırlığı 15-25kg arasında olmalıdır!");
        System.out.println("Dış hat uçuşlarında bagaj ağırlığı 20-40kg arasında olmalıdır!");
        System.out.println("\nYolcunun Bagaj ağırlığı: ");
        this.bagajAgirligi = Integer.parseInt(scanner.nextLine());

        if (bagajAgirligi >= 15 && bagajAgirligi <= 25) {
            System.out.println("Bagaj ağırlığınız : " + bagajAgirligi + " kg " + "İç hat uçuşları için geçerli bagaj ağırlığı kurallarına uyuyor. GEÇEBİLİR!");
        } else if (bagajAgirligi >= 20 && bagajAgirligi <= 40) {
            System.out.println("Bagaj ağırlığınız : " + bagajAgirligi + " kg " + "Dış hat uçuşları için geçerli bagaj ağırlığı kurallarına uyuyor. GEÇEBİLİR!");
        } else {
            System.out.println("Bagaj ağırlığı kurallara uymamaktadır. GEÇEMEZ!");
            System.out.println("Check-in işlemleri tamamlanamadı.");
            return;
        }

        this.checkInIslemiYapildiMi = true;
        System.out.println("Check-in işlemi tamamlandı.");
    }

    // Uçak içerisindeki temel prosedürler simule edildi.
    public void emniyetTalimatlari() {
        System.out.println("Sayın yolcularımız, seferimize hoşgeldiniz! Lütfen emniyet talimatlarını dikkatle dinleyiniz.");
        String[] emniyetTalimatlari = {
                "1- Lütfen emniyet kemerlerinizi takın ve sıkıca bağlayın! İhtiyacı olan yolcularımıza ilave kemer ve bebek kemeri kabin ekibi tarafından verilecektir.",
                "2- Kabin içerisinde herhangi bir değişiklik olursa başınızın üstündeki kapaklar otomatik olarak açılacak ve oksijen maskeleri ortaya çıkacaktır. Kendi maskenizi takın ve sonra çocuklarınıza yardım ediniz.",
                "3- Cep telefonu ve elektronik cihazları uçuş moduna alınız ve uçuş sırasında kullanmayınız."
        };

        for (String talimat : emniyetTalimatlari) { // Metinler diziden döndürülerek thread.sleep komutu ile 2 saniye aralıklı olarak talimatların konsolda gözükmesi sağlandı.
            System.out.println(talimat);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Yolcuların seçtiği uçuş sınıflarına göre ikişer adet menü önlerine seriliyor ve bu menülerden birini seçmesi isteniyor.
    public void yiyecekIcecekHizmetiYap(yerHizmetleri yerHizmetleri) {
        System.out.println("Merhaba efendim, bu menülerden hangisini arzu edersiniz?\n");

        if (yerHizmetleri.getKoltukTipi() == 'e') {
            System.out.println("Ekonomi Menü 1:");
            printMenu(ekonomiMenu1());
            System.out.println("\nEkonomi Menü 2:");
            printMenu(ekonomiMenu2());
        } else if (yerHizmetleri.getKoltukTipi() == 'b') {
            System.out.println("Business Menü 1:");
            printMenu(businessMenu1());
            System.out.println("\nBusiness Menü 2:");
            printMenu(businessMenu2());
        }

        System.out.println("\nHangi menüyü seçmek istersiniz? (1 - 2)");
        int secim = scanner.nextInt();
        scanner.nextLine();

        if (yerHizmetleri.getKoltukTipi() == 'e') {
            if (secim == 1) {
                System.out.println("Buyrun efendim, afiyet olsun.");
                printMenu(ekonomiMenu1());
            } else if (secim == 2) {
                System.out.println("Buyrun efendim, afiyet olsun.");
                printMenu(ekonomiMenu2());
            }
        } else if (yerHizmetleri.getKoltukTipi() == 'b') {
            if (secim == 1) {
                System.out.println("Buyrun efendim, afiyet olsun.");
                printMenu(businessMenu1());
            } else if (secim == 2) {
                System.out.println("Buyrun efendim, afiyet olsun.");
                printMenu(businessMenu2());
            }
        } else {
            System.out.println("Geçersiz seçim, lütfen 1 veya 2'yi seçiniz.");
            return;
        }
    }

    // Bu işlemler için string dizisiyle menüler oluşturduk ve bunları birer metot haline getirdik.
    public String[] ekonomiMenu1(){
        String[] ekonomi1 = {
                "Yiyecek Menüsü",
                "- Kaşarlı sandviç",
                "- Portakal suyu",
                "- Biskuvi",
        };
        return ekonomi1;
    }

    public String[] ekonomiMenu2(){
        String[] ekonomi2 = {
                "Yiyecek Menüsü",
                "- Salamlı sandviç",
                "- Elma suyu",
                "- Kek"
        };
        return ekonomi2;
    }

    public String[] businessMenu1(){
        String[] business1 = {
                "Yiyecek Menüsü",
                "- Adana kebap",
                "- Kaşarlı omlet",
                "- Focacio ekmek",
                "- Kola",
                "- Viski"
        };
        return business1;
    }

    public String[] businessMenu2(){
        String[] business2 = {
                "Yiyecek Menüsü",
                "- Ricotta peynirli ev yapımı cappelacci",
                "- Poşe yumurta ve şakşuka",
                "- Ispanaklı su böreği",
                "- Portakal suyu",
                "- Gazlı içecekler"
        };
        return business2;
    }

    private void printMenu(String[] menu) {
        for (String item : menu) {
            System.out.println(item);
        }
    }

    /*
       Uçuş sonrası yolcudan uçuş değerlendirmesi almak istedik. Buffered kütaphanesi ile bu puanlamaların bir text dosyasında depolanmasını sağladık. Bu puanlamalarında ortalamasını alarak havayolu şirketinin
       değerlendirmesini yapmış olduk.
    */
    public void yolcuUcusDegerlendirmesi() {
        puanDegerlendirmeleriniYukle();

        System.out.println("Sayın yolcumuz umarım yolculuğunuzdan keyif almışsınızdır. Uçuş deneyimimizi değerlendirmek ister misiniz? (EVET/HAYIR)");
        String degerlendirmekIstiyorMu = scanner.nextLine().toLowerCase();

        if (degerlendirmekIstiyorMu.equals("evet")) {
            System.out.println("Lütfen uçuş deneyiminizi 1-10 arasında değerlendiriniz");
            double puanDegerlendirmesi = scanner.nextDouble();

            while (puanDegerlendirmesi < 1 || puanDegerlendirmesi > 10) {
                System.out.println("Lütfen 1-10 arasında bir puan giriniz!");
                puanDegerlendirmesi = scanner.nextDouble();
            }
            puanDegerlendirmesiTopla.add(puanDegerlendirmesi);
            puanDegerlendirmeleriniKaydet();
            scanner.nextLine();
            ortalamaHesapla();
            System.out.println("Son olarak uçuş deneyiminizle ilgili bir yorum bırakınız!");
            scanner.nextLine();
            System.out.println("Bizi tercih ettiğiniz için teşekkür ederiz!");
        } else if (degerlendirmekIstiyorMu.equals("hayır")) {
            System.out.println("Bizi tercih ettiğiniz için teşekkür ederiz!");
        } else {
            System.out.println("Geçersiz bir seçim yaptınız.");
            return;
        }
    }

    public void puanDegerlendirmeleriniYukle() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                puanDegerlendirmesiTopla.add(Double.parseDouble(satir));
            }
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu.");
            e.printStackTrace();
        }
    }

    public void puanDegerlendirmeleriniKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI, true))) {
            for (double puan : puanDegerlendirmesiTopla) {
                writer.write(String.valueOf(puan));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Dosya yazılırken bir hata oluştu.");
            e.printStackTrace();
        }
    }

    public void ortalamaHesapla() {
        double toplam = 0;
        for (double puan : puanDegerlendirmesiTopla) {
            toplam += puan;
        }
        this.ortalama = toplam / puanDegerlendirmesiTopla.size();
        System.out.println("Uçuş deneyimi ortalaması: " + ortalama);
    }
}
