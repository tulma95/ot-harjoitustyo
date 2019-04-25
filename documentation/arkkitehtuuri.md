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

## Tietokannanhallinta


## Pakkauskaavio

<img src="https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/Kuvat/Pakkauskaavio.png" width="750">


## Päätoiminnallisuudet

### Pelin aloittaminen

Kun käyttäjä painaa main menussa "play game"-nappia, tapahtuu seuraavanlainen tapahtumaketju:
<img src="https://github.com/tulma95/ot-harjoitustyo/blob/master/documentation/Kuvat/Sekvenssikaavio.png" width="750">

