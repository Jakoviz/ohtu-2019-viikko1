package ohtu.ohtuvarasto;

import java.nio.channels.UnsupportedAddressTypeException;
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
    public void negatiivinenOttaminenPalauttaaNollan() {
	    double otaVarastosta = varasto.otaVarastosta(-1);
	    assertEquals(0, otaVarastosta, vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsitysVarastoOliolle() {
	    assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void yritetaanLaittaaLiikaaTavaraaLaittaaSaldoksiTilavuuden() {
	    varasto.lisaaVarastoon(11);
	    assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    } 

    @Test
    public void yritetaanOttaaEnemmanKuinSiellaOnAntaaSaldoksiNollan() {
	    varasto.otaVarastosta(1);
	    assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTilavuussaldoisenVarastonKunAlkusaldoYliTilavuus() {
	    varasto = new Varasto(10, 11);
	    assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoNollakokoisenVarastonKunTilavuusNollaTaiAlle() {
	    varasto = new Varasto(0, 1);
	    assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoNollakokoisenVarastonKunTilavuusNollaTaiAlleIlmanAlkusaldoa() {
	    varasto = new Varasto(0);
	    assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoNollasaldoisenVarastonKunAlkusaldoAlleNolla() {
	    varasto = new Varasto(1, -1);
	    assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoAlkusaldosaldoisenVarastonKunAlkusaldoTilavuusTaiAlle() {
	    varasto = new Varasto(11, 10);
	    assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiLisaa() {
	    varasto.lisaaVarastoon(-1);
	    assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriIlmanParametrejaHeittaaPoikkeuksen() {
	    boolean thrown = false;
	    try {
	    	varasto = new Varasto();
	    } catch (UnsupportedOperationException e) {
		thrown = true;	
	    }
	    assertTrue(thrown);
    }
}