package com.bdd.glue;


import com.bdd.step.CatalogStep;
import io.cucumber.java.es.Y;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

/**
 * @author Karla Ccallo
 */
public class CatalogGlue {

    @Steps
    private CatalogStep catalogStep;

    @Y("deberia mostrarme la pantalla de catalogo de productos {string}")
    public void meMuestraLaPantallaDe(String sTitulo) {
        Assert.assertTrue("No mostro pantalla: "+sTitulo, catalogStep.validarPantalla(sTitulo));
    }

}
