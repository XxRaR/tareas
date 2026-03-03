SHOW VARIABLES LIKE 'innodb_file_per_table';
CREATE DATABASE bd_aplicacion CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'usr_app'@'localhost' IDENTIFIED BY 'Password123!';
GRANT ALL PRIVILEGES ON bd_aplicacion.* TO 'usr_app'@'localhost';
FLUSH PRIVILEGES;

USE bd_aplicacion;
CREATE TABLE registros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255),
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

DELIMITER $$
CREATE procedure InsertarRegistros(IN num INT)
BEGIN
	declare i INT DEFAULT 1;
    WHILE i <= num DO
		insert into registros (descripcion) VALUES (concat('Registro ', i));
        SET i = i + 1;
	END WHILE;
END$$
DELIMITER ;
call InsertarRegistros(5000);

-- Consulta para obtener el tamaño total por base de datos en MB
SELECT 
    table_schema AS 'Base de Datos', 
    ROUND(SUM(data_length + index_length) / 1024 / 1024, 2) AS 'Tamaño (MB)', 
    ROUND(SUM(data_length) / 1024 / 1024, 2) AS 'Datos (MB)', 
    ROUND(SUM(index_length) / 1024 / 1024, 2) AS 'Índices (MB)' 
FROM information_schema.tables 
GROUP BY table_schema 
ORDER BY SUM(data_length + index_length) DESC;

SELECT 
    table_schema AS 'Base de Datos', 
    ROUND(SUM(data_length + index_length) / 1024 / 1024, 2) AS 'Tamaño (MB)'
FROM information_schema.tables 
WHERE table_schema = 'bd_aplicacion'
GROUP BY table_schema;

SHOW VARIABLES LIKE 'datadir';