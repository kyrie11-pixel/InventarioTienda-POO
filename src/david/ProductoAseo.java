package david;

import java.util.*;


public class ProductoAseo extends Producto1 {

   private String tipoDeAseo;

    public ProductoAseo() {
    }

    public ProductoAseo(String nombre, int cantidad, double precio, double peso, String unidad, String tipoDeAseo, ArrayList<Producto1> productoS) {
        super(nombre, cantidad, precio, peso, unidad, productoS);
        this.tipoDeAseo = tipoDeAseo;
    }
    public void monstrarInformacionProductoAseo(){
        System.out.println("*** Producto de Aseo ***");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Peso: " + getPeso());
        System.out.println("Unidad: " + getUnidad());
        System.out.println("Tipo de Aseo (Personal o Hogar): " + tipoDeAseo);
    }


    @Override
    public Boolean esCompatible(Producto1 otro) {
        if (otro instanceof ProductoAseo) {
            ProductoAseo aseo = (ProductoAseo) otro;
            return this.tipoDeAseo.equals(aseo.getTipoDeAseo());
        }
        return false;
    }

    // Añade un producto a la lista si es compatible
    @Override
    public void agregarProducto(Producto1 producto) {
        if (producto != null && esCompatible(producto)) {
            getProductoS().add(producto);
            System.out.println("Producto de aseo " + producto.getNombre() + " agregado.");
        } else {
            System.out.println("Error: No se puede agregar el producto.");
        }
    }

    // Elimina un producto de la lista por nombre
    @Override
    public void eliminarProducto(Scanner scanner) {
        System.out.println("Ingrese el nombre del producto de aseo a eliminar:");
        String nombre = scanner.nextLine();
        boolean encontrado = false;

        // Busca el producto en la lista
        for (int i = 0; i < getProductoS().size(); i++) {
            Producto1 producto = getProductoS().get(i);
            if (esCompatible(producto) && producto.getNombre().equalsIgnoreCase(nombre)) {
                getProductoS().remove(i);
                System.out.println("Producto de aseo " + nombre + " eliminado.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró el producto de aseo " + nombre + ".");
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

        System.out.println("Total de productos de aseo: " + total);
    }

    @Override
    public void mostrarProductos() {
        System.out.println("Lista de productos de aseo (Tipo: " + tipoDeAseo + "):");
        boolean hayProductosAseo = false;

        for (Producto1 producto : getProductoS()) {
            if (esCompatible(producto)) {
                System.out.println("-------------------------");
                ProductoAseo aseo = (ProductoAseo) producto;
                aseo.monstrarInformacionProductoAseo();
                hayProductosAseo = true;
            }
        }

        if (!hayProductosAseo) {
            System.out.println("No hay productos de aseo de este tipo.");
        }
    }

    public String getTipoDeAseo() {
        return tipoDeAseo;
    }

    public void setTipoDeAseo(String tipoDeAseo) {
        this.tipoDeAseo = tipoDeAseo;
    }




}
