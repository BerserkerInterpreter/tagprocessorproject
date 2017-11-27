# tagprocessorproject

Un proyecto de tipo prueba elaborado para TechAndSolve.

El codigo de la aplicacion implementada en los frameworks react y redux se encuentra en la siguiente ubicacion: https://drive.google.com/open?id=1H1WjJkn2YHU7KKNAjv-cuiNa1d2LISvj

GUIA PARA LA EJECUCION DEL PROYECTO

El proyecto actual contiene dos componentes elaborados con el proposito de suplir una solucion al problema denominado "Procesamiento de tags". El problema "Procesamiento de Tags" solicita que a partir de unos datos introducidos por el usuario se genere una plantilla HTML con algunos tags personalizados creados a peticion del usuario.

Los componentes actuales implementados como solucion a la problematica actual son los siguientes:
  
  1. tag-processor-app: Componente implementado en los frameworks reactjs y reduxjs. Este componente contiene un formulario el cual 
      tiene dos campos html de tipo "textarea". El primero de los campos recibe un valor que puede ser como el siguiente: ### 
      trabajemos en html, a partir del anterior valor se genera una plantilla html con un tag personalizado basado en los
      los caracteres especiales de tipo numeral. El segundo de los campos tiene el proposito de mostrar una plantilla html    
      personalizada a partir de los datos suministrados por el usuario en el primer campo.
      
  2. tagprocessorproject: Componente creado utilizando el framework Spring Boot para la implementacion de microservicios     
      en java. Este componente contiene la logica para procesar los datos suministrados por el usuario y a partir de estos generar 
      una plantilla html personalizada con algunos tags especiales.
