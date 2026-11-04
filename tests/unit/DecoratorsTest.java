package unit;

import decorator.ChuvaDecorator;
import decorator.PicoDecorator;
import decorator.Tarifa;
import decorator.TarifaBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecoratorsTest {

    private final Tarifa stub100 = () -> 100.00;

    @Test
    void U10_chuva_aplicaFator1_15() {
        assertEquals(115.00, new ChuvaDecorator(stub100).calcular(), 0.001);
    }

    @Test
    void U11_pico_aplicaFator1_25() {
        assertEquals(125.00, new PicoDecorator(stub100).calcular(), 0.001);
    }

    @Test
    void U12_ordemDosDecorators_naoAlteraOTotal() {
        Tarifa chuvaDepoisPico = new PicoDecorator(new ChuvaDecorator(new TarifaBase(20)));
        Tarifa picoDepoisChuva = new ChuvaDecorator(new PicoDecorator(new TarifaBase(20)));

        assertEquals(28.75, chuvaDepoisPico.calcular(), 0.001);
        assertEquals(28.75, picoDepoisChuva.calcular(), 0.001);
    }

    @Test
    void U13_tarifaBase_devolveOValorInjetado() {
        assertEquals(20.00, new TarifaBase(20).calcular(), 0.0);
    }
}
