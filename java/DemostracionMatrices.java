import java.util.Arrays;

public class DemostracionMatrices {

    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DE OPERACIONES CON MATRICES ===\n");

        // Matriz inicial de 3x3
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("--- MATRIZ ORIGINAL ---");
        imprimirMatriz(matriz);

        // ---------------------------------------------------------
        // 1. RECORRIDO (Filas vs Columnas)
        // ---------------------------------------------------------
        System.out.println("\n--- 1. RECORRIDOS ---");
        recorrerPorFilas(matriz);
        recorrerPorColumnas(matriz);

        // ---------------------------------------------------------
        // 2. BÚSQUEDA POR VALOR
        // ---------------------------------------------------------
        System.out.println("\n--- 2. BÚSQUEDA DE VALOR ---");
        int valorBuscado = 6;
        int[] coordenadas = buscarValor(matriz, valorBuscado);
        
        if (coordenadas != null) {
            System.out.println("El valor " + valorBuscado + " está en: Fila " + coordenadas[0] + ", Columna " + coordenadas[1]);
        } else {
            System.out.println("Valor no encontrado.");
        }

        // ---------------------------------------------------------
        // 3. INSERTAR FILA
        // ---------------------------------------------------------
        System.out.println("\n--- 3. INSERTAR FILA (en índice 1) ---");
        int[] nuevaFila = {99, 99, 99}; // Debe tener el mismo ancho que la matriz
        matriz = insertarFila(matriz, nuevaFila, 1); // Reconstruimos la matriz
        imprimirMatriz(matriz);

        // ---------------------------------------------------------
        // 4. INSERTAR COLUMNA
        // ---------------------------------------------------------
        System.out.println("\n--- 4. INSERTAR COLUMNA (en índice 0) ---");
        int[] nuevaColumna = {50, 50, 50, 50}; // Debe tener el mismo alto que la matriz actual
        matriz = insertarColumna(matriz, nuevaColumna, 0);
        imprimirMatriz(matriz);

        // ---------------------------------------------------------
        // 5. ELIMINAR FILA
        // ---------------------------------------------------------
        System.out.println("\n--- 5. ELIMINAR FILA (índice 3 - la última original) ---");
        matriz = eliminarFila(matriz, 3);
        imprimirMatriz(matriz);

        // ---------------------------------------------------------
        // 6. ELIMINAR COLUMNA
        // ---------------------------------------------------------
        System.out.println("\n--- 6. ELIMINAR COLUMNA (índice 2) ---");
        matriz = eliminarColumna(matriz, 2);
        imprimirMatriz(matriz);
    }

    // ==========================================
    // MÉTODOS DE UTILIDAD
    // ==========================================

    public static void imprimirMatriz(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(m[i]));
        }
    }

    // ==========================================
    // MÉTODOS LÓGICOS
    // ==========================================

    // 1. Recorrer por Filas (Estándar)
    public static void recorrerPorFilas(int[][] m) {
        System.out.print("Por Filas: ");
        for (int i = 0; i < m.length; i++) {       // i recorre filas
            for (int j = 0; j < m[0].length; j++) { // j recorre columnas
                System.out.print(m[i][j] + " ");
            }
        }
        System.out.println();
    }

    // 1. Recorrer por Columnas
    public static void recorrerPorColumnas(int[][] m) {
        System.out.print("Por Columnas: ");
        // Nota: invertimos el orden de los bucles
        if (m.length > 0) {
            for (int j = 0; j < m[0].length; j++) {    // j recorre columnas primero
                for (int i = 0; i < m.length; i++) {   // i recorre filas dentro
                    System.out.print(m[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    // 2. Buscar Valor (Retorna coordenadas [fila, col])
    public static int[] buscarValor(int[][] m, int valor) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == valor) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // No encontrado
    }

    // 3. Insertar Fila (Reconstrucción de Matriz)
    public static int[][] insertarFila(int[][] original, int[] nuevaFila, int pos) {
        int filas = original.length;
        int cols = original[0].length;
        
        int[][] nuevaMatriz = new int[filas + 1][cols];

        for (int i = 0; i < filas + 1; i++) {
            if (i < pos) {
                nuevaMatriz[i] = original[i];
            } else if (i == pos) {
                nuevaMatriz[i] = nuevaFila;
            } else {
                nuevaMatriz[i] = original[i - 1];
            }
        }
        return nuevaMatriz;
    }

    // 4. Insertar Columna (Reconstrucción de Matriz)
    public static int[][] insertarColumna(int[][] original, int[] nuevaCol, int pos) {
        int filas = original.length;
        int cols = original[0].length;

        int[][] nuevaMatriz = new int[filas][cols + 1];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols + 1; j++) {
                if (j < pos) {
                    nuevaMatriz[i][j] = original[i][j];
                } else if (j == pos) {
                    nuevaMatriz[i][j] = nuevaCol[i];
                } else {
                    nuevaMatriz[i][j] = original[i][j - 1];
                }
            }
        }
        return nuevaMatriz;
    }

    // 5. Eliminar Fila
    public static int[][] eliminarFila(int[][] original, int indiceFila) {
        int filas = original.length;
        int cols = original[0].length;
        
        int[][] nuevaMatriz = new int[filas - 1][cols];
        int k = 0; // índice para la nueva matriz

        for (int i = 0; i < filas; i++) {
            if (i == indiceFila) continue; // Saltamos la fila a eliminar
            nuevaMatriz[k++] = original[i];
        }
        return nuevaMatriz;
    }

    // 6. Eliminar Columna
    public static int[][] eliminarColumna(int[][] original, int indiceCol) {
        int filas = original.length;
        int cols = original[0].length;

        int[][] nuevaMatriz = new int[filas][cols - 1];

        for (int i = 0; i < filas; i++) {
            int k = 0; // índice para las columnas de la nueva fila
            for (int j = 0; j < cols; j++) {
                if (j == indiceCol) continue; // Saltamos la columna a eliminar
                nuevaMatriz[i][k++] = original[i][j];
            }
        }
        return nuevaMatriz;
    }
}