# sd-doevida-server-2021
Projeto servidor Sistemas Distribuidos

Equipe 5:
Lucas, Thiago, Leonardo e Bruno

	Instruções para executar Servidor e Cliente

1 - Bibliotecas 

	Adicionar as bibliotecas ao NetBeans em Tools => Libraries:
	Clicar em "New Library", criar a biblioteca "sd" e inserir os arquivos abaixo:.

	- mysql-connector-java-8.0.25.jar
    - json-20210307.jar


2 - Banco de Dados

	Com o Apache e MySQL rodando, acessar o painel de administração do MySQL.
	Na aba "Importar", selecionar o arquivo "sd_bd.sql" e executar.
	No NetBeans, verificar a conexão com o banco de dados na aba "Services".
	
3 - NetBeans

	Abrir o projeto "sd-doevida-server-2021".
	
3.1 - Servidor

	Clicar com o botão direito no projeto do servidor e selecionar "Properties".
	CLicar em "Libraries" e adicionar as bibliotecas do item 1 e clicar em OK.
	Compilar o projeto e aguardar

    Para executar o servidor, execute o arquivo "Teste.java" no pacote "sdServer".
	
	ATENÇÂO: O servidor não possui interface gráfica, todas as saídas são feitas pelo terminal.
	

4 - Cliente

    Para executar o cliente, execute o arquivo "ServerConnect.java" no pacote "View".
