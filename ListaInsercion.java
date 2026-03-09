import java.util.Scanner;

public class ListaInsercion {

    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo anterior;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    static class Lista {
        Nodo cabeza;
        Nodo cola;
        int tamaño;

        Lista() {
            cabeza = null;
            cola = null;
            tamaño = 0;
        }
        //recorrido 

        void mostrarIzquierdaDerecha() {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            Nodo actual = cabeza;
            while (actual != null) {
                System.out.print("[" + actual.dato + "]");
                if (actual.siguiente != null) System.out.print(" - ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        void mostrarDerechaIzquierda() {
            if (cola == null) { System.out.println("Lista vacia"); return; }
            Nodo actual = cola;
            while (actual != null) {
                System.out.print("[" + actual.dato + "]");
                if (actual.anterior != null) System.out.print(" - ");
                actual = actual.anterior;
            }
            System.out.println();
        }

        // Recorrido completo con indices (opcion de menu)
        void recorrer() {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            System.out.println("Recorrido izquierda -> derecha:");
            Nodo actual = cabeza;
            int pos = 1;
            while (actual != null) {
                System.out.println("  Posicion " + pos + ": [" + actual.dato + "]");
                actual = actual.siguiente;
                pos++;
            }
            System.out.println("Total de nodos: " + tamaño);
            System.out.println("\nRecorrido derecha -> izquierda:");
            actual = cola;
            pos = tamaño;
            while (actual != null) {
                System.out.println("  Posicion " + pos + ": [" + actual.dato + "]");
                actual = actual.anterior;
                pos--;
            }
        }

        //inserccion de nodos

        void insertarInicio(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (cabeza == null) { cabeza = cola = nuevo; }
            else { nuevo.siguiente = cabeza; cabeza.anterior = nuevo; cabeza = nuevo; }
            tamaño++;
        }

        void insertarFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (cola == null) { cabeza = cola = nuevo; }
            else { nuevo.anterior = cola; cola.siguiente = nuevo; cola = nuevo; }
            tamaño++;
        }

        void insertarPosicion(int pos, int dato) {
            if (pos <= 1) { insertarInicio(dato); return; }
            if (pos > tamaño) { insertarFinal(dato); return; }
            Nodo nuevo = new Nodo(dato);
            Nodo actual = cabeza;
            for (int i = 1; i < pos - 1; i++) actual = actual.siguiente;
            Nodo siguiente = actual.siguiente;
            nuevo.anterior = actual;
            nuevo.siguiente = siguiente;
            actual.siguiente = nuevo;
            if (siguiente != null) siguiente.anterior = nuevo;
            tamaño++;
        }

        void insertarAntes(int pos, int dato) { insertarPosicion(pos, dato); }
        void insertarDespues(int pos, int dato) { insertarPosicion(pos + 1, dato); }

        //Aqui se eliminan los nodos

        void eliminarInicio() {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            if (cabeza == cola) { cabeza = cola = null; }
            else { cabeza = cabeza.siguiente; cabeza.anterior = null; }
            tamaño--;
        }

        void eliminarFinal() {
            if (cola == null) { System.out.println("Lista vacia"); return; }
            if (cabeza == cola) { cabeza = cola = null; }
            else { cola = cola.anterior; cola.siguiente = null; }
            tamaño--;
        }

        void eliminarPosicion(int pos) {
            if (pos < 1 || pos > tamaño) { System.out.println("Posicion invalida"); return; }
            if (pos == 1) { eliminarInicio(); return; }
            if (pos == tamaño) { eliminarFinal(); return; }
            Nodo actual = cabeza;
            for (int i = 1; i < pos; i++) actual = actual.siguiente;
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
            tamaño--;
        }

        void eliminarAntes(int pos) {
            if (pos <= 1) { System.out.println("No existe nodo anterior"); return; }
            eliminarPosicion(pos - 1);
        }

        void eliminarDespues(int pos) {
            if (pos >= tamaño) { System.out.println("No existe nodo siguiente"); return; }
            eliminarPosicion(pos + 1);
        }

        void eliminarDato(int dato) {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            Nodo actual = cabeza;
            int pos = 1;
            while (actual != null && actual.dato != dato) { actual = actual.siguiente; pos++; }
            if (actual == null) { System.out.println("Dato no encontrado"); return; }
            eliminarPosicion(pos);
            System.out.println("Dato " + dato + " eliminado.");
        }

        void eliminarSecuencia(int dato) {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            int eliminados = 0;
            Nodo actual = cabeza;
            while (actual != null) {
                Nodo siguiente = actual.siguiente;
                if (actual.dato == dato) {
                    if (actual == cabeza && actual == cola) {
                        cabeza = cola = null;
                    } else if (actual == cabeza) {
                        cabeza = cabeza.siguiente;
                        cabeza.anterior = null;
                    } else if (actual == cola) {
                        cola = cola.anterior;
                        cola.siguiente = null;
                    } else {
                        actual.anterior.siguiente = actual.siguiente;
                        actual.siguiente.anterior = actual.anterior;
                    }
                    tamaño--;
                    eliminados++;
                }
                actual = siguiente;
            }
            if (eliminados == 0) System.out.println("Dato " + dato + " NO encontrado.");
            else System.out.println(eliminados + " nodo(s) con dato " + dato + " eliminado(s).");
        }

        // las busquedas 

        void busquedaSecuencial(int dato) {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            Nodo actual = cabeza;
            int pos = 1;
            boolean encontrado = false;
            int repeticiones = 0;
            while (actual != null) {
                if (actual.dato == dato) {
                    System.out.println("  Dato " + dato + " encontrado en posicion " + pos);
                    encontrado = true;
                    repeticiones++;
                }
                actual = actual.siguiente; pos++;
            }
            if (!encontrado) System.out.println("  Dato " + dato + " NO encontrado.");
            else System.out.println("  Total de repeticiones: " + repeticiones);
        }

        void busquedaBinaria(int dato) {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            int[] arreglo = new int[tamaño];
            Nodo actual = cabeza;
            for (int i = 0; i < tamaño; i++) { arreglo[i] = actual.dato; actual = actual.siguiente; }
            int izquierda = 0, derecha = tamaño - 1;
            while (izquierda <= derecha) {
                int medio = (izquierda + derecha) / 2;
                if (arreglo[medio] == dato) {
                    System.out.println("  Busqueda binaria: dato " + dato + " encontrado en posicion " + (medio + 1));
                    return;
                } else if (arreglo[medio] < dato) izquierda = medio + 1;
                else derecha = medio - 1;
            }
            System.out.println("  Busqueda binaria: dato " + dato + " NO encontrado.");
        }

       // Aqui se ordena todo

        private int[] aArreglo() {
            int[] arr = new int[tamaño];
            Nodo actual = cabeza;
            for (int i = 0; i < tamaño; i++) { arr[i] = actual.dato; actual = actual.siguiente; }
            return arr;
        }

        private void desdeArreglo(int[] arr) {
            cabeza = cola = null; tamaño = 0;
            for (int v : arr) insertarFinal(v);
        }

        // 1. Intercambio directo (burbuja)
        void ordenarBurbuja() {
            int[] arr = aArreglo();
            for (int i = 0; i < arr.length - 1; i++)
                for (int j = 0; j < arr.length - 1 - i; j++)
                    if (arr[j] > arr[j + 1]) {
                        int t = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = t;
                    }
            desdeArreglo(arr);
            System.out.println("Lista ordenada con Intercambio Directo (Burbuja).");
        }

        // 2. Seleccion
        void ordenarSeleccion() {
            int[] arr = aArreglo();
            for (int i = 0; i < arr.length - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < arr.length; j++)
                    if (arr[j] < arr[minIdx]) minIdx = j;
                int t = arr[minIdx]; arr[minIdx] = arr[i]; arr[i] = t;
            }
            desdeArreglo(arr);
            System.out.println("Lista ordenada con Seleccion.");
        }

        // 3. Insercion
        void ordenarInsercion() {
            int[] arr = aArreglo();
            for (int i = 1; i < arr.length; i++) {
                int clave = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > clave) { arr[j + 1] = arr[j]; j--; }
                arr[j + 1] = clave;
            }
            desdeArreglo(arr);
            System.out.println("Lista ordenada con Insercion.");
        }

        // 4. Quicksort
        void ordenarQuicksort() {
            int[] arr = aArreglo();
            quicksort(arr, 0, arr.length - 1);
            desdeArreglo(arr);
            System.out.println("Lista ordenada con Quicksort.");
        }

        private void quicksort(int[] arr, int bajo, int alto) {
            if (bajo < alto) {
                int pi = particion(arr, bajo, alto);
                quicksort(arr, bajo, pi - 1);
                quicksort(arr, pi + 1, alto);
            }
        }

        private int particion(int[] arr, int bajo, int alto) {
            int pivote = arr[alto];
            int i = bajo - 1;
            for (int j = bajo; j < alto; j++) {
                if (arr[j] <= pivote) {
                    i++;
                    int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
                }
            }
            int t = arr[i + 1]; arr[i + 1] = arr[alto]; arr[alto] = t;
            return i + 1;
        }

    

        // Modifica el primer nodo que tenga el valor buscado
        void modificarPorValor(int valorBuscado, int nuevoValor) {
            if (cabeza == null) { System.out.println("Lista vacia"); return; }
            Nodo actual = cabeza;
            while (actual != null) {
                if (actual.dato == valorBuscado) {
                    actual.dato = nuevoValor;
                    System.out.println("Valor " + valorBuscado + " modificado a " + nuevoValor + ".");
                    return;
                }
                actual = actual.siguiente;
            }
            System.out.println("Valor " + valorBuscado + " NO encontrado.");
        }

        // Modifica el nodo en la posicion indicada
        void modificarPorPosicion(int pos, int nuevoValor) {
            if (pos < 1 || pos > tamaño) { System.out.println("Posicion invalida"); return; }
            Nodo actual = cabeza;
            for (int i = 1; i < pos; i++) actual = actual.siguiente;
            int anterior = actual.dato;
            actual.dato = nuevoValor;
            System.out.println("Posicion " + pos + ": " + anterior + " -> " + nuevoValor + ".");
        }


        void invertir() {
            if (tamaño <= 1) return;
            Nodo actual = cabeza;
            Nodo temp = null;
            cola = cabeza;
            while (actual != null) {
                temp = actual.anterior;
                actual.anterior = actual.siguiente;
                actual.siguiente = temp;
                actual = actual.anterior;
            }
            if (temp != null) cabeza = temp.anterior;
            System.out.println("Lista invertida.");
        }

        void eliminarDuplicados() {
            if (tamaño <= 1) return;
            Nodo actual = cabeza;
            int eliminados = 0;
            while (actual != null) {
                Nodo comparar = actual.siguiente;
                while (comparar != null) {
                    Nodo siguiente = comparar.siguiente;
                    if (comparar.dato == actual.dato) {
                        if (comparar == cola) {
                            cola = cola.anterior;
                            cola.siguiente = null;
                        } else {
                            comparar.anterior.siguiente = comparar.siguiente;
                            comparar.siguiente.anterior = comparar.anterior;
                        }
                        tamaño--;
                        eliminados++;
                    }
                    comparar = siguiente;
                }
                actual = actual.siguiente;
            }
            System.out.println(eliminados + " duplicado(s) eliminado(s).");
        }
    }

    
    // Aqui estan los main y los menus
    

    static Scanner sc = new Scanner(System.in);
    static Lista lista = new Lista();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Insercion");
            System.out.println("2. Eliminacion");
            System.out.println("3. Busqueda");
            System.out.println("4. Ordenamiento");
            System.out.println("5. Modificacion");
            System.out.println("6. Recorrido");
            System.out.println("7. Utilidades (invertir / duplicados)");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> menuInsercion();
                case 2 -> menuEliminacion();
                case 3 -> menuBusqueda();
                case 4 -> menuOrdenamiento();
                case 5 -> menuModificacion();
                case 6 -> menuRecorrido();
                case 7 -> menuUtilidades();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    static void mostrarLista() {
        System.out.println("\n¿Como desea ver la lista?");
        System.out.println("1. Izquierda a derecha");
        System.out.println("2. Derecha a izquierda");
        System.out.print("Opcion: ");
        int dir = leerEntero();
        System.out.print("Lista: ");
        if (dir == 2) lista.mostrarDerechaIzquierda();
        else lista.mostrarIzquierdaDerecha();
    }

    static void menuInsercion() {
        System.out.println("\n--- INSERCION ---");
        System.out.println("1. Al inicio");
        System.out.println("2. Al final");
        System.out.println("3. Por posicion");
        System.out.println("4. Antes de una posicion");
        System.out.println("5. Despues de una posicion");
        System.out.print("Opcion: ");
        int op = leerEntero();

        if (op < 1 || op > 5) { System.out.println("Opcion no valida."); return; }

        System.out.print("¿Cuantos datos desea insertar? ");
        int cantidad = leerEntero();

        int posRef = 0;
        if (op == 3 || op == 4 || op == 5) {
            String etiqueta = op == 3 ? "Posicion: " : op == 4 ? "Antes de la posicion: " : "Despues de la posicion: ";
            System.out.print(etiqueta);
            posRef = leerEntero();
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Dato " + (i + 1) + ": ");
            int dato = leerEntero();
            switch (op) {
                case 1 -> lista.insertarInicio(dato);
                case 2 -> lista.insertarFinal(dato);
                case 3 -> { lista.insertarPosicion(posRef, dato); posRef++; }
                case 4 -> { lista.insertarAntes(posRef, dato); posRef++; }
                case 5 -> { lista.insertarDespues(posRef, dato); posRef++; }
            }
        }

        mostrarLista();
    }

    static void menuEliminacion() {
        System.out.println("\n--- ELIMINACION ---");
        System.out.println("1. Al inicio");
        System.out.println("2. Al final");
        System.out.println("3. Por posicion");
        System.out.println("4. Antes de una posicion");
        System.out.println("5. Despues de una posicion");
        System.out.println("6. Por dato (primera ocurrencia)");
        System.out.println("7. Por secuencia (todas las ocurrencias del dato)");
        System.out.print("Opcion: ");
        int op = leerEntero();

        switch (op) {
            case 1 -> lista.eliminarInicio();
            case 2 -> lista.eliminarFinal();
            case 3 -> { System.out.print("Posicion: "); lista.eliminarPosicion(leerEntero()); }
            case 4 -> { System.out.print("Posicion de referencia: "); lista.eliminarAntes(leerEntero()); }
            case 5 -> { System.out.print("Posicion de referencia: "); lista.eliminarDespues(leerEntero()); }
            case 6 -> { System.out.print("Dato a eliminar: "); lista.eliminarDato(leerEntero()); }
            case 7 -> { System.out.print("Dato a eliminar (todas las ocurrencias): "); lista.eliminarSecuencia(leerEntero()); }
            default -> System.out.println("Opcion no valida.");
        }

        mostrarLista();
    }

    static void menuBusqueda() {
        System.out.println("\n--- BUSQUEDA ---");
        System.out.println("1. Busqueda secuencial (existencia + repeticiones, cualquier lista)");
        System.out.println("2. Busqueda binaria   (la lista DEBE estar ordenada)");
        System.out.print("Opcion: ");
        int op = leerEntero();
        System.out.print("Dato a buscar: ");
        int dato = leerEntero();
        switch (op) {
            case 1 -> lista.busquedaSecuencial(dato);
            case 2 -> lista.busquedaBinaria(dato);
            default -> System.out.println("Opcion no valida.");
        }
        mostrarLista();
    }

    //  Menu de ordenamiento con los 4 algoritmos requeridos
    static void menuOrdenamiento() {
        System.out.println("\n--- ORDENAMIENTO ---");
        System.out.println("1. Intercambio Directo (Burbuja)");
        System.out.println("2. Seleccion");
        System.out.println("3. Insercion");
        System.out.println("4. Quicksort");
        System.out.print("Opcion: ");
        int op = leerEntero();
        switch (op) {
            case 1 -> lista.ordenarBurbuja();
            case 2 -> lista.ordenarSeleccion();
            case 3 -> lista.ordenarInsercion();
            case 4 -> lista.ordenarQuicksort();
            default -> System.out.println("Opcion no valida.");
        }
        mostrarLista();
    }

    //  Menu de modificacion (por valor y por posicion)
    static void menuModificacion() {
        System.out.println("\n--- MODIFICACION ---");
        System.out.println("1. Modificar por valor/dato (primera ocurrencia)");
        System.out.println("2. Modificar por posicion");
        System.out.print("Opcion: ");
        int op = leerEntero();
        switch (op) {
            case 1 -> {
                System.out.print("Valor a buscar: ");
                int viejo = leerEntero();
                System.out.print("Nuevo valor: ");
                int nuevo = leerEntero();
                lista.modificarPorValor(viejo, nuevo);
            }
            case 2 -> {
                System.out.print("Posicion a modificar: ");
                int pos = leerEntero();
                System.out.print("Nuevo valor: ");
                int nuevo = leerEntero();
                lista.modificarPorPosicion(pos, nuevo);
            }
            default -> System.out.println("Opcion no valida.");
        }
        mostrarLista();
    }

    // Recorrido como opcion explicita del menu principal
    static void menuRecorrido() {
        System.out.println("\n--- RECORRIDO ---");
        lista.recorrer();
    }

    static void menuUtilidades() {
        System.out.println("\n--- UTILIDADES ---");
        System.out.println("1. Invertir lista");
        System.out.println("2. Eliminar duplicados");
        System.out.print("Opcion: ");
        int op = leerEntero();
        switch (op) {
            case 1 -> lista.invertir();
            case 2 -> lista.eliminarDuplicados();
            default -> System.out.println("Opcion no valida.");
        }
        mostrarLista();
    }

    static int leerEntero() {
        while (!sc.hasNextInt()) { System.out.print("Ingresa un numero: "); sc.next(); }
        return sc.nextInt();
    }
}