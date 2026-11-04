package integration;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainSaidaTest {

    @Test
    void I06_main_imprimeValorEmReaisComDuasCasas() throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            Class.forName("Main").getMethod("main", String[].class)
                    .invoke(null, (Object) new String[0]);
        } finally {
            System.setOut(original);
        }
        String saida = buffer.toString(StandardCharsets.UTF_8);

        assertTrue(saida.matches("(?s).*R\\$ 64[.,]69\\s*"),
                "Saida inesperada do Main: " + saida);
    }
}
