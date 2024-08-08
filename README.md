# Projeto ChurchManager

Este projeto é composto por um backend em Spring Boot e um frontend em Kotlin Compose Multiplatform. Abaixo estão as instruções para iniciar ambos os aplicativos.

## Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em sua máquina:

- [Java 17](https://adoptium.net/?variant=openjdk17)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- [Maven](https://maven.apache.org/)
- [Gradle](https://gradle.org/)

## Instruções para Iniciar o Projeto

### 1. Backend

1. Clone o repositório do projeto:
    ```sh
    git clone https://github.com/DevAzeredo/ChurchManager-KMP-SpringBoot.git
    cd ChurchManager-KMP-SpringBoot/backend
    ```

2. Compile e empacote o backend usando Maven:
    ```sh
    ./mvnw clean package
    ```

### 2. Frontend

1. Navegue até o diretório do frontend:
    ```sh
    cd ../frontend
    ```

2. Compile o frontend usando Gradle:
    ```sh
    ./gradlew build
    ```

### 3. Docker

1. Volte para o diretório raiz do projeto:
    ```sh
    cd ..
    ```

2. Construa as imagens Docker:
    ```sh
    docker-compose build
    ```

3. Inicie os contêineres:
    ```sh
    docker-compose up
    ```

4. Acesse a aplicação em seu navegador:
    ```
    http://localhost:8080
    ```
