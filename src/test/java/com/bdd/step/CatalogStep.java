package com.bdd.step;

import com.bdd.view.CatalogView;
import net.thucydides.core.annotations.Step;

/**
 * @author Karla Ccallo
 */
public class CatalogStep {

    private CatalogView catalogSauceView() {
        return new CatalogView();
    }

    @Step
    public boolean validarPantalla(String sTitulo) {
        return catalogSauceView().validarPantalla(sTitulo);
    }

}
