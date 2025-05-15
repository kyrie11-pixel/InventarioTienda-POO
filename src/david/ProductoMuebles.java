package david;

import java.util.*;

public class ProductoMuebles extends Producto1{

    private String tipoDeMueble;
    private double alto;
    private double ancho;


    public ProductoMuebles() {
    }

    public ProductoMuebles(String nombre, int cantidad, double precio, double peso, String unidad, String tipoDeMueble, double alto, double ancho, ArrayList<Producto1> productoS) {
        super(nombre, cantidad, precio, peso, unidad, productoS);
    this.tipoDeMueble = tipoDeMueble;
    this.alto = alto;
    this.ancho = ancho;
    }

    public void monstrarInformacionProductoMueble(){
        System.out.println("*** Produco de muebles ***");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Peso: " + getPeso());
        System.out.println("Unidad: " + getUnidad());
        System.out.println("Tipo de Mueble(Sala,Cocina,Habitacion): " + tipoDeMueble);
        System.out.println("Alto: " + alto + " m");
        System.out.println("Ancho: " + ancho + " m");
    }

    @Override
    public Boolean esCompatible(Producto1 otro) {
        if (otro instanceof ProductoMuebles) {
            ProductoMuebles mueble = (ProductoMuebles) otro;
            return this.tipoDeMueble.equals(mueble.getTipoDeMueble());
        }
        return false;
    }

    // Añade un producto a la lista si es compatible
    @Override
    public void agregarProducto(Producto1 producto) {
        if (producto != null && esCompatible(producto)) {
            getProductoS().add(producto);
            System.out.println("Mueble " + producto.getNombre() + " agregado.");
        } else {
            System.out.println("Error: No se puede agregar el producto.");
        }
    }

    @Override
    public void eliminarProducto(Scanner scanner) {
        System.out.println("Ingrese el nombre del mueble a eliminar:");
        String nombre = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < getProductoS().size(); i++) {
            Producto1 producto = getProductoS().get(i);
            if (esCompatible(producto) && producto.getNombre().equalsIgnoreCase(nombre)) {
                getProductoS().remove(i);
                System.out.println("Mueble " + nombre + " eliminado.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró el mueble " + nombre + ".");
        }
    }


    @Override
    public void totalProductos() {
        int total = 0;


        for (Producto1 producto : getProductoS()) {
            if (esCompatible(producto)) {
                total += producto.getCantidad();
            }
        }

        System.out.println("Total de muebles: " + total);
    }


    @Override
    public void mostrarProductos() {
        System.out.println("Lista de muebles (Tipo: " + tipoDeMueble + "):");
        boolean hayMuebles = false;


        for (Producto1 producto : getProductoS()) {
            if (esCompatible(producto)) {
                System.out.println("-------------------------");
                ProductoMuebles mueble = (ProductoMuebles) producto;
                mueble.monstrarInformacionProductoMueble();
                hayMuebles = true;
            }
        }


        if (!hayMuebles) {
            System.out.println("No hay muebles de este tipo.");
        }
    }

    public String getTipoDeMueble() {
        return tipoDeMueble;
    }

    public void setTipoDeMueble(String tipoDeMueble) {
        this.tipoDeMueble = tipoDeMueble;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }


}