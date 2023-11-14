# Proyecto2
## Integrantes  
Rodrigo Di Giulio  
Andres Perez

# Instrucciones  
## Contexto
Un aspecto interesante del funcionamiento de los sistemas operativos es, entre otras cosas, los mecanismos que este aplica para la administración de ciertos recursos, como por ejemplo la impresora. Usualmente, se colocan en una cola las peticiones de impresión que son recibidas por el SO, aunque esto no suele ser siempre la mejor opción, ni la más óptima. Lo anterior se evidencia cuando, por ejemplo, un documento es enviado por un usuario de especial prioridad, de modo que es muy recomendable permitir que se lleve a cabo tan pronto como la impresora esté disponible, aunque no haya llegado de primero. Otro caso podría ser que existan varios documentos en cola de apenas una única página y un documento de 100 páginas, en cuyo caso, podría ser razonable postergar el documento de gran tamaño, aun cuando no haya llegado de último a la cola.

Una alternativa que ha probado ser más eficiente es implementar una cola de prioridad. La figura que se presenta a continuación muestra en forma superficial el mecanismo:  
![image](https://github.com/RodrigoDiGiulio/Proyecto2/assets/145570964/8df60779-6b10-4457-b730-374399a52368)  
El TDA que se utiliza comúnmente para la implementación de una cola de prioridad es el montículo binario. Este es en esencia un árbol binario completamente lleno, con la posible excepción del nivel más bajo, el cual se llena  de izquierda a derecha. Un aspecto importante es que como los árboles binarios completos son tan regulares, se pueden implementar como un arreglo sin recurrir a los apuntadores. Para la implementación de este proyecto, deberá realizar una investigación documental sobre este TDA. 
## El problema:
Implemente en Java un programa que simule una cola de impresión utilizando el TDA  Montículo Binario.  Las primitivas de este tipo de datos abstracto son: insertar y eliminar_min.
A continuación los requerimientos de la solución:
1) El programa debe poseer una interfaz gráfica.
2) El programa permitirá agregar un usuario en la simulación, el cual tendrá un identificador (nombre de usuario) y un tipo. Cada tipo de usuario se le asignará un nivel de prioridad que podría ser utilizado para disminuir los tiempos de espera de los documentos que este envíe a la cola de impresión.
3) El programa deberá ofrecer la posibilidad de cargar a los usuarios desde un archivo “.CSV”
El formato del archivo debe ser como se indica a continuación: 
usuario, tipo
Jperez, prioridad_alta
m_himiot_alfaro, prioridad_media
yurdaneta_1, prioridad_baja
…
4) El sistema operativo ofrecerá la funcionalidad de eliminar un usuario, lo que implicará la eliminación de sus documentos creados, pero no de los que ya estén en la cola de impresión
5) Por cada usuario se pueden crear documentos. Un usuario puede tener varios documentos creados de manera que, en un momento dado, se puede seleccionar uno de ellos y mandarlo a imprimir. Por cada documento se conoce el nombre, tamaño y tipo.
6) Cuando un documento es enviado a la cola de impresión (cola de prioridad), es decir, se manda a imprimir, se crea un registro con los datos del documento y una etiqueta que guarda el tiempo transcurrido desde el inicio de la simulación. Cuando un documento se manda a imprimir, se puede indicar si es prioritario o no. Si no es prioritario, entonces el mismo es agregado a la cola de impresión en función del tiempo medido por el reloj. Si es prioritario, entonces la etiqueta del tiempo es alterada en función del tipo de usuario y, por lo tanto, agregado a la cola en función del valor obtenido tras la aplicación de la prioridad sobre la etiqueta de tiempo generada por el reloj. Tome en cuenta que el registro agregado (encolado) a la cola de impresión no tiene información sobre el propietario del documento. Agregar a la cola de impresión es equivalente a la primitiva insertar del TDA montículo binario, lo cual implica que se deben cumplir con las restricciones que se imponen en este TDA a la hora de insertar un elemento.
7) En todo momento se podrán observar los usuarios creados y por cada usuario se podrán observar sus documentos creados. Además, se podrán diferenciar aquellos que estén en la cola de impresión de los que aún no han sido enviados a la cola de impresión.
8) Un usuario podrá eliminar un documento que aún no ha sido enviado a la cola de impresión.
9) En todo momento se podrá observar la cola de impresión, para lo cual, se dispondrán de dos vistas, en la primera, se verá la cola como una simple secuencia de registros correspondientes a los documentos agregados a la cola de impresión y en la otra se podrá ver la cola como una estructura de árbol.
10) El sistema operativo necesitará de un reloj que mida el tiempo desde la inicialización de la simulación.
11) El sistema operativo deberá proporcionar funcionalidades que permita controlar la cola de impresión. Para eso se necesitarán las funciones:
  11.1) Liberar impresora: que simulará el avance en la cola de impresión, es decir, se toma el elemento que tiene la etiqueta de tiempo más pequeña, se desencola y se “imprime”. No pierda de vista que esta operación es equivalente a eliminar_min del Montículo binario.
  11.2) Eliminar un documento de la cola. Es decir, se especifica el usuario  y se procede a lo propio. A este respecto, se debe tomar en cuenta que no existe la primitiva ELIMINAR en los montículos binarios, por lo que el proceso de eliminación consistirá en cambiar la etiqueta de tiempo del registro correspondiente, de forma que sea el de menor prioridad en la cola, por lo tanto, debe ser movido al inicio de la cola y luego eliminado de la cola, pero sin haber sido impreso, lo que es equivalente a la primitiva eliminar_min de un montículo binario.
12) Un aspecto muy importante es que la cola de impresión no guarda información referente a los propietarios de los documentos que contiene, de manera que esto es una dificultad a la hora de mandar a eliminar un documento. La manera de solventar esto es utilizar una tabla de dispersión (puede consultar la siguiente fuente como punto de partida para comprender el mecanismo de las tablas de dispersión) en la que se registre la información de cada usuario que ha enviado un documento a la cola de impresión, el lugar que ocupa el documento en la cola (etiqueta de tiempo), así como la información propia del documento. Es imperativo que la búsqueda de un usuario tenga una complejidad lo más cercana a O(1), lo cual puede ser logrado precisamente con la aplicación de las tablas de dispersión. Por otra parte, es importante recalcar que no se puede buscar (recorrer) en un montículo, pues este debe cumplir con las restricciones de una cola, aunque adopte la estructura de un árbol.
## Consideraciones
- Los proyectos podrán ser sometidos a defensa, es decir, el facilitador convocará al equipo para una revisión.
- Los equipos de trabajo deberán utilizar GitHub para el control de versiones y facilitar el trabajo en equipo de manera remota. De esta forma, podrán comenzar a crear su portafolio de trabajos, elemento que puede ser importante a la hora de buscar trabajo. En el registro se deberá reflejar la participación activa y significativa de los integrantes.
- Los proyectos que no tengan interfaz gráfica, serán calificados con 0 (cero).
- Los proyectos que sean iguales o parecidos, serán calificados con 0 (cero).
- Los programas que “no corran”, serán calificados con 0 (cero).
- Los equipos pueden tener como máximo 3 personas.
## Criterios de evaluación
### Funcionalidad: Capacidad para proporcionar las funcionalidades que satisfacen las necesidades explícitas e implícitas bajo unas ciertas condiciones. (60%)
- Adecuación: El programa ofrece todas funcionalidades que respondan a las necesidades, tanto explícitas (contenidas en el documento descriptivo del proyecto) como implícitas; entendiendo como necesidades implícitas, aquellas que, no estando descritas en el documento, surgen como resultado de un concienzudo análisis del problema planteado y que aseguran el correcto funcionamiento del programa.
- Exactitud: El programa genera los resultados o efectos correctos o acordados, con el grado necesario de precisión.
### Fiabilidad: Capacidad para mantener un nivel especificado de prestaciones cuando se usa bajo ciertas condiciones.
- Madurez: El programa no presenta fallas originadas por errores de programación, análisis o diseño. (10%)
- Tolerancia a fallos: El programa responde adecuadamente al manejo inadecuado del usuario; es decir, mantiene su correcto funcionamiento aun cuando el usuario introduzca datos erróneos o manipule inadecuadamente las interfaces de usuario. (10%)
### Usabilidad: Capacidad del proyecto para ser entendido, aprendido, usado y al mismo tiempo, ser atractivo para el usuario, cuando se usa bajo condiciones específicas.
- Comprensibilidad: El programa ofrece una interfaz de fácil comprensión, facilitando su aprendizaje y correcta utilización. El programa emite mensajes de alerta cuando se introducen valores erróneos. Existen elementos informativos que indican al usuario como operar el programa. (5%)
- Capacidad de ser atractivo: El diseño de la interfaz de usuario, esto es: disposición de controles, esquema de colores, utilización de cajas de diálogo y demás elementos gráficos; es atractivo para el usuario. (5%)
### Eficiencia: Capacidad para proporcionar prestaciones apropiadas, relativas a la cantidad de recursos usados, bajo condiciones determinadas.
- Estructuras de datos: Utiliza eficientemente las estructuras de datos para la solución del problema. (10%)
## Requerimientos técnicos
1) Puede utilizar cualquier otra estructura auxiliar de ser necesario. Sin embargo, NO podrá utilizar librerías para la implementación de las estructuras de datos, solo podrá utilizar librerías para lo relativo a la representación gráfica del montículo binario.
2) El programa debe poder representar el montículo binario correspondiente de manera gráfica. 
3) La aplicación debe ofrecer una interfaz gráfica al usuario.
4) El programa debe poder cargar un archivo de texto para la lectura de datos. Para ello, es requerido el uso del componente JFileChooser.
5) Debe documentar el proyecto con Javadoc.
Junto al programa, cada equipo deberá presentar un Diagrama de clases (arquitectura detallada) que explique la solución obtenida. 
