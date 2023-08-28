package tienda;

import productos.Bebida;
import productos.Producto;

import java.util.*;


public class Tienda {
    private int id;
    private String nombre;
    private Short capacidadStock;
    private Double saldo;
    private Map<String, List<Producto>> stock;

    public Tienda(int id, String nombre, Short capacidadStock, Double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadStock = capacidadStock;
        this.saldo = saldo;
        this.stock = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getCapacidadStock() {
        return capacidadStock;
    }

    public void setCapacidadStock(Short capacidadStock) {
        this.capacidadStock = capacidadStock;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Map<String, List<Producto>> getStock() {
        return stock;
    }

    public String agregarListaProducto(String clave,List<Producto> lista){
        if (this.stock.containsKey(clave)){
            return "No se pudo crear la lista, ya que el nombre del indice de la lista ya existe\n"+
                    "y esta asocioado a otra lista.";
        }
        this.stock.put(clave,lista);
        return "La lista de "+clave+" se creo correctamente";
    }
    public Boolean revisarStock(Integer cantidad){
        int cant=0;
        for(List<Producto> lista:this.stock.values()){
            for (Producto producto:lista){
                cant+=producto.getStock();
            }
        }
        cant+=cantidad;
        return cant <= this.capacidadStock;
    }
    public Boolean calcularCosto(Double costo,Integer stock){
        Double costoTotal=(costo*stock);
        return this.saldo >= costoTotal;
    }
    public Boolean verificarIndice(String clave){
        return this.stock.containsKey(clave);
    }

    public int capacidadDisponibleStock(){
        int cant=0;
        for(List<Producto> lista:this.stock.values()){
            for (Producto producto:lista){
                cant+=producto.getStock();
            }
        }
        return this.capacidadStock-cant;
    }
    public String agregarProducto(String clave, Producto produc){
        this.stock.get(clave).add(produc);
        this.saldo-=(produc.getCosto()*produc.getStock());
        System.out.println(this.stock.get(clave));
        return "El producto se agrego a la lista correctamente";
    }
    public Producto encontrarProducto(String clave, String nombre) {
        if (this.stock.containsKey(clave)) {
            List<Producto> lista = this.stock.get(clave);
            System.out.println(lista);
            for (Producto producto : lista) {
                if (producto.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                    return producto;
                }
            }
        }
        return null;
    }
    public void actualizarSaldoVenta(Double monto){
        this.saldo+=monto;
    }
    public List<Producto> verProductos(){
        List<Producto> productos= new ArrayList<>();
        for (List<Producto> list: this.stock.values()){
            productos.addAll(list);
        }
        return productos;
    }

}
