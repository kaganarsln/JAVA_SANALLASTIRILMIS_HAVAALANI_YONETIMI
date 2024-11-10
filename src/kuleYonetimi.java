import java.util.Random;
import java.util.Scanner;

public class kuleYonetimi {
    Scanner bilgial = new Scanner(System.in);

    // düzeltilmiş hız 30 knotın üstündeyse kalkışa izin verme!
    // burundan gelen rüzgar (180 derece) 30 knottan fazla ise kalkışa
    // kuyruktan gelen rüzgar (0 derce) 30 knottan fazla ise inişe izin verme
    public void ruzgarDuzeltmesikalkis() {
        System.out.println("Uçuşa başlamadan önce rüzgarın etkisiyle birlikte uçağın ortalama hızını hesaplamak için:");
        System.out.println("Rüzgarın yönünü giriniz : ");
        double ruzgarYonu = bilgial.nextDouble();
        // Birimi knot
        System.out.println("Rüzgarın hızını knot cinsinden giriniz :");
        double ruzgarHizi = bilgial.nextDouble();
        // Birimi knot
        System.out.println("Uçağın hızını knot cinsinden giriniz:");
        double ucakHizi = bilgial.nextDouble();
        bilgial.nextLine();  // Scanner buffer temizleme

        // Rüzgarın uçağın hızına olan etkisini hesaplar
        double ruzgarAcisi = Math.toRadians(ruzgarYonu);
        double etkiliRuzgarhizi = ruzgarHizi * Math.cos(ruzgarAcisi);
        double duzeltilmisHiz = ucakHizi + etkiliRuzgarhizi;

        double km = duzeltilmisHiz * 1.852; // 1 knot == 1.852 km

        if (etkiliRuzgarhizi > 30 && ruzgarYonu > 90 && ruzgarYonu < 270) {
            System.out.println("Rüzgar sebebiyle kalkışa izin verilemedi!");
            return;
        }

        if (etkiliRuzgarhizi > 30 && (ruzgarAcisi == 270 || ruzgarAcisi == 90)) {
            System.out.println("Rüzgar sebebiyle kalkışa izin verilemedi!");
            return;
        }

        System.out.println("Rüzgarın etkisiyle düzeltilmiş hız: " + duzeltilmisHiz + " knot");
        System.out.println("Rüzgarın etkisiyle düzeltilmiş hız: " + km + " km/h");
    }

    public void havaDurumuKalkis() {
        System.out.println("Hava durumu bilgisi almak için '1' basınız");
        String hava = bilgial.nextLine();

        if (hava.equals("1")) {
            Random rastgelehava = new Random();

            int[] havaOlayi = {1, 2, 3};

            int havasonuc = rastgelehava.nextInt(havaOlayi.length) + 1;

            if (havasonuc == 1) {
                System.out.println("Rüzgar 280 dereceden 10 knot, görüş mesafesi mükemmel. Güneşli bir gün, keyifli uçuşlar dilerim.");
            } else if (havasonuc == 2) {
                System.out.println("Şu anda hava yağmurlu ve hafif rüzgarlı. Rüzgar yönü 250 derece, 10 knot.");
            } else if (havasonuc == 3) {
                System.out.println("Rüzgar 320 derece, 10 knot.");
                System.out.println("Kalkış sırasında karlı hava dikkate alınmalı.");
            }
        } else {
            System.out.println("Anlaşılamadı lütfen tekrar deneyiniz.");
            return;
        }


    }

    public void kalkisBilgisi() {
        System.out.println("Kalkış izni istemek için 'k' basınız.");

        String kalkis = bilgial.nextLine();

        if (kalkis.equals("k")) {
            Random rastgelek = new Random();

            int rastgelesayi = rastgelek.nextInt(100);

            String kalkissonuc = (rastgelesayi < 70) ? "1" : "0";

            if (kalkissonuc.equals("1")) {
                System.out.println("Kalkışa izin verildi İYİ UÇUŞLAR...");
            } else {
                System.out.println("Pist yoğunluğu nedeniyle kalkış izni verilemedi.");
                System.out.println("Kalkış izni istemek için 'k' basınız.");
                String kalkis2 = bilgial.nextLine();
                if (kalkis2.equals("k")) {
                    System.out.println("Kalkışa izin verildi İYİ UÇUŞLAR...");
                } else {
                    System.out.println("Anlaşılmadı lütfen tekrar deneyiniz.");
                    return;
                }
            }
        } else {
            System.out.println("Anlaşılmadı lütfen tekrar deneyiniz...");
            return;
        }

    }

    // İNİŞ BAŞLANGICI
    public void ruzgarDuzeltmesiInis() {
        System.out.println("İnmeden önce rüzgarın etkisiyle birlikte uçağın ortalama hızını hesaplamak için:");
        System.out.println("Rüzgarın yönünü giriniz : ");
        double ruzgarYonu = bilgial.nextDouble();
        // Birimi knot
        System.out.println("Rüzgarın hızını knot cinsinden giriniz :");
        double ruzgarHizi = bilgial.nextDouble();
        // Birimi knot
        System.out.println("Uçağın hızını knot cinsinden giriniz:");
        double ucakHizi = bilgial.nextDouble();
        bilgial.nextLine();  // Scanner buffer temizleme

        // Rüzgarın uçağın hızına olan etkisini hesaplar
        double ruzgarAcisi = Math.toRadians(ruzgarYonu);
        double etkiliRuzgarhizi = ruzgarHizi * Math.cos(ruzgarAcisi);
        double duzeltilmisHiz = ucakHizi + etkiliRuzgarhizi;

        double km = duzeltilmisHiz * 1.852; // 1 knot == 1.852 km

        if (ruzgarAcisi > 270 && ruzgarAcisi < 90) {
            if (etkiliRuzgarhizi > 30) {
                System.out.println("Rüzgar sebebiyle inişe izin verilemedi!");
                return;
            }
        }

        if (etkiliRuzgarhizi > 30 && (ruzgarAcisi == 270 || ruzgarAcisi == 90)) {
            System.out.println("Rüzgar sebebiyle inişe izin verilemedi!");
            System.out.println("Alternatif meydanlara yönelin!");
        }
        System.out.println("Rüzgarın etkisiyle düzeltilmiş hız: " + duzeltilmisHiz + " knot");
        System.out.println("Rüzgarın etkisiyle düzeltilmiş hız: " + km + " km/h");
    }

    public void havaDurumInis() {
        System.out.println("Hava durumu bilgisi almak için '2' basınız");
        String hava2 = bilgial.nextLine();

        if (hava2.equals("2")) {
            Random rastgelehava = new Random();

            int havaSonuc2 = rastgelehava.nextInt(4) + 1;

            if (havaSonuc2 == 1) {
                System.out.println("Havaalanında şu anda sıcaklık 40 derece, asfalt sıcak olabilir.");
                System.out.println("İniş sırasındaki hızınızı 160 knot'ta tutun. Önünüzdeki uçak kalkış yapıyor. Piste yaklaştığınızda 140 knot'a düşebilirsiniz.");
            } else if (havaSonuc2 == 2) {
                System.out.println("İniş için yaklaşıyorsunuz. Havaalanında şu anda hafif yağmur var, pistler kaygan olabilir.");
                System.out.println("Rüzgar 270 dereceden 10 knot");
                System.out.println(", Piste yaklaşırken hızınızı 150 knot'ta tutun, inişten sonra 3 numaralı taksi yolunu kullanın.");
            } else if (havaSonuc2 == 3) {
                System.out.println("Havaalanında yoğun sis var, görüş mesafesi 500 metre.");
                System.out.println("Rüzgar 180 dereceden 7 knot. Gece şartları nedeniyle hızınızı 160 knot'ta tutun, ışıklandırmalar aktif.");
            } else if (havaSonuc2 == 4) {
                //PAS GEÇME SENARYOSU
                System.out.println("ACİL İNİŞ");
                String[] senaryo = {"P: Mayday, Mayday! Burası sefer 1549 kuşlara çarptık.",
                        "K: 1-5000, Delta 3-3-1'i koruyun.",
                        "P: İki motorun itiş gücünü yitirdik geri dönüyoruz.",
                        "K: Sola dönüp 2-2-0 rotasında ilerleyin.",
                        "K: Hangi motoru kaybettiniz.",
                        "P: İkisini, iki motoru da.",
                        "(Diğer tüm uçaklar için iniş ve kalkışlar durur)",
                        "K: Sefer 1549 hazırlayabilirsek pist R39'a inebilir misiniz? ",
                        "P: Olumsuz nehire düşebiliriz.",
                        "K: (Bir A320  nehre doğru dalışta! Gelen uçakları bekletin!)",
                        "K: Sefer 1549, sağa 2-8-0 rotasına ilerleyin. Xhavaalnında 1'e inebilirsiniz.",
                        "P: Başaramayacağız, nehre ineceğiz",
                        "K: Duyamadım ne dediniz... (sefer 1549 ile radar bağlantısı kesildi...)"
                };

                for (String talimat : senaryo) {
                    System.out.println(talimat);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("15 Ocak 2009'da Sullenberger, US Airways Flight 1549'un kaptanıydı,\n" +
                        " Airbus A320, LaGuardia Havaalanı'ndan kalkıyordu.\n" +
                        " Kalkıştan kısa süre sonra uçağın motorlarını büyük bir kuş sürüsü (Kanada kazları) vurdu ve her iki motor da güç kaybetti.");
                System.exit(0);
            }
        } else {
            System.out.println("Anlaşılamadı Lütfen Tekrar Deneyiniz.");
        }
    }

    void inisBilgisi() {
        System.out.println("İniş izni istemek için 'i' basınız.");

        String inis = bilgial.nextLine();

        if (inis.equals("i")) {
            Random rastgelei = new Random();

            int rastgeleOlasilik = rastgelei.nextInt(100);

            String inisSonuc = (rastgeleOlasilik < 70) ? "1" : "0";
            /* iki tane birbirine paralel pist var (39R ve 39L onları temsil ediyor.) */
            if (inisSonuc.equals("1")) {
                System.out.println("İniş için 39R pistine yönelebilirsiniz.");
            } else {
                System.out.println("Pist yoğunluğu nedeniyle izin verilemedi lütfen 39L pistine güneyden yaklaşın.");
            }
        }
    }
}
