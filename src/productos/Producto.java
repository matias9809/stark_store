package productos;


public class Producto {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected Integer stock;
    protected Double precio;
    protected Double costo;
    protected Boolean disponibilidad;

    public Producto(String id, String descripcion, Integer stock, Double precio, Double costo,String nombre) {

        this.codigo = id;
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.costo = costo;
        this.disponibilidad = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "id='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", costo=" + costo +
                ", disponibilidad=" + disponibilidad +
                '}';
    }
}
