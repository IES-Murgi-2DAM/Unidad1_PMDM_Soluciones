// La interfaz es un instrumento que nos permite definir un tipo de dato que puede tener múltiples implementaciones.
// En este caso, la interfaz `ResultadoLectura` representa el resultado de una lectura de datos, que puede ser un éxito,
// un error de red o un estado de carga.
sealed interface ResultadoLectura {
    data class Exito(val temperatura: Double, val humedad: Double) : ResultadoLectura
    data class ErrorRed(val codigoError: Int, val mensaje: String) : ResultadoLectura
    object Cargando : ResultadoLectura
}

// Para procesar la respuesta de la lectura, se define la función `procesarRespuesta` que toma un objeto de tipo
// `ResultadoLectura`. Con when se evalúa el tipo de resultado y se ejecuta la lógica correspondiente para cada caso.
// En este caso no necesitamos la cláusula else porque estamos manejando todos los posibles tipos de `ResultadoLectura`.
fun procesarRespuesta(resultado: ResultadoLectura) {
    when (resultado) {
        is ResultadoLectura.Exito -> {
            println("Lectura exitosa: Temperatura = ${resultado.temperatura}, Humedad = ${resultado.humedad}")
        }
        is ResultadoLectura.ErrorRed -> {
            println("Error de red: Código = ${resultado.codigoError}, Mensaje = ${resultado.mensaje}")
        }
        ResultadoLectura.Cargando -> {
            println("Cargando datos...")
        }
    }
}

// Vamos a probar que funciona
fun main() {
    val exito = ResultadoLectura.Exito(25.0, 60.0)
    val errorRed = ResultadoLectura.ErrorRed(404, "No encontrado")
    val cargando = ResultadoLectura.Cargando

    procesarRespuesta(exito)
    procesarRespuesta(errorRed)
    procesarRespuesta(cargando)
}