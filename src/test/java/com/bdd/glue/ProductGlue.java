package com.bdd.glue;

import com.bdd.step.ProductStep;
import com.mobile.ManageScenario;
import com.mobile.MobileDriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.*;
import net.thucydides.core.annotations.Steps;


public class ProductGlue {

    @Steps
    private ProductStep productStep;

    private Scenario scenario;
    private int unidadesAgregadas;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
        ManageScenario.setScenario(this.scenario);
        unidadesAgregadas = 0;
    }

    @Dado("^estoy en la aplicación de SauceLabs$")
    public void estoyEnLaAplicacion() {
        MobileDriverManager.setMobileDriver();
    }

    @Y("^valido que carguen correctamente los productos en la galeria$")
    public void validoProductosEnGaleria() {
        productStep.validarProductosEnGaleria();
    }

    @Cuando("^agrego (\\d+) del siguiente producto \"([^\"]*)\"$")
    public void agregoProducto(int unidades, String producto) {
        unidadesAgregadas = unidades;
        productStep.agregarProducto(producto, unidades);
    }

    @Entonces("^valido el carrito de compra actualice correctamente$")
    public void validoCarritoActualizado() {
        productStep.validarCarritoActualizado(unidadesAgregadas);
    }
}
