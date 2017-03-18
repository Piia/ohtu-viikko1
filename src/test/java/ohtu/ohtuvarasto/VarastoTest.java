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

    // - konstruktori varasto(tilavuus) -
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriKorjaaNegatiivisenTilavuuden() {
        Varasto hyodyton = new Varasto(-20.0);
        assertEquals(0, hyodyton.getTilavuus(), vertailuTarkkuus);
    }
    
    // - konstruktori varasto(tilavuus, alkuSaldo) -
    
    @Test
    public void konstruktori2LuoTyhjanVarastonAnnetullaTilavuudella() {
        Varasto uusi = new Varasto(20, 0);
        assertEquals(0, uusi.getSaldo(), vertailuTarkkuus);
        assertEquals(20, uusi.getTilavuus(), vertailuTarkkuus);
        
    }
    
    @Test
    public void luoTurhanVarastonJaHylkaaYlimaaraisenAlkusaldon() {
        Varasto hyodyton = new Varasto(-20, 20);
        assertEquals(10, hyodyton.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, hyodyton.getSaldo(), vertailuTarkkuus);
        
    }

    @Test
    public void varastollaOikeaTilavuusJaAlkusaldo() {
        Varasto uusi = new Varasto(10, 2);
        assertEquals(10, uusi.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, uusi.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void negatiivinenAlkusaldoHylataanMuttaTilavuusPysyy() {
        Varasto uusi = new Varasto(10, -2);
        assertEquals(10, uusi.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, uusi.getSaldo(), vertailuTarkkuus);
        
    }
    
    // - muut metodit -

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi pysyä samana, eli nollana
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanLiikaa() {
        varasto.lisaaVarastoon(8000);

        // saldon pitäisi nousta maksimiin, ei sen yli
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
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
    public void otetaanLiikaaJolloinSaldoNollaantuu() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(20);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void otetaanNegatiivinenMaara() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-1);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(5);
        String teksti = varasto.toString();
        assertEquals("saldo = 5.0, vielä tilaa 5.0", teksti);
    }

}