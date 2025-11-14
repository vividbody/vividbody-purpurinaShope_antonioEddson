# PurpurinaShop — Sistema Online para Joalheria

Sistema de gestão de clientes, produtos e pedidos para uma joalheria, desenvolvido com **Spring Boot 3.1.4**, **Thymeleaf**, **JPA/Hibernate**, **Flyway** e banco de dados **H2** (ambiente de desenvolvimento).

## Estrutura do Projeto

```
purpurinaShop_antonioEdson/
├── backend/                 # Aplicação Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/purpurina/
│   │   │   │   ├── PurpurinaApplication.java
│   │   │   │   └── controller/
│   │   │   │       └── HomeController.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       ├── db/migration/
│   │   │       │   └── V1__init.sql
│   │   │       └── templates/
│   │   │           └── index.html
│   │   └── test/
│   ├── target/              # Build output (JAR compilado aqui)
│   ├── pom.xml              # Maven config
│   ├── scripts/
│   │   ├── setup-maven.ps1
│   │   └── run-jar-background.ps1
│   └── README.md
├── docs/
│   ├── checklist.md         # User stories por milestone
│   └── data_model.md        # Mapa de entidades e relacionamentos
└── README.md (este arquivo)
```

## Marcos (Milestones)

- **Milestone 1** (24/10/2025 — 14/11/2025): US01–US04
  - CRUD Clientes
  - CRUD Produtos
  - CRUD Pedidos
  - Validações e cálculos em runtime

- **Milestone 2** (24/10/2025 — 12/12/2025): US05–US08
  - Pagamentos e formas
  - Relatórios e filtros
  - Autenticação/Autorização
  - UI aprimorada

Consulte `docs/checklist.md` para detalhes completos.

## Requisitos

- **Java 17+** (Temurin JDK recomendado)
- **Maven 3.9+**
- **Windows 10/11** (PowerShell 5.1 ou superior)

## Como Executar Localmente

### 1. Instalar Dependências (primeira vez)

Se não tiver Java ou Maven instalados:

```powershell
# Instalar JDK 17 via winget
winget install EclipseAdoptium.Temurin.17.JDK --accept-source-agreements --accept-package-agreements

# Instalar Maven via Chocolatey (requer Admin)
choco install maven -y

# Verificar instalações
java -version
mvn -version
```

Se usar o script auxiliar (sem instalação global):

```powershell
cd "purpurinaShop_antonioEdson\backend"
.\scripts\setup-maven.ps1
```

### 2. Compilar e Rodar

**Opção A: Modo foreground (terminal interativo)**

```powershell
cd "purpurinaShop_antonioEdson\backend"
mvn -DskipTests spring-boot:run
```

Acesse: **http://localhost:8080/**

Pressione `Ctrl+C` para parar.

**Opção B: Modo background (JAR em processo separado)**

```powershell
cd "purpurinaShop_antonioEdson\backend"
mvn -DskipTests package

# Iniciar em background
$jar = Resolve-Path 'target\purpurina-backend-0.0.1-SNAPSHOT.jar'
$javaExe = 'C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java.exe'
Start-Process -FilePath $javaExe -ArgumentList '-jar', $jar.Path -WindowStyle Hidden -RedirectStandardOutput "$env:USERPROFILE\purpurina-backend.log"

# Ver logs
Get-Content "$env:USERPROFILE\purpurina-backend.log" -Tail 100 -Wait

# Parar
Get-Process java | Stop-Process -Force
```

Acesse: **http://localhost:8080/**

### 3. Verificar Status

```powershell
# Processos java rodando
Get-Process -Name java -ErrorAction SilentlyContinue

# Porta 8080 em uso
netstat -ano | Select-String ":8080"

# Log do servidor
Get-Content "$env:USERPROFILE\purpurina-backend.log" -Tail 50
```

## Banco de Dados

- **Tipo:** H2 (em memória — dados perdidos ao reiniciar)
- **URL:** `jdbc:h2:mem:purpurina`
- **Migrações:** Flyway automático (`src/main/resources/db/migration/V1__init.sql`)
- **Tabelas:** Cliente, Dependente, Produto, Pedido, ItemPedido, Pagamento

Para persistência permanente, migre para PostgreSQL/MySQL editando `application.properties`.

## Documentação

- `docs/checklist.md` — User stories, tarefas, marcos
- `docs/data_model.md` — Mapa de entidades, campos, relacionamentos
- `backend/README.md` — Instruções técnicas do backend

## Stack Técnico

| Componente | Versão | Função |
|---|---|---|
| Spring Boot | 3.1.4 | Framework web |
| Thymeleaf | 3.x | Template engine HTML |
| Spring Data JPA | 3.1.4 | ORM/Persistência |
| Hibernate | 6.2.9 | Implementação JPA |
| Flyway | 9.16.3 | Migrações de BD |
| H2 Database | 2.1 | BD em memória |
| Java | 17.0.16 | Linguagem |
| Maven | 3.9+ | Build tool |

## Próximas Etapas

1. Implementar controladores para CRUD de Clientes, Produtos, Pedidos (Milestone 1).
2. Criar repositórios JPA e serviços.
3. Validações e regras de negócio.
4. Templates Thymeleaf para listagem, criação e edição.
5. Testes unitários e de integração.

## Contato / Suporte

Desenvolvido por: **Antonio Eddson**  
Repositório: [GitHub](https://github.com/vividbody/vividbody-purpurinaShope_antonioEddson)  
Data: **14 de novembro de 2025**
