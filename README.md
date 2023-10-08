# Data-analysis-Deputados
## Introdução
Este projeto tem como objetivo principal reunir informações sobre deputados, seus partidos e as despesas associadas ao longo dos meses/anos. Além disso, pretende elaborar um relatório abrangendo o número de proposições apresentadas por cada deputado e os tipos de proposições mais realizadas, com o propósito de analisar a atividade legislativa e identificar os parlamentares mais proativos, assim como as temáticas de suas proposições.



## Serviço de coleta de dados 
Neste projeto, utilizamos uma API gratuita disponibilizada pelo governo brasileiro, que oferece uma ampla gama de informações acessíveis por meio da API. O foco da coleta de dados foi direcionado exclusivamente para os dados relacionados aos Deputados, Partidos e Proposições.

A coleta de dados foi realizada por meio do seguinte link: [https://dadosabertos.camara.leg.br/swagger/api.html](https://dadosabertos.camara.leg.br/swagger/api.html).

É importante observar que, embora essa API ofereça diversos tipos de dados, nossa análise concentrou-se especificamente nos dados dos Deputados, Partidos e Proposições para este trabalho.

## Amostra de dados 
### Deputados
- Resquest URL
```
    https://dadosabertos.camara.leg.br/api/v2/deputados
```
- Server Response
```json
{
    "dados": [
    {
        "id": 220593,
        "nome": "Abilio Brunini",
        "siglaPartido": "PL",
        "idLegislatura": 57,
        "urlFoto": "https://www.camara.leg.br/internet/deputado/bandep/220593.jpg",
        "email": "dep.abiliobrunini@camara.leg.br"
    }
    ]
}
```
### Despesas Deputado
- Resquest URL
```
    https://dadosabertos.camara.leg.br/api/v2/deputados/220593/despesas?ordem=ASC&ordenarPor=ano
```
- Server Response
```json
    {
    "dados": [
    {
        "ano": 2023,
        "mes": 9,
        "tipoDespesa": "MANUTENÇÃO DE ESCRITÓRIO DE APOIO À ATIVIDADE PARLAMENTAR",
        "codDocumento": 7610066,
        "tipoDocumento": "Nota Fiscal Eletrônica",
        "codTipoDocumento": 4,
        "dataDocumento": "2023-09-19",
        "numDocumento": "35537",
        "valorDocumento": 52.98,
        "urlDocumento": "http://www.camara.leg.br/cota-parlamentar/nota-fiscal-eletronica?ideDocumentoFiscal=7610066",
        "nomeFornecedor": "3XIS COMERCIO VAREJISTA E ATACADISTA DE PAPELARIA LTDA",
        "cnpjCpfFornecedor": "42999796000188",
        "valorLiquido": 52.98,
        "valorGlosa": 0,
        "numRessarcimento": "",
        "codLote": 1969221,
        "parcela": 0
    }
    ]
}
```

### Partidos
- Resquest URL
```
    https://dadosabertos.camara.leg.br/api/v2/partidos?ordem=ASC&ordenarPor=sigla
```
- Server Response
```json
{
    "dados": [
    {
            "id": 36898,
            "sigla": "AVANTE",
            "nome": "Avante",
            "uri": "https://dadosabertos.camara.leg.br/api/v2/partidos/36898"
        }
        ]
}

```


### Proposições
- Resquest URL
```
    https://dadosabertos.camara.leg.br/api/v2/proposicoes?ordem=ASC&ordenarPor=id
```
- Server Response
```json
{
    "dados": [
        {
            "id": 13077,
            "uri": "https://dadosabertos.camara.leg.br/api/v2/proposicoes/13077",
            "siglaTipo": "MSC",
            "codTipo": 401,
            "numero": 167,
            "ano": 1990,
            "ementa": "SUBMETE A APRECIAÇÃO DO CONGRESSO NACIONAL O ATO QUE OUTORGA PERMISSÃO A RADIO FM PIRENOPOLIS LTDA., PARA EXPLORAR, PELO PRAZO DE 10 (DEZ) ANOS, SEM DIREITO DE EXCLUSIVIDADE, SERVIÇO DE RADIODIFUSÃO SONORA EM FREQUENCIA MODULADA, NA CIDADE DE PIRENOPOLIS, ESTADO DE GOIAS."
        },
    ]
}
```



### Autores Proposições
- Resquest URL
```
    https://dadosabertos.camara.leg.br/api/v2/proposicoes/13077/autores
```
- Server Response
```json
{
    "dados": [
        {
            "uri": "https://dadosabertos.camara.leg.br/api/v2/orgaos/253",
            "nome": "Poder Executivo",
            "codTipo": 30000,
            "tipo": "Órgão do Poder Executivo",
            "ordemAssinatura": 1,
            "proponente": 1
            }
  ],
  "links": [
        {
        "rel": "self",
        "href": "https://dadosabertos.camara.leg.br/api/v2/proposicoes/13077/autores"
        }
    ]
}
```


### Tipo Proposição
- Resquest URL
```
https://dadosabertos.camara.leg.br/api/v2/referencias/proposicoes/siglaTipo
```
- Server Response
```json
{
    "dados": [
        {
        "cod": "129",
        "sigla": "CON",
        "nome": "Consulta",
        "descricao": "Consulta"
        }
    ]
}
```

## Tabelas que estão presentes no bando de dados

| Deputado    |                |             |             |            |
| ----------- | -----------    | ----------- | ----------- |----------- |
| ID (PK)     | ID_partido (FK)|    nome     |   urlFoto   |   email    |


| Despesa_deputado     |                |             |             |                |            |
| ----------- | -----------    | ----------- | ----------- |-----------     |----------- |
| cod_documento (PK)     |id_deputado (FK)|    ano      |   mes       |   tipo_despesa  |valorLiquedo|


| Partido     |                |           |
| ----------- | -----------    | --------- |
| ID (PK)     | sigla          |    nome   | 


| Proposicao  |                |             |             |            |      
| ----------- | -----------    | ----------- | ----------- |----------- |      
| id (PK)     |id_deputado (FK)|cod_tipo (FK) |   ano       |   ementa   |


| Proposicao_Tipo|              |            |            |      
| -----------    | -----------  | -----------|----------- |      
| cod (PK)       |sigla         |   nome     |   descricao|

## Script SQL implementação do banco de dados 

```sql
CREATE SCHEMA IF NOT EXISTS "camara"
```
```sql 
create table if not exists camara.deputado(
	id int,
	id_partido int,
	nome char(200) not null,
	url_foto char(300),
	email char (100),
	constraint pk_deputado primary key(id)
);
```


 ```sql
    create table if not exists camara.partido(
        id int,
        sigla char(50) not null,
        nome char(150)not null,
        constraint pk_partido primary key(id)
    );
```

```sql 
alter table camara.deputado
	add constraint fk_deputado_partido foreign key(id_partido) references camara.partido(id) on update cascade;
```

```sql
create table if not exists camara.despesa_deputado(
	cod_documento int,
	id_deputado int,
	ano int,
	mes int,
	tipo_despesa char(300),
	valor_liquedo float,
	constraint pk_despesa primary key(cod_documento),
	constraint fk_despesa_deputado foreign key(id_deputado) references camara.deputado(id) on update cascade,
    constraint check_valor_positivo check (valor_liquedo > 0 or valor_liquedo = 0)

);
```

```sql
create table if not exists camara.proposicao_tipo(
	cod int,
	sigla char(50),
	nome char(100),
	descricao char(200),
	constraint pk_proposicao_tipo primary key(cod)
);
```

```sql
    create table if not exists camara.proposicao(
        id int,
        id_deputado int,
        cod_tipo int ,
        ano int,
        ementa char(800),
        constraint pk_proposicao primary key(id),
        constraint fk_proposicao_deputado foreign key(id_deputado) references camara.deputado(id) on update cascade
    );
```