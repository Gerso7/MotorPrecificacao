package integration;

import builder.PedidoBuilder;
import model.Pedido;
import model.TipoCategoria;
import org.junit.jupiter.api.Test;
import service.CalculadoraController;
import service.CalculadoraSemPadrao;

import static org.junit.jupiter.api.Assertions.*;

class CorridaPipelineTest {

    private final CalculadoraController controller = new CalculadoraController();

    @Test
    void I01_popular_semExtras_10km_20min_deveSer30() {
        Pedido p = new PedidoBuilder().comDistancia(10.0).comTempo(20)
                .comTipoCategoria(TipoCategoria.POPULAR).build();

        assertEquals(30.00, controller.processarCorrida(p), 0.001);
    }

    @Test
    void I02_luxo_chuvaEPico_10km_20min() {
        Pedido p = new PedidoBuilder().comDistancia(10.0).comTempo(20)
                .comTipoCategoria(TipoCategoria.LUXO)
                .comChuva(true).noHorarioPico(true).build();

        double total = controller.processarCorrida(p);

        assertEquals(64.6875, total, 0.01);
        assertEquals(Math.round(total * 100) / 100.0, total, 0.0,
                "Tarifa deveria estar arredondada em centavos, mas veio: " + total);
    }

    @Test
    void I03_popular_apenasChuva_ouApenasPico() {
        Pedido soChuva = new PedidoBuilder().comDistancia(10.0).comTempo(20)
                .comChuva(true).build();
        Pedido soPico = new PedidoBuilder().comDistancia(10.0).comTempo(20)
                .noHorarioPico(true).build();

        assertEquals(34.50, controller.processarCorrida(soChuva), 0.001);
        assertEquals(37.50, controller.processarCorrida(soPico), 0.001);
    }

    @Test
    void I04_controllerEquivaleAoMotorLegadoSemPadrao() {
        Pedido p = new PedidoBuilder().comDistancia(10.0).comTempo(20)
                .comChuva(true).noHorarioPico(true).build();

        double novo = controller.processarCorrida(p);
        double legado = new CalculadoraSemPadrao().calcular(30.0, true, true);

        assertEquals(43.125, legado, 0.001);
        assertEquals(legado, novo, 0.001);
    }

    
    @Test
    void I05_pedidoInvalido_naoDeveGerarTarifaNegativa() {
        Pedido p = new PedidoBuilder().comDistancia(-10.0).comTempo(-20).build();

        assertThrows(IllegalArgumentException.class, () -> controller.processarCorrida(p));
    }
}
