package unit;

import model.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PedidoTest {

    
    @Test
    void U09_categoriaNula_deveSerRejeitada() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(1.0, 1, false, false, null));
    }
}
