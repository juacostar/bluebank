bluebank

# Ejecutar en entorno local
```
backend: ejecutar archivo BluebankApplication.java, para testing unitario ejecutar el archivo AccountControllerTest.java de la carpeta test.
Log: bluebank.log
frontend: en la carpeta de frontend ejecutar ng serve
docker: docker build -t name . ; docker run -p 80:8080 name
```
# Planteamiento y tecnologìas:
La idea era crear un modelo de base de datos con la cual se crea un ùnico nùmero de cuenta con una persona y un valor inicial. Dado esto, se crea una ùnica entidad denominada account que referìa a las cuentas creadas. Se crea una API Rest con Spring Boot y Hibernate. En el frontend se utilizò angular para forntend y HttpClient para interacciòn al backend. Se sube el backend a una instancia EC2 de linux en AWS mediante Docker. La base de datos fue elaborada en postgreSQL y desplegada en Heroku.

# Arquitectura
Contruido en una aplicaciòn cliente servidor en donde el cliente lleva la arquitectura modelo-vista-controlador que es la que provee angular, al igual que en l backend se implementò una similar mediante el modelo, repositorio y controlador de la entidad usada en el modelo (cuentas del banco en este caso).

# Tecnologìas
En este caso se utilizò Spring debido al requisito de JAVA para la aplicaciòn del empleo, ademàs de ser un backend muy versàtil para el uso de un ORM como Hibernate. En el frontend se usò angular como framework debido a su facilidad con la programaciòn asìncrona mediante los observables y debido a la integraciòn con el mòdulo de HttpClient para llamados a la API mediante mètodos HTTP de acuerdo a los requisitos de la prueba. Luego se utiliza docker para optimizar los recursos

# Mejoras
En dado caso de que hubiera màs tiempo, se podrìa mejorar estèticamente el frontend y en caso de manejar un gran volumen de usuarios mejorar el renidmiento mediante estrategias de escalbilidad horizontal, como utilizar una herramienta de almacenamiento de base de datos en cachè para optimizar los recursos de la base de datos, al igual que un mecanismo de Login mediante Token para mantener el atributo de calidad del software en el sistema planteado.
