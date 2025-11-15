# 🚀 PurpurinaShop - Guia de Desenvolvimento

## Status Atual

✅ **Milestone 1 - 100% Completo** (Prazo: 14/11/2025)
- ✅ US01: Cadastro de Clientes
- ✅ US02: Cadastro de Produtos  
- ✅ US03: Cadastro de Pedidos
- ⏳ US04: Validações e Cálculos Runtime (próximo)

## Stack Tecnológico

```
Frontend:        Thymeleaf + HTML5 + CSS3 + JavaScript
Backend:         Spring Boot 3.2.12 + Spring Data JPA
Database:        H2 In-Memory + Flyway Migrations
ORM:             Hibernate 6.4.10
Build Tool:      Maven 3.9.6 (Maven Wrapper incluído)
Runtime:         Java 21.0.5 LTS (Temurin/Adoptium)
Server:          Apache Tomcat 10.1.33 (embedded)
```

## Estrutura de Diretórios

```
purpurinaShop_antonioEdson/
├── backend/
│   ├── src/main/java/com/purpurina/
│   │   ├── entity/          (JPA Entities)
│   │   ├── repository/      (Spring Data Repositories)
│   │   ├── service/         (Business Logic)
│   │   └── controller/      (HTTP Endpoints)
│   ├── src/main/resources/
│   │   ├── templates/       (Thymeleaf HTML)
│   │   │   ├── clientes/
│   │   │   ├── produtos/
│   │   │   └── pedidos/
│   │   ├── db/migration/    (Flyway SQL Scripts)
│   │   └── application.properties
│   ├── pom.xml
│   └── README.md
├── docs/
│   ├── checklist.md         (User Stories)
│   └── data_model.md        (Entity Mapping)
└── README.md                (Este arquivo)
```

## Entidades Implementadas

### 1. Cliente (US01)
```java
- id: Long (PK)
- nome: String
- genero: String (Masculino/Feminino/Não Informado/Indefinido)
- email: String (unique)
- telefone: String
- rua: String
- numero: String
- bairro: String
- cidade: String
- dataCriacao: LocalDateTime (auto)
```

### 2. Produto (US02)
```java
- id: Long (PK)
- marca: String
- metal: String (Ouro/Prata/Ródio/Platina/Cobre/Bronze)
- gema1-5: String (nullable, até 5 gemas)
- peso: BigDecimal (gramas)
- quilates: BigDecimal
- dimensoes: String (AxLxP)
- precoCusto: BigDecimal
- precoVenda: BigDecimal
- dataCadastro: LocalDateTime (auto)
```

### 3. Pedido (US03)
```java
- id: Long (PK)
- cliente: Cliente (FK)
- dataPedido: LocalDateTime
- status: String (Pendente/Confirmado/Enviado/Entregue/Cancelado)
- observacoes: String
- dataCriacao: LocalDateTime (auto)
- itens: List<ItemPedido> (OneToMany)
```

### 4. ItemPedido (US03)
```java
- id: Long (PK)
- pedido: Pedido (FK)
- produto: Produto (FK)
- quantidade: Integer
- precoUnitario: BigDecimal
+ getSubtotal(): BigDecimal (quantidade * precoUnitario)
```

## API REST - Endpoints

### Clientes
```
GET    /clientes              → Listar todos
GET    /clientes/novo         → Formulário novo
POST   /clientes              → Criar
GET    /clientes/{id}         → Detalhar
GET    /clientes/{id}/editar  → Formulário editar
POST   /clientes/{id}/atualizar → Atualizar
GET    /clientes/{id}/deletar → Deletar
```

### Produtos
```
GET    /produtos              → Listar todos
GET    /produtos/novo         → Formulário novo
POST   /produtos              → Criar
GET    /produtos/{id}         → Detalhar
GET    /produtos/{id}/editar  → Formulário editar
POST   /produtos/{id}/atualizar → Atualizar
GET    /produtos/{id}/deletar → Deletar
```

### Pedidos
```
GET    /pedidos               → Listar todos
GET    /pedidos/novo          → Formulário novo
POST   /pedidos               → Criar
GET    /pedidos/{id}          → Detalhar
GET    /pedidos/{id}/editar   → Formulário editar
POST   /pedidos/{id}/atualizar → Atualizar
GET    /pedidos/{id}/deletar  → Deletar
```

## Como Executar

### 1. Compilar o Projeto
```powershell
cd backend
mvn clean package -DskipTests
```

### 2. Executar o Servidor
```powershell
java -jar target/purpurina-backend-0.0.1-SNAPSHOT.jar
```

### 3. Acessar a Aplicação
- Abrir navegador em: **http://localhost:8080/**
- Home: http://localhost:8080/
- Clientes: http://localhost:8080/clientes
- Produtos: http://localhost:8080/produtos
- Pedidos: http://localhost:8080/pedidos

## Migrações de Banco de Dados

Todas as migrações são automáticas via Flyway no startup:

```sql
V1__init.sql                    → Tabelas iniciais
V2__update_cliente_schema.sql   → Colunas específicas cliente
V3__add_pedido_columns.sql      → Coluna data_criacao pedido
```

## Padrão de Código

Todos os módulos (Cliente, Produto, Pedido) seguem padrão MVC:

```
HTTP Request
    ↓
@Controller (PedidoController)
    ↓
@Service (PedidoService) → business logic
    ↓
@Repository (PedidoRepository) → JPA queries
    ↓
@Entity (Pedido) → JPA mapping
    ↓
Database (H2)
    ↓
HTTP Response (Thymeleaf template)
```

## Funcionalidades Especiais

### Cálculos no Produto
- **Margem de Lucro**: `(precoVenda - precoCusto) / precoCusto * 100`

### Cálculos no Pedido
- **Subtotal Item**: `quantidade * precoUnitario`
- **Total Pedido**: `sum(itens.subtotal)`

### Templates Reutilizáveis
- Cada módulo tem **formulario.html** compartilhado para criar/editar
- Usa conditional `th:if="${entidade.id == null}"` para diferenciar operação

## Próximas User Stories (Milestone 2)

### US04: Validações e Cálculos Runtime
- [ ] Validar email duplicado (ClienteService)
- [ ] Validar quantidade mínima de produtos
- [ ] Calcular impostos (15% do total)
- [ ] Calcular desconto automático (10% acima de R$5000)

### US05: Módulo de Pagamentos
- [ ] Entidade Pagamento
- [ ] Métodos: Dinheiro, Cartão Crédito, Débito, Pix
- [ ] Status de pagamento

### US06: Relatórios
- [ ] Relatório de vendas por mês
- [ ] Ranking de produtos mais vendidos
- [ ] Dashboard com gráficos

### US07: Autenticação
- [ ] Spring Security
- [ ] Login de usuários
- [ ] Roles: Admin, Vendedor

### US08: UI Aprimorada
- [ ] Design responsivo
- [ ] Temas claro/escuro
- [ ] Busca avançada
- [ ] Paginação de listas

## Troubleshooting

### Erro: "Column not found"
**Causa**: Mismatch entre entidade Java e schema SQL
**Solução**: Adicionar nova migração Flyway com ALTER TABLE

### Erro: "Address already in use" (Port 8080)
**Causa**: Outro processo usando porta 8080
**Solução**: `Get-Process java | Stop-Process -Force`

### Build falha com erros de compilação
**Solução**: Verificar logs com `mvn -X clean package`

## Contato & Suporte

- **Desenvolvedor**: Antonio Eddson
- **Projeto**: PurpurinaShop v0.0.1-SNAPSHOT
- **Início**: 24/10/2025
- **Deadline Milestone 1**: 14/11/2025 ✅

---

**Última atualização**: 14/11/2025 - 02:09 (BRT)
