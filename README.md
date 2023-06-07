# Global Solution EyeCrop API

Esta é a nossa solução para a Global Solution da [FIAP](https://www.fiap.com.br)
Mas, o que é esse projeto?

* <details><summary><a href="#EyCrop">EyeCrop</a></summary>

    * [Descrição do projeto](#Descrição-do-projeto)

    <details>
    <summary><a href="#Mais-informações-sobre-este-projeto">Mais informações sobre este projeto</a></summary>

    * [Instalação](#Instalação)
    * [Execução](#Execução)
    * [Teste dos recursos](#Teste-dos-recursos) 
    * [Collections endpoints](#Collections-endpoints)
    * [Lista de endpoints utilizados](#Lista-de-endpoints-utilizados)
    * [Tecnologias](#Tecnologias)
    * [Últimas atualizações](#Últimas-atualizações)

    <details>
    <summary><a href="#Contatos">Contatos</a></summary>

    * [LinkedIn](#LinkedIn)
    </details>

</details>


# EyeCrop

## Descrição do projeto

[![Nossa Proposta para a GS](./images/thumbnail_pitch.png)](https://youtu.be/vr3RtNTjP8M)

O EyeCrop é um aplicativo mobile que tem como objetivo avaliar se um alimento está próprio para o consumo ou não, através de uma foto do alimento, diminuindo, assim, o desperdício de alimentos.  

<br>

# Mais informações sobre este projeto

[![Vídeo Explicativo Técnico](./images/thumbnail_yt.png)](https://youtu.be/vr3RtNTjP8M)

## Instalação

**1 -** No application.properties troque as variaveis de ambiente '{USERNAME}' e '{PASSWORD}' para as suas credencias no banco de dados da Oracle; Caso esteja utilizando outro modelo de banco de dados, altere o driver e a url de conexão no mesmo arquivo.

**2 -**  Execute o projeto;

**3 -** Abra o Postman e importe as Colections que estão na pasta raiz do arquivo.

## Execução

Em execução, os endpoints liberados para teste são o POST /login e POST /usuario, se não tiver uma conta cadastrada na aplicação se cadastre a partir do /usuario. Após isso realize o Login com o /login, pegue o Token que vira na resposta e adicione ao cabeçalho de cada requisição que tentar executar.

## Teste dos recursos

* Uso do PostMan para testes de cadastro de usuário 

## Collections endpoints
<a href="./collections-postman">Pasta com as collections</a>

## Lista de endpoints utilizados

### Fotos
| Função 	                               | Endpoint     				                | Verbo  | Retorno				                                                                  |
|:----------------------------------------:|:------------------------------------------:|:------:|:--------------------------------------------------------------------------------------:|
| Lista todas as fotos                     | /fotos/                                    | GET    | 200 - Sucesso 204 - Nenhuma foto encontrada                                            |
| Busca foto por ID                        | /fotos/{id}                                | GET    | 200 - Sucesso 404 - Foto não encontrada                                                |
| Salva uma Foto                           | /fotos/                                    | POST   | 201 - Foto salva 409 - Violação de Restrição de dados                                  |
| Atualiza parcialmente uma foto           | /fotos/{id}                                | PATCH  | 200 - Foto atualizada 404 - Foto não encontrada Violação de Restrição de dados         |

### Respostas
| Função 	                               | Endpoint     				                | Verbo  | Retorno								                                                  |
|:----------------------------------------:|:------------------------------------------:|:------:|:--------------------------------------------------------------------------------------:|
| Exclui uma foto pelo ID                  | /fotos/{id}                                | DELETE | 204 - Foto excluída 404 - Foto não encontrada                                          |
| Lista todas as respostas                 | /respostas/                                | GET    | 200 - Sucesso 204 - Nenhuma resposta encontrada                                        |
| Busca resposta por ID                    | /respostas/{id}                            | GET    | 200 - Sucesso 404 - Resposta não encontrada                                            |
| Salva uma Resposta                       | /respostas/                                | POST   | 201 - Resposta salva 409 - Violação de Restrição de dados                              |
| Atualiza parcialmente uma Resposta       | /respostas/{id}                            | PATCH  | 200 - Resposta atualizada 404 - Resposta não encontrada Violação de Restrição de dados |
| Exclui uma resposta pelo ID              | /respostas/{id}                            | DELETE | 204 - Resposta excluída 404 - Resposta não encontrada                                  |

### Usuários
| Função 	                               | Endpoint     				                | Verbo  | Retorno								                                                  |
|:----------------------------------------:|:------------------------------------------:|:------:|:--------------------------------------------------------------------------------------:|
| Lista todos os usuários                  | /usuarios/                                 | GET    | 200 - Sucesso 204 - Nenhum usuário encontrada                                          |
| Busca usuário por ID                     | /usuarios/{id}                             | GET    | 200 - Sucesso 404 - usuário não encontrada                                             |
| Salva uma usuário                        | /usuarios/                                 | POST   | 201 - usuário salva 409 - Violação de Restrição de dados                               |
| Atualiza  um usuário existente           | /usuarios/{id}                             | PUT    | 200 - usuário atualizada 404 - usuário não encontrada Violação de Restrição de dados   |
| Atualiza parcialmente um usuário         | /usuarios/{id}                             | PATCH  | 200 - usuário atualizada 404 - usuário não encontrada Violação de Restrição de dados   |
| Exclui uma usuário pelo ID               | /usuarios/{id}				                | DELETE | 204 - usuário excluída 404 - Resposta não encontrada      			                  |

### Endereços
| Função 	                               | Endpoint     				                | Verbo  | Retorno								                                                  |
|:----------------------------------------:|:------------------------------------------:|:------:|:--------------------------------------------------------------------------------------:|
| Lista todos os endereços                 | /enderecos/?size=10&page=0&sort=logradouro | GET    | 200 - Sucesso 400 - Nenhum Endereço encontrado					                      |
| Recupera um endereço por ID              | /enderecos/{id}               		        | GET    | 200 - Sucesso 404 - Endereço não encontrado					                          |
| Recupera um endereço pelo estado         | /enderecos/estado/{estado}		  	        | GET    | 200 - Sucesso 404 - Endereço não encontrado					                          |
| Recupera um endereço pela cidade         | /enderecos/cidade/{cidade}		        	| GET    | 200 - Sucesso 404 - Endereço não encontrado					                          |
| Salva o endereço	                       | /enderecos/				                | POST   | 201 - Sucesso 409 - Violação de restrição de dados				                      |
| Exclui um endereço por ID                | /enderecos/{id}				            | DELETE | 204 - Sucesso 404 - Endereço não encontrado					                          |
| Atualiza parcialmente um endereço por ID | /enderecos/{id}				            | PATCH  | 200 - Sucesso 404 - Endereço não encontrado 409 - Violação de restrição de dados       |
| Atualiza totalment um endereço por ID    | /enderecos/{id}				            | PATCH  | 200 - Sucesso 404 - Endereço não encontrado 409 - Violação de restrição de dados       |





## Tecnologias 🛠️
* Ferramentas utilizadas para este projeto:
    - [Java SE 8](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html) || [Swagger](https://swagger.io) || [Postman](https://www.postman.com) || [Oracle Database](https://www.oracle.com/br/database/)

## Últimas atualizações 
* 05/06/2023
---

# Feito por JapeTech <img alt="JapeTech" title="japetech" src="./images/japetech.png" width="50vw" height="50vh"> 


## Contatos

### LinkedIn
* <img alt="LinkedIn" title="linkedIn" src="./images/linkedin.png" width="20vw" height="20vh"> <a href="https://www.linkedin.com/in/mateus-da-costa-leme-35a5ab235/">Mateus da Costa Leme</a>
</br>

* <img alt="LinkedIn" title="linkedIn" src="./images/linkedin.png" width="20vw" height="20vh"> <a href="https://www.linkedin.com/in/heitor-borba-marini/">Heitor Borba Marini</a>
</br>

* <img alt="LinkedIn" title="linkedIn" src="./images/linkedin.png" width="20vw" height="20vh"> <a href="https://www.linkedin.com/in/luisfelipe-gm/">Luís Felipe Garcia Menezes</a>
</br>

* <img alt="LinkedIn" title="linkedIn" src="./images/linkedin.png" width="20vw" height="20vh"> <a href="https://www.linkedin.com/in/pedro-henrique-chueiri-574387234/">Pedro Henrique Chueiri</a>
</br>

* <img alt="LinkedIn" title="linkedIn" src="./images/linkedin.png" width="20vw" height="20vh"> <a href="https://www.linkedin.com/in/diogogiarrantikahn/">Diogo Giarranti Kahn</a>





