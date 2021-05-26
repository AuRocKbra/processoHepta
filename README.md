# processoHepta
- O projeto foi migrado para spring-boot, afim de agilizar a implementação do back-end. No front-end manteve-se o uso de html, css e Vue.js.
- O projeto pode ser importado no eclipse, como a implementação usa maven para o gerenciamento das dependências, não haverá problema com estas, porém
aconselho a usar o spring-boot-tools para evitar qualquer impecílho. Abaixo segue o link para download.
https://spring.io/tools
- Criado dois perfis de uso para start da aplicação, o perfil de test que usa o h2 como banco de dados. Este perfíl implementa uma classe de configuração
que irá realizar um pré-preenchimento das tabelas. O outro perfil definido como dev, fara uso da configuração do datasource definido no próprio arquivo
do perfil, no caso o mysql. Os arquivos em questão são o application-test.properties e o application-dev.properties respectivamente. Para alterar 
entre as definições dos perfis, edite o arquivo application.properties, alterando o valor do parâmetro "spring.profiles.active" para "dev" ou "test".
- Caso necessite, no projeto terá um script que pode ser usado pera dar carga no banco mysql.
- Faça uso do setor "RH" no cadastro de novos funcioanrios. 
