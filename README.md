# teste-planisa
API de teste da Planisa fornecendo dados sobre a COVID-19

Neste projeto foi desenvolvida uma API que fornece alguns dados comparativos sobre a quantidade de casos e mortes por COVID-19 entre os anos de 2020 a 2023. Esta API chama a API externa cujo endereço é [https://api-ninjas.com/api/covid19](https://api-ninjas.com/api/covid19).  

As tecnologias utilizadas para elaboração deste projeto foram as seguintes:  
* Java (versão 17) com os frameworks Spring Boot e MVC para o desenvolvimento do back-end, Maven para gerenciar as dependências do projeto e Hibernate para o ORM (mapeamento objeto-relacional)
*  PostgreSQL como banco de dados relacional
*  Swagger/Open API para documentar as chamadas da API desenvolvida
*  Docker para envolver a aplicação em um container
*  HTML, CSS e JavaScript para o desnevolvimento do front-end

Este projeto se divide em duas pastas: frontend e backend. Em backend, usei o padrão MVC para dividí-lo na seguintes categorias:  
* [Config](https://github.com/ederp/teste-planisa/tree/main/backend/src/main/java/com/example/planisa/config): contém a classe de configuração do CORS para permitir que o front se comunique com o back
* [Controller](https://github.com/ederp/teste-planisa/tree/main/backend/src/main/java/com/example/planisa/controller): contém a classe de chamada da API desenvolvida
* [DTO](https://github.com/ederp/teste-planisa/tree/main/backend/src/main/java/com/example/planisa/dto): contém as classes DTO necessárias para transferir os dados
* [Entity](https://github.com/ederp/teste-planisa/tree/main/backend/src/main/java/com/example/planisa/entity): contém as classes que realizam o mapeamento das tabelas
* [Repository](https://github.com/ederp/teste-planisa/tree/main/backend/src/main/java/com/example/planisa/entity): comtém as classes que fazem as operações do CRUD no banco de dados
* [Service](https://github.com/ederp/teste-planisa/tree/main/backend/src/main/java/com/example/planisa/service): contém as classes que fazem a ponte entre o controller e as classes de modelo
* [Swagger](https://github.com/ederp/teste-planisa/tree/main/backend/src/main/java/com/example/planisa/swagger): contém as classes de configuração do Swagger, que permitem que a documentação da API seja acessada através do endereço [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Quanto as decisões de projeto, após ver como que a API externa funciona, decidi criar um front que faz quatro tipos diferentes de pesquisa: quantidade de casos em um intervalo de dias, quantidade de mortes em um intervalo de dias, quantidade de casos em um determinado dia e quantidade de mortes em um determinado dia. A aplicação segue o seguinte fluxo: faz a chamada do controller, armazena o benchmark de consulta, faz a chamada da API externa, guarda os resultados no banco de dados e devolve os dados da consulta no banco em uma tabela HTML. Embora tenha pensado em fazer o armazenamento dos dados somente depois de devolver os resultados da consulta, para fins de performance, decidi fazer da maneira descrita.

Por fim, para rodar a aplicação, os seguintes passos se fazem necessários:

* Baixar o projeto na máquina
* Tendo o Docker já instalado, usar o comando `docker-compose up` para subir o container da aplicação
* Após a aplicação ser iniciada, os seguintes links estarão disponíveis: [http://127.0.0.1:3001/frontend/index.html](http://127.0.0.1:3001/frontend/index.html) para acessar a aplicação e 
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) para consultar a documentação do Swagger.


Para usar a aplicação, cuja tela inicial tem este layout:  

![Tela inicial da aplicação](https://github.com/ederp/teste-planisa/blob/main/COVID-19%20Dashboard.jpg)

O usuário pode selecionar ou escrever uma parte dos dois países selecionados para a comparação, no seletor de dados, escolher um dos quatro comparativos ("Casos entre um intervalo de dias", "Mortes entre um intervalo de dias", "Casos em um único dia" e "Mortes em um único dia"), selecionar as datas, e clicar no botão "Obter dados" para ver a tabela gerada.
