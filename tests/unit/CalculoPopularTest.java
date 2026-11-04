package unit;

import model.Pedido;
import model.TipoCategoria;
import org.junit.jupiter.api.Test;
import strategy.CalculoPopular;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculoPopularTest {

    private final CalculoPopular estrategia = new CalculoPopular();

    private Pedido pedido(double km, int min) {
        return new Pedido(km, min, false, false, TipoCategoria.POPULAR);
    }

    @Test
    void U01_tarifaBase_10km_20min_deveSer30() {
        assertEquals(30.00, estrategia.calcularTarifaBase(pedido(10.0, 20)), 0.001);
    }

    @Test
    void U03_borda_zeroKm_zeroMin_deveSerZero() {
        assertEquals(0.00, estrategia.calcularTarifaBase(pedido(0.0, 0)), 0.001);
    }

    
    @Test
    void U05_entradaNegativa_deveSerRejeitada() {
        assertThrows(IllegalArgumentException.class,
                () -> estrategia.calcularTarifaBase(pedido(-10.0, -20)));
    }
}
