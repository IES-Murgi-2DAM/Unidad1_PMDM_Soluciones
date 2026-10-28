/* Defino aquí los contenidos del punto dos, pero irían en sendos ficheros cada uno
 */
// PerfilDispositivo.kt
enum class PerfilDispositivo {
    NO_COMPATIBLE, COMPATIBLE_CON_LIMITACIONES, RECOMENDADO
}

// Dispositivo.kt
data class Dispositivo(
    val nombre: String,
    val vAndroid: Int,
    val ram: Int,
    val bateria: Int,
    val pantalla: Double,
    val bluetooth: Boolean,
    val datos: Boolean
) {
    // Podemos definir atributos de la clase para definir los valores mínimos
    // La información fija que nos da el enunciado suele ser buena candidata
    // para valores constantes
    companion object {
        const val MIN_ANDROID = 12
        const val MIN_RAM = 4
        const val MIN_BATERIA = 4000
        const val MIN_PANTALLA = 5.5
    }
}

fun Dispositivo.evaluarPerfil(): PerfilDispositivo =
    if (vAndroid < Dispositivo.MIN_ANDROID ||
        ram < Dispositivo.MIN_RAM ||
        bateria < Dispositivo.MIN_BATERIA ||
        pantalla < Dispositivo.MIN_PANTALLA ||
        !bluetooth
    ) {
        PerfilDispositivo.NO_COMPATIBLE
    } else if (bateria < 5000 || !datos) {
        PerfilDispositivo.COMPATIBLE_CON_LIMITACIONES
    } else {
        PerfilDispositivo.RECOMENDADO
    }

fun main() {
    val dispositivos: List<Dispositivo> = listOf(
        Dispositivo("Murgi Móvil A", 13, 3, 4500, 5.5, true, true),
        Dispositivo("Murgi Móvil B", 14, 6, 5000, 6.4, true, true),
        Dispositivo("Murgi Tablet C", 12, 4, 7000, 10.1, true, false),
        Dispositivo("Murgi Móvil D", 11, 8, 6000, 6.7, true, true)
    )

    val dispositivosAdmitidos = dispositivos.filter { it.evaluarPerfil() != PerfilDispositivo.NO_COMPATIBLE }
    println("Dispositivos admitidos: ${dispositivosAdmitidos.map { it.nombre }}")
    val dispositivosAgrupados = dispositivos.groupBy { it.evaluarPerfil() }
    println("Dispositivos agrupados por perfil:")
    // El groupby devuelve un Map<PerfilDispositivo, List<Dispositivo>>, por lo que podemos iterar sobre él y mostrar
    // los resultados
    dispositivosAgrupados.forEach { (dispositivo, dispositivos) ->
        println("\tPerfil: $dispositivo, Dispositivos: ${dispositivos.map { it.nombre }}")
    }
    val mensaje = dispositivos.firstOrNull { it.evaluarPerfil() == PerfilDispositivo.RECOMENDADO }
        ?.let { "El primer dispositivo recomendado es: ${it.nombre}" } ?: "No hay dispositivos recomendados"
    println(mensaje)
}