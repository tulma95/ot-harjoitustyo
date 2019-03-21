package com.mycompany.unicafe;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }
    
    @Test
    public void toStringToimiiHalutusti() {
        assertThat(kortti.toString(), is(equalTo("saldo: 0.10")));
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertThat(kortti.saldo(), is(10));
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertThat(kortti.saldo(), is(15));
    }

    @Test
    public void rahanVähennysToimiiJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        assertThat(kortti.saldo(), is(5));
    }

    @Test
    public void rahaaEiVahenentaJosSaldoEiRiita() {
        kortti.otaRahaa(15);
        assertThat(kortti.saldo(), is(10));
    }

    @Test
    public void palauttaaTrueJosSaldoRiittaa() {
        assertTrue("Palautti false vaikka saldo riitti", kortti.otaRahaa(5));
    }
    
    @Test
    public void palauttaaFalseJosSaldoEiRiita() {
        assertFalse("Palautti true vaikka saldo ei riittänyt", kortti.otaRahaa(50));
    }

}
