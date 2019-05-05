# Arkkitehtuurikuvaus

## Rakenne

FishSwim sovelluksen rakennenteeseen kuuluu kolme eri pakkausta. Nämä pakkauksen ovat fishswim.ui, fishswim.domain ja fishswim.dao.

Ui-pakkaus on käyttöliittymä pakkaus, eli se huolehtii siitä, mitä sovelluksen käyttäjälle näytetään. Käyttöliittymä on tehty JavaFX:llä.

Domain-pakkaus sisältää pelille oleelliset oliot sekä pelin logiikan.

Dao-pakkaus pitää sisällään tietokantayhteydestä huolehtivan koodin ja kaikki kommunikaatio tietokannan kautta tapahtuu dao:n kautta.

## Käyttöliittymä

Käyttöliittymä rakentuu neljästä eri näkymästä, näkymät ovat:

- Main menu
- High score-lista
- Fish Swim
- End game-ruutu

Näkymät ovat Scene-olioita joista kaikki muut generoidaan uudestaan niitä kutsuttaessa, paitsi main menu, joka pysyy aina samanlaisena. Kaikki muut ovat riippuvaisia muuttuvista asioista kuten tietokannasta tai pelitilanteesta. Näkymät ovat esillä yksi kerrallaan. 

## Sovelluslogiikka

Sovelluslogiikka pitää huolen itse FishSwim pelistä. Peliin injektoidaan oliot Fish, Obstacle ja Random joita peli tarvitsee toimiakseen. Käyttöliittymä pääsee käsiksi pelin pisteisiin getScores()-metodilla, jotta tietokantaan voidaan tallentaa oikeat pisteet.

Tietokantayhteydet tapahtuvat UI-luokan kautta Hi-score ja endgame-näkymässä ScoresDaon metodeja saveScores(Player player) ja getScores() hyödyntäen.

Pakkauskaavio josta näkee komponenttien yhteydet:

<img src="https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/Kuvat/Pakkauskaaviov2.png" width="750">

## Tietokannanhallinta

Tietokannan luomisesta ja sen yheyksien hallinnasta huolehtii pakkauksessa fishswim.dao oleva luokka ScoresDao.

ScoresDao luo sqlite tietokannan scores.db tiedostoon. 
Tietokannan tietokantakaavio:
<img src="https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/Kuvat/tietokantakaavio.png" width="300">

## Päätoiminnallisuudet

### Pelin aloittaminen

Kun käyttäjä painaa main menussa "play game"-nappia, tapahtuu seuraavanlainen tapahtumaketju:
<img src="https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/Kuvat/Sekvenssikaavio.png" width="750">

## Sovelluksen parannuksia

### sovelluslogiikka
Ohjelmaan olisi voinut tehdä vielä yhden laajemman sovelluslogiikasta huolehtivan luokan. Se käynnistäisi pelin ja hoitaisi tietokantayhteydet, jottei ne tapahtuisi käyttöliittymästä.