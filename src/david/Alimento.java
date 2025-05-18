package david;

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
    public Boolean esCompatible(Producto1 otro) {
        if (otro  instanceof Alimento) {
            Alimento alimento = (Alimento) otro;
            return this.tipoDeAlimento.equals(alimento.getTipoDeAlimento());
        }
        return false;
    }

    @Override
    public void mostrarProductos() {
        System.out.println("=== Lista de alimentos (Tipo: " + tipoDeAlimento + ") ===");
        boolean hayAlimentos = false;

        for (int i = 0; i < getProductos().size(); i++) {
            Producto1 producto = getProductos().get(i);
            if (esCompatible(producto)) {
                System.out.println((i + 1) + ". ");
                Alimento alimento = (Alimento) producto;
                alimento.mostrarInformacion();
                hayAlimentos = true;
            }
        }
        if (!hayAlimentos) {
            System.out.println("No hay alimentos de este tipo.");
        }
    }

    @Override
    protected void modificarPropiedadesEspecificas(Scanner scanner, Producto1 producto) {
        System.out.println("6. Fecha de Caducidad");
    }

    @Override
    protected void modificarPropiedadEspecifica(Scanner scanner, Producto1 producto, int opcion) {
        if (opcion == 6) {
            System.out.print("Nueva fecha de caducidad: ");
            ((Alimento) producto).setFechaDeCaducidad(scanner.nextLine());
        }
    }

    @Override
    protected void mostrarInformacionProducto(Producto1 producto) {
        ((Alimento) producto).mostrarInformacion();
    }



    public String getFechaDeCaducidad() {
        return fechaDeCaducidad;
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
