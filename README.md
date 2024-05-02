# reto2_GoogleScholarAPI
Documentación de uso de Google Scholar API 

Esta aplicación Java te permite consultar una API para obtener información sobre autores y sus artículos relacionados con una determinada consulta. Sigue estos pasos para ejecutar la aplicación:

1.- Clona este repositorio en tu máquina local o descarga los archivos como un archivo ZIP.

2- Asegúrate de tener instalado Java en tu sistema. Puedes descargar Java desde java.com. java 17

3.- Asegúrate de tener configurado Maven en tu sistema. Puedes descargar Maven desde maven.apache.org.
 
 3.1.- Ten instalado mysqlserver y ejecuta el script sql para crear  la bd y tabla necesaria
 3.2-  MODIFICAR EL usuario y password 
     private void guardarAutoresEnBD(List<ApiResponse.AuthorInfo> autores) {
        String url = "jdbc:mysql://localhost:3306/serpapi";
        String user = "root";
        String password = "root";


4.- Abre una terminal o línea de comandos y navega hasta el directorio raíz del proyecto.

5.- Compila el proyecto ejecutando el siguiente comando:mvn compile


//Antes de EJECUTAR el main 
     key: debes modificar el parametro "key" de tu cuenta de serpapi
     mauthors: el cual sera el argumento de busqueda

6.-Ejecuta el main

RESULTADOS::
La aplicación mostrará los 10 principales autores con la cantidad de artículos que fueron guardados en la BD ANTERRIORMENTE  y  ademas de realizar el registro en la BD  de los actuales y mostarlos.

