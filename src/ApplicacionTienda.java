import enums.Categoria;
import enums.TipoAplicacion;
import enums.TipoEnvase;
import productos.Bebida;
import productos.Envasado;
import productos.Limpieza;
import productos.Producto;
import tienda.Tienda;
import ventas.ControlDeVentas;
import ventas.Venta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilidades.utilidad.*;


public class ApplicacionTienda {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String nombre="Stark store";
        ControlDeVentas registroVentas=new ControlDeVentas();
        Double saldo= 500000.0;
        Short CapacidadSctockMax= 20000;
        Tienda starkStore=new Tienda(1,nombre,CapacidadSctockMax,saldo);
        List<Producto> listaBebidas=new ArrayList<>();
        List<Producto> listaEnvasados=new ArrayList<>();
        List<Producto> listaLimpieza=new ArrayList<>();
        Bebida cocaCola=new Bebida(0.0,true,GeneradorDeId(Categoria.BEBIDA),"Coca cola","Bebida con gas y azucares", 50,400.0*1.2,400.0);
        listaBebidas.add( cocaCola);
        Envasado atun=new Envasado(TipoEnvase.LATA,false,GeneradorDeId(Categoria.ENVASADO),"Atun","Atun sumergido en aceite",30,190*1.2,190.0);
        listaEnvasados.add(atun);
        Limpieza suavisante=new Limpieza(TipoAplicacion.ROPA,GeneradorDeId(Categoria.LIMPIEZA),"suavisante","suavisante con fragancia de lavanda de 3L",15,700.0*1.2,700.0);
        listaLimpieza.add(suavisante);
        Bebida manaos=new Bebida(0.0,false,GeneradorDeId(Categoria.BEBIDA),"Manaos lima","Bebida con gas y azucares", 50,200.0*1.1,200.0);
        listaBebidas.add( manaos);
        Envasado arroz=new Envasado(TipoEnvase.LATA,false,GeneradorDeId(Categoria.ENVASADO),"arroz","arroz",30,190.0*1.1,190.0);
        listaEnvasados.add(arroz);
        Limpieza jabon=new Limpieza(TipoAplicacion.ROPA,GeneradorDeId(Categoria.LIMPIEZA),"javon","javon con fragancia de lavanda de 3L",15,100.0*1.2,100.0);
        listaLimpieza.add(jabon);

        starkStore.agregarListaProducto("Bebida",listaBebidas);
        starkStore.agregarListaProducto("Envasado",listaEnvasados);
        starkStore.agregarListaProducto("Limpieza",listaLimpieza);
        List<Bebida> listaBebid=new ArrayList<>();
        for (Producto prod:starkStore.getStock().get("Bebida")){
             listaBebid.add((Bebida) prod);
        }
        List<Envasado> listaEnvas=new ArrayList<>();
        for (Producto produc:starkStore.getStock().get("Envasado")){
            listaEnvas.add((Envasado) produc);
        }
        obtenerComestiblesConMenorDescuento(15,listaEnvas ,listaBebid );
        listarProductosConUtilidadesInferiores(15.0,starkStore);
        System.out.println("---------------------------------------------Bienvenido a "+nombre+"------------------------------------------");
        Boolean opcionIngreso=true;

        while (opcionIngreso) {

            System.out.println("-----------------------------------------¿que accion desea realizar?---------------------------------------------");
            System.out.println("1:Comprar");
            System.out.println("2:vender");
            System.out.println("3:Lista de productos");
            System.out.println("4:Salir");
            Boolean opccionMenuComprasVentas=true;
            Byte opcionAccion=scan.nextByte();
            try {
                switch (opcionAccion) {
//-----------------------------------------------------Compras----------------------------------------------------------
                    case 1:
                        while (opccionMenuComprasVentas) {
                            String id;
                            String nombreProduc;
                            String descripcion;
                            Integer cantidadProductos;
                            Double precio;
                            Double costo;
                            Boolean importada;
                            Boolean stockDisponible;
                            Boolean saldoDisponible;
                            Boolean revisarClave;
                            System.out.println("seleccione el tipo de producto que desea comprar");
                            System.out.println("1:Bebidas");
                            System.out.println("2:Envasado");
                            System.out.println("3:Limpieza");
                            System.out.println("4:Salir");
                            opcionAccion = scan.nextByte();
                            switch (opcionAccion) {
//-------------------------------------------------------Bebidas--------------------------------------------------------
                                case 1:
                                    Double graduacionAlcoholica;
                                    id = GeneradorDeId(Categoria.BEBIDA);
                                    System.out.println("ingrese el nombre del producto: ");
                                    scan.nextLine();
                                    nombreProduc = scan.nextLine();
                                    System.out.println("ingrese una breve descripcion del producto: ");
                                    descripcion = scan.nextLine();
                                    System.out.println("ingrese la cantidad de dicho producto en numeros: ");
                                    cantidadProductos = scan.nextInt();
                                    System.out.println("ingrese el costo del producto: ");
                                    costo = scan.nextDouble();
                                    System.out.println("ingrese el precio del producto: ");
                                    precio = scan.nextDouble();
                                    if (calcularGancia(costo, precio, "Bebida")) {
                                        System.out.println("¿La bebida contiene alcohol? ingrese 1 si la respuesta es si de lo contrario ingrese 2");
                                        opcionAccion = scan.nextByte();
                                        switch (opcionAccion) {
                                            case 1:
                                                System.out.println("ingrese la graduacion alcoholica solo en numeros sin simbolos");
                                                graduacionAlcoholica = scan.nextDouble();
                                                System.out.println("¿La bebida es importada? ingrese 1 si la respuesta es si de lo contrario ingrese 2");
                                                opcionAccion = scan.nextByte();
                                                switch (opcionAccion) {
                                                    case 1:
                                                        importada = true;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        revisarClave = starkStore.verificarIndice("Bebida");
                                                        if (saldoDisponible && stockDisponible && revisarClave) {
                                                            Bebida nuevaBevida = new Bebida(graduacionAlcoholica, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Bebida", nuevaBevida);
                                                            System.out.println("Codigo:");
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevaBevida.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        }
                                                        break;
                                                    case 2:
                                                        importada = false;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        revisarClave = starkStore.verificarIndice("Bebida");
                                                        if (saldoDisponible && stockDisponible && revisarClave) {
                                                            Bebida nuevaBevida = new Bebida(graduacionAlcoholica, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Bebida", nuevaBevida);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevaBevida.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("La opcion ingresada es incorrecta");
                                                        break;
                                                }
                                                break;
                                            case 2:
                                                graduacionAlcoholica = 0.0;
                                                System.out.println("¿La bebida es importada? ingrese 1 si la respuesta " +
                                                        "es si de lo contrario ingrese 2");
                                                opcionAccion = scan.nextByte();
                                                switch (opcionAccion) {
                                                    case 1:
                                                        importada = true;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        revisarClave = starkStore.verificarIndice("Bebida");
                                                        if (saldoDisponible && stockDisponible && revisarClave) {
                                                            Bebida nuevaBevida = new Bebida(graduacionAlcoholica, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Bebida", nuevaBevida);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevaBevida.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        }
                                                        break;
                                                    case 2:
                                                        importada = false;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        revisarClave = starkStore.verificarIndice("Bebida");
                                                        if (saldoDisponible && stockDisponible && revisarClave) {
                                                            Bebida nuevaBevida = new Bebida(graduacionAlcoholica, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Bebida", nuevaBevida);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevaBevida.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("La opcion ingresada es incorrecta");
                                                        break;
                                                }
                                                break;
                                            default:
                                                System.out.println("la opcion ingresada es incorrecta");
                                                break;
                                        }
                                    } else {
                                        System.out.println("La ganancia excede 20% permitido le sugerimos poner como precio maximo: " + costo * 1.2);
                                        break;
                                    }
                                    break;
//-------------------------------------------------------Envasados--------------------------------------------------------
                                case 2:
                                    Enum tipoEnvase;
                                    id = GeneradorDeId(Categoria.ENVASADO);
                                    System.out.println("ingrese el nombre del producto: ");
                                    scan.nextLine();
                                    nombreProduc = scan.nextLine();
                                    System.out.println("ingrese una breve descripcion del producto: ");
                                    descripcion = scan.nextLine();
                                    System.out.println("ingrese la cantidad de dicho producto en numeros: ");
                                    cantidadProductos = scan.nextInt();
                                    System.out.println("ingrese el costo del producto: ");
                                    costo = scan.nextDouble();
                                    System.out.println("ingrese el precio del producto: ");
                                    precio = scan.nextDouble();
                                    if (calcularGancia(costo, precio, "Envasado")) {
                                        System.out.println("¿Que tipo de envase es? ingrese una de las siguientes opciones: \n"
                                                + "1:" + TipoEnvase.LATA + "\n" +
                                                "2:" + TipoEnvase.PLASTICO + "\n" +
                                                "3:" + TipoEnvase.VIDRIO);
                                        opcionAccion = scan.nextByte();
                                        switch (opcionAccion) {
                                            case 1:
                                                tipoEnvase = TipoEnvase.LATA;
                                                System.out.println("¿La bebida es importada? ingrese 1 si la respuesta " +
                                                        "es si de lo contrario ingrese 2");
                                                opcionAccion = scan.nextByte();
                                                switch (opcionAccion) {
                                                    case 1:
                                                        importada = true;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        if (saldoDisponible && stockDisponible) {
                                                            Envasado nuevoEnvasado = new Envasado(tipoEnvase, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Envasado", nuevoEnvasado);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevoEnvasado.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        } else if (!saldoDisponible) {
                                                            System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                                    "saldo insuficiente en la caja");
                                                            break;
                                                        } else if (!stockDisponible) {
                                                            System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                        }
                                                        break;
                                                    case 2:
                                                        importada = false;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        if (saldoDisponible && stockDisponible) {
                                                            Envasado nuevoEnvasado = new Envasado(tipoEnvase, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Envasado", nuevoEnvasado);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevoEnvasado.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        } else if (!saldoDisponible) {
                                                            System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                                    "saldo insuficiente en la caja");
                                                            break;
                                                        } else if (!stockDisponible) {
                                                            System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("La opcion ingresada es incorrecta");
                                                        break;
                                                }
                                                break;

                                            case 2:
                                                tipoEnvase = TipoEnvase.PLASTICO;
                                                System.out.println("¿La bebida es importada? ingrese 1 si la respuesta es si de lo contrario ingrese 2");
                                                opcionAccion = scan.nextByte();
                                                switch (opcionAccion) {
                                                    case 1:
                                                        importada = true;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        if (saldoDisponible && stockDisponible) {
                                                            Envasado nuevoEnvasado = new Envasado(tipoEnvase, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Envasado", nuevoEnvasado);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevoEnvasado.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        } else if (!saldoDisponible) {
                                                            System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                                    "saldo insuficiente en la caja");
                                                            break;
                                                        } else if (!stockDisponible) {
                                                            System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                        }
                                                        break;
                                                    case 2:
                                                        importada = false;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        if (saldoDisponible && stockDisponible) {
                                                            Envasado nuevoEnvasado = new Envasado(tipoEnvase, importada, id,
                                                                    nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Envasado", nuevoEnvasado);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevoEnvasado.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        } else if (!saldoDisponible) {
                                                            System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                                    "saldo insuficiente en la caja");
                                                            break;
                                                        } else if (!stockDisponible) {
                                                            System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("La opcion ingresada es incorrecta");
                                                        break;
                                                }
                                                break;

                                            case 3:
                                                tipoEnvase = TipoEnvase.VIDRIO;
                                                System.out.println("¿La bebida es importada? ingrese 1 si la respuesta es si de lo" +
                                                        " contrario ingrese 2");
                                                opcionAccion = scan.nextByte();
                                                switch (opcionAccion) {
                                                    case 1:
                                                        importada = true;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        if (saldoDisponible && stockDisponible) {
                                                            Envasado nuevoEnvasado = new Envasado(tipoEnvase, importada, id, nombreProduc,
                                                                    descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Envasado", nuevoEnvasado);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevoEnvasado.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        }
                                                        break;
                                                    case 2:
                                                        importada = false;
                                                        saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                                        stockDisponible = starkStore.revisarStock(cantidadProductos);
                                                        if (saldoDisponible && stockDisponible) {
                                                            Envasado nuevoEnvasado = new Envasado(tipoEnvase, importada, id, nombreProduc, descripcion, cantidadProductos, precio, costo);
                                                            starkStore.agregarProducto("Envasado", nuevoEnvasado);
                                                            System.out.println("El producto fue registrado exitosamente");
                                                            System.out.println(nuevoEnvasado.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                            System.out.println("Precio total: " + costo * cantidadProductos);
                                                        } else if (!saldoDisponible) {
                                                            System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                                    "saldo insuficiente en la caja");
                                                            break;
                                                        } else if (!stockDisponible) {
                                                            System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("La opcion ingresada es incorrecta");
                                                        break;
                                                }

                                            default:
                                                System.out.println("la opcion ingresada es incorrecta");
                                                break;
                                        }
                                    }
                                    break;
//-------------------------------------------------------Limpieza-------------------------------------------------------
                                case 3:
                                    TipoAplicacion tipoAplicacion;
                                    id = GeneradorDeId(Categoria.LIMPIEZA);
                                    System.out.println("ingrese el nombre del producto: ");
                                    scan.nextLine();
                                    nombreProduc = scan.nextLine();
                                    System.out.println("ingrese una breve descripcion del producto: ");
                                    descripcion = scan.nextLine();
                                    System.out.println("ingrese la cantidad de dicho producto en numeros: ");
                                    cantidadProductos = scan.nextInt();
                                    System.out.println("ingrese el costo del producto: ");
                                    costo = scan.nextDouble();
                                    System.out.println("ingrese el precio del producto: ");
                                    precio = scan.nextDouble();
                                    System.out.println("¿Que tipo de envase es? ingrese una de las siguientes opciones: \n"
                                            + "1:" + TipoAplicacion.COCINA + "\n" +
                                            "2:" + TipoAplicacion.MULTIUSO + "\n" +
                                            "3:" + TipoAplicacion.PISOS + "\n" +
                                            "4:" + TipoAplicacion.ROPA);
                                    opcionAccion = scan.nextByte();
                                    switch (opcionAccion) {
                                        case 1:
                                            tipoAplicacion = TipoAplicacion.COCINA;
                                            saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                            stockDisponible = starkStore.revisarStock(cantidadProductos);
                                            revisarClave = starkStore.verificarIndice("Limpieza");
                                            if (calcularGanciaMultiusoRopa(costo, precio)) {
                                                if (saldoDisponible && stockDisponible && revisarClave) {
                                                    Limpieza nuevoLimpieza = new Limpieza(tipoAplicacion, id, nombreProduc, descripcion,
                                                            cantidadProductos, precio, costo);
                                                    starkStore.agregarProducto("Envasado", nuevoLimpieza);
                                                    System.out.println("El producto fue registrado exitosamente");
                                                    System.out.println(nuevoLimpieza.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                    System.out.println("Precio total: " + costo * cantidadProductos);
                                                    break;
                                                } else if (!saldoDisponible) {
                                                    System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                            "saldo insuficiente en la caja");
                                                    break;
                                                } else if (!stockDisponible) {
                                                    System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                    break;
                                                }
                                            } else {
                                                System.out.println("La ganancia excede 20% permitido le sugerimos poner como precio maximo: " + costo * 1.25);
                                                break;
                                            }
                                            break;
                                        case 2:
                                            tipoAplicacion = TipoAplicacion.MULTIUSO;
                                            saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                            stockDisponible = starkStore.revisarStock(cantidadProductos);
                                            revisarClave = starkStore.verificarIndice("Limpieza");

                                            if (calcularGanciaMultiusoRopa(costo, precio)) {
                                                if (saldoDisponible && stockDisponible && revisarClave) {
                                                    Limpieza nuevoLimpieza = new Limpieza(tipoAplicacion, id, nombreProduc, descripcion,
                                                            cantidadProductos, precio, costo);
                                                    starkStore.agregarProducto("Envasado", nuevoLimpieza);
                                                    System.out.println("El producto fue registrado exitosamente");
                                                    System.out.println(nuevoLimpieza.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                    System.out.println("Precio total: " + costo * cantidadProductos);
                                                    break;
                                                } else if (!saldoDisponible) {
                                                    System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                            "saldo insuficiente en la caja");
                                                    break;
                                                } else if (!stockDisponible) {
                                                    System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                    break;
                                                }
                                            } else {
                                                System.out.println("La ganancia excede 20% permitido le sugerimos poner como precio maximo: " + costo * 1.25);
                                                break;
                                            }
                                            break;
                                        case 3:
                                            tipoAplicacion = TipoAplicacion.PISOS;
                                            saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                            stockDisponible = starkStore.revisarStock(cantidadProductos);
                                            revisarClave = starkStore.verificarIndice("Limpieza");
                                            if (calcularGancia(costo, precio, "Limpieza")) {
                                                if (saldoDisponible && stockDisponible && revisarClave) {
                                                    Limpieza nuevoLimpieza = new Limpieza(tipoAplicacion, id, nombreProduc, descripcion,
                                                            cantidadProductos, precio, costo);
                                                    starkStore.agregarProducto("Limpieza", nuevoLimpieza);
                                                    System.out.println("El producto fue registrado exitosamente");
                                                    System.out.println(nuevoLimpieza.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                    System.out.println("Precio total: " + costo * cantidadProductos);
                                                } else if (!saldoDisponible) {
                                                    System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                            "saldo insuficiente en la caja");
                                                    break;
                                                } else if (!stockDisponible) {
                                                    System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                    break;
                                                }
                                            } else {
                                                System.out.println("La ganancia excede 25% permitido le sugerimos poner como precio maximo: $"
                                                        + costo * 1.25 + "y tampoco sea menor a: $" + costo * 1.1);
                                                break;
                                            }
                                            break;
                                        case 4:
                                            tipoAplicacion = TipoAplicacion.ROPA;
                                            saldoDisponible = starkStore.calcularCosto(costo, cantidadProductos);
                                            stockDisponible = starkStore.revisarStock(cantidadProductos);
                                            revisarClave = starkStore.verificarIndice("Limpieza");
                                            if (calcularGanciaMultiusoRopa(costo, precio)) {
                                                if (saldoDisponible && stockDisponible && revisarClave) {
                                                    Limpieza nuevoLimpieza = new Limpieza(tipoAplicacion, id, nombreProduc, descripcion,
                                                            cantidadProductos, precio, costo);
                                                    starkStore.agregarProducto("Limpieza", nuevoLimpieza);
                                                    System.out.println("El producto fue registrado exitosamente");
                                                    System.out.println(nuevoLimpieza.getCodigo() + " " + nombreProduc + " " + cantidadProductos + "U" + " * $" + precio);
                                                    System.out.println("Precio total: " + costo * cantidadProductos);
                                                } else if (!saldoDisponible) {
                                                    System.out.println("El producto no podrá ser agregado a la tienda por\n" +
                                                            "saldo insuficiente en la caja");
                                                    break;
                                                } else if (!stockDisponible) {
                                                    System.out.println("no hay stock disponible para la cantidad de productos, la capacidad disponible es de " + starkStore.capacidadDisponibleStock());
                                                    break;
                                                }
                                            } else {
                                                System.out.println("La ganancia excede 25% permitido le sugerimos poner como precio maximo: " + costo * 1.25);
                                                break;
                                            }
                                            break;

                                        default:
                                            System.out.println("la opcion ingresada es incorrecta");
                                            break;
                                    }
                                case 4:
                                    opccionMenuComprasVentas=false;
                                    break;
                                default:
                                    System.out.println("la opcion ingresada es incorrecta");
                                    break;

                            }
                        }
                        break;
//-------------------------------------------------------Ventas---------------------------------------------------------
                    case 2:
                        while (opccionMenuComprasVentas) {
                            Integer cantDeProductos = 0;
                            Integer CantidadDisponible = 0;
                            Double precioFinal = 0.0;
                            String nombreProducVenta;
                            String idVent;
                            System.out.println("Seleccione el tipo de producto que quiere vender de las siguientes opciones: \n" +
                                    "1:Bebida \n" +
                                    "2:Envasado \n" +
                                    "3:Limpieza \n"+
                                    "4:Salir");
                            opcionAccion = scan.nextByte();

                            switch (opcionAccion) {
                                case 1:
                                    System.out.println("ingrese el nombre del producto");
                                    scan.nextLine();
                                    nombreProducVenta = scan.nextLine();
                                    System.out.println("ingrese la cantidad deseada del producto no de superar las 10 unidades");
                                    cantDeProductos = scan.nextInt();
                                    Bebida bebidaVenta = (Bebida) starkStore.encontrarProducto("Bebida", nombreProducVenta);
                                    Double precioBebida = bebidaVenta.getPrecio();
                                    CantidadDisponible = bebidaVenta.getStock();
                                    precioFinal = precioBebida * cantDeProductos;
                                    idVent = generadorIdVentas(registroVentas);

                                    if (bebidaVenta != null) {
                                        if (bebidaVenta.getDisponibilidad()) {
                                            if (cantDeProductos <= 10 && cantDeProductos > 0) {
                                                if (CantidadDisponible >= cantDeProductos) {
                                                    Venta bebidaVendida = new Venta(idVent, precioFinal, cantDeProductos, nombreProducVenta);
                                                    Integer nuevaCantDisponible = (CantidadDisponible - cantDeProductos);
                                                    bebidaVenta.setStock(nuevaCantDisponible);
                                                    starkStore.actualizarSaldoVenta(precioBebida * cantDeProductos);
                                                    registroVentas.getRegistroDeVentas().add(bebidaVendida);
                                                    System.out.println(bebidaVendida.getId() + " " + nombreProducVenta + " " + cantDeProductos + "U" + " * $" + bebidaVendida.getPrecioPorUnidad());
                                                    System.out.println("Precio total: " + bebidaVendida.getPrecioFinal());
                                                    break;
                                                } else if (CantidadDisponible < cantDeProductos && CantidadDisponible > 0) {
                                                    System.out.println("Hay productos con stock disponible \n" +
                                                            "menor al solicitado esta es la disponibilidad con la que contamos: " + CantidadDisponible);
                                                    Venta bebidaVendida = new Venta(idVent, precioFinal, bebidaVenta.getStock(), nombreProducVenta);
                                                    bebidaVenta.setStock(0);
                                                    starkStore.actualizarSaldoVenta(precioBebida * CantidadDisponible);
                                                    registroVentas.getRegistroDeVentas().add(bebidaVendida);
                                                    System.out.println(bebidaVendida.getId() + " " + nombreProducVenta + " " + cantDeProductos + "U" + " * $" + bebidaVendida.getPrecioPorUnidad());
                                                    System.out.println("Precio total: " + bebidaVendida.getPrecioFinal());
                                                    break;
                                                } else {
                                                    System.out.println("El producto no esta disponible");
                                                    break;
                                                }

                                            } else {
                                                System.out.println("La cantidad del producto solicitada es invalida");
                                                break;
                                            }
                                        } else {
                                            System.out.println("El producto " + bebidaVenta.getCodigo() + " " + nombreProducVenta + " no se encuentra disponible");
                                            break;
                                        }
                                    } else {
                                        System.out.println("nose encontro el producto");
                                        break;
                                    }
                                case 2:
                                    System.out.println("ingrese el nombre del producto");
                                    scan.nextLine();
                                    nombreProducVenta = scan.nextLine();
                                    System.out.println("ingrese la cantidad deseada del producto no de superar las 10 unidades");
                                    cantDeProductos = scan.nextInt();
                                    Envasado EnvasadoVenta = (Envasado) starkStore.encontrarProducto("Envasado", nombreProducVenta);
                                    Double precioEnvasado = EnvasadoVenta.getPrecio();
                                    CantidadDisponible = EnvasadoVenta.getStock();
                                    precioFinal = precioEnvasado * cantDeProductos;
                                    idVent = generadorIdVentas(registroVentas);
                                    if (EnvasadoVenta != null) {
                                        if (EnvasadoVenta.getDisponibilidad()) {
                                            if (cantDeProductos <= 10 && cantDeProductos > 0) {
                                                if (CantidadDisponible >= cantDeProductos) {
                                                    Venta Envasadovendido = new Venta(idVent, precioFinal, cantDeProductos, nombreProducVenta);
                                                    Integer nuevaCantDisponible = (CantidadDisponible - cantDeProductos);
                                                    EnvasadoVenta.setStock(nuevaCantDisponible);
                                                    registroVentas.getRegistroDeVentas().add(Envasadovendido);
                                                    starkStore.actualizarSaldoVenta(precioEnvasado * cantDeProductos);
                                                    System.out.println(Envasadovendido.getId() + " " + nombreProducVenta + " " + cantDeProductos + "U" + " * $" + Envasadovendido.getPrecioPorUnidad());
                                                    System.out.println("Precio total: " + Envasadovendido.getPrecioFinal());
                                                    break;
                                                } else if (CantidadDisponible < cantDeProductos && CantidadDisponible > 0) {
                                                    System.out.println("Hay productos con stock disponible \n" +
                                                            "menor al solicitado esta es la disponibilidad con la que contamos: " + CantidadDisponible);
                                                    Venta EnvasadoVendida = new Venta(idVent, precioFinal, CantidadDisponible, nombreProducVenta);
                                                    EnvasadoVenta.setStock(0);
                                                    starkStore.actualizarSaldoVenta(precioEnvasado * CantidadDisponible);
                                                    registroVentas.getRegistroDeVentas().add(EnvasadoVendida);
                                                    System.out.println(EnvasadoVendida.getId() + " " + nombreProducVenta + " " + cantDeProductos + "U" + " * $" + EnvasadoVendida.getPrecioPorUnidad());
                                                    System.out.println("Precio total: " + EnvasadoVendida.getPrecioFinal());
                                                    break;
                                                } else {
                                                    System.out.println("El producto no esta disponible");
                                                    break;
                                                }

                                            } else {
                                                System.out.println("La cantidad del producto solicitada es invalida");
                                                break;
                                            }
                                        } else {
                                            System.out.println("El producto " + EnvasadoVenta.getCodigo() + " " + nombreProducVenta + " no se encuentra disponible");
                                            break;
                                        }
                                    } else {
                                        System.out.println("nose encontro el producto");
                                        break;
                                    }
                                case 3:
                                    System.out.println("ingrese el nombre del producto");
                                    scan.nextLine();
                                    nombreProducVenta = scan.nextLine();
                                    System.out.println("ingrese la cantidad deseada del producto no de superar las 10 unidades");
                                    cantDeProductos = scan.nextInt();
                                    Limpieza LimpiezaVenta = (Limpieza) starkStore.encontrarProducto("Envasado", nombreProducVenta);
                                    Double precioLimpieza = LimpiezaVenta.getPrecio();
                                    CantidadDisponible = LimpiezaVenta.getStock();
                                    precioFinal = precioLimpieza * cantDeProductos;
                                    idVent = generadorIdVentas(registroVentas);
                                    if (LimpiezaVenta != null) {
                                        if (LimpiezaVenta.getDisponibilidad()) {
                                            if (cantDeProductos <= 10 && cantDeProductos > 0) {
                                                if (CantidadDisponible >= cantDeProductos) {
                                                    Venta limpiezaVendida = new Venta(idVent, precioFinal, cantDeProductos, nombreProducVenta);
                                                    registroVentas.getRegistroDeVentas().add(limpiezaVendida);
                                                    Integer nuevaCantDisponible = (CantidadDisponible - cantDeProductos);
                                                    LimpiezaVenta.setStock(nuevaCantDisponible);
                                                    starkStore.actualizarSaldoVenta(precioLimpieza * cantDeProductos);
                                                    System.out.println(limpiezaVendida.getId() + " " + nombreProducVenta + " " + cantDeProductos + "U" + " * $" + limpiezaVendida.getPrecioPorUnidad());
                                                    System.out.println("Precio total: " + limpiezaVendida.getPrecioFinal());
                                                    break;
                                                } else if (CantidadDisponible < cantDeProductos && CantidadDisponible > 0) {
                                                    System.out.println("Hay productos con stock disponible \n" +
                                                            "menor al solicitado esta es la disponibilidad con la que contamos: " + CantidadDisponible);
                                                    Venta limpiezaVendida = new Venta(idVent, precioFinal, CantidadDisponible, nombreProducVenta);
                                                    LimpiezaVenta.setStock(0);
                                                    starkStore.actualizarSaldoVenta(precioLimpieza * CantidadDisponible);
                                                    registroVentas.getRegistroDeVentas().add(limpiezaVendida);
                                                    System.out.println(limpiezaVendida.getId() + " " + nombreProducVenta + " " + cantDeProductos + "U" + " * $" + limpiezaVendida.getPrecioPorUnidad());
                                                    System.out.println("Precio total: " + limpiezaVendida.getPrecioFinal());
                                                    break;
                                                } else {
                                                    System.out.println("El producto no esta disponible");
                                                    break;
                                                }

                                            } else {
                                                System.out.println("La cantidad del producto solicitada es invalida");
                                                break;
                                            }
                                        } else {
                                            System.out.println("El producto " + LimpiezaVenta.getCodigo() + " " + nombreProducVenta + " no se encuentra disponible");
                                            break;
                                        }
                                    } else {
                                        System.out.println("nose encontro el producto");
                                        break;
                                    }
                                case 4:
                                    opccionMenuComprasVentas=false;
                                    break;
                                default:
                                    System.out.println("La opcion ingresada es incorrecta");
                                    break;
                            }
                        }
                        break;
//-------------------------------------------------------lista----------------------------------------------------------
                    case 3:
                            for (Producto producto : starkStore.verProductos()) {
                                System.out.println("------------------------------------------------------------------------");
                                System.out.println("Codigo: " + producto.getCodigo() + "\n" + "nombre: " + producto.getNombre() + "\n" + "cantidad: " + producto.getStock() + "\n" + "precio: $" + producto.getPrecio() + "\n" + "disponibilidad: " + producto.getDisponibilidad());
                                System.out.println("------------------------------------------------------------------------");
                            }
                            break;
                    case 4:
                        opcionIngreso=false;
                        break;
                    default:
                        System.out.println("La opcion ingresada es incorrecta");
                        break;

                }


            }catch (Exception e){
                System.out.println("Se produjo un error " +e.getMessage());
            }
        }
    }
}