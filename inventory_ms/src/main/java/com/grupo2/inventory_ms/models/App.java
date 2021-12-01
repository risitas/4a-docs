package com.grupo2.inventory_ms.models;


import java.util.Date;

public class App {

    public static void main(String[] args) {

        // LLamado de la clase de lógica

        Implementation logica = new Implementation();

        // Creación de inventario


        Inventory miInventory = new Inventory("01-u", "yosi", "01-i","mintic","10-12-2021");
        boolean bandera = logica.crearInventario(miInventory);

        System.out.println("\n------------Se creo exitosamente: " + bandera);
        System.out.println(logica.getListaInventarios());

        // Creación de producto


        Product producto1 = new Product("01","reloj","rojo", 87,40000);
        Product producto2 = new Product("02", "gafas", "Verde", 50, 50000);
        logica.crearProducto(producto1, "01");
        logica.crearProducto(producto2, "01");

        System.out.println("\n-------------Se registro exitosamente: ");
        System.out.println(logica.getListaProductos());

        // Buscar producto

        System.out.println("\n\n-----------Resultado de busqueda: \n" + logica.buscarProducto("02"));

        // ELiminar producto

        System.out.println("\n\n-----------Eliminación: \n" + logica.eliminarProducto("02"));

        System.out.println("\n\n-----------Lista de productos despues de eliminar: \n" + logica.getListaProductos());

        // -------------------Modificar producto

        Product productoModi = new Product("01", "Coche", "Verde", 4, 250000);

        System.out.println("\n\n-----------MOdificado: \n" + logica.modificarProducto(productoModi));

        System.out.println("\n\n-----------Lista de productos despues de Modificar: \n" + logica.getListaProductos());

        // ---------------------buscar inventario ---------------

        Inventory mi_inventario_encontrado = logica.buscarInventario("01-i","01-u");
        System.out.println("resultado de busqueda de inventario : " + mi_inventario_encontrado);
    }

}