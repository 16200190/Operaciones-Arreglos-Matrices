public class CursorListSearchSortApp {

    static class ListaCursorial {
        private String[] datos;
        private int[] sig;
        private int cabeza;
        private int ultimo; // Referencia al último nodo
        private int libre;
        private int max;

        public ListaCursorial(int tamano) {
            this.max = tamano;
            datos = new String[tamano];
            sig = new int[tamano];
            cabeza = -1;
            ultimo = -1;
            libre = 0;

            for (int i = 0; i < tamano - 1; i++) sig[i] = i + 1;
            sig[tamano - 1] = -1;
        }

        // --- MÉTODOS AUXILIARES Y DE INSERCIÓN (Base) ---
        public boolean estaVacia() { return cabeza == -1; }
        public boolean estaLlena() { return libre == -1; }

        private int obtenerNodo() {
            int p = libre;
            if (libre != -1) libre = sig[libre];
            return p;
        }

        public void insertarFinal(String dato) {
            if (estaLlena()) {
                System.out.println("Error: Lista llena.");
                return;
            }
            int nuevo = obtenerNodo();
            datos[nuevo] = dato;
            sig[nuevo] = -1;

            if (estaVacia()) cabeza = nuevo;
            else sig[ultimo] = nuevo;
            
            ultimo = nuevo;
        }

        // ==========================================================
        // 1. BUSCAR POR VALOR
        // Devuelve la posición lógica (0, 1, 2...) donde se encuentra
        // ==========================================================
        public int buscarPorValor(String valorBuscado) {
            if (estaVacia()) return -1;

            int actual = cabeza;
            int contadorPosicion = 0;

            while (actual != -1) {
                // Comparamos cadenas con .equals
                if (datos[actual].equals(valorBuscado)) {
                    return contadorPosicion; // Encontrado
                }
                actual = sig[actual]; // Avanzar cursor
                contadorPosicion++;
            }
            return -1; // No encontrado
        }

        // ==========================================================
        // 2. BUSCAR POR POSICIÓN (Índice Lógico)
        // Devuelve el valor en la posición N (0 es el primero)
        // ==========================================================
        public String buscarPorPosicion(int posLogica) {
            if (estaVacia()) return null;
            if (posLogica < 0) return null; // Validación básica

            int actual = cabeza;
            int contador = 0;

            while (actual != -1) {
                if (contador == posLogica) {
                    return datos[actual]; // Retorna el dato
                }
                actual = sig[actual];
                contador++;
            }
            
            return null; // La posición es mayor que el tamaño de la lista
        }

        // ==========================================================
        // 3. ORDENAR ASCENDENTE (Burbuja por Intercambio de Datos)
        // ==========================================================
        public void ordenarAscendente() {
            if (estaVacia() || cabeza == ultimo) {
                return; // Lista vacía o con 1 elemento ya está ordenada
            }

            boolean huboCambio;
            
            // Algoritmo de Burbuja optimizado para listas
            do {
                huboCambio = false;
                int actual = cabeza;
                
                // Mientras el actual tenga un siguiente
                while (sig[actual] != -1) {
                    int siguiente = sig[actual];

                    // Comparación alfabética: > 0 significa que actual es mayor que siguiente
                    if (datos[actual].compareTo(datos[siguiente]) > 0) {
                        
                        // INTERCAMBIO DE DATOS (SWAP)
                        String temp = datos[actual];
                        datos[actual] = datos[siguiente];
                        datos[siguiente] = temp;

                        huboCambio = true;
                    }
                    actual = sig[actual]; // Avanzar
                }
            } while (huboCambio);
            
            System.out.println(">> Lista ordenada ascendentemente.");
        }

        // --- MOSTRAR ---
        public void mostrarLista() {
            System.out.print("Lista: ");
            int p = cabeza;
            while (p != -1) {
                System.out.print("[" + datos[p] + "] -> ");
                p = sig[p];
            }
            System.out.println("null");
        }
    }

    // --- PRUEBA DE FUNCIONALIDADES ---
    public static void main(String[] args) {
        ListaCursorial lista = new ListaCursorial(10);

        System.out.println("--- 1. Insertando datos desordenados ---");
        lista.insertarFinal("Banana");
        lista.insertarFinal("Avión");
        lista.insertarFinal("Zapato");
        lista.insertarFinal("Dedo");
        lista.mostrarLista();

        System.out.println("\n--- 2. Buscar por Valor ---");
        String val = "Zapato";
        int pos = lista.buscarPorValor(val);
        if (pos != -1) System.out.println("El valor '" + val + "' está en la posición lógica: " + pos);
        else System.out.println("Valor no encontrado.");

        System.out.println("\n--- 3. Buscar por Posición ---");
        int p = 1;
        String encontrado = lista.buscarPorPosicion(p);
        if (encontrado != null) System.out.println("En la posición " + p + " está: " + encontrado);
        else System.out.println("Posición inválida.");

        System.out.println("\n--- 4. Ordenar Ascendente ---");
        lista.ordenarAscendente();
        lista.mostrarLista();
        
        // Verificamos que la cabeza y el último sigan lógicos
        // Ahora "Avión" debería estar al inicio y "Zapato" al final
        System.out.println("(Verificación visual: Avión debe ser el primero, Zapato el último)");
    }
}