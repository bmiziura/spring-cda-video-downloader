# Cda Video Downloader

Aplikacja umożliwia pobranie wszystkich źródeł filmu z platformy cda.pl za pomocą linku lub id filmu.


### Jak uruchomić aplikację?

1. `cd spring-cda-video-downloader`
2. `./mvnw clean install -DskipTests`
3. `cd docker`
4. `docker-compose up`
5. Aplikacja będzie dostępna pod adresem: localhost:8080

### Endpointy

1. Pobieranie źródła za pomocą url
    - GET `localhost:8080/api/video?url={}`
    - np. `localhost:8080/api/video?url=www.cda.pl/video/123`
2. Pobieranie źródła za pomocą id
    - GET `localhost:8080/api/video/{id}`
    - np. `localhost:8080/api/video/123`