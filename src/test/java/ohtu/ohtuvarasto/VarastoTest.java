package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoLuodaanNollaTilavuudellaJosTilavuusAlleNolla() {
        varasto = new Varasto(-10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoLuodaanNollaTilavuudellaJosTilavuusAlleNollaJaAlkusaldoAnnettu() {
        varasto = new Varasto(-10, 20);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoLuotoAlkuSaldolla() {
        varasto = new Varasto(20, 10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilavuudenYlittysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(100);
        assertEquals(100, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaArvoja() {
        varasto.lisaaVarastoon(-10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }  
    
    @Test
    public void saldoaSuurempiOttoEiVieSaldoaNegatiiviseksi() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    } 

    @Test
    public void negatiivisellaArvollaOttaminenEiTeeMitaan() {
        varasto.lisaaVarastoon(10);
        double otettu = varasto.otaVarastosta(-20);
        assertEquals(0, otettu, vertailuTarkkuus);
    }

    @Test
    public void negatiivisellaArvollaOttaminenEiMuutaSaldoa() {
        varasto.lisaaVarastoon(10);
        double otettu = varasto.otaVarastosta(-20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

}