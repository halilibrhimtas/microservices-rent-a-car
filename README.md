# RENT A CAR MICROSERVICES

Bu proje, bir araç kiralama işi için mikroservis mimarisini kullanarak geliştirilmiştir. Proje, Spring Boot ve Spring Cloud framework'lerini kullanarak Customer Service, Car Service, Rental Service, Notification Service, Discovery Server ve Api Gateway mikroservislerini içermektedir.

## Mikroservisler

1. **Customer Service**
   - Müşteri verilerini yönetir.
   - RESTful API üzerinden müşteri bilgileri için CRUD işlemlerinin yapılmasını sağlar.
   - Müşterinin adı, soyadı, email adresi, şifresi, doğum tarihi bilgileri istenir ve bakiye bilgisi takip edilir.
   - Veritabanı olarak PostgreSQL kullanılır.

2. **Car Service**
   - Araç bilgilerini yönetir.
   - Araç bilgileri için her aracın bir modeli, her modelin ise bir markası olacak şekilde oluşturulur. Aracın plakası, ücreti, model yılı, rengi ve kullanılabilirliği bilgileri takip edilir. Araç plakası için Türkiye standartlarına uygun olması için Regex mevcuttur.
   - Araba resimleri kullanıcıdan alınır ve Cloudinary bulut sisteminde tutulur. Kullanıcının fotoğrafları yüklemesi için thymeleaf kütüphanesi ile arayüz oluşturulmuştur.
   - Araba, araba resimleri, model ve marka için MongoDB veritabanında ayrı ayrı collection olarak tutulmaktadır.
    
3. **Rental Service**
   - Kiralama işlemlerini yönetir.
   - Araç kiralama işlemi için araba id'si ve müşteri id'si bilgileri alınır. Kiralama sürecinin başlangıç, bitiş tarihi ve kiralama durumu takip edilir.
   - Car Service ve Customer Service ile senkron bir iletişim halinde olup aracın müsaitliği, günlük ücreti ve kiralama için müşteri bakiyesi bilgileri takip edilmektedir.
   - Senkron iletişim için Web Client kullanılmaktadır. Web Client, senkron/asenkron olarak çalışan, web tabanlı servislerle etkileşimde bulunmak için kullanılan bir HTTP istemcisidir.
   - Kiralama işlemi gerçekleştiğinde Notification Service ile asenkron iletişim halinde kiralama bilgilerini mail formatında(rentalTopic) gönderilmektedir. Bu iletişim için Kafka kullanılmaktadır(RabbitMQ ile aynı işleve sahip). 
   - Veritabanı olarak PostgreSQL kullanılır.

4. **Notification Service**
   - Kullanıcılara bildirim gönderme işlemlerini yönetir.
   - Kiralama durumu güncellemeleri ve diğer önemli bildirimleri sağlar.
   - Async iletişim için Kafka'yı kullanmaktadır.

5. **Discovery Server**
   - Servislerin dinamik olarak bulunmasını ve bağlanmasını sağlayan Eureka tabanlı keşif sunucusudur.

6. **Api Gateway**
   - Harici talepleri yönlendiren ve iç servislere ileten API Gateway.
   - OAuth 2.0 tipinde Authorization kullanılmaktadır. Keycloak üzerinden client oluşturularak, `localhost:8181` url'i üzerinden istekler göndermek için JWT token oluşturulur.

## Installation

Her servisi başlatmak için aşağıdaki adımları takip edin.

1. **Projeyi Kopyalayın**
   - `git clone https://github.com/username/ai-customer-support-chatbot.git cd microservices-rent-a-car`

2. **Install Package**
    - Projenin olduğu klasörde `mvn clean install` komutunu çalıştırın.

3. **Docker**
    - Projeyi Docker'da çalıştırmak için öncelikle yüklemeliyiz. `docker-compose up -d`
    - Projeyi durdurmak için `docker-compose stop`, tekrar başlatmak için `docker-compose start`, Docker'dan kaldırmak için `docker-compose down`

## Video
  - [Video 1](https://vimeo.com/manage/videos/887836060/839fd8a31c)
  - [Video 2](https://vimeo.com/888510252/16ad302faa?share=copy)
  - [Video 3](https://vimeo.com/888511359/a643a218e3?share=copy)
    
## Project Schemas
  **UML DIAGRAM**
  <img src="https://github.com/halilibrhimtas/microservices-rent-a-car/assets/74383996/7b44a495-08f0-4df5-94c3-2343a558ee10"><br>

  **PROJECT STRUCTURE**
  <img src="https://github.com/halilibrhimtas/rent-a-car-microservices/assets/74383996/53db5454-3ad5-4c01-930d-f21b2c57f060"><br>

## Sosyal Medya
  - [Linkedin](https://www.linkedin.com/in/halilibrhimtas)
