# API RESTful de creación de usuarios.
## Arquitectura
![image](https://github.com/user-attachments/assets/5cb16a58-ebbc-4fe3-827a-e65d4ed7cc5a)



## Proceso de construcción de aplicación

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/pgananc/users.git

   ```

2. Ir al directorio:

   ```bash
   cd users
3. Ejecutar:

   ```bash
    mvn clean install
    ```
## Ejecución
 ```bash
java -jar users-1.0.0.jar
 ```
## EndPoints
- Token Service:
  - API: http://localhost:8080/oauth/token
- Users Service:
  - API:http://localhost:8080/users
    
## Recursos para pruebas.

```bash
1. Script de base de datos: schema.sql y data.sql se ejecutan al inciar la aplicación.
2. EndPoints: users.postman_collection.json
3. Variable de entorno: users.postman_environment.json

```




**DEVELOPER:** Pablo Ganan

**Correo:** pabi1984@hotmail.com
