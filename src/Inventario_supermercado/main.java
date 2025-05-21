package Inventario_supermercado;

import java.util.*;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Producto1> inventario = new ArrayList<>();

        Producto1 gestor;

        boolean continuar = true;

        while (continuar) {

            mostrarPrincipal();
            int tipoInventario = obtenerEntero(scanner, 1, 5);



            gestor = switch (tipoInventario) {
                case 1 -> {
                    System.out.println("Seleccione el tipo de alimento:");
                    System.out.println("1. Comida");
                    System.out.println("2. Bebida");
                    System.out.print("Opción (1-2): ");
                    int opcionAlimento = obtenerEntero(scanner, 1, 2);
                    String tipoAlimento = opcionAlimento == 1 ? "comida" : "Bebida";
                    yield crearGestorAlimentos(tipoAlimento, inventario);
                }
                case 2 -> {
                    System.out.println("Seleccione el tipo de aseo:");
                    System.out.println("1. Hogar");
                    System.out.println("2. Personal");
                    System.out.print("Opción (1-2): ");
                    int opcionAseo = obtenerEntero(scanner, 1, 2);
                    String tipoAseo = opcionAseo == 1 ? "Hogar" : "Personal";
                    yield crearGestorAseo(tipoAseo, inventario);
                }
                case 3 -> {
                    System.out.println("Seleccione el tipo de mueble:");
                    System.out.println("1. Cocina");
                    System.out.println("2. Sala");
                    System.out.println("3. Habitación");
                    System.out.print("Opción (1-3): ");
                    int opcionMueble = obtenerEntero(scanner, 1, 3);
                    String tipoMueble = switch (opcionMueble) {
                        case 1 -> "Cocina";
                        case 2 -> "Sala";
                        case 3 -> "Habitación";
                        default -> "";
                    };
                    yield crearGestorMuebles(tipoMueble, inventario);
                }
                case 4 -> {
                    mostrarInventarioCompleto(inventario);
                    yield null;
                }
                case 5 -> {
                    continuar = false;
                    yield null;
                }
                default -> {
                    System.out.println("Opción inválida.");
                    yield null;
                }
            };

            if (!continuar) break;

            if (tipoInventario != 4) {
                boolean mostrarMenu = true;
                while (mostrarMenu) {
                    mostrarMenu();
                    int opcion = obtenerEntero(scanner, 1, 5);

                    switch (opcion) {
                        case 1 -> {
                            System.out.println("=== Agregar nuevo producto ===");
                            System.out.print("Nombre: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Cantidad: ");
                            int cantidad = obtenerEntero(scanner, 1, Integer.MAX_VALUE);
                            System.out.print("Precio: ");
                            double precio = obtenerDoble(scanner);
                            System.out.print("Peso: ");
                            double peso = obtenerDoble(scanner);
                            System.out.print("Unidad (ej. kg, L , Ml , Gr ): ");
                            System.out.println();
                            String unidad = scanner.nextLine();


                            if (tipoInventario == 1) {
                                System.out.println("Fecha de caducidad (ej. 2025-12-31): ");
                                String fecha = scanner.nextLine();
                                String tipoAlimento = ((Alimento) gestor).getTipoDeAlimento();
                                agregarProducto(gestor, nombre, cantidad, precio, peso, unidad, fecha, tipoAlimento);
                            } else if (tipoInventario == 2) {
                                String tipoAseo = ((ProductoAseo) gestor).getTipoDeAseo();
                                agregarProducto(gestor, nombre, cantidad, precio, peso, unidad, tipoAseo);
                            } else {
                                System.out.print("Alto (metros): ");
                                double alto = obtenerDoble(scanner);
                                System.out.print("Ancho (metros): ");
                                double ancho = obtenerDoble(scanner);
                                String tipoMueble = ((ProductoMuebles) gestor).getTipoDeMueble();
                                agregarProducto(gestor, nombre, cantidad, precio, peso, unidad, alto, ancho, tipoMueble);
                            }

                        }
                        case 2 -> {

                            System.out.print("Ingrese el número del producto a eliminar: ");
                            int numero = obtenerEntero(scanner, 1, Integer.MAX_VALUE);
                            gestor.eliminarProducto(String.valueOf(numero), gestor.getProductos());
                        }
                        case 3 -> {
                            System.out.println("=== Productos de la sección ===");
                            if (gestor != null) {
                                gestor.mostrarProductos();
                            } else {
                                System.out.println("No hay productos en esta sección.");
                            }
                        }
                        case 4 -> {
                            System.out.println("=== Modificar producto ===");
                            gestor.mostrarProductos();
                            if (gestor.getProductos().isEmpty()) {
                                System.out.println("No hay productos para modificar.");
                                continue;
                            }
                            System.out.print("Ingrese el número del producto a modificar: ");
                            int numero = obtenerEntero(scanner, 1, gestor.getProductos().size());
                            int indice = numero - 1;

                            if (indice >= 0 && indice < gestor.getProductos().size()) {
                                Producto1 producto = gestor.getProductos().get(indice);
                                if (gestor.esCompatible(producto)) {
                                    System.out.println("Has seleccionado: " + producto.getNombre());
                                    System.out.println("Seleccione qué desea modificar:");
                                    System.out.println("1. Nombre");
                                    System.out.println("2. Cantidad");
                                    System.out.println("3. Precio");
                                    System.out.println("4. Peso");
                                    System.out.println("5. Unidad");
                                    List<String> propiedadesEspecificas = gestor.modificarPropiedadesEspecificas();
                                    for (int i = 0; i < propiedadesEspecificas.size(); i++) {
                                        System.out.println((6 + i) + ". " + propiedadesEspecificas.get(i));
                                    }

                                    System.out.print("Opción: ");
                                    int opcionModificar = obtenerEntero(scanner, 1, 5 + propiedadesEspecificas.size());


                                    String nombre = producto.getNombre();
                                    int cantidad = producto.getCantidad();
                                    double precio = producto.getPrecio();
                                    double peso = producto.getPeso();
                                    String unidad = producto.getUnidad();
                                    String valorEspecifico = null;
                                    int indicePropiedad = -1;


                                    switch (opcionModificar) {
                                        case 1 -> {
                                            System.out.print("Nuevo nombre: ");
                                            nombre = scanner.nextLine();
                                        }
                                        case 2 -> {
                                            System.out.print("Nueva cantidad: ");
                                            cantidad = obtenerEntero(scanner, 1, Integer.MAX_VALUE);
                                        }
                                        case 3 -> {
                                            System.out.print("Nuevo precio: ");
                                            precio = obtenerDoble(scanner);
                                        }
                                        case 4 -> {
                                            System.out.print("Nuevo peso: ");
                                            peso = obtenerDoble(scanner);
                                        }
                                        case 5 -> {
                                            System.out.print("Nueva unidad: ");
                                            unidad = scanner.nextLine();
                                        }
                                        default -> {
                                            indicePropiedad = opcionModificar - 6;
                                            System.out.print("Nuevo valor para " + propiedadesEspecificas.get(indicePropiedad) + ": ");
                                            valorEspecifico = scanner.nextLine();
                                        }
                                    }
                                    modificarProducto(gestor, indice, nombre, cantidad, precio, peso, unidad, indicePropiedad, valorEspecifico);
                                } else {
                                    System.out.println("Este producto no puede modificarse con este gestor.");
                                }
                            } else {
                                System.out.println("Número de producto inválido.");
                            }
                        }
                        case 5 -> {

                            System.out.println("Volviendo al menú principal.");
                            mostrarMenu = false;
                        }
                        default -> System.out.println("Opción inválida.");
                    }
                }
            }
        }
        System.out.println("¡Gracias por usar el sistema de inventario!");
        System.out.println("Cerrando el programa...");
        scanner.close();
    }


    private static void mostrarPrincipal() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           Sistema de Inventario        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  Seleccione el tipo de inventario:     ║");
        System.out.println("║  1. Alimentos (Comida/Bebida)          ║");
        System.out.println("║  2. Productos de Aseo (Hogar/Personal) ║");
        System.out.println("║  3. Muebles (Cocina/Sala/Habitación)   ║");
        System.out.println("║  4. Mostrar Inventario Completo        ║");
        System.out.println("║  5. Salir                              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Opción (1-5): ");
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
        System.out.print("Opción (1-5): ");
    }


    private static int obtenerEntero(Scanner scanner, int min, int max) {
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


    private static double obtenerDoble(Scanner scanner) {
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

    private static Producto1 crearGestorAlimentos(String tipoAlimento, ArrayList<Producto1> inventario) {
        return new Alimento("Gestor Alimentos", 0, 0.0, 0.0, "Unidad", inventario, "N/A", tipoAlimento);
    }


    private static Producto1 crearGestorAseo(String tipoAseo, ArrayList<Producto1> inventario) {
        return new ProductoAseo("Gestor Aseo", 0, 0.0, 0.0, "Unidad", tipoAseo, inventario);
    }


    private static Producto1 crearGestorMuebles(String tipoMueble, ArrayList<Producto1> inventario) {
        return new ProductoMuebles("Gestor Muebles", 0, 0.0, 0.0, "Unidad", tipoMueble, 0.0, 0.0, inventario);
    }


    private static void agregarProducto(Producto1 gestor, String nombre, int cantidad, double precio, double peso, String unidad, String fecha, String tipoAlimento) {
        Producto1 nuevoProducto = new Alimento(nombre, cantidad, precio, peso, unidad, null, fecha, tipoAlimento);
        gestor.agregarProducto(nuevoProducto);
    }


    private static void agregarProducto(Producto1 gestor, String nombre, int cantidad, double precio, double peso, String unidad, String tipoAseo) {
        Producto1 nuevoProducto = new ProductoAseo(nombre, cantidad, precio, peso, unidad, tipoAseo, null);
        gestor.agregarProducto(nuevoProducto);
    }


    private static void agregarProducto(Producto1 gestor, String nombre, int cantidad, double precio, double peso, String unidad, double alto, double ancho, String tipoMueble) {
        Producto1 nuevoProducto = new ProductoMuebles(nombre, cantidad, precio, peso, unidad, tipoMueble, alto, ancho, null);
        gestor.agregarProducto(nuevoProducto);
    }


    private static void modificarProducto(Producto1 gestor, int indice, String nombre, int cantidad, double precio, double peso, String unidad, int indicePropiedad, String valorEspecifico) {

        if (indice < 0 || indice >= gestor.getProductos().size() || !gestor.esCompatible(gestor.getProductos().get(indice))) {
            System.out.println("No se puede modificar este producto.");
            return;
        }

        Producto1 producto = gestor.getProductos().get(indice);

        if (!nombre.equals(producto.getNombre())) producto.setNombre(nombre);
        if (cantidad != producto.getCantidad()) producto.setCantidad(cantidad);
        if (precio != producto.getPrecio()) producto.setPrecio(precio);
        if (peso != producto.getPeso()) producto.setPeso(peso);
        if (!unidad.equals(producto.getUnidad())) producto.setUnidad(unidad);
        if (indicePropiedad >= 0 && valorEspecifico != null) {
            gestor.modificarPropiedadEspecifica(producto, indicePropiedad, valorEspecifico);
        }
        System.out.println("Producto " + producto.getNombre() + " modificado con éxito.");
    }

    private static void mostrarInventarioCompleto(ArrayList<Producto1> inventario) {
        System.out.println("=== Inventario Completo ===");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Producto1 producto : inventario) {
            System.out.println("-------------------------");
            System.out.println("Tipo: " + producto.obtenerTipo());
            producto.mostrarInformacionProducto(producto);
        }
    }
}