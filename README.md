# Mikroservis Projesi

Bu proje, **Inventory Service** ve **Order Service** adlı iki mikroservisi içerir. Bu servisler, Apache Kafka kullanarak haberleşir ve MongoDB veritabanını kullanır. Docker ve Docker Compose kullanılarak kolayca dağıtılabilir ve çalıştırılabilir. docker-compose up --build komutuyla derleyip çalıştırabilirsiniz servisleriniizi docker desktop üzerindne kolayca kontrol edebilir ve yönetebilirsniz. 

## İçindekiler

- [Başlangıç](#başlangıç)
- [Gereksinimler](#gereksinimler)
- [Kurulum](#kurulum)
- [Kullanım](#kullanım)
- [Servisler](#servisler)
- [API Dökümantasyonu](#api-dökümantasyonu)
- [Katkıda Bulunma](#katkıda-bulunma)
- [Lisans](#lisans)

## Başlangıç

Bu proje, Docker ve Docker Compose kullanarak hızlıca başlatılabilir. 

## Gereksinimler

- [Docker](https://www.docker.com/products/docker-desktop) (sürüm 20.x ve üstü)
- [Docker Compose](https://docs.docker.com/compose/install/) (sürüm 1.29 ve üstü)

## Kurulum

### Adım 1: Depoyu Klonlayın

Öncelikle bu projeyi bilgisayarınıza klonlayın:

```bash
git clone https://github.com/alicancagdas1/mikroservis-projesi.git
cd mikroservis-projesi
Adım 2: Docker Compose İle Başlatın
Docker Compose kullanarak tüm servisleri başlatın:

bash
Kodu kopyala
docker-compose up -d
Bu komut, tüm servisleri arka planda başlatacaktır.

Adım 3: Docker Hub'dan İmajları Çekin
Alternatif olarak, Docker Hub'dan imajları manuel olarak çekebilirsiniz:

bash
Kodu kopyala
docker pull alicancagdas1/inventory-service:latest
docker pull alicancagdas1/order-service:latest
Kullanım
Inventory Service
URL: http://localhost:8081
Kullanım: Ürün stoklarını yönetmek için kullanılır.
Order Service
URL: http://localhost:8082
Kullanım: Siparişleri yönetmek ve stok güncellemelerini tetiklemek için kullanılır.
Servisler
Inventory Service
Bu servis, ürünlerin stok yönetimini sağlar ve Kafka üzerinden sipariş güncellemelerini dinler.

API Uç Noktaları
Stok Ekleme:

POST /inventory
Body:
json
Kodu kopyala
{
  "productNumber": "prod1",
  "quantity": 100
}
Stok Bilgisi Alma:

GET /inventory/:productNumber
Tüm Stokları Listeleme:

GET /inventory
Order Service
Bu servis, siparişlerin oluşturulmasını ve iptal edilmesini sağlar.

API Uç Noktaları
Sipariş Oluşturma:

POST /orders
Body:
json
Kodu kopyala
{
  "productNumber": "prod1",
  "quantity": 5,
  "orderType": "CREATE"
}
Sipariş İptali:

DELETE /orders/:orderNumber
API Dökümantasyonu
Postman veya benzeri bir araç kullanarak yukarıdaki API uç noktalarını test edebilirsiniz. Detaylı API dökümantasyonu ilerleyen zamanlarda eklenecektir.

Katkıda Bulunma
Bu projeye katkıda bulunmak istiyorsanız, lütfen önce bir issue açın veya pull request gönderin.
