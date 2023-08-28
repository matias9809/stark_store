package utilidades;

import enums.Categoria;
import productos.Bebida;
import productos.Envasado;
import productos.Producto;
import tienda.Tienda;
import ventas.ControlDeVentas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class utilidad {
    public static String GeneradorDeId(Enum catego){
        int numero1=(int) (Math.random() * (999));
        String.format("%03d", numero1);
        String numero;
        if(catego== Categoria.ENVASADO){
             numero ="AB"+numero1;
        }
        else if(catego== Categoria.BEBIDA){
             numero ="AC"+numero1;
        }
        else if(catego== Categoria.LIMPIEZA){
             numero ="AZ"+numero1;
        }
        else if(catego== Categoria.VENTA){
            numero ="AZ"+numero1;
        }
        else {
            return "no existe la categoria "+catego+" para registrar un nuevo producto";
        }
        return numero;
    }
    public static String generadorIdVentas(ControlDeVentas historial){
        int ultimaPosicion=historial.getRegistroDeVentas().size();
        StringBuilder numeros=new StringBuilder();
        int numero;
        if (ultimaPosicion>0){
            String id=historial.getRegistroDeVentas().get(ultimaPosicion-1).getId();
            for (char c: id.toCharArray()){
                if (Character.isDigit(c)){
                    numeros.append(c);
                }
            }
            int numeroId=Integer.parseInt(numeros.toString());
            numero=numeroId+1;
            String.format("%03d", numero);
            return "AB"+numero;
        }
        return "AB001";

    }
    public static Boolean calcularGancia(Double costo, Double precio, String clave){
        Double porcentaje=(precio/costo)-1;
        if (clave.equals("Bebida")||clave.equals("Envasado")){
            if (porcentaje>0.2){
                return false;
            }
            return true;
        }

        if (porcentaje<0.1&&porcentaje>0.25){
                return false;
            }
            return true;

    }
    public static Boolean calcularGanciaMultiusoRopa(Double costo, Double precio){
        Double porcentaje=(precio/costo)-1;

        if (porcentaje>0.25){
            return false;
        }
        return true;

    }
    public static Boolean calcularDescuento(Double desc, String clave){
        if (clave.equals("Bebida")){
            if (desc>15){
                return false;
            }
            return true;
        }
        if (clave.equals("Envasado")) {
            if (desc>20) {
                return false;
            }
            return true;
        }
        if(desc>25){
            return false;
        }
        return true;
    }
    public static List<String> obtenerComestiblesConMenorDescuento(double porcentajeDescuento,List<Envasado> productosEnvasados, List<Bebida> productosBebidas) {
        List<String> productoEnvasadoList = productosEnvasados.stream()
                .filter(p -> !p.getImportado()  && p.getPorcentajeDescuento() < porcentajeDescuento)
                .sorted(Comparator.comparingDouble(Envasado::getPrecio))
                .map(p -> p.getNombre().toUpperCase())
                .collect(Collectors.toList());

        List<String> productoBebidaList = productosBebidas.stream()
                .filter(productoBebida -> !productoBebida.getImportado() && productoBebida.getPorcentajeDescuento() < porcentajeDescuento)
                .sorted(Comparator.comparingDouble(Bebida::getPrecio))
                .map(productoBebida -> productoBebida.getNombre().toUpperCase())
                .collect(Collectors.toList());


        List<String> productosComestiblesConMenorDescuento = Stream.of(productoBebidaList, productoEnvasadoList)
                .flatMap(Collection::stream)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(productosComestiblesConMenorDescuento);
        return productosComestiblesConMenorDescuento;
    }
    public static void listarProductosConUtilidadesInferiores(Double porcentaje, Tienda tienda){
        List<String> productoBebidaList=tienda.getStock().get("Bebida")
                .stream().filter(beb->((beb.getPrecio()/beb.getCosto())-1 )*100<porcentaje)
                .map(Bebida ->Bebida.getCodigo()+" "+ Bebida.getNombre().toUpperCase()+" "+Bebida.getStock())
                .collect(toList());
        List<String> productoEnvasadoList=tienda.getStock().get("Envasado")
                .stream()
                .filter(Env->((Env.getPrecio()/Env.getCosto())-1 )*100<porcentaje)
                .map(Envasado ->Envasado.getCodigo()+" "+ Envasado.getNombre().toUpperCase()+" "+Envasado.getStock())
                .collect(toList());
        List<String> productoLimpiezaList=tienda.getStock().get("Limpieza")
                .stream()
                .filter(Lim->((Lim.getPrecio()/Lim.getCosto())-1 )*100<porcentaje)
                .map(Limpieza ->Limpieza.getCodigo()+" "+ Limpieza.getNombre().toUpperCase()+" "+Limpieza.getStock())
                .collect(toList());
        List<String> productosConMenorGanacia = Stream.of(productoBebidaList, productoEnvasadoList,productoLimpiezaList)
                .flatMap(Collection::stream)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(productosConMenorGanacia);
    }


}
