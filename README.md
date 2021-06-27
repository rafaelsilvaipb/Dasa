# Dasa
 API's de Laboratório e Exames da Dasa


# Tecnologias

- Java 8
- Spring Boot
- Lombok
- Intellij
- Maven
- H2
- JAX-RS
- Docker
- Heroku

# Banco de dados

Por se tratar de uma api de teste, usei o H2. Não é preciso instalar banco de dados local para a aplicação funcionar.

# GIT

Só fiz push depois de quase tudo terminado, e ainda deu problema que não consegui commitar separado, já foi direto como init commit. Relevem os comentários do Dockerfile, realmente estava me esforçando pra conseguir subir a aplicação no heroku como docker. Pode ter certeza que vou continuar tentando mesmo que não valha mais pro teste.


# Heroku

Caso queira acessar a aplicação, clique no link abaixo:

```
https://dasa-api.herokuapp.com/swagger-ui.html#/
```

# Como usar:

Assim que entrar na aplicação, verá as API's em lista. Vá no Controller Exams e execute a api Populate.
Essa API já vai gerar exames e laboratórios e vai associar um com o outro, para facilitar os testes.



# Build - Maven

Dentro do diretório raíz da aplicacao, execute o comando abaixo:

```
$ mvn clean install -DskipTests=true

```

Recomendo usar o -DskipTests=true, pois a aplicação tem muitos testes e eu coloquei pra ele restartar a cada novo testes por questão do banco de dados deixar sujeiras que atrapalhavam outros testes.


## Run Maven

Dentro do diretório raíz da aplicacao, execute o comando abaixo:

```
$ mvn spring-boot:run

```

## Executando os testes

Para executar os testes, basta executar o comando abaixo dentro do diretório da aplicação:

```
$ mvn test

```

## Executando a aplicação

Com a aplicação rodando, vá ao navegador e digite:

http://localhost:8080/swagger-ui.html#/




# Build Docker

Infelizmente não consegui subir a aplicação no hub.docker e nem no heroku como container, até criei um repositório lá, mas precisaria de mais tempo pra fazer funcionar.

Mas...para remediar, só seguir os passos abaixo que terá a aplicação rodadno num container também:

Dentro do diretório raiz da aplicação, execute o comando abaixo:

```
$ mvn clean install -DskipTests=true

```

Precisamos dessa etapa pra poder ter o jar do projeto.

Dentro ainda do diretório, digite o comando abaixo:


```
$ docker login

$ docker build -t dasa-api.jar .

```


Se tudo correr bem, você terá uma imagem docker pronto pra ser executada.

Para confirmar que gerou, rode o comando abaixo:


```

$ docker image ls

```



## Run Docker

Para executar a aplicação, basta fazer um docker run conforme abaixo.


```

& docker run -p 9000:8080 dasa-api.jar 

```


## Executando a aplicação Docker

Depois do run, vá no navegador e digite


```

http://localhost:9000/swagger-ui.html#/ 

```
