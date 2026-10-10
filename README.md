# Unidad 1 PMDM

En este repositorio iré resolviendo los ejercicios de la Unidad 1 de PMDM

## AC101
He generado la versión más corta con el código que se ha dado en clase hasta ahora

## AC102
He generado una versión acortada del código que intenta probar varios casos posibles

## AC103
He generado la versión más cercana a lo que indican los apuntes añadiendo comentarios explicativos

### Requisito 1
La clase no es incompatible con Android por estar escrita en Java, pero tiene aspectos que pueden hacer que una aplicación móvil sea menos fiable:
1. El estado del sensor no está limitado a opciones válidas. estado es un String, así que se le puede asignar cualquier texto, incluso uno incorrecto o escrito de otra forma, como "OK" y "ok". La aplicación podría no reconocerlo y tratar mal la lectura. Un tipo con opciones definidas, como un enum, evitaría muchos de esos errores.
2. Los datos se pueden modificar sin validarlos. Aunque los campos son private, los métodos setEstado y setUltimaLectura son públicos y aceptan cualquier valor. Por ejemplo, se podría guardar un estado desconocido o una lectura que no tenga sentido para la aplicación. En una app móvil, esos datos incorrectos pueden provocar pantallas o resultados inesperados.
3. Un valor nulo podría causar un fallo en tiempo de ejecución. Java permite que idSensor sea null, y el método equals llama a idSensor.equals(...). Si se compara un sensor cuyo identificador es nulo, puede producirse una NullPointerException y cerrarse la operación que estaba realizando la app.
Los campos no tienen visibilidad pública: son privados. El problema relacionado con la visibilidad es que algunos métodos públicos permiten cambiar ciertos datos sin comprobar que sean válidos. La falta de gestión de excepciones no la presentaría como un inconveniente propio de esta clase, ya que su función principal es almacenar datos y no realiza operaciones que necesariamente deban lanzar excepciones.

## AP104
He generado un ejemplo de cómo usar la lógica funcional para operar con datos. He intentado dejar usos de formas más avanzadas de hacer las cosas para que los estudiantes puedan ver cómo se puede hacer de manera más eficiente y elegante. He añadido comentarios explicativos para que se entienda mejor el código.

Ejemplo de salida:
```
Lecturas válidas: [23.5, 36.8, 41.2, 18.0, 35.5]
Lecturas formateadas: [Lectura: 23,50 ºC, Lectura: 36,80 ºC, Lectura: 41,20 ºC, Lectura: 18,00 ºC, Lectura: 35,50 ºC]
Media de lecturas válidas: 31,00 ºC
Lectura crítica: Lectura crítica: 41,20 ºC
```

## AR105
He resuelto la parte del ejercicio de Kotlin mostrando cómo se puede hacer de manera más eficiente y elegante. He añadido comentarios explicativos para que se entienda mejor el código.

Las respuestas a la pregunta de reflexión serían:
1. **Java, switch y when:**
   En Java, un switch puede no tener un case para una nueva subclase y, si no hay un default, el programa puede no hacer nada para ese caso. Así, la nueva situación queda sin gestionar. En Kotlin, si usamos una sealed interface y un when sin else, el compilador comprueba que se contemplen todos los estados conocidos. Si añadimos otro estado y olvidamos tratarlo, nos avisará para que completemos el when. Si ponemos else, dejamos de recibir esa comprobación concreta.
2. **Estados y excepciones en una app móvil:**
   La carga, una lectura correcta y un fallo de conexión son situaciones que la aplicación puede esperar. Si las representamos como estados, el código puede decidir qué mostrar en cada caso: por ejemplo, un indicador de carga, el resultado o un mensaje con opción para reintentar. En cambio, usar excepciones para gestionar continuamente situaciones normales puede hacer que el flujo sea más difícil de entender. Las excepciones siguen siendo útiles para fallos excepcionales, pero deben gestionarse cuando corresponda: un error no controlado puede cerrar la app. El modelado de estados no garantiza por sí solo que la app sea fluida, pero facilita responder a cada situación sin dejarla sin tratar.