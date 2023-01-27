
# PATİKA - DEFINEX
## Semboller ve Anlamları
- ✅ -> Test edildi ve çalışıyor.
- ❗-> Bitmedi yada tam olarak çalışmıyor.
- ❓-> İstenileni tam olarak anlamadım. 


## Kurallar

- ✅ Sisteme yeni kullanıcılar tanımlanabilir, mevcut müşteriler güncellenebilir veya silinebilir.
- ✅ Kredi skoru 500’ün altında ise kullanıcı reddedilir. (Kredi sonucu: Red)
- ✅ Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin altında ise Kullanıcının kredi başvurusu onaylanır ve kullanıcıya 10.000 TL limit atanır. (Kredi Sonucu: Onay). Eğer teminat vermişse teminat bedelinin yüzde 10 u kadar tutar kredi limitine eklenir.
- ✅ Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL ile 10.000TL arasında ise kullanıcının kredi başvurusu onaylanır ve kullanıcıya 20.000 TL limit atanır. (Kredi Sonucu:Onay) Eğer teminat vermişse teminat bedelinin yüzde 20 si kadar tutar kredi limitine eklenir.
- ✅ Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 10.000 TL’nin üstünde ise kullanıcının kredi başvurusu onaylanır ve kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ LİMİT ÇARPANI/2 kadar limit atanır. (Kredi Sonucu:Onay) Eğer teminat vermişse teminat bedelinin yüzde 25 i kadar tutar kredi limitine eklenir.
- ✅ Kredi skoru 1000 puana eşit veya üzerinde ise kullanıcıya AYLIK GELİR BİLGİSİ * KREDİ LİMİT ÇARPANI kadar limit atanır. (Kredi Sonucu: Onay) Eğer teminat vermişse teminat bedelinin yüzde 50 si kadar tutar kredi limitine eklenir.
- ❓ Kredinin neticelenmesi sonucunda ilgili başvuru veritabanına kaydedilir. Daha sonrasında ise ilgili telefon numarasına bilgilendirme SMS’i gönderilir ve endpoint’ten onay durum bilgisi (red veya onay), limit bilgisi dönülür.
- ✅ Gerçekleştirilmiş bir kredi başvurusu sadece kimlik numarası ve doğum tarihi bilgisi ile sorgulanabilir. Doğum tarihi ve kimlik bilgisi eşleşmezse sorgulanamamalıdır. 

## Projeden Beklenenler
- ✅Backend projesinin belirtilen kurallara göre doğru çalışır olması (readme dosyasının eklenmesi ya da Dockerfile yazılması)
- ✅Kodun kalitesine (Clean Code), yapılandırılmasına (Structure) dikkat edilmesi, yeni özellikler için geliştirmeye açık olması ve anlaşılabilir olması. (SOLID principles) 
- ❗Test yazılması
- ✅Design Patterns kullanımı **Singleton**
- ❗Dokümantasyon(Swagger, OpenApi vb) 
- ✅NoSQL,RDBMS(ORM) ve Hibernate (JPA) gibi teknolojilerin kullanımı **MySql(JPA)**
- ✅Loglama mekanizmasının kurulması **Dosyaya loglama**
- ✅Frontend projesinin çalışır olması **React - Tailwindcss**