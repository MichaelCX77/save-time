# Save Time
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/MichaelCX77/save-time/blob/main/LICENSE)

O Save Time é uma plataforma desenvolvida para Windows Desktop com o intuito de registrar a quantidade de tempo investida em atividades diárias como Leitura, Cursos e Linguagens.
A proposta da ferramenta é que você possua maior visibilidade e controle das atividades que executa.
<br/><br/>

## <li> Sobre o Projeto

Clique <a href ="https://github.com/MichaelCX77/artefatos/tree/master/save-time/versions">aqui</a> e faça download do executável mais atual<br/>

Após concluir o download, descompacte o arquivo em e clique duas vezes sobre o executável.

<br/>

## <li> Planos para a Plataforma

A plataforma posteriormente poderá ter ferramentas de relatórios, lembretes, gráficos, entre outras informações pertinentes ao controle de tarefas pessoais.

<br/>

## <li> Layout da Plataforma

<br/>
<div align="center">
  <img height="500em" src="https://github.com/MichaelCX77/assets/blob/master/assets-save-time/png/login.png"></a><br/><br/>
  <img height="500em" src="https://github.com/MichaelCX77/assets/blob/master/assets-save-time/png/home.png"></a><br/><br/>
  <img height="500em" src="https://github.com/MichaelCX77/assets/blob/master/assets-save-time/png/dietas-page.png"></a><br/><br/>
  <img height="300em" src="https://github.com/MichaelCX77/assets/blob/master/assets-save-time/png/timer.png">&nbsp&nbsp&nbsp
  <img height="300em" src="https://github.com/MichaelCX77/assets/blob/master/assets-save-time/png/detalhe-finalize.png"></a><br/><br/>
  <img height="300em" src="https://github.com/MichaelCX77/assets/blob/master/assets-save-time/png/detalhe-curso-existente.png"></a>&nbsp&nbsp&nbsp
  <img height="300em" src="https://github.com/MichaelCX77/assets/blob/master/assets-save-time/png/detalhe-save.png"></a>
</div>
<br/><br/>

# Tecnologias utilizadas


## Front end

<li> Java Swing
  
<br/>
  
## Back end
  
<li> Java
<li> JPA
<li> EntityManager
<li> Hibernate
<li> JodaTime

<br/>
  
## Implantação em Produção

<li> Postgresql em Nuvem Heroku (AWS)
  
<br/><br/>
  
# Como criar o banco de dados local
  
 Pré Requisitos: PSQL com variáveis de ambiente configuradas
  
  ```bash
  #1 Logar no console PSQL
    Logar em uma database do PSQL (PostgreSQL) com usuário que possua permissões de administrador
  
  #2 Executar script de criação do banco
    Executar script "Criacao do Banco Local.sql" presente na pasta "doc" do projeto
  ```
<br/>
  
# Como executar o Projeto
  
Pré Requisitos: Java 8 e Maven com variáveis de ambiente configuradas

  ```bash
#1 clonar repositório
  git clone https://github.com/MichaelCX77/save-time/
  
#2 entrar na pasta do projeto
  cd save-time
  
#3 executar build maven
  mvn clean package

#4 executar artefato
  java -jar target/save.time-{VERSION}-jar-with-dependencies.jar
  
  ```
<br/>

## Informações adicionais
  
 ```bash
  Após finalizar a configuração já estará criado o user "saveadmin" com senha "saveadmin2021" para login na aplicação
```
