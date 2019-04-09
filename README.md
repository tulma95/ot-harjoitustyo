# Fish Swim

Fish Swim on peli, jossa pelaajan tarkoituksena on päästä mahdollisimman pitkälle merilevää väistellen. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/arkkitehtuuri.md)

## Komentorivitoiminnot

### Ohjelman käynnistäminen

Ohjelman voi käynnistää komennolla

```
mvn compile exec:java -Dexec.mainClass=fishswim.ui.FishSwimUI
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Checkstyle

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_