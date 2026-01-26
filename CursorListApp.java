public class CursorListApp {

    // Clase interna que gestiona la lógica de la Lista Cursorial
    static class ListaCursorial {
        // "Tabla de dos columnas":
        // columnaDatos almacena la información
        // columnaSig almacena el índice del siguiente nodo (el cursor)
        private String[] columnaDatos;
        private int[] columnaSig;
        
        private int cabeza; // Referencia al primer nodo lógico
        private int fin;    // Referencia al último nodo lógico (OPTIMIZACIÓN SOLICITADA)
        private int libre;  // Referencia al inicio de la lista de espacios vacíos
        private int max;    // Tamaño máximo de la tabla

        // Constructor: Inicializa la "memoria"
        public ListaCursorial(int tamano) {
            this.max = tamano;
            this.columnaDatos = new String[tamano];
            this.columnaSig = new int[tamano];
            this.cabeza = -1; // -1 representa null
            this.fin = -1;
            this.libre = 0;   // El primer espacio libre es el índice 0

            // Enlazamos todos los nodos libres inicialmente
            // 0->1->2->...->(max-1)->-1
            for (int i = 0; i < tamano - 1; i++) {
                columnaSig[i] = i + 1;
            }
            columnaSig[tamano - 1] = -1; // El último no tiene siguiente
        }

        // --- VALIDACIONES ---
        public boolean estaLlena() {
            return libre == -1;
        }

        public boolean estaVacia() {
            return cabeza == -1;
        }

        // --- GESTIÓN DE MEMORIA (Simulada) ---
        // Obtiene un índice libre para usar (equivalente a 'new' o 'malloc')
        private int obtenerNodo() {
            int nodo = libre;
            if (nodo != -1) {
                libre = columnaSig[libre]; // Avanzamos el puntero libre
            }
            return nodo;
        }

        // --- OPERACIONES ---

        // 1. Insertar al Inicio
        public void insertarInicio(String dato) {
            if (estaLlena()) {
                System.out.println("Error: La lista está llena (Overflow).");
                return;
            }

            int nuevo = obtenerNodo();
            columnaDatos[nuevo] = dato;
            
            // Enlazado
            columnaSig[nuevo] = cabeza;
            cabeza = nuevo;

            // Si la lista estaba vacía, el nuevo también es el final
            if (fin == -1) {
                fin = nuevo;
            }
            
            System.out.println("Insertado al inicio: " + dato);
        }

        // 2. Insertar al Final (Usando referencia 'fin')
        public void insertarFinal(String dato) {
            if (estaLlena()) {
                System.out.println("Error: La lista está llena (Overflow).");
                return;
            }

            int nuevo = obtenerNodo();
            columnaDatos[nuevo] = dato;
            columnaSig[nuevo] = -1; // Al ser el último, su siguiente es nulo (-1)

            if (estaVacia()) {
                cabeza = nuevo;
            } else {
                // Usamos la referencia 'fin' para evitar recorrer toda la lista
                columnaSig[fin] = nuevo;
            }
            
            // Actualizamos la referencia al último nodo
            fin = nuevo;
            
            System.out.println("Insertado al final: " + dato);
        }

        // Método para visualizar la tabla y la lista lógica
        public void mostrarEstado() {
            System.out.println("\n--- ESTADO DE LA LISTA CURSORIAL ---");
            System.out.println("Cabeza: " + cabeza + " | Fin: " + fin + " | Libre: " + libre);
            System.out.println("------------------------------------");
            System.out.println("IDX\t| DATO\t| SIG");
            System.out.println("------------------------------------");
            for (int i = 0; i < max; i++) {
                String d = (columnaDatos[i] == null) ? "null" : columnaDatos[i];
                System.out.println(i + "\t| " + d + "\t| " + columnaSig[i]);
            }
            
            System.out.print("Recorrido Lógico: ");
            int actual = cabeza;
            while (actual != -1) {
                System.out.print("[" + columnaDatos[actual] + "] -> ");
                actual = columnaSig[actual];
            }
            System.out.println("null\n");
        }
    }

    // --- MAIN PARA PRUEBAS ---
    public static void main(String[] args) {
        // Creamos una lista pequeña de 5 espacios para probar la saturación
        ListaCursorial lista = new ListaCursorial(5);

        // Caso 1: Insertar en lista vacía (actualiza inicio y fin)
        lista.insertarInicio("A"); 
        
        // Caso 2: Insertar al final (usa puntero fin)
        lista.insertarFinal("B");
        
        // Caso 3: Insertar al inicio de nuevo
        lista.insertarInicio("C"); 
        
        // Caso 4: Insertar al final nuevamente
        lista.insertarFinal("D");
        
        // Caso 5: Llenar la lista
        lista.insertarFinal("E");

        lista.mostrarEstado();

        // Caso 6: Intentar insertar cuando está llena (Validación)
        lista.insertarInicio("F_Error"); 
    }
}