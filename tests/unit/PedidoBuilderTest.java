package unit;

import builder.PedidoBuilder;
import model.Pedido;
import model.TipoCategoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoBuilderTest {

    @Test
    void U06_valoresPadrao_popular_semChuva_semPico() {
        Pedido p = new PedidoBuilder().build();

        assertEquals(TipoCategoria.POPULAR, p.getTipoCategoria());
        assertFalse(p.isEstaChovendo());
        assertFalse(p.isHorarioPico());
        assertEquals(0.0, p.getDistanciaKm(), 0.0);
        assertEquals(0, p.getTempoMinutos());
    }

    @Test
    void U07_todosOsCamposChegamAoPedido() {
        Pedido p = new PedidoBuilder()
                .comDistancia(7.5)
                .comTempo(15)
                .comTipoCategoria(TipoCategoria.LUXO)
                .comChuva(true)
                .noHorarioPico(true)
                .build();

        assertEquals(7.5, p.getDistanciaKm(), 0.0);
        assertEquals(15, p.getTempoMinutos());
        assertEquals(TipoCategoria.LUXO, p.getTipoCategoria());
        assertTrue(p.isEstaChovendo());
        assertTrue(p.isHorarioPico());
    }

    
    @Test
    void U08_distanciaNula_deveLancarIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new PedidoBuilder().comDistancia(null));
    }
}
