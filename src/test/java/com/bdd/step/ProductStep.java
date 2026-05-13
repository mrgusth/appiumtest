package com.bdd.step;

import com.bdd.view.ProductView;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class ProductStep {

    private ProductView productView() {
        return new ProductView();
    }

    @Step
    public void validarProductosEnGaleria() {
        Assert.assertTrue("Productos no cargaron correctamente", productView().validarProductosCargados());
    }

    @Step
    public void agregarProducto(String producto, int unidades) {
        productView().agregarProducto(producto, unidades);
    }

    @Step
    public void validarCarritoActualizado(int unidades) {
        Assert.assertTrue("Carrito no se actualizó correctamente", productView().validarCarritoActualizado(unidades));
    }
}
