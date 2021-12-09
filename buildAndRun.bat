@echo off
call mvn clean package
call docker build -t br.edu.utfpr/aula4Atividade04 .
call docker rm -f aula4Atividade04
call docker run -d -p 9080:9080 -p 9443:9443 --name aula4Atividade04 br.edu.utfpr/aula4Atividade04