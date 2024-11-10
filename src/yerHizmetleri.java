import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class yerHizmetleri {

    private Scanner scanner = new Scanner(System.in);
    // Uçuş bilgilerini tutacak dizileri saklamak için boolean'dan giriş yaptırdım.
    private String[] ucusSaatleri = new String[24];
    private Ucak[] ucuslar = new Ucak[24];
    private boolean[] rotarliUcuslar = new boolean[24];

    public yerHizmetleri() { // Uçuş saatlerini ve uçakları oluşturdum burda.
        for (int i = 0; i < 24; i++) {
            ucusSaatleri[i] = i + ":00";
            ucuslar[i] = new Ucak();
            if (i % 4 == 0) { // Her 4 saate bir rötarlı uçuş ayarladım.
                rotarliUcuslar[i] = true;
            }
        }
    }

    // ucakIciIslemler sınıfının benden değer alması için getter ve setter metotlarını oluşturduk.
    private char seyahat;
    private char koltukTipi;

    public char getSeyahat() {
        return seyahat;
    }

    public void setSeyahat(char seyahat) {
        if (seyahat == 'i' || seyahat == 'd') {
            this.seyahat = seyahat;
        } else {
            System.out.println("Geçersiz seyahat seçeneği. Varsayılan olarak yurt içi seyahat seçildi.");
            this.seyahat = 'i';
        }
    }

    public char getKoltukTipi() {
        return koltukTipi;
    }

    public void setKoltukTipi(char koltukTipi) {
        if (koltukTipi == 'e' || koltukTipi == 'b') {
            this.koltukTipi = koltukTipi;
        } else {
            System.out.println("Geçersiz koltuk tipi seçeneği. Varsayılan olarak ekonomi sınıfı seçildi.");
            this.koltukTipi = 'e';
        }
    }


    public void biletSatisSistemi() {
        System.out.println("Turkish Airlines Bilet Satış Sistemine Hoşgeldiniz!");
        System.out.println("Yurt içi mi seyahat etmek istersiniz yoksa yurt dışı mı? (i / d)");
        char seyahatSecimi = scanner.next().charAt(0);

        setSeyahat(seyahatSecimi);
        int fiyatTemel = 0;
        // Yolcunun seçtiği hat türüne göre oluşturulan iç hat ve dış hat text dosyalarından ülke ve şehir isimleri konsolda sıralanarak yolcunun gitmek istediği yeri seçmesi sağlandı.
        if (seyahat == 'i') {
            List<String> icHatlar = hatlariAl("icHat.txt");
            System.out.println("Hangi şehire seyahat etmek istiyorsunuz?");
            for (int i = 0; i < icHatlar.size(); i++) {
                System.out.println(i + ": " + icHatlar.get(i));
            }
            int secilenSehir = scanner.nextInt();
            System.out.println("Gitmek istediğiniz şehir: " + icHatlar.get(secilenSehir));
            fiyatTemel = 300;

        } else if (seyahat == 'd') {
            List<String> disHatlar = hatlariAl("disHat.txt");
            System.out.println("Hangi ülkeye seyahat etmek istiyorsunuz?");
            for (int i = 0; i < disHatlar.size(); i++) {
                System.out.println(i + ": " + disHatlar.get(i));
            }
            int secilenUlke = scanner.nextInt();
            System.out.println("Gitmek istediğiniz ülke: " + disHatlar.get(secilenUlke));
            fiyatTemel = 1000;
        }

        // Kullanıcıdan uçuş saati seçmesini istedim burda.
        System.out.println("Lütfen bir uçuş saati seçiniz:");
        for (int i = 0; i < 24; i++) {
            System.out.println(i + ": " + ucusSaatleri[i] + (rotarliUcuslar[i] ? " (Rötarlı)" : ""));
        }
        int ucusSaati = scanner.nextInt();

        Ucak secilenUcus = ucuslar[ucusSaati];
        boolean rotarliUcus = rotarliUcuslar[ucusSaati];

        if (rotarliUcus) {
            System.out.println("Seçtiğiniz Uçuş Saati rötarlı bir uçağın uçuş saatidir. Bu sayede bilet fiyatı yarıya düşecektir.");
        }

        // Kullanıcıdan koltuk tipi seçmesini isteyen kod bloklarını oluşturduk.
        System.out.println("Economy uçuşunu mu tercih edersiniz yoksa Business mı ? (e/b)");
        char koltukTipiSecimi = scanner.next().charAt(0);
        setKoltukTipi(koltukTipiSecimi);

        int koltukNumarasi = 0;
        boolean gecerliKoltuk = false;

        while (!gecerliKoltuk) {
            // Kullanıcıdan koltuk numarasını seçmesini burada istedik.
            if (koltukTipi == 'e') {
                System.out.println("Lütfen bir koltuk numarası seçiniz. (1-250 arasında olmalıdır.):");
                koltukNumarasi = scanner.nextInt();
                if (koltukNumarasi >= 1 && koltukNumarasi <= 250 && secilenUcus.rezerveKoltuk(koltukNumarasi, "economy")) {
                    gecerliKoltuk = true;
                } else {
                    System.out.println("Geçersiz koltuk numarası veya koltuk dolu. Lütfen tekrar deneyin.");
                }
            } else if (koltukTipi == 'b') {
                System.out.println("Lütfen bir koltuk numarası seçin (251-300):");
                koltukNumarasi = scanner.nextInt();
                if (koltukNumarasi >= 251 && koltukNumarasi <= 300 && secilenUcus.rezerveKoltuk(koltukNumarasi, "business")) {
                    gecerliKoltuk = true;
                } else {
                    System.out.println("Geçersiz koltuk numarası veya seçilen koltuk dolu. Lütfen başka bir koltuğu deneyiniz.");
                }
            } else {
                System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
                return;
            }
        }

        // Bilet fiyatını hesaplatmayı yaptık.
        int fiyat = fiyatTemel + secilenUcus.biletFiyati(koltukNumarasi);
        if (rotarliUcus) {
            fiyat /= 2; // Rötarlı uçuşlarda fiyat yarıya düşer dedik. Fiyatın yarısını aldırmış olduk.
        }
        System.out.println("Bilet Ücreti: " + fiyat + " TL");

        // Ödeme bilgilerini kontrol etme ve alma işlemini if koşulu ile gerçekleştirdik. Belirli metotları tanımlamak ve eşleştirmek için regex komutlarına yer verdik.
        while (true) {
            System.out.println("Ödeme Bilgilerini Giriniz:");
            System.out.print("Kart Numarası: ");
            String kartNumarasi = scanner.next();
            System.out.print("Son Kullanma Tarihi (MM/YY): ");
            String sonKullanmaTarihi = scanner.next();
            System.out.print("CVV: ");
            String cvv = scanner.next();

            if (kartNumarasi.length() != 16 || !kartNumarasi.matches("\\d+")) {
                System.out.println("Geçersiz Kart! Lütfen tekrar deneyiniz.");
                continue;
            }

            if (cvv.length() != 3 || !cvv.matches("\\d+")) {
                System.out.println("Geçersiz CVV! Lütfen tekrar deneyiniz.");
                continue;
            }

            String[] tarihParts = sonKullanmaTarihi.split("/");
            if (tarihParts.length != 2 || tarihParts[1].length() != 2 || !tarihParts[1].matches("\\d+")) {
                System.out.println("Geçersiz Son Kullanma Tarihi! Lütfen tekrar deneyiniz.");
                continue;
            }

            int yil = Integer.parseInt(tarihParts[1]);
            if (yil < 25) {
                System.out.println("Kartınızın Son Kullanma Tarihi geçmiştir. Lütfen başka kart deneyiniz.");
                continue;
            }

            System.out.println("Ödeme Alındı!");
            break;
        }

        // Yolcu bilgilerinin alınması sağlandı.
        System.out.print("Lütfen adınızı giriniz: ");
        String ad = scanner.next();
        System.out.print("Lütfen soyadınızı giriniz: ");
        String soyad = scanner.next();
        yolcuBilgisi(ad, soyad);

        System.out.println("Yolcu adı: " + ad + ", Yolcu soyadı: " + soyad + ", Uçuş Saati: " + ucusSaatleri[ucusSaati] + ", Koltuk Numarası: " + koltukNumarasi);
        System.out.println("Turkish Airlines'ı tercih ettiğiniz için mutluluk duyar, İyi Yolculuklar Dileriz!");
    }
    public void yolcuBilgisi(String ad, String soyad) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("yolcuBilgisi.txt", true))) {
            writer.write(ad + " " + soyad);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Dosya oluşturulurken bir hata oluştu.");
            e.printStackTrace();
        }
    }


    static List<String> hatlariAl(String dosyaAdi) {
        List<String> hatListesi = new ArrayList<>();
        try (BufferedReader al = new BufferedReader(new FileReader(dosyaAdi))) {
            String hat;
            while ((hat = al.readLine()) != null) {
                hatListesi.add(hat);
            }
        } catch (IOException e) {
            System.out.println("Dosya okunamadı: " + dosyaAdi);
            e.printStackTrace();
        }
        return hatListesi;
    }

    // Burada ayrı bir Ucak adında sınıf oluşturduk. Yolcu uçuş tipini seçerken tip metodu olarak equals'i tercih ettik .Bunun sebebi iki nesne arasında karşılaştırma yapmasını istediğimiz içindi.
    public static class Ucak {
        private boolean[] economyKoltuklar = new boolean[250];
        private boolean[] businessKoltuklar = new boolean[50];

        public boolean rezerveKoltuk(int koltukNumarasi, String tip) {
            if (tip.equals("economy")) {
                if (!economyKoltuklar[koltukNumarasi - 1]) {
                    economyKoltuklar[koltukNumarasi - 1] = true;
                    return true;
                }
            } else if (tip.equals("business")) {
                if (!businessKoltuklar[koltukNumarasi - 251]) {
                    businessKoltuklar[koltukNumarasi - 251] = true;
                    return true;
                }
            }
            return false;
        }

        public int biletFiyati(int koltukNumarasi) {
            if (koltukNumarasi <= 250) {
                return 200;
            } else {
                return 450;
            }
        }
    }
}


