public class CursorListDeleteApp {

    static class ListaCursorial {
        // Tabla de dos columnas
        private String[] datos;
        private int[] sig; // Columna 'siguiente'

        // Punteros de control
        private int cabeza; // Inicio de la lista
        private int ultimo; // Referencia al final (para insertar rápido)
        private int libre;  // Inicio de espacios vacíos
        private int max;    // Tamaño del array

        public ListaCursorial(int tamano) {
            this.max = tamano;
            this.datos = new String[tamano];
            this.sig = new int[tamano];
            this.cabeza = -1; // -1 = null
            this.ultimo = -1;
            this.libre = 0;

            // Inicializamos la lista de libres: 0->1->2...
            for (int i = 0; i < tamano - 1; i++) {
                sig[i] = i + 1;
            }
            sig[tamano - 1] = -1;
        }

        // --- VALIDACIONES ---
        public boolean estaVacia() {
            return cabeza == -1;
        }

        public boolean estaLlena() {
            return libre == -1;
        }

        // --- GESTIÓN DE MEMORIA SIMULADA ---
        
        // "Malloc": Obtener un índice libre
        private int obtenerNodo() {
            int p = libre;
            if (libre != -1) {
                libre = sig[libre]; // El nuevo libre es el siguiente del actual
            }
            return p;
        }

        // "Free": Devolver un índice a la lista de libres
        private void liberarNodo(int p) {
            datos[p] = null;    // Limpiar dato (opcional pero recomendado)
            sig[p] = libre;     // El nodo liberado apunta al antiguo libre
            libre = p;          // El nodo liberado ahora es el primer libre
        }

        // --- MÉTODOS DE INSERCIÓN (Necesarios para probar) ---
        public void insertarFinal(String dato) {
            if (estaLlena()) {
                System.out.println("Error: Lista llena.");
                return;
            }
            int nuevo = obtenerNodo();
            datos[nuevo] = dato;
            sig[nuevo] = -1;

            if (estaVacia()) {
                cabeza = nuevo;
            } else {
                sig[ultimo] = nuevo;
            }
            ultimo = nuevo;
        }

        // --- MÉTODOS DE ELIMINACIÓN SOLICITADOS ---

        /**
         * 1. Eliminar Inicio
         * Complejidad: O(1)
         */
        public void eliminarInicio() {
            // Validación
            if (estaVacia()) {
                System.out.println("Error al eliminar inicio: La lista está vacía.");
                return;
            }

            int temp = cabeza; // Guardamos referencia al nodo a borrar

            // Caso especial: Solo queda un nodo
            if (cabeza == ultimo) {
                cabeza = -1;
                ultimo = -1;
            } else {
                // El nuevo inicio es el siguiente del actual inicio
                cabeza = sig[cabeza];
            }

            // Devolver memoria
            String datoBorrado = datos[temp];
            liberarNodo(temp);
            System.out.println("Se eliminó del inicio: " + datoBorrado);
        }

        /**
         * 2. Eliminar Final
         * Complejidad: O(N) - Requiere buscar el penúltimo nodo
         */
        public void eliminarFinal() {
            // Validación
            if (estaVacia()) {
                System.out.println("Error al eliminar final: La lista está vacía.");
                return;
            }

            int temp = ultimo; // Nodo a borrar

            // Caso especial: Solo hay un elemento
            if (cabeza == ultimo) {
                cabeza = -1;
                ultimo = -1;
            } else {
                // Necesitamos encontrar el nodo ANTERIOR al último.
                // Como es una lista simple, no podemos ir hacia atrás,
                // debemos recorrer desde el principio.
                int actual = cabeza;
                while (sig[actual] != ultimo) {
                    actual = sig[actual];
                }
                
                // 'actual' es ahora el penúltimo nodo
                sig[actual] = -1; // Desconectamos el último
                ultimo = actual;  // Actualizamos la referencia ultimo
            }

            // Devolver memoria
            String datoBorrado = datos[temp];
            liberarNodo(temp);
            System.out.println("Se eliminó del final: " + datoBorrado);
        }

        // Mostrar estado de la tabla
        public void mostrarTabla() {
            System.out.print("Lista Lógica: ");
            int p = cabeza;
            while (p != -1) {
                System.out.print("[" + datos[p] + "] -> ");
                p = sig[p];
            }
            System.out.println("null");
            System.out.println("Indices -> Cabeza: " + cabeza + ", Ultimo: " + ultimo + ", Libre: " + libre);
            System.out.println("---------------------------------");
        }
    }

    public static void main(String[] args) {
        ListaCursorial lista = new ListaCursorial(5);

        System.out.println("--- Llenando lista ---");
        lista.insertarFinal("A");
        lista.insertarFinal("B");
        lista.insertarFinal("C");
        lista.insertarFinal("D");
        lista.mostrarTabla();

        System.out.println("\n--- Eliminando Inicio (Debe borrar A) ---");
        lista.eliminarInicio();
        lista.mostrarTabla();

        System.out.println("\n--- Eliminando Final (Debe borrar D) ---");
        lista.eliminarFinal();
        lista.mostrarTabla();

        System.out.println("\n--- Vaciando lista ---");
        lista.eliminarFinal(); // Borra C
        lista.eliminarInicio(); // Borra B (ahora queda vacía)
        lista.mostrarTabla();

        System.out.println("\n--- Intentando borrar en vacía ---");
        lista.eliminarInicio();
    }
}