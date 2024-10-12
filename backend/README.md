<h1 align=center>Back End E-Commerce de Albums</h1>

### FUNCIONALIDADES

- Implementação de Autenticação JWT para garantia de segurança e integridade das comunicações.
- Arquitetura MVC para escalabilidade e flexibilidade operacional.
- Personalização na validação e tratamento de exceções, assegurando robustez e confiabilidade no sistema.
- Persistência de dados usando PostgresSQL.
- Normalização de dados através de Data Objects Transactions para padronização e consistência de informações.
- Utilização do Swagger UI para uma documentação complementar detalhada e acessível.
- Integração com a API do Spotify para permitir que os usuários busquem álbuns de artistas diretamente pela plataforma.
- Testes unitários para assegurar o correto funcionamento das funcionalidades integradas, incluindo as interações com a API do Spotify.



### REQUISITOS

Para executar nossa plataforma localmente, é necessário ter em seu dispositivo computacional:

  1. Certifique-se de que o Docker está instalado para gerenciar containers e ambientes virtualizados. [Baixe aqui](https://www.docker.com/products/docker-desktop/)

  2. Certifique-se de que instalou alguma IDE em sua máquina. [Baixe aqui](https://www.jetbrains.com/idea/download/?section=windows)

#
### DOWNLOAD DO PROJETO

Baixe o projeto em seu computador através do comando:

```bash
git clone https://github.com/bc-fullstack-05/anthony_chukwudi_ndubisi.git
```

**ou**

1. Clique em `<> Code`.
2. Faça o download do arquivo ZIP.
3. Abra o seu explorador de arquivos na localização da instalação.
4. Extraia o arquivo ZIP.

#
### EXECUÇÃO

Sequência de execução do projeto:

1. Acesse a IDE na qual deseja executar o projeto.
2. Clique em "Abrir um projeto já existente".
3. Selecione o local da pasta descompactada do projeto.
4. Confirme a seleção.
5. Localize e clique no botão "Terminal" localizado na parte inferior da IDE.
6. Execute os comandos na respectiva ordem:

```bash
docker-compose -f docker-compose.yml -f docker-compose.dev.yml build
```
   
```bash
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up
```
8. Localize e clique no botão "Play" (verde) localizado na parte superior da IDE.
9. Um terminal integrado será aberto.
10. Aguarde a instalação das dependências do projeto.
11. Após a conclusão das instalações, o projeto será executado.

#
### VISUALIZAÇÃO

Abra a janela do seu navegador web padrão e acesse o seguinte endereço pela barra de pesquisa:

```bash
http://localhost:8080/api/swagger-ui/index.html
```


#
### TECNOLOGIAS

![Java](https://img.shields.io/badge/Java-0D1117?style=for-the-badge&logo=openjdk&logoColor=white&labelColor=0D1117)&nbsp;
![Spring](https://img.shields.io/badge/Spring-0D1117?style=for-the-badge&logo=spring&logoColor=107C10&labelColor=0D1117)&nbsp;
![SpringBoot](https://img.shields.io/badge/Spring_Boot-0D1117?style=for-the-badge&logo=springboot&logoColor=239120&labelColor=0D1117)&nbsp;
![SpringSecurity](https://img.shields.io/badge/Spring_Security-0D1117?style=for-the-badge&logo=Spring-Security&logoColor=239120&labelColor=0D1117)&nbsp;
![JWT](https://img.shields.io/badge/JWT-0D1117?style=for-the-badge&logo=JSON%20web%20tokens&labelColor=0D1117)&nbsp;
![Hibernate](https://img.shields.io/badge/Hibernate-0D1117?style=for-the-badge&logo=Hibernate&logoColor=239120&labelColor=0D1117)&nbsp;
![Swagger](https://img.shields.io/badge/Swagger-0D1117?style=for-the-badge&logo=Swagger&logoColor=85EA2D&labelColor=0D1117)&nbsp;
![Maven](https://img.shields.io/badge/apache_maven-0D1117?style=for-the-badge&logo=apachemaven&logoColor=E34F26&labelColor=0D1117)&nbsp;
![POSTGRESQL](https://img.shields.io/badge/PostgreSQL-0D1117?style=for-the-badge&logo=postgresql&labelColor=0D1117)&nbsp;
![JUnit5](https://img.shields.io/badge/Junit5-0D1117?style=for-the-badge&logo=junit5&logoColor=25A162&labelColor=0D1117)&nbsp;
![Docker](https://img.shields.io/badge/Docker-0D1117?style=for-the-badge&logo=docker&logoColor=2496ED&labelColor=0D1117)


#
### DOMINIO DA API

```
http://localhost:8080/api
```

#
### DOCUMENTACAO DA API

```
http://localhost:8080/api/swagger-ui/index.html
```

