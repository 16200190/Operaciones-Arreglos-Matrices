import java.util.Arrays;

public class DemostracionArreglos {

    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DE OPERACIONES CON ARREGLOS ===\n");

        // ---------------------------------------------------------
        // 1. BÚSQUEDA POR VALOR (Lineal)
        // ---------------------------------------------------------
        System.out.println("--- 1. BÚSQUEDA POR VALOR (LINEAL) ---");
        int[] datosBusqueda = {45, 12, 89, 33, 7};
        int valorAEncontrar = 33;
        int valorNoExistente = 100;

        System.out.println("Arreglo: " + Arrays.toString(datosBusqueda));
        
        // Caso de éxito
        int indiceEncontrado = busquedaLineal(datosBusqueda, valorAEncontrar);
        System.out.println("Buscando " + valorAEncontrar + "... Encontrado en índice: " + indiceEncontrado);
        
        // Caso de fallo
        int indiceNoEncontrado = busquedaLineal(datosBusqueda, valorNoExistente);
        System.out.println("Buscando " + valorNoExistente + "... Resultado: " + indiceNoEncontrado + " (No existe)");
        System.out.println();


        // ---------------------------------------------------------
        // 2. ORDENAMIENTO (NATURAL: ALFABÉTICO)
        // ---------------------------------------------------------
        System.out.println("--- 2. ORDENAMIENTO ALFABÉTICO ---");
        String[] nombres = {"Zara", "Andrés", "Mario", "Carla", "Beto"};
        
        System.out.println("Original: " + Arrays.toString(nombres));
        Arrays.sort(nombres); // Usamos el método nativo de Java
        System.out.println("Ordenado: " + Arrays.toString(nombres));
        System.out.println();


        // ---------------------------------------------------------
        // 3. INVERSIÓN DEL ARREGLO
        // ---------------------------------------------------------
        System.out.println("--- 3. INVERSIÓN DEL ARREGLO ---");
        int[] datosInversion = {1, 2, 3, 4, 5};
        
        System.out.println("Original:  " + Arrays.toString(datosInversion));
        invertirArreglo(datosInversion);
        System.out.println("Invertido: " + Arrays.toString(datosInversion));
        System.out.println();


        // ---------------------------------------------------------
        // 4. BÚSQUEDA BINARIA (Requiere arreglo ordenado)
        // ---------------------------------------------------------
        System.out.println("--- 4. BÚSQUEDA BINARIA ---");
        // Nota: El arreglo DEBE estar ordenado para que esto funcione
        int[] datosOrdenados = {10, 20, 30, 40, 50, 60, 70}; 
        int objetivoBinario = 50;
        
        System.out.println("Arreglo ordenado: " + Arrays.toString(datosOrdenados));
        int indexBinario = busquedaBinaria(datosOrdenados, objetivoBinario);
        System.out.println("El valor " + objetivoBinario + " está en el índice: " + indexBinario);
        System.out.println();


        // ---------------------------------------------------------
        // 5. INSERCIÓN EN ARREGLO ORDENADO
        // ---------------------------------------------------------
        System.out.println("--- 5. INSERCIÓN ORDENADA ---");
        int[] arregloBase = {2, 4, 6, 8, 10};
        int nuevoValor = 5; // Debería quedar entre el 4 y el 6
        
        System.out.println("Arreglo inicial: " + Arrays.toString(arregloBase));
        System.out.println("Insertar valor:  " + nuevoValor);
        
        int[] arregloFinal = insertarOrdenado(arregloBase, nuevoValor);
        
        System.out.println("Resultado final: " + Arrays.toString(arregloFinal));
    }

    // ==========================================
    // MÉTODOS DE LÓGICA (ALGORITMOS)
    // ==========================================

    // 1. Método de Búsqueda Lineal
    public static int busquedaLineal(int[] arr, int valor) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == valor) return i;
        }
        return -1;
    }

    // 3. Método de Inversión
    public static void invertirArreglo(int[] arr) {
        int inicio = 0;
        int fin = arr.length - 1;
        while (inicio < fin) {
            int temp = arr[inicio];
            arr[inicio] = arr[fin];
            arr[fin] = temp;
            inicio++;
            fin--;
        }
    }

    // 4. Método de Búsqueda Binaria
    public static int busquedaBinaria(int[] arr, int valor) {
        int izquierda = 0;
        int derecha = arr.length - 1;
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            if (arr[medio] == valor) return medio;
            if (arr[medio] < valor) izquierda = medio + 1;
            else derecha = medio - 1;
        }
        return -1;
    }

    // 5. Método de Inserción Ordenada
    public static int[] insertarOrdenado(int[] arrOriginal, int valor) {
        int[] nuevoArr = new int[arrOriginal.length + 1];
        int i = 0; // Índice para recorrer el original
        int j = 0; // Índice para llenar el nuevo

        // Copiar elementos menores
        while (i < arrOriginal.length && arrOriginal[i] < valor) {
            nuevoArr[j++] = arrOriginal[i++];
        }

        // Insertar el nuevo valor
        nuevoArr[j++] = valor;

        // Copiar el resto
        while (i < arrOriginal.length) {
            nuevoArr[j++] = arrOriginal[i++];
        }

        return nuevoArr;
    }
}