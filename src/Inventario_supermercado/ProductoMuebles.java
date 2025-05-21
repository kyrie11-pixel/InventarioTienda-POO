package Inventario_supermercado;

import java.util.*;

public class ProductoMuebles extends Producto1 {
    private String tipoDeMueble;
    private double alto;
    private double ancho;

    public ProductoMuebles() {
    }

    public ProductoMuebles(String nombre, int cantidad, double precio, double peso, String unidad, String tipoDeMueble, double alto, double ancho, ArrayList<Producto1> productos) {
        super(nombre, cantidad, precio, peso, unidad, productos);
        this.tipoDeMueble = tipoDeMueble;
        this.alto = alto;
        this.ancho = ancho;
    }

    public void mostrarInformacionProductoMueble() {
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
    public boolean esCompatible(Producto1 otro) {
        if (otro instanceof ProductoMuebles) {
            ProductoMuebles mueble = (ProductoMuebles) otro;
            return this.tipoDeMueble.equals(mueble.getTipoDeMueble());
        }
        return false;
    }

    @Override
    public void mostrarProductos() {
        System.out.println("=== Lista de Muebles (" + tipoDeMueble + ") ===");
        boolean hayMuebles = false;
        if (getProductos() != null) {
            for (int i = 0; i < getProductos().size(); i++) {
                Producto1 producto = getProductos().get(i);
                if (esCompatible(producto)) {
                    System.out.println((i + 1) + ". ");
                    ProductoMuebles mueble = (ProductoMuebles) producto;
                    mueble.mostrarInformacionProductoMueble();
                    hayMuebles = true;
                }
            }
        }
        if (!hayMuebles) {
            System.out.println("No hay muebles de este tipo.");
        }
    }

    @Override
    protected void mostrarInformacionProducto(Producto1 producto) {
        ((ProductoMuebles) producto).mostrarInformacionProductoMueble();
    }

    @Override
    public List<String> modificarPropiedadesEspecificas() {
        List<String> propiedades = new ArrayList<>();
        propiedades.add("Alto");
        propiedades.add("Ancho");
        return propiedades;
    }

    @Override
    public void modificarPropiedadEspecifica(Producto1 producto, int indicePropiedad, String valor) {
        if (producto instanceof ProductoMuebles) {
            ProductoMuebles mueble = (ProductoMuebles) producto;
            try {
                double valorDouble = Double.parseDouble(valor);
                if (indicePropiedad == 0) {
                    mueble.setAlto(valorDouble);
                } else if (indicePropiedad == 1) {
                    mueble.setAncho(valorDouble);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Valor inválido para " + (indicePropiedad == 0 ? "alto" : "ancho"));
            }
        }
    }
    @Override
    public String obtenerTipo() {
        return "Mueble";
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

