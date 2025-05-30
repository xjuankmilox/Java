microservicio 1 de Products 
microservicio 2 de Inventory
![image](https://github.com/user-attachments/assets/1cc29220-5939-4edd-b7c0-50f3bc311001)
configurar en properties base de datos baseejemplo con tablas Product , Inventory como se muestra acontinuacion

CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio long NOT NULL
);
CREATE TABLE Inventory (
    id INT PRIMARY KEY,
    quantity INT
);

Spring Security, queda por defecto user admin password aA@1234 por opcional localhost:8888 corre en puerto 8080

endpoints 
api/Products/ - toda la tabla 
api/Products/1 -id valor especifico

Se creo con Arquitectura Hexagonal para el escalamiento de los microservicios , con patron DDD para una mejor lectura
se divide cada proyecto como un tipo de microservicio para su facil escalamiento , se puede configurar un baleanceador para que pueda permitir mejores usos a la hora de realizar consultas y no se tenga que saturar
se puede incluir una base de datos redis para agilizar tiempos de consulta pero la principal seria mysql teniendola como prioritaria , se proyecta para que sea algo escalable y flexible , en donde se pueda ahorrar tiempo, la infraestructura 
se realizo separada para que se pueda compilar sin que haya que detener todo.
