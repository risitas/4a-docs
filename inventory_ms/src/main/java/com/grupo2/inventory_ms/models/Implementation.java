package com.grupo2.inventory_ms.models;

import java.util.ArrayList;

public class Implementation {

    private ArrayList<Inventory> listaInventarios;
    private ArrayList<Product> listaProductos;

    public Implementation() {
        super();
        this.listaInventarios = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
    }

    //-----------------------------------------  crud de inventario ------------------------------------------

    /**
     * Método para crear inventario
     *
     * @param miInventario
     * @return
     */
    public boolean crearInventario(Inventory miInventario) {

        if (miInventario != null) {
            getListaInventarios().add(miInventario);
            return true;
        }
        return false;
    }

    /**
     * método para buscar un inventario
     * @param id_inventario
     * @return
     */
    public Inventory buscarInventario(String id_inventario ,String id_user ) {

        Inventory mi_inventario = null;
        String codigoEncontrado = "";
        String id_usuario_encontrado;

        for (int i = 0; i < getListaInventarios().size(); i++) {

            codigoEncontrado = getListaInventarios().get(i).getId_inventory();

            if (codigoEncontrado.equals(id_inventario)) {
                 mi_inventario = getListaInventarios().get(i);

                 if(mi_inventario.getId_user().equals(id_user)){
                     return mi_inventario;
                 }
            }

        }

        return mi_inventario;
    }

    //-----------------------------------------  crud de productos ------------------------------------------

    /**
     * Método para crear producto, validando si el inventario existe.
     *
     * @param miProduct
     * @param codeInventory
     * @return
     */
    public boolean crearProducto(Product miProduct, String codeInventory) {

        String nombreLista = "";

        for (int i = 0; i < getListaInventarios().size(); i++) {
            nombreLista = getListaInventarios().get(i).getId_inventory();

            if (nombreLista.equals(codeInventory) && miProduct != null) {
                getListaProductos().add(miProduct);
                return true;
            }
        }

        return false;
    }

    /**
     * Método para buscar un producto, dado su código del producto.
     *
     * @param codigo
     * @return
     */
    public Product buscarProducto(String codigo) {

        Product miProducto = null;
        String codigoEncontrado = "";

        for (int i = 0; i < getListaProductos().size(); i++) {

            codigoEncontrado = getListaProductos().get(i).getCode();

            if (codigoEncontrado.equals(codigo)) {
                return miProducto = getListaProductos().get(i);
            }

        }

        return miProducto;
    }

    /**
     * Método para busacar la posición de un producto dentro de la lista.
     * @param miProduct
     * @return
     */
    public int buscarPosicionDelProducto(Product miProduct) {

        int posicion = 0;

        for (int i = 0; i < getListaProductos().size(); i++) {

            if (getListaProductos().get(i).getCode() == miProduct.getCode()) {
                posicion = 0;
            }
        }

        return posicion;
    }

    /**
     * Método para modificar un producto.
     *
     * @param miProducto
     * @return
     */
    public boolean modificarProducto(Product miProducto) {

        Product productABuscar = buscarProducto(miProducto.getCode());

        if (productABuscar != null) {

            productABuscar.setName(miProducto.getName());
            productABuscar.setEspecification(miProducto.getEspecification());
            productABuscar.setPrice(miProducto.getPrice());
            productABuscar.setAmount(miProducto.getAmount());

            getListaProductos().set(buscarPosicionDelProducto(miProducto), productABuscar);
            return true;

        }

        return false;

    }

    /**
     * Método para eliminar un producto, solo si existe.
     *
     * @param codigo
     * @return
     */
    public boolean eliminarProducto(String codigo) {

        String codigoEncontrado = "";

        for (int i = 0; i < getListaProductos().size(); i++) {

            codigoEncontrado = getListaProductos().get(i).getCode();

            if (codigoEncontrado.equals(codigo)) {
                getListaProductos().remove(i);
                return true;
            }

        }

        return false;
    }

    // Get y set

    public ArrayList<Inventory> getListaInventarios() {
        return listaInventarios;
    }

    public void setListaInventarios(ArrayList<Inventory> listaInventarios) {
        this.listaInventarios = listaInventarios;
    }

    public ArrayList<Product> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Product> listaProductos) {
        this.listaProductos = listaProductos;
    }

}

