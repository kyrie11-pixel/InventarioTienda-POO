package david;

import java.util.*;

public class ProductoAseo extends Producto1 {

    private String tipoDeAseo;

    public ProductoAseo() {
    }

    public ProductoAseo(String nombre, int cantidad, double precio, double peso, String unidad, String tipoDeAseo, ArrayList<Producto1> productos) {
        super(nombre, cantidad, precio, peso, unidad, productos);
        this.tipoDeAseo = tipoDeAseo;
    }

    public void mostrarInformacionProductoAseo() {
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

    @Override
    public void mostrarProductos() {
        System.out.println("=== Lista de productos de aseo (Tipo: " + tipoDeAseo + ") ===");
        boolean hayProductosAseo = false;

        for (int i = 0; i < getProductos().size(); i++) {
            Producto1 producto = getProductos().get(i);
            if (esCompatible(producto)) {
                System.out.println((i + 1) + ". ");
                ProductoAseo aseo = (ProductoAseo) producto;
                aseo.mostrarInformacionProductoAseo();
                hayProductosAseo = true;
            }
        }

        if (!hayProductosAseo) {
            System.out.println("No hay productos de aseo de este tipo.");
        }
    }

    @Override
    protected void modificarPropiedadesEspecificas(Scanner scanner, Producto1 producto) {

    }

    @Override
    protected void modificarPropiedadEspecifica(Scanner scanner, Producto1 producto, int opcion) {

    }

    @Override
    protected void mostrarInformacionProducto(Producto1 producto) {
        ((ProductoAseo) producto).mostrarInformacionProductoAseo();
    }
    public String getTipoDeAseo() {
        return tipoDeAseo;
    }

    public void setTipoDeAseo(String tipoDeAseo) {
        this.tipoDeAseo = tipoDeAseo;
    }
}