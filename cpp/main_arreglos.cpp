#include <iostream>
#include <vector>
#include <algorithm> // Para sort
#include <string>

using namespace std;

// ==========================================
// MÉTODOS DE UTILIDAD (Para imprimir)
// ==========================================
void imprimirVector(const vector<int>& arr) {
    cout << "[";
    for (size_t i = 0; i < arr.size(); i++) {
        cout << arr[i] << (i < arr.size() - 1 ? ", " : "");
    }
    cout << "]" << endl;
}

void imprimirVectorString(const vector<string>& arr) {
    cout << "[";
    for (size_t i = 0; i < arr.size(); i++) {
        cout << arr[i] << (i < arr.size() - 1 ? ", " : "");
    }
    cout << "]" << endl;
}

// ==========================================
// MÉTODOS DE LÓGICA
// ==========================================

// 1. Búsqueda Lineal
int busquedaLineal(const vector<int>& arr, int valor) {
    for (size_t i = 0; i < arr.size(); i++) {
        if (arr[i] == valor) return i;
    }
    return -1;
}

// 2. Ordenamiento (Usando std::sort para cadenas)
void ordenarAlfabeticamente(vector<string>& arr) {
    sort(arr.begin(), arr.end());
}

// 3. Inversión del Arreglo (Lógica manual de dos punteros)
void invertirArreglo(vector<int>& arr) {
    int inicio = 0;
    int fin = arr.size() - 1;
    while (inicio < fin) {
        int temp = arr[inicio];
        arr[inicio] = arr[fin];
        arr[fin] = temp;
        inicio++;
        fin--;
    }
}

// 4. Búsqueda Binaria
int busquedaBinaria(const vector<int>& arr, int valor) {
    int izquierda = 0;
    int derecha = arr.size() - 1;

    while (izquierda <= derecha) {
        int medio = (izquierda + derecha) / 2;
        if (arr[medio] == valor) return medio;
        if (arr[medio] < valor) izquierda = medio + 1;
        else derecha = medio - 1;
    }
    return -1;
}

// 5. Inserción Ordenada (Creando nuevo vector para simular redimensión)
vector<int> insertarOrdenado(const vector<int>& arrOriginal, int valor) {
    vector<int> nuevoArr;
    nuevoArr.reserve(arrOriginal.size() + 1); // Optimización de memoria
    
    size_t i = 0;
    // Copiar elementos menores
    while (i < arrOriginal.size() && arrOriginal[i] < valor) {
        nuevoArr.push_back(arrOriginal[i]);
        i++;
    }
    
    // Insertar el nuevo valor
    nuevoArr.push_back(valor);
    
    // Copiar el resto
    while (i < arrOriginal.size()) {
        nuevoArr.push_back(arrOriginal[i]);
        i++;
    }
    
    return nuevoArr;
}

// ==========================================
// MAIN
// ==========================================
int main() {
    cout << "=== DEMOSTRACION ARREGLOS UNIDIMENSIONALES (C++) ===" << endl << endl;

    // --- 1. BÚSQUEDA ---
    cout << "--- 1. BUSQUEDA LINEAL ---" << endl;
    vector<int> datosBusqueda = {45, 12, 89, 33, 7};
    imprimirVector(datosBusqueda);
    cout << "Indice de 33: " << busquedaLineal(datosBusqueda, 33) << endl << endl;

    // --- 2. ORDENAMIENTO ---
    cout << "--- 2. ORDENAMIENTO ALFABETICO ---" << endl;
    vector<string> nombres = {"Zara", "Andres", "Mario", "Carla"};
    imprimirVectorString(nombres);
    ordenarAlfabeticamente(nombres);
    cout << "Ordenado: ";
    imprimirVectorString(nombres);
    cout << endl;

    // --- 3. INVERSION ---
    cout << "--- 3. INVERSION ---" << endl;
    vector<int> datosInv = {1, 2, 3, 4, 5};
    invertirArreglo(datosInv);
    cout << "Invertido: ";
    imprimirVector(datosInv);
    cout << endl;

    // --- 4. BUSQUEDA BINARIA ---
    cout << "--- 4. BUSQUEDA BINARIA ---" << endl;
    vector<int> ordenados = {10, 20, 30, 40, 50};
    cout << "Buscando 40 en: ";
    imprimirVector(ordenados);
    cout << "Indice encontrado: " << busquedaBinaria(ordenados, 40) << endl << endl;

    // --- 5. INSERCION ORDENADA ---
    cout << "--- 5. INSERCION ORDENADA ---" << endl;
    vector<int> base = {2, 4, 6, 8, 10};
    cout << "Base: "; imprimirVector(base);
    cout << "Insertar 5..." << endl;
    vector<int> resultado = insertarOrdenado(base, 5);
    cout << "Resultado: "; imprimirVector(resultado);

    return 0;
}