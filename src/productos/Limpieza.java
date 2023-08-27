package productos;

import interfaces.IDescuento;

public class Limpieza extends Producto implements IDescuento {
    private Enum tipoAplicacion;
    protected Byte descuento;

    public Limpieza(Enum tipoAplicacion, String id, String nombre, String descripcion, Integer stock, Double precio, Double costo) {
        super(id,  descripcion,  stock,  precio,  costo,nombre);
        this.tipoAplicacion = tipoAplicacion;
    }

    public Enum getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(Enum tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }
    @Override
    public void setPorcentajeDescuento(Byte porcentajeDescuento) {
        this.descuento=porcentajeDescuento;
    }

    @Override
    public double getPorcentajeDescuento() {
        return this.descuento;
    }

    @Override
    public double getPrecioConDescuento() {
        return this.precio-((this.precio*this.descuento)/100);
    }
}

