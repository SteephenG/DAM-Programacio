package com.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.stefanbirkner.systemlambda.SystemLambda;

import java.io.ByteArrayInputStream;
import java.util.Locale;

public class TestMain {

    @Test
    public void testTransformacions() throws Exception {
        Locale defaultLocale = Locale.getDefault(); // Guarda la configuració regional per defecte
        Locale.setDefault(Locale.US); // Estableix la configuració regional a US

        try {
            String input = "10\n"; // Simula l'entrada de l'usuari (10 kilòmetres)
            String expectedOutput = "Entra una distància en kilòmetres: La distància de 10.000 kilòmetres en milles és 6.200\n" +
                                    "El resultat de tornar a calcular els kilòmetres és: 10.000\n";

            String actualOutput = SystemLambda.tapSystemOut(() -> {
                System.setIn(new ByteArrayInputStream(input.getBytes())); // Simula l'entrada de l'usuari
                Main.main(new String[]{});
            });

            actualOutput = actualOutput.replace("\r\n", "\n"); // Normalitza les noves línies
            String diff = TestStringUtils.findFirstDifference(actualOutput, expectedOutput);
            assertTrue(diff.compareTo("identical") == 0, 
                "\n>>>>>>>>>> >>>>>>>>>>\n" +
                diff +
                "<<<<<<<<<< <<<<<<<<<<\n");
        } finally {
            Locale.setDefault(defaultLocale); // Restaura la configuració regional per defecte
        }
    }
}
