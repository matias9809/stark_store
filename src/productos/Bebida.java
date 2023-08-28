package productos;

import interfaces.IComestible;
import interfaces.IDescuento;

import java.time.LocalDate;

public class Bebida extends Producto implements IComestible, IDescuento {
    private Double graducionAlcoholica;
    private Boolean importado;
    private LocalDate fechaVencimiento;
    private Byte descuento=0;
    private Byte impuesto;

    private double calorias;

    public Bebida(Double graducionAlcoholica, Boolean importado, String id,String nombre, String descripcion, Integer stock, Double precio, Double costo) {
        super( id,  descripcion,  stock,  precio,  costo,nombre);
        this.graducionAlcoholica = graducionAlcoholica;
        this.importado = importado;
        this.precio=aplicarImpuesto();
    }

    public Double getGraducionAlcoholica() {
        return graducionAlcoholica;
    }

    public void setGraducionAlcoholica(Double graducionAlcoholica) {
        this.graducionAlcoholica = graducionAlcoholica;
    }

    public Boolean getImportado() {
        return importado;
    }

    public void setImportado(Boolean importado) {
        this.importado = importado;
    }
    public Double aplicarImpuesto() {
        if(this.importado){
            this.impuesto=10;
            return this.precio+(this.precio*this.impuesto)/100;
        }
        return this.precio;
    }

    @Override
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento=fechaVencimiento;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    @Override
    public void setCalorias(double calorias) {
        this.calorias=calorias;
    }

    @Override
    public double getCalorias() {
        return this.calorias;
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
