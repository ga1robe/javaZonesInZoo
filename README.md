# javaZonesInZoo

## Opis zadania

Zoo składa się z n stref. Zoo zamieszkuje m zwierząt. Zwierzęta mieszkają w różnych strefach. Każde zwierzę zamieszkuje tylko jedną strefę. W strefie żyje wiele różnych zwierząt. Zwierzę potrzebują karmy. Zwierzę ma własne imię.

### Zapotrzebowanie dzienne karmy
* Słoń potrzebuje 20 jednostek karmy dziennie.
* Lew potrzebuje 11 jednostek karmy dziennie.
* Królik potrzebuje 4 jednostki karmy dziennie.

### Zoo potrzebuje raportów:
* Która strefa potrzebuje najwięcej karmy?
* w której strefie mieszka najmniej zwierząt?

### Zbudowanie endpointów REST:
- dodawanie stref
- dodawanie zwierzęcia i przypisania go do strefy.
- pobieranie zwierząt z danej strefy.
- pobieranie zwierząt o danym imieniu.
- pobranie raportu, która strefa Zoo potrzebuje najwięcej karmy.
- pobranie raportu, w której strefie mieszka najmniej zwierząt.

### Użyta technologia:

- Java
- Spring
- Java Collections
- Clean Code, OOP.
- Mile widziane testy.

## Opis wykonania

Zbudowanie systemu w technologii SpringBoot do rejestracji i wyświetlania stref w ZOO i zwierząt.
Wykorzystanie odpowiednich struktur kolekcji dla przechowywania danych zbudowanych w pamięci bez korzystania z bazy danych.

System posiada formatkę endpointów REST zapisaną w podkatalogu 'postman' w formacie JSON.
Po załadowaniu ich do postmana, odpowiednie endpoint POST dodają strefę,dodają zwierzę i przypisują go do strefy.
Endpointy GET pobieranją zwierzęta z danej strefy, pobierają zwierze o danym imieniu,
a także pobrają raport o strefie ZOO potrzebującej najwięcej karmy,
i pobrają raport, o strefie o najmniejszej ilości zwierząt.

Poza tym w systemie znajdują się formatki stron JSP do wyświetlania stref, zwierząt i raportów w przeglądarce internetowej.