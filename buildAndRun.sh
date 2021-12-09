#!/bin/sh
mvn clean package && docker build -t br.edu.utfpr/aula4Atividade04 .
docker rm -f aula4Atividade04 || true && docker run -d -p 9080:9080 -p 9443:9443 --name aula4Atividade04 br.edu.utfpr/aula4Atividade04