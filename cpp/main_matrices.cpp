#include <iostream>
#include <vector>

using namespace std;

// Estructura simple para devolver coordenadas
struct Coordenada {
    int fila;
    int columna;
    bool encontrado;
};

// ==========================================
// MÉTODOS DE UTILIDAD
// ==========================================
void imprimirMatriz(const vector<vector<int>>& m) {
    for (const auto& fila : m) {
        cout << "[ ";
        for (int val : fila) {
            cout << val << " ";
        }
        cout << "]" << endl;
    }
}

// ==========================================
// MÉTODOS DE LÓGICA
// ==========================================

// 1. Recorridos
void recorrerPorFilas(const vector<vector<int>>& m) {
    cout << "Por Filas: ";
    for (size_t i = 0; i < m.size(); i++) {
        for (size_t j = 0; j < m[i].size(); j++) {
            cout << m[i][j] << " ";
        }
    }
    cout << endl;
}

void recorrerPorColumnas(const vector<vector<int>>& m) {
    cout << "Por Columnas: ";
    if (m.empty()) return;
    
    size_t filas = m.size();
    size_t cols = m[0].size();

    for (size_t j = 0; j < cols; j++) {
        for (size_t i = 0; i < filas; i++) {
            cout << m[i][j] << " ";
        }
    }
    cout << endl;
}

// 2. Búsqueda de Valor
Coordenada buscarValor(const vector<vector<int>>& m, int valor) {
    for (int i = 0; i < m.size(); i++) {
        for (int j = 0; j < m[i].size(); j++) {
            if (m[i][j] == valor) {
                return {i, j, true};
            }
        }
    }
    return {-1, -1, false};
}

// 3. Insertar Fila (Reconstrucción)
vector<vector<int>> insertarFila(const vector<vector<int>>& original, const vector<int>& nuevaFila, int pos) {
    vector<vector<int>> nuevaMatriz;
    // Iteramos sobre la matriz original
    for (int i = 0; i < original.size() + 1; i++) {
        if (i < pos) {
            nuevaMatriz.push_back(original[i]);
        } else if (i == pos) {
            nuevaMatriz.push_back(nuevaFila);
        } else {
            nuevaMatriz.push_back(original[i - 1]);
        }
    }
    return nuevaMatriz;
}

// 4. Insertar Columna (Reconstrucción manual celda por celda)
vector<vector<int>> insertarColumna(const vector<vector<int>>& original, const vector<int>& nuevaCol, int pos) {
    int filas = original.size();
    int cols = original[0].size();
    
    // Creamos matriz nueva con una columna extra
    vector<vector<int>> nuevaMatriz(filas, vector<int>(cols + 1));

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
vector<vector<int>> eliminarFila(const vector<vector<int>>& original, int indiceFila) {
    vector<vector<int>> nuevaMatriz;
    for (int i = 0; i < original.size(); i++) {
        if (i == indiceFila) continue; // Saltar
        nuevaMatriz.push_back(original[i]);
    }
    return nuevaMatriz;
}

// 6. Eliminar Columna
vector<vector<int>> eliminarColumna(const vector<vector<int>>& original, int indiceCol) {
    int filas = original.size();
    int cols = original[0].size();
    
    vector<vector<int>> nuevaMatriz(filas, vector<int>(cols - 1));

    for (int i = 0; i < filas; i++) {
        int k = 0; // indice nueva columna
        for (int j = 0; j < cols; j++) {
            if (j == indiceCol) continue; // Saltar
            nuevaMatriz[i][k++] = original[i][j];
        }
    }
    return nuevaMatriz;
}

// ==========================================
// MAIN
// ==========================================
int main() {
    cout << "=== DEMOSTRACION MATRICES (C++) ===" << endl << endl;

    // Inicialización de vector de vectores (Matriz 3x3)
    vector<vector<int>> matriz = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    cout << "--- MATRIZ ORIGINAL ---" << endl;
    imprimirMatriz(matriz);

    cout << endl << "--- 1. RECORRIDOS ---" << endl;
    recorrerPorFilas(matriz);
    recorrerPorColumnas(matriz);

    cout << endl << "--- 2. BUSQUEDA (Valor 6) ---" << endl;
    Coordenada coord = buscarValor(matriz, 6);
    if (coord.encontrado) {
        cout << "Encontrado en: Fila " << coord.fila << ", Columna " << coord.columna << endl;
    } else {
        cout << "No encontrado" << endl;
    }

    cout << endl << "--- 3. INSERTAR FILA (pos 1) ---" << endl;
    vector<int> filaNueva = {99, 99, 99};
    matriz = insertarFila(matriz, filaNueva, 1);
    imprimirMatriz(matriz);

    cout << endl << "--- 4. INSERTAR COLUMNA (pos 0) ---" << endl;
    vector<int> colNueva = {50, 50, 50, 50}; // Debe coincidir con nuevas filas (4)
    matriz = insertarColumna(matriz, colNueva, 0);
    imprimirMatriz(matriz);

    cout << endl << "--- 5. ELIMINAR FILA (ultima) ---" << endl;
    matriz = eliminarFila(matriz, 3);
    imprimirMatriz(matriz);

    cout << endl << "--- 6. ELIMINAR COLUMNA (indice 2) ---" << endl;
    matriz = eliminarColumna(matriz, 2);
    imprimirMatriz(matriz);

    return 0;
}