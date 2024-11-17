data class Tarea(val nombre: String, val prioridad: Int)

class NodoArbol(val tarea: Tarea){
    var izquierdo: NodoArbol? = null
    var derecho: NodoArbol? = null
}
    class ArbolTareas{
        private var raiz: NodoArbol? = null
        
        fun insertar(tarea: Tarea){
            raiz = insertarRecursivo(raiz, tarea)
        }

        private fun insertarRecursivo(actual: NodoArbol?, tarea: Tarea): NodoArbol?{
            if(actual==null){
                return NodoArbol(tarea)
            }

            if (tarea.prioridad < actual.tarea.prioridad){
                actual.izquierdo = insertarRecursivo(actual.izquierdo, tarea)
        } else {
            actual.derecho = insertarRecursivo(actual.derecho, tarea)
        }
        return actual
    }

    fun mostrarEnOrden(){
        mostrarEnOrdenRecursivo(raiz)
    }
    
    private fun mostrarEnOrdenRecursivo(nodo: NodoArbol?){
        if(nodo != null){
            mostrarEnOrdenRecursivo(nodo.izquierdo)
            println("Tarea: ${nodo.tarea.nombre}, Prioridad: ${nodo.tarea.prioridad}")
            mostrarEnOrdenRecursivo(nodo.derecho)
        }
    }
}

//Gestion de tareas
class CustomList<T> {
    private val elements = mutableListOf<T>()

    fun add(element: T): Boolean {
        return if (element !in elements){
            elements.add(element)
            println("Tareas agregadas exitosamente")
            true 
        } else {
            println("La tarea ya existe")
            false
        }
    }

    fun remove(element: T): Boolean{
        return if (element in elements){
            elements.remove(element)
            println("Tarea eliminada!")
            true
        } else {
            println("Tarea no se encontro en la lista")
            false
        }
    }

    fun showAll(){
        if (elements.isEmpty()){
            println("Lista de tareas vacia")
        }else{
            println("Lista de tareas:")
            elements.forEach {println(" -$it")}
        }
    }

    fun size(): Int{
        return elements.size
    }

    fun getAll(): List<T>{
        return elements
    }
}

//Main 
fun main(args: Array<String>) {
    val listaTareas = CustomList<Tarea>()
    val arbolTareas = ArbolTareas()
    var opcion: Int

    do{
        println("\nGestor de tareas")
        println("1. Agregar tareas")
        println("2. Eliminar tareas")
        println("3. Ver lista de tareas")
        println("4. Ver lista de tareas por prioridad")
        println("5. Salir")
        println("Selecione una opcion")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion){
            1->{
                println("Ingrese el nombre tarea")
                val nombre = readLine()?: ""
                println("Ingrese la prioridad de la tarea: ")
                val prioridad = readLine()?.toIntOrNull()?: 0
                val nuevaTarea = Tarea(nombre, prioridad)
                listaTareas.add(nuevaTarea)
                arbolTareas.insertar(nuevaTarea)
            }
            2->{
                if (listaTareas.size() > 0){
                    println("Selecione la tarea que desea eliminar")
                    listaTareas.showAll()
                    println("Ingrese el nombre de la tarea:")
                    val nombre = readLine()?: ""
                    val eliminarTarea = listaTareas.getAll().find {it.nombre == nombre}
                    if(eliminarTarea != null){
                        listaTareas.remove(eliminarTarea)
                    }
                }else{
                    println("La tarea no esta en la lista")
                }
            }
            3->{
                listaTareas.showAll()
            }
            4->{
               println("Lista de tareas ordenadas por prioridad")
               arbolTareas.mostrarEnOrden()
            }
            5->println("Saliendo del programa...")

            else -> println("Opcion no valida, ingrese una opcion valida.")
        }
    } while (opcion != 5)
}