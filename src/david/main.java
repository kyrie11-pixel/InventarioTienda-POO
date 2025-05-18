package david;

import java.util.*;

public class main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto1> inventario = new ArrayList<>();
        Producto1 gestor;
        boolean continuar = true;

        while (continuar) {
            mostrarPrincipal();
            Integer tipoInventario = obtenerEntero(scanner,1, 4);

            if (tipoInventario == 1) {
                gestor = crearGestorAlimentos(scanner, inventario);
            } else if (tipoInventario == 2) {
                gestor = crearGestorAseo(scanner, inventario);
            } else if (tipoInventario == 3) {
                gestor = crearGestorMuebles(scanner, inventario);
            } else {
                continuar = false;
                break;
            }

            boolean mostrarMenu = true;
            while (mostrarMenu) {
                mostrarMenu();
                int opcion = obtenerEntero(scanner, 1, 5);
                switch (opcion) {
                    case 1 -> agregarProducto(scanner, gestor, tipoInventario);
                    case 2 -> gestor.eliminarProducto(scanner);
                    case 3 -> gestor.totalProductos();
                    case 4 -> gestor.modificarProducto(scanner);
                    case 5 -> {
                        System.out.println("Volviendo al menú principal.");
                        mostrarMenu = false;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            }
        }
        System.out.println("¡Gracias por usar el sistema de inventario!");
        System.out.println("Cerrando el programa...");
    }

    public static void mostrarPrincipal() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Sistema de Inventario        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  Seleccione el tipo de inventario:     ║");
        System.out.println("║                                        ║");
        System.out.println("║  1. Alimentos (Comida/Bebida)          ║");
        System.out.println("║  2. Productos de Aseo (Hogar/Personal) ║");
        System.out.println("║  3. Muebles (Cocina/Sala/Habitacion)   ║");
        System.out.println("║  4. Salir                              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("   Opción (" + 1 + "-" + 4 + "): ");
    }

    private static void mostrarMenu() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║             Menú de Inventario         ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Agregar producto                   ║");
        System.out.println("║  2. Eliminar producto                  ║");
        System.out.println("║  3. Mostrar total de productos         ║");
        System.out.println("║  4. Modificar producto                 ║");
        System.out.println("║  5. Volver al Menú Principal           ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("   Seleccione una opción (1-5): ");
    }

    private static int obtenerEntero(Scanner scanner, int min, int max) {
        int numero;
        while (true) {
            try {
                numero = Integer.parseInt(scanner.nextLine().replace(",","."));
                if (numero >= min && numero <= max) {
                    return numero;
                }
                System.out.print("   Por favor, ingrese un número entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("   Entrada inválida. Ingrese un número: ");
            }
        }
    }

    private static Producto1 crearGestorAlimentos(Scanner scanner, ArrayList<Producto1> inventario) {
        System.out.println("Seleccione el tipo de alimento:");
        System.out.println();
        System.out.println("1. comida ");
        System.out.println("2. Bebida");
        System.out.print("Opción (1-2): ");
        int opcionAlimento = obtenerEntero(scanner, 1, 2);
        String tipoAlimento = (opcionAlimento == 1) ? "comida" : "Bebida";
        return new Alimento("Gestor Alimentos", 0, 0.0, 0.0, "Unidad", inventario, "N/A", tipoAlimento);
    }

    private static Producto1 crearGestorAseo(Scanner scanner, ArrayList<Producto1> inventario) {
        System.out.println("Seleccione el tipo de aseo:");
        System.out.println();
        System.out.println("1. Hogar");
        System.out.println("2. Personal");
        System.out.print("Opción (1-2): ");
        int opcionAseo = obtenerEntero(scanner, 1, 2);
        String tipoAseo = (opcionAseo == 1) ? "Hogar" : "Personal";
        return new ProductoAseo("Gestor Aseo", 0, 0.0, 0.0, "Unidad", tipoAseo, inventario);
    }

    private static Producto1 crearGestorMuebles(Scanner scanner, ArrayList<Producto1> inventario) {
        System.out.println("Seleccione el tipo de mueble:");
        System.out.println();
        System.out.println("1. Cocina");
        System.out.println("2. Sala");
        System.out.println("3. Habitacion");
        System.out.print("Opción (1-3): ");
        int opcionMueble = obtenerEntero(scanner, 1, 3);
        String tipoMueble = null;
        switch (opcionMueble) {
            case 1 -> tipoMueble = "Cocina";
            case 2 -> tipoMueble = "Sala";
            case 3 -> tipoMueble = "Habitacion";
        }
        return new ProductoMuebles("Gestor Muebles", 0, 0.0, 0.0, "Unidad", tipoMueble, 0.0, 0.0, inventario);
    }

    private static void agregarProducto(Scanner scanner, Producto1 gestor, int tipoInventario) {
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
                precio = Double.parseDouble(scanner.nextLine().replace(",","."));
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
                peso = Double.parseDouble(scanner.nextLine().replace(",","."));
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

        Producto1 nuevoProducto = null;

        if (tipoInventario == 1) {
            System.out.print("Fecha de caducidad (ej. 2025-12-31): ");
            String fecha = scanner.nextLine();
            String tipoAlimento = ((Alimento) gestor).getTipoDeAlimento();
            nuevoProducto = new Alimento(nombre, cantidad, precio, peso, unidad, null, fecha, tipoAlimento);
        } else if (tipoInventario == 2) {
            String tipoAseo = ((ProductoAseo) gestor).getTipoDeAseo();
            nuevoProducto = new ProductoAseo(nombre, cantidad, precio, peso, unidad, tipoAseo, null);
        } else {
            String tipoMueble = ((ProductoMuebles) gestor).getTipoDeMueble();
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
            nuevoProducto = new ProductoMuebles(nombre, cantidad, precio, peso, unidad, tipoMueble, alto, ancho, null);
        }

        if (nuevoProducto != null) {
            gestor.agregarProducto(nuevoProducto);
        }
    }
}