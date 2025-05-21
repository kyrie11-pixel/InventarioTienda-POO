package Inventario_supermercado;
import java.util.*;


public class Alimento extends Producto1 {
    private String fechaDeCaducidad;
    private String tipoDeAlimento;

    public Alimento() {
    }

    public Alimento(String nombre, int cantidad, double precio, double peso, String unidad, ArrayList<Producto1> productos, String fechaDeCaducidad, String tipoDeAlimento) {
        super(nombre, cantidad, precio, peso, unidad, productos);
        this.fechaDeCaducidad = fechaDeCaducidad;
        this.tipoDeAlimento = tipoDeAlimento;
    }

    public void mostrarFechaDeCaducidad() {
        System.out.println("Fecha de caducidad: " + fechaDeCaducidad);
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Peso: " + getPeso());
        System.out.println("Unidad en kg o L: " + getUnidad());
        mostrarFechaDeCaducidad();
        System.out.println("Tipo: " + tipoDeAlimento);
    }


    @Override
    public boolean esCompatible(Producto1 otro) {
        if (otro instanceof Alimento) {
            Alimento alimento = (Alimento) otro;
            return this.tipoDeAlimento.equals(alimento.getTipoDeAlimento());
        }
        return false;
    }

    @Override
    public void mostrarProductos() {
        System.out.println("=== Lista de productos alimenticios (" + tipoDeAlimento + ") ===");
        boolean hayAlimentos = false;

        if (getProductos() != null) {
            for (int i = 0; i < getProductos().size(); i++) {
                Producto1 producto = getProductos().get(i);
                if (esCompatible(producto)) {
                    System.out.println((i + 1) + ". ");
                    Alimento alimento = (Alimento) producto;
                    alimento.mostrarInformacion();
                    hayAlimentos = true;
                }
            }
        }

        if (!hayAlimentos) {
            System.out.println("No hay alimentos de este tipo.");
        }
    }

    @Override
    protected void mostrarInformacionProducto(Producto1 producto) {
        ((Alimento) producto).mostrarInformacion();
    }

    @Override
    public List<String> modificarPropiedadesEspecificas() {
        List<String> propiedades = new ArrayList<>();
        propiedades.add("Fecha de caducidad");
        return propiedades;
    }

    @Override
    public void modificarPropiedadEspecifica(Producto1 producto, int indicePropiedad, String valor) {
        if (producto instanceof Alimento && indicePropiedad == 0) {
            ((Alimento) producto).setFechaDeCaducidad(valor);
        }
    }
    @Override
    public String obtenerTipo() {
        return "Alimento";
    }

    public void setFechaDeCaducidad(String fechaDeCaducidad) {
        this.fechaDeCaducidad = fechaDeCaducidad;
    }

    public String getTipoDeAlimento() {
        return tipoDeAlimento;
    }

    public void setTipoDeAlimento(String tipoDeAlimento) {
        this.tipoDeAlimento = tipoDeAlimento;
    }
}
