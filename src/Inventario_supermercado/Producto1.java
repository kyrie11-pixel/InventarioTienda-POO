package Inventario_supermercado;

import java.util.*;

public abstract class Producto1 {
    private String nombre;
    private int cantidad;
    private double precio;
    private double peso;
    private String unidad;
    private ArrayList<Producto1> productos;

    public Producto1() {
    }

    public Producto1(String nombre, int cantidad, double precio, double peso, String unidad, ArrayList<Producto1> productos) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.peso = peso;
        this.unidad = unidad;
        this.productos = productos;
    }

    public abstract boolean esCompatible(Producto1 otro);

    public void agregarProducto(Producto1 producto) {
        if (producto != null && esCompatible(producto)) {
            if (getProductos() != null) {
                getProductos().add(producto);
                System.out.println("Producto " + producto.getNombre() + " agregado.");
            } else {
                System.out.println("Error: No se puede agregar el producto. La lista de productos no está inicializada.");
            }
        } else {
            System.out.println("Error: No se puede agregar el producto.");
        }
    }

    public void eliminarProducto(String input, ArrayList<Producto1> productos) {
        try {
            int numero = Integer.parseInt(input) - 1;
            if (productos != null && numero >= 0 && numero < productos.size()) {
                productos.remove(numero);
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Número de producto inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese un número válido.");
        }
    }

    public void totalProductos() {
        if (getProductos() == null || getProductos().isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        double total = 0;
        for (Producto1 producto : getProductos()) {
            total += producto.getPrecio() * producto.getCantidad();
        }
        System.out.println("Total de productos en el inventario: " + total);
    }

    public void modificarProducto(Scanner scanner) {
        System.out.println("=== Modificar producto ===");
        System.out.println();
        mostrarProductos();
        if (getProductos() == null || getProductos().isEmpty()) {
            System.out.println("No hay productos para modificar.");
            return;
        }
        System.out.print("Ingrese el número del producto a modificar: ");
        int numero = obtenerEntero(scanner, 1, getProductos().size());
        int indice = numero - 1;

        if (indice >= 0 && indice < getProductos().size()) {
            Producto1 producto = getProductos().get(indice);
            if (esCompatible(producto)) {
                System.out.println("Has seleccionado: " + producto.getNombre());
                System.out.println("Seleccione qué desea modificar:");
                System.out.println("1. Nombre");
                System.out.println("2. Cantidad");
                System.out.println("3. Precio");
                System.out.println("4. Peso");
                System.out.println("5. Unidad");
                List<String> propiedadesEspecificas = modificarPropiedadesEspecificas();
                for (int i = 0; i < propiedadesEspecificas.size(); i++) {
                    System.out.println((6 + i) + ". " + propiedadesEspecificas.get(i));
                }

                System.out.print("Opción: ");
                int opcion = obtenerEntero(scanner, 1, 5 + propiedadesEspecificas.size());

                String nuevoNombre = producto.getNombre();
                int nuevaCantidad = producto.getCantidad();
                double nuevoPrecio = producto.getPrecio();
                double nuevoPeso = producto.getPeso();
                String nuevaUnidad = producto.getUnidad();
                String nuevoValorEspecifico = null;
                int indicePropiedad = -1;

                switch (opcion) {
                    case 1:
                        System.out.print("Nuevo nombre: ");
                        nuevoNombre = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("Nueva cantidad: ");
                        nuevaCantidad = obtenerEntero(scanner, 1, Integer.MAX_VALUE);
                        break;
                    case 3:
                        System.out.print("Nuevo precio: ");
                        nuevoPrecio = obtenerDoble(scanner);
                        break;
                    case 4:
                        System.out.print("Nuevo peso: ");
                        nuevoPeso = obtenerDoble(scanner);
                        break;
                    case 5:
                        System.out.print("Nueva unidad: ");
                        nuevaUnidad = scanner.nextLine();
                        break;
                    default:
                        indicePropiedad = opcion - 6;
                        System.out.print("Nuevo valor para " + propiedadesEspecificas.get(indicePropiedad) + ": ");
                        nuevoValorEspecifico = scanner.nextLine();
                        break;
                }
                producto.setNombre(nuevoNombre);
                producto.setCantidad(nuevaCantidad);
                producto.setPrecio(nuevoPrecio);
                producto.setPeso(nuevoPeso);
                producto.setUnidad(nuevaUnidad);
                if (indicePropiedad >= 0 && nuevoValorEspecifico != null) {
                    modificarPropiedadEspecifica(producto, indicePropiedad, nuevoValorEspecifico);
                }
                System.out.println("Producto modificado con éxito.");
            } else {
                System.out.println("Este producto no puede modificarse con este gestor.");
            }
        } else {
            System.out.println("Número de producto inválido.");
        }
    }

    private int obtenerEntero(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().replace(",", ".");
                int numero = Integer.parseInt(input);
                if (numero >= min && numero <= max) {
                    return numero;
                }
                System.out.print("Por favor, ingrese un número entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    private double obtenerDoble(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().replace(",", ".");
                double valor = Double.parseDouble(input);
                if (valor >= 0) {
                    return valor;
                }
                System.out.print("El valor debe ser positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    public abstract List<String> modificarPropiedadesEspecificas();

    public abstract void modificarPropiedadEspecifica(Producto1 producto, int indicePropiedad, String valor);

    public void mostrarProductos() {
        if (getProductos() == null || getProductos().isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        for (Producto1 producto : getProductos()) {
            if (esCompatible(producto)) {
                System.out.println("-------------------------");
                mostrarInformacionProducto(producto);
            }
        }
    }
    public abstract String obtenerTipo();

    protected abstract void mostrarInformacionProducto(Producto1 producto);

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

    public ArrayList<Producto1> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto1> productos) {
        this.productos = productos;
    }
}