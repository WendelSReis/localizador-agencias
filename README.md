# Localizador de Agências – Guia de Testes de Ponta a Ponta (E2E)

Este README explica **como executar todos os testes** (unitários, JPA, web) e inclui um **roteiro E2E** — automatizado (JUnit + Spring Boot) e manual (cURL) — para validar o fluxo completo da API.

> Stack: **Java 21 • Spring Boot 3.3 • H2 • JPA • Actuator • springdoc-openapi**

---

## 📦 Pré-requisitos

- **Java 21** instalado (`java -version`)
- **Maven 3.9+** (`mvn -v`)
- Porta **8080** livre (apenas para o modo manual E2E)

---

## 🗂️ Estrutura de pastas (resumo)

```
src
├─ main
│  ├─ java/br/com/localizador
│  │  ├─ Aplicacao.java
│  │  ├─ controller/AgenciaControlador.java
│  │  ├─ service/AgenciaServico.java
│  │  ├─ repository/AgenciaRepositorio.java
│  │  ├─ model/Agencia.java
│  │  ├─ dto/{RequisicaoAgencia,RespostaAgencia}.java
│  │  ├─ exception/TratadorExcecoes.java
│  │  └─ util/CalculadoraDistancia.java
│  └─ resources/application.yml
└─ test
   └─ java/br/com/localizador
      ├─ util/CalculadoraDistanciaTeste.java
      ├─ repository/AgenciaRepositorioTeste.java
      ├─ service/AgenciaServicoIntegracaoTeste.java
      ├─ controller/AgenciaControladorTeste.java
      └─ e2e/AgenciaE2ETeste.java      ⟵ (você cria com o código abaixo)
```


---

## ✅ Rodar **todos** os testes (rápido)

```bash
mvn -q clean test
```

- Executa testes **unitários**, **Web (MockMvc)** e **integração JPA**.
- Relatório de cobertura (se JaCoCo estiver configurado): `target/site/jacoco/index.html`

---



## 🚀 E2E **manual** (cURL) – com servidor rodando

1) Suba a aplicação:
```bash
mvn spring-boot:run
```

2) POST - Cadastrar agências:
```bash
curl -s -X POST http://localhost:8080/desafio/cadastrar -H 'Content-Type: application/json' -d '{"posX":-2,"posY":2}'
curl -s -X POST http://localhost:8080/desafio/cadastrar -H 'Content-Type: application/json' -d '{"posX":10,"posY":4}'
curl -s -X POST http://localhost:8080/desafio/cadastrar -H 'Content-Type: application/json' -d '{"posX":-5,"posY":-2}'
curl -s -X POST http://localhost:8080/desafio/cadastrar -H 'Content-Type: application/json' -d '{"posX":10,"posY":-7}'
```

3) GET - Calcular distâncias a partir do usuário (-10, 5):
```bash
curl -s "http://localhost:8080/agencias/distancia?posX=-10&posY=5"
```
 
> Health: `http://localhost:8080/actuator/health`  
> H2 Console: `http://localhost:8080/h2-console` (JDBC: `jdbc:h2:mem:localizador`, user: `sa`)

---

## 🧩 Dicas & Troubleshooting

- **Porta ocupada (8080):** finalize processos que estão usando a porta ou altere `server.port` no `application.yml`.
- **Erros de validação 400:** confira se o corpo JSON tem `posX` e `posY` inteiros.
- **Banco limpo a cada execução:** por padrão o H2 está **em memória**.  
  Para persistir em arquivo (entre reinícios), troque a URL no `application.yml`:
  ```
  spring.datasource.url=jdbc:h2:file:./.data/localizador;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE
  ```

---

## 🧾 Cobertura
Se o plugin **JaCoCo** estiver configurado no `pom.xml`:
```bash
mvn clean test
# abra target/site/jacoco/index.html
```

---