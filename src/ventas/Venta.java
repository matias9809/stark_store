package ventas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Venta {
    private String id;
    private Double precioFinal;
    private Integer cantidad;
    private Double precioPorUnidad;
    private String nombre;

    public Venta(String id,Double precioFinal,Integer cantidad,String nombre) {
        this.id=id;
        this.precioFinal = precioFinal;
        this.precioPorUnidad=precioFinal/cantidad;
        this.cantidad=cantidad;
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public Double getPrecioFinal() {
        return this.precioFinal;
    }

    public void setPrecioFinal(Double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(Double precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }
}
