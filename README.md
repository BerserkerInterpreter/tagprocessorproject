# tagprocessorproject

Un proyecto de tipo prueba elaborado para TechAndSolve.

DESCRIPCION DEL PROYECTO ACTUAL

El proyecto actual contiene dos componentes elaborados con el proposito de suplir una solucion al problema denominado "Procesamiento de tags". El problema "Procesamiento de Tags" solicita que a partir de unos datos introducidos por el usuario se genere una plantilla HTML con algunos tags personalizados creados a peticion del usuario.

Los componentes actuales implementados como solucion a la problematica actual son los siguientes:
  
  1. tag-processor-app: Componente implementado en los frameworks reactjs y reduxjs. Este componente contiene un formulario el    
      cual tiene dos campos html de tipo "textarea". El primero de los campos recibe un valor que puede ser como el siguiente:
      "###" trabajemos en html, a partir del anterior valor se genera una plantilla html con un tag personalizado basado en los
      los caracteres especiales de tipo numeral. El segundo de los campos tiene el proposito de mostrar una plantilla html    
      personalizada a partir de los datos suministrados por el usuario en el primer campo.
      
  2. tagprocessorproject: Componente creado utilizando el framework Spring Boot para la implementacion de microservicios     
      en java. Este componente contiene la logica para procesar los datos suministrados por el usuario y a partir de estos  
      generar una plantilla html personalizada con algunos tags especiales.
      

GUIA PARA LA EJECUCION DEL PROYECTO

 * PRIMERA POSIBILIDAD: EJECUCION DE LOS WORKSPACES
    
    1. Primero descargamos el componente "tag-processor-app" de la siguiente URL: 
        https://drive.google.com/open?id=1H1WjJkn2YHU7KKNAjv-cuiNa1d2LISvj. Como consejo: Google analiza el componente 
        en busca de virus. El cargado de la pagina y cuadro de dialogo de descarga se puede demorar un poco, se aconseja esperar  
        por un momento.
    2. Descomprimimos el componente descargado.
    3. Ejecutamos la siguiente sentencia en una consola de comandos estando ubicados directamente en la carpeta o directorio base   
        donde se encuentra ubicado el proyecto: "npm start". La anterior sentencia lo que hace es inicializar el servidor npm 
        para ejecutar la aplicacion en un navegador web y mostrar el resultado actual. 
    4. Descargamos el componente tagprocessor. Este componente es un workspace en eclipse que contiene el microservicio 
        implementado para realizar la logica de procesamiento de los datos suministrados por el usuario: 
        https://drive.google.com/open?id=1n23FoaFykJ4aCgjk5Pw8YqnVAkXP1YDj
    5. Inicializamos el workspace en eclipse.
    6. Instalamos el plugin de Spring en eclipse: Spring Tools (aka Spring IDE and Spring Tool Suite) 3.9.1 RELEASE. Si no 
        tenemos instalado el plugin denominado "MAVEN (JAVA EE) Integration for eclipse WTP (Luna/Mars) 1.2.0" tambien lo 
        instalamos.
    8. Click derecho sobre la ubicacion del proyecto en el gestor de proyectos de eclipse.
    9. Nos paramos sobre la opcion "Run as".
    10. Ejecutamos la opcion denominada: "Spring Boot App". El componente se encuentra desplegado por defecto en la siguiente   
          URL: localhost:8080/api/procesartag
    12. Procedemos a rellenar el formulario y a enviarlo para ver el resultado de la ejecucion.
   
 * SEGUNDA POSIBILIDAD: EJECUCION DEL EJECUTABLE DEL MICROSERVICIO
 
    1. Realizamos la guia anterior pero con una diferencia, utilizaremos el ejecutable del componente tagprocessorproject 
       (microservicio implementado en java). Descargamos el componente tagprocessorproject de la siguiente URL:
       https://drive.google.com/open?id=10jmKhHhHZzKGRQsrV51CKkybl_3YdYuR
    2. Descomprimimos el componente descargado
    3. En la carpeta base del componente descargado ejecutamos la siguiente sentencia: java -jar tagprocessorproject.jar. El 
        componte se desplegara en la siguiente ruta: http://localhost:8080/api/procesartag.
