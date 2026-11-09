# Checkpoint

Checkpoint, oyuncuların oynadıkları oyunları takip edebilmesi ve oyun başına kişisel notlar tutabilmesi için geliştirilen bir REST API projesidir. Proje; Spring Boot, Spring Security ve PostgreSQL kullanılarak katmanlı bir backend yapısı üzerinde geliştirilmiştir.

> **Proje durumu:** Bu depo geliştirme aşamasındadır. Oyun listeleme ve detay görüntüleme ile kullanıcı kaydı çalışır durumdadır. Oyun silme ve oyun notlarının bazı uçları henüz tamamlanmamıştır; aşağıdaki API bölümünde durumları belirtilmiştir.

## Özellikler

- Kullanıcı kaydı; e-posta ve kullanıcı adı benzersizliği kontrolü
- Parolaların BCrypt ile hash'lenerek saklanması
- Oyun oluşturma, tüm oyunları listeleme ve oyun detayını görüntüleme
- Oyun notları için oluşturma, listeleme, güncelleme ve silme uçlarının taslakları
- Bean Validation ile istek doğrulama
- JPA/Hibernate ile PostgreSQL kalıcılığı
- Merkezi hata işleme ve bulunamayan kaynaklar için özel hata türü
- Spring Security ile endpoint erişim kuralları

## Teknolojiler

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- Spring Security
- Jakarta Bean Validation
- PostgreSQL
- Maven Wrapper
- Lombok

## Gereksinimler

- JDK 21
- PostgreSQL

## Yerel ortamda çalıştırma

1. PostgreSQL'de `checkpoint` adında bir veritabanı oluşturun.
2. Uygulamanın bağlantı bilgilerini ortam değişkenleri olarak tanımlayın:

```powershell
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your-password"
```

3. Uygulamayı Maven Wrapper ile başlatın:

```powershell
./mvnw.cmd spring-boot:run
```

Uygulama varsayılan olarak `http://localhost:8080` adresinde çalışır. Veritabanı bağlantı adresi `src/main/resources/application.properties` içinde `localhost:5432/checkpoint` olarak tanımlıdır.

## API

Temel yol: `/checkpoint`

| Metot | Uç nokta | Açıklama | Durum |
|---|---|---|---|
| `POST` | `/auth/register` | Yeni kullanıcı kaydı | Hazır |
| `POST` | `/games` | Oyun oluşturur | Hazır; kimlik doğrulama gerekir |
| `GET` | `/games` | Oyunları listeler | Hazır; herkese açık |
| `GET` | `/games/{gameId}` | Oyun detayını getirir | Hazır; herkese açık |
| `DELETE` | `/games/{gameId}` | Oyun siler | Henüz uygulanmadı (`501`) |
| `POST` | `/games/{gameId}/notes` | Oyuna not ekler | Henüz uygulanmadı (`501`) |
| `GET` | `/games/{gameId}/notes` | Oyunun notlarını listeler | Henüz uygulanmadı (`501`) |
| `PATCH` | `/notes/{logEntryId}` | Notu günceller | Hazır; kimlik doğrulama gerekir |
| `DELETE` | `/notes/{logEntryId}` | Notu siler | Hazır; kimlik doğrulama gerekir |

Kayıt isteği örneği:

```http
POST /checkpoint/auth/register
Content-Type: application/json
```

```json
{
  "username": "oyuncu",
  "email": "oyuncu@example.com",
  "password": "guclu-bir-parola"
}
```

Oyun oluşturma isteği örneği:

```http
POST /checkpoint/games
Content-Type: application/json
```

```json
{
  "name": "Hades",
  "genre": "Roguelike",
  "producer": "Supergiant Games",
  "releaseDate": "2020-09-17"
}
```

Tarih alanı ISO `YYYY-MM-DD` biçimindedir. İstek gövdeleri doğrulamadan geçer; örneğin oyun adı boş bırakılamaz, not en fazla 2000 karakter olabilir, parola en az 8 karakter olmalıdır.

## Güvenlik ve geliştirme notları

Kayıt uç noktası ve oyunların `GET` uçları herkese açıktır. Diğer istekler Spring Security tarafından kimlik doğrulama gerektirecek şekilde korunur. Bu sürümde projede ayrı bir oturum açma veya token üretme endpoint'i bulunmamaktadır; `LoginRequest` DTO'su tanımlı olsa da akış henüz uygulanmamıştır.

Geliştirme ortamında Hibernate şema güncellemesi (`ddl-auto=update`) ve SQL çıktısı etkinleştirilmiştir. Üretim ortamına geçmeden önce şema yönetimi, kimlik doğrulama akışı ve veritabanı ayarları gözden geçirilmelidir.

## Proje yapısı

```text
src/main/java/com/mertezer/checkpoint/
├── config/       # Spring Security yapılandırması
├── controller/   # REST uç noktaları
├── dto/          # İstek ve yanıt modelleri
├── entity/       # JPA varlıkları
├── exception/    # Hata türleri ve merkezi hata işleme
├── repository/   # Spring Data depoları
├── security/     # Kullanıcı kimlik bilgisi yükleme
└── service/      # Uygulama iş mantığı
```

## Lisans

Bu depo için henüz bir lisans belirtilmemiştir.
