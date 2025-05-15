package david;


import java.util.*;

public class main {

    private static final int MINI_VALUE = 1;
    private static final int MAX_VALUE = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto1> inventario = new ArrayList<>();
        Producto1 gestor;


        System.out.println("=== Sistema de Inventario ===");
        System.out.println("Seleccione el tipo de inventario:");
        System.out.println("1. Alimentos");
        System.out.println("2. Productos de aseo");
        System.out.println("3. Muebles");
        System.out.print("Opción (" + MINI_VALUE + "-" + MAX_VALUE + "): ");
        int tipoInventario = obtenerEntero(scanner, MINI_VALUE, MAX_VALUE);

        if (tipoInventario == 1) {
            System.out.print("Ingrese el tipo de alimento (ej. Lácteo, Cárnico): ");
            String tipoAlimento = scanner.nextLine();
            gestor = new Alimento("Gestor Alimentos", 0, 0.0, 0.0, "kg", inventario, "N/A", tipoAlimento);
        } else if (tipoInventario == 2) {
            System.out.print("Ingrese el tipo de aseo (ej. Personal, Hogar): ");
            String tipoAseo = scanner.nextLine();
            gestor = new ProductoAseo("Gestor Aseo", 0, 0.0, 0.0, "kg", tipoAseo, inventario);
        } else {
            System.out.print("Ingrese el tipo de mueble (ej. Sala, Cocina, Habitación): ");
            String tipoMueble = scanner.nextLine();
            gestor = new ProductoMuebles("Gestor Muebles", 0, 0.0, 0.0, "kg", tipoMueble, 0.0, 0.0, inventario);
        }

        while (true) {
            mostrarMenu();
            int opcion = obtenerEntero(scanner, 1, 5);
            scanner.nextLine(); // Limpiar el búfer

            switch (opcion) {
                case 1 -> {Producto1 nuevoProducto = crearProducto(scanner, tipoInventario);
                    gestor.agregarProducto(nuevoProducto);
                }
                case 2 -> {
                    gestor.eliminarProducto(scanner);
                }
                case 3 -> {
                    gestor.totalProductos();
                }
                case 4 -> {
                    gestor.mostrarProductos();
                }
                case 5 -> {
                    System.out.println("¡Gracias por usar el sistema de inventario!");
                    break;
                }
            }

        }


    }

    private static void mostrarMenu() {
        System.out.println("=========================");
        System.out.println();
        System.out.println("    Menú de Inventario");
        System.out.println("=========================");
        System.out.println();
        System.out.println("1. Agregar producto");
        System.out.println("2. Eliminar producto");
        System.out.println("3. Mostrar total de productos");
        System.out.println("4. Mostrar productos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción (1-5): ");
    }

    private static int obtenerEntero(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int numero = Integer.parseInt(scanner.nextLine());
                if (numero >= min && numero <= max) {
                    return numero;
                }
                System.out.print("Por favor, ingrese un número entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    private static Producto1 crearProducto(Scanner scanner, int tipoInventario) {
        System.out.println("=== Ingresar nuevo producto ===");
        System.out.println();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = obtenerEntero(scanner, 1, Integer.MAX_VALUE);

        System.out.print("Precio: ");
        double precio;
        while (true) {
            try {
                precio = Double.parseDouble(scanner.nextLine());
                if (precio >= 0) {
                    break;
                }
                System.out.print("El precio debe ser positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }

        System.out.print("Peso: ");
        double peso;
        while (true) {
            try {
                peso = Double.parseDouble(scanner.nextLine());
                if (peso >= 0) {
                    break;
                }
                System.out.print("El peso debe ser positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }

        System.out.print("Unidad (ej. kg, L): ");
        String unidad = scanner.nextLine();

        if (tipoInventario == 1) {
            System.out.print("Fecha de caducidad (ej. 2025-12-31): ");
            String fecha = scanner.nextLine();
            System.out.print("Tipo de alimento (ej. Lácteo, Cárnico): ");
            String tipo = scanner.nextLine();
            return new Alimento(nombre, cantidad, precio, peso, unidad, null, fecha, tipo);
        } else if (tipoInventario == 2) {
            System.out.print("Tipo de aseo (ej. Personal, Hogar): ");
            String tipoAseo = scanner.nextLine();
            return new ProductoAseo(nombre, cantidad, precio, peso, unidad, tipoAseo, null);
        } else {
            System.out.print("Tipo de mueble (ej. Sala, Cocina, Habitación): ");
            String tipoMueble = scanner.nextLine();
            System.out.print("Alto (metros): ");
            double alto;
            while (true) {
                try {
                    alto = Double.parseDouble(scanner.nextLine());
                    if (alto >= 0) {
                        break;
                    }
                    System.out.print("El alto debe ser positivo: ");
                } catch (NumberFormatException e) {
                    System.out.print("Entrada inválida. Ingrese un número: ");
                }
            }
            System.out.print("Ancho (metros): ");
            double ancho;
            while (true) {
                try {
                    ancho = Double.parseDouble(scanner.nextLine());
                    if (ancho >= 0) {
                        break;
                    }
                    System.out.print("El ancho debe ser positivo: ");
                } catch (NumberFormatException e) {
                    System.out.print("Entrada inválida. Ingrese un número: ");
                }
            }
            return new ProductoMuebles(nombre, cantidad, precio, peso, unidad, tipoMueble, alto, ancho, null);
        }
    }
}

