package unit;

import model.Pedido;
import model.TipoCategoria;
import org.junit.jupiter.api.Test;
import strategy.CalculoLuxo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculoLuxoTest {

    private final CalculoLuxo estrategia = new CalculoLuxo();

    private Pedido pedido(double km, int min) {
        return new Pedido(km, min, false, false, TipoCategoria.LUXO);
    }

    @Test
    void U02_tarifaBase_10km_20min_deveSer45() {
        assertEquals(45.00, estrategia.calcularTarifaBase(pedido(10.0, 20)), 0.001);
    }

    @Test
    void U04_valoresFracionarios_5_5km_12min_deveSer25_5() {
        assertEquals(25.50, estrategia.calcularTarifaBase(pedido(5.5, 12)), 0.001);
    }
}
