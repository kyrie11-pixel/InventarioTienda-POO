package david;

import java.util.*;


public abstract class Producto1 {

    private String nombre;
    private Integer cantidad;
    private Double precio;
    private Double peso;
    private String unidad;
    private ArrayList<Producto1>productoS;


    public  Producto1() {
    }

    public Producto1(String nombre, Integer cantidad, Double precio, Double peso, String unidad, ArrayList<Producto1> productoS) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.peso = peso;
        this.unidad = unidad;
        this.productoS = productoS;


    }
    public abstract Boolean esCompatible(Producto1 otro);

    public abstract void agregarProducto(Producto1 producto);

    public abstract void eliminarProducto(Scanner scanner);

    public abstract void totalProductos();

    public abstract void mostrarProductos();

    public ArrayList<Producto1> getProductoS() {
        return productoS;
    }

    public void setProductoS(ArrayList<Producto1> productoS) {
        this.productoS = productoS;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}