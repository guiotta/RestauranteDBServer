# RestauranteDBServer
Repositório para o projeto da prova da DBServer.
Projeto desenvolvido em Eclipse utilizando Spring Tools, utiliza o banco em memória H2 para manter os dados.

# Dicas para executar o projeto
Após importar o projeto no git, executar os comados Maven para preparar as pastas do projeto.
	- Caso o projeto vá ser aberto no Eclipse, executar um 'mvn eclipse:eclipse';
	- É importante executar o comando 'mvn clean install' antes de rodar o projeto;
	- O projeto pode ser executado diretamente pelo Maven, utilizando o comando 'mvn spring-boot:run;
	- Após importar o projeto da IDE, será só iniciar a execição normalmente;
	- A classe contendo o main é: 'RestaurantDbServerApplication.java';
	- Para acessar a aplicação, entrar com o seguinte endereço no navegador: 'http://localhost:8080/';

# Decisões de projeto
	- O projeto foi construído utilizando o banco H2, assim, os dados salvos serão perdidos sempre que a aplicação reiniciar;
	- Não existem usuários padrão cadastrados no sistema, é preciso criar um novo usuário para os testes (existe um botão na tela de Login para acessar essa funcionalidade);
	- Também não existem restaurantes cadastrados, é preciso cadastrar os mesmos pela tela com esta funcionalidade;
	- As votações ficam abertas o dia inteiro, na tela principal os votos do dia são tabelados e é possível verificar qual é o restaurante mais votado;
	- No dia posterior, a eleição será reiniciada e os restaurantes vencedores da semana não estarão disponíveis na eleição;
	- Cada segunda-feira, os restaurantes vencedores das eleições da semana anterior estão disponíveis para votação novamente;
	- Os restaurantes vencedores da semana não estão sendo apresentados em alguma tela. Nos testes realizados, não encontrei uma forma amigável de exibir estas informações;
