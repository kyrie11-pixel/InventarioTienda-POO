package Inventario_supermercado;

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
    public boolean esCompatible(Producto1 otro) {
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

        if (getProductos() != null) {
            for (int i = 0; i < getProductos().size(); i++) {
                Producto1 producto = getProductos().get(i);
                if (esCompatible(producto)) {
                    System.out.println((i + 1) + ". ");
                    ProductoAseo aseo = (ProductoAseo) producto;
                    aseo.mostrarInformacionProductoAseo();
                    hayProductosAseo = true;
                }
            }
        }

        if (!hayProductosAseo) {
            System.out.println("No hay productos de aseo de este tipo.");
        }
    }

    @Override
    protected void mostrarInformacionProducto(Producto1 producto) {
        ((ProductoAseo) producto).mostrarInformacionProductoAseo();
    }

    @Override
    public List<String> modificarPropiedadesEspecificas() {
        return new ArrayList<>();
    }

    @Override
    public void modificarPropiedadEspecifica(Producto1 producto, int indicePropiedad, String valor) {
    }
    @Override
    public String obtenerTipo() {
        return "Aseo";
    }

    public String getTipoDeAseo() {
        return tipoDeAseo;
    }

    public void setTipoDeAseo(String tipoDeAseo) {
        this.tipoDeAseo = tipoDeAseo;
    }
}