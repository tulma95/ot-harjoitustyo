# Käyttöohje

Voit ladata FishSwim sovelluksen painamalla [tästä](https://github.com/tulma95/ot-harjoitustyo/releases/download/viikko6/FishSwim-1.1-SNAPSHOT.jar)

## Käynnistäminen
Voit käynnistää ohjelman komentorivikomennolla 
``java -jar FishSwim-1.1-SNAPSHOT.jar`` 


## Aloitusnäkymä
Ohjelma käynnistyy päävalikkoon jossa on kolme eri nappia. 
- Painamalla "Play game"-nappia pääset pelaamaan pelitä.
- Painamalla "Hi-Scores"-nappia pääset tarkastelemaan tallennettuja huippupisteitä
- Painamalla "Exit"-nappia sovellus sulkeutuu

## Pelinäkymä
Pelin tarkoituksena on päästä mahdollisimman pitkälle koukkuja ja merilevää väistellen. Pelaaja saa aina pisteen ohittaessaan esteen. Pelihahmo vajoaa kiihtyvällä tahdilla alaspäin ja painamalla joko W:tä tai välilyöntiä pelihahmo ui ylöspäin.

## Pelin jälkeinen näkymä
Kun peli päättyy vaihtuu näkymä pelin jälkeiseen näkymään. Näkymässä on pelaajan saamat pisteet, tekstikenttä ja kolme nappia. Pelaaja voi syöttää haluamansa nimen tekstikenttään ja painaa "Save score"-nappia, jolloin pisteet tallentuvat tietokantaan. "Play again"-nappi aloittaa uuden pelin ja "Main Menu"-nappi palaa takaisin päävalikkoon.

## Hi-scores näkymä
Huippupisteet näkymään pääsee painamalla "Hi-scores"-nappia päävalikossa. Näkymässä on Back-nappula joka palaa takaisin päävalikkoon, teksti joka näyttää mitä sivua tarkastellaan sekä nappulat sivun vaihtamiseen. Piseitä näytetään aina 10 per sivu, parhaat ensin.
