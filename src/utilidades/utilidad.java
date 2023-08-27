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
    public static List<String> obtenerComestiblesConMenorDescuento(Double porcentajeDesc,List<Bebida> listaBebid, List<Envasado> listaEnvas){
//        List<String> listaConMenorDesc=new ArrayList<>();
//        for (Bebida produc:listaBebid){
//            if (!produc.getImportado()&&produc.getPorcentajeDescuento()<porcentajeDesc) {
//                listaConMenorDesc.add(produc.getNombre().toUpperCase());
//            }
//        }
//        for (Envasado produc:listaEnvas){
//            if (!produc.getImportado()&&produc.getPorcentajeDescuento()<porcentajeDesc) {
//                listaConMenorDesc.add(produc.getNombre().toUpperCase());
//            }
//        }
        List<String> listaBebidas=listaBebid.stream()
                .filter(beb->!beb.getImportado()&&beb.getPorcentajeDescuento()<porcentajeDesc)
                .sorted(Comparator.comparingDouble(Bebida::getPrecio))
                .map(be->be.getNombre().toUpperCase())
                .collect(toList());
        List<String> listaEnvasados=listaEnvas.stream()
                .filter(env->!env.getImportado()&&env.getPorcentajeDescuento()<porcentajeDesc)
                .sorted(Comparator.comparingDouble(Envasado::getPrecio))
                .map(be->be.getNombre().toUpperCase())
                .collect(toList());
        List<String> listaConMenorDesc= Stream.of(listaBebidas,listaEnvasados).flatMap(Collection::stream).sorted().collect(toList());
        return listaConMenorDesc;
    }
    public static List<Producto> listarProductosConUtilidadesInferiores(Double porcentaje, Tienda tienda){
        List<Producto> lista=tienda.getStock().get("Bebida").stream().filter(beb->(beb.getPrecio()*beb.getCosto() )*100<porcentaje).collect(toList());
        return lista;
    }


}
