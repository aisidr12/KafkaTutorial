
🔶 Comandos Zookeeper y Kafka:

▶️ Iniciar Zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

▶️ Iniciar Kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties

▶️ Crea un nuevo topic en el servidor de kafka
.\bin\windows\kafka-topics.bat --create --topic {topic-name} --bootstrap-server {host}:9092

▶️ Decribir los detalles de un topic
.\bin\windows\kafka-topics.bat --describe --topic {topic-name} --bootstrap-server {host}:9092

▶️ Listar todos los topics que existen dentro del broker
.\bin\windows\kafka-topics.bat --list --bootstrap-server {host}:9092

▶️ Inicia una consola para ver mensajes de un topic específico
.\bin\windows\kafka-console-consumer.bat --topic {nombreTopic} --bootstrap-server {host}:9092

▶️ Inicia una consola para enviar mensajes a un topic específico
.\bin\windows\kafka-console-producer.bat --broker-list {host}:9092 --topic {topic-name}

-- Los comandos del docker compose -- 

Para entrar dentro del broker y crear tanto el producer y el consumer

docker exec -it kafka bash
Con ese docker compose creo un broker.
La manera de crear un producer con el topic seria este comando: 
kafka-console-producer --bootstrap-server kafka-broker-1:9092 --create --topic testing

Para leer en forma de consumer
kafka-console-consumer --bootstrap-server kafka-broker-1:9092 --topic testing --from-beginning