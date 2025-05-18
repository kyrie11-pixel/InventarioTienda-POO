package david;

import java.util.*;

public abstract class Producto1 {

    private String nombre;
    private Integer cantidad;
    private Double precio;
    private Double peso;
    private String unidad;
    private ArrayList<Producto1> productos;

    public Producto1() {
    }

    public Producto1(String nombre, Integer cantidad, Double precio, Double peso, String unidad, ArrayList<Producto1> productos) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.peso = peso;
        this.unidad = unidad;
        this.productos = productos;
    }

    public void agregarProducto(Producto1 producto) {
        if (producto != null && esCompatible(producto)) {
            getProductos().add(producto);
            System.out.println("Producto " + producto.getNombre() + " agregado.");
        } else {
            System.out.println("Error: No se puede agregar el producto.");
        }
    }

    public void eliminarProducto(Scanner scanner) {
        System.out.println("=== Eliminar producto ===");
        System.out.println();
        mostrarProductos();
        if (getProductos().isEmpty()) {
            System.out.println("No hay productos para eliminar.");
            return;
        }
        System.out.print("Ingrese el número del producto a eliminar: ");
        System.out.println();
        Integer numero = Integer.parseInt(scanner.nextLine()) - 1;

        if (numero >= 0 && numero < getProductos().size()) {
            Producto1 productoAEliminar = getProductos().get(numero);
            if (esCompatible(productoAEliminar)) {
                getProductos().remove(numero);
                System.out.println("Producto " + productoAEliminar.getNombre() + " eliminado.");
            } else {
                System.out.println("No se puede eliminar este producto con este gestor.");
            }
        } else {
            System.out.println("Número de producto inválido.");
        }
    }

    public void totalProductos() {
        System.out.println("=== Total de productos ===");
        System.out.println();
        if (getProductos().isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        for (Producto1 producto : getProductos()) {
            if (esCompatible(producto)) {
                System.out.println("-------------------------");
                mostrarInformacionProducto(producto); // Usamos el nuevo método abstracto
            }
        }
    }

    public void modificarProducto(Scanner scanner) {
        System.out.println("=== Modificar producto ===");
        System.out.println();
        mostrarProductos();
        if (getProductos().isEmpty()) {
            System.out.println("No hay productos para modificar.");
            return;
        }
        System.out.print("Ingrese el número del producto a modificar: ");
        Integer numero = Integer.parseInt(scanner.nextLine()) - 1;

        if (numero >= 0 && numero < getProductos().size()) {
            Producto1 productoAModificar = getProductos().get(numero);
            if (esCompatible(productoAModificar)) {
                System.out.println("Has seleccionado el producto: " + productoAModificar.getNombre());
                System.out.println("Seleccione qué desea modificar:");
                System.out.println("1. Nombre");
                System.out.println("2. Cantidad");
                System.out.println("3. Precio");
                System.out.println("4. Peso");
                System.out.println("5. Unidad");
                modificarPropiedadesEspecificas(scanner, productoAModificar);

                System.out.print("Opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nuevo nombre: ");
                        productoAModificar.setNombre(scanner.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nueva cantidad: ");
                        productoAModificar.setCantidad(Integer.parseInt(scanner.nextLine()));
                    }
                    case 3 -> {
                        System.out.print("Nuevo precio: ");
                        productoAModificar.setPrecio(Double.parseDouble(scanner.nextLine()));
                    }
                    case 4 -> {
                        System.out.print("Nuevo peso: ");
                        productoAModificar.setPeso(Double.parseDouble(scanner.nextLine()));
                    }
                    case 5 -> {
                        System.out.print("Nueva unidad: ");
                        productoAModificar.setUnidad(scanner.nextLine());
                    }
                    default -> modificarPropiedadEspecifica(scanner, productoAModificar, opcion);
                }
                System.out.println("Producto " + productoAModificar.getNombre() + " modificado con éxito.");
            } else {
                System.out.println("No se puede modificar este producto con este gestor.");
            }
        } else {
            System.out.println("Número de producto inválido.");
        }
    }
    public abstract Boolean esCompatible(Producto1 otro);

    protected abstract void modificarPropiedadesEspecificas(Scanner scanner, Producto1 producto);

    protected abstract void modificarPropiedadEspecifica(Scanner scanner, Producto1 producto, int opcion);

    public abstract void mostrarProductos();

    protected abstract void mostrarInformacionProducto(Producto1 producto);

    public ArrayList<Producto1> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto1> productos) {
        this.productos = productos;
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