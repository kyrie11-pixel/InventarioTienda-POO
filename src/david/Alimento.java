package david;

import java.util.*;

public  class  Alimento extends Producto1 {

    private String fechaDeCaducidad;
    private String tipoDeAlimento;

    public Alimento() {
    }

    public Alimento(String nombre, int cantidad, double precio, double peso, String unidad, ArrayList<Producto1> productoS, String fechaDeCaducidad, String tipoDeAlimento) {
        super(nombre, cantidad, precio, peso, unidad, productoS);
        this.fechaDeCaducidad = fechaDeCaducidad;
        this.tipoDeAlimento = tipoDeAlimento;
    }

    public void mostrarFechaDeCaducidad(){
        System.out.println("Fecha de caducidad: " + fechaDeCaducidad);
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cantidad: " + getCantidad());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Peso: " + getPeso());
        System.out.println("Unidad en kg o L: " + getUnidad());
        mostrarFechaDeCaducidad();
    }

    public String getFechaDeCaducidad() {
        return fechaDeCaducidad;
    }

    public void setFechaDeCaducidad(String fechaDeCaducidad) {
        this.fechaDeCaducidad = fechaDeCaducidad;
    }



    @Override
    public Boolean esCompatible(Producto1 compatible) {
        if (compatible instanceof Alimento) {
            Alimento alimento = (Alimento) compatible;
            return this.tipoDeAlimento.equals(alimento.getTipoDeAlimento());
        }
        return false;
    }

    @Override
    public void agregarProducto(Producto1 producto) {
        if (producto != null && esCompatible(producto)) {
            getProductoS().add(producto);
            System.out.println("Alimento " + producto.getNombre() + " agregado.");
        } else {
            System.out.println("Error: No se puede agregar el producto.");
        }
    }


    @Override
    public void eliminarProducto(Scanner scanner) {
        System.out.println("Ingrese el nombre del alimento a eliminar:");
        String nombre = scanner.nextLine();
       Boolean encontrado = false;


        for (int i = 0; i < getProductoS().size(); i++) {
            Producto1 producto = getProductoS().get(i);

            if (esCompatible(producto) && producto.getNombre().equalsIgnoreCase(nombre)) {
                getProductoS().remove(i);
                System.out.println("Alimento " + nombre + " eliminado.");
                encontrado = true;
                break;
            }
        }


        if (!encontrado) {
            System.out.println("No se encontró el alimento " + nombre + ".");
        }
    }

    @Override
    public void totalProductos() {
      Integer total = 0;


        for (Producto1 producto : getProductoS()) {
            if (esCompatible(producto)) {
                total += producto.getCantidad();
            }
        }

        System.out.println("Total de alimentos: " + total);
    }


    @Override
    public void mostrarProductos() {
        System.out.println("Lista de alimentos (Tipo: " + tipoDeAlimento + "):");
        Boolean hayAlimentos = false;


        for (Producto1 producto : getProductoS()) {
            if (esCompatible(producto)) {
                System.out.println("-------------------------");
                Alimento alimento = (Alimento) producto;
                alimento.mostrarInformacion();
                hayAlimentos = true;
            }
        }
        if (!hayAlimentos) {
            System.out.println("No hay alimentos de este tipo.");
        }
    }


    public String getTipoDeAlimento() {
        return tipoDeAlimento;
    }

    public void setTipoDeAlimento(String tipoDeAlimento) {
        this.tipoDeAlimento = tipoDeAlimento;
    }

    public void mostrarTipoDeAlimento() {
        System.out.println("Tipo de Alimento: " + tipoDeAlimento);
    }
}