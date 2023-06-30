# Kafka

Spring Boot + Apache Kafka Application
=======================================

Step-1 : Download Zookeeper from below URL

   URL : http://mirrors.estointernet.in/apache/zookeeper/stable/

Step-2 : Download Apache Kafka from below URL

   URL : http://mirrors.estointernet.in/apache/kafka/

Step-3 : Set Path to ZOOKEEPER in Environment variables upto bin folder

Step-4 : Start Zookeeper server using below command from Kafka folder(zoopkeep server is running on portno::2181)

    Command : zookeeper-server-start.bat zookeeper.properties

Note: Above command will available in kafka/bin/windows folder

Note: zookeeper.properties file will be available in kafka/config folder. You can copy zookeeper.properties and server.properties files from kafka/config
      folder to kafka/bin/windows folder.

Step-5: Start Kafka Server using below command from Kakfa folder

    Command : kafka-server-start.bat server.properties

Note: server.properties file will be available in kafka/config folder (Copied to kafka/bin/windows folder)

Step-6 : Create Kakfa Topic using below command from kafka/bin/windows folder


Command :  kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic octbatch-ineuron-topic

Updated command : kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4

Step-7 : View created Topics using below command

      Command : kafka-topics.bat --list --zookeeper localhost:2181

     for updated version:- kafka-topics.bat --bootstrap-server=localhost:9092 --list

     for single - kafka-topics.bat --bootstrap-server=localhost:9092 --describe --topic test-topic


For Producer adding msg

    Command : kafka-console-producer.bat --broker-list localhost:9092 --topic mangesh-topic

For Consumer listen

      Command :  kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic mangesh-topic --from-beginning

For bulk message pushing using csv file

      Command : kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic <D:\Kafka\file.csv
