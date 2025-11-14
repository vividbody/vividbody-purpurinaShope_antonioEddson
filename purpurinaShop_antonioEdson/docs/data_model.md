# Data Model — PurpurinaShop (visão inicial)

Entidades principais e campos (mínimo requerido pelas US):

- Cliente
  - id: BIGINT PK (auto)
  - nome: VARCHAR
  - genero: VARCHAR
  - endereco: VARCHAR (rua, número, complemento, cidade, estado, cep)
  - contato: VARCHAR (telefone / email)

- Dependente
  - id: BIGINT PK
  - cliente_id: FK -> Cliente(id)
  - nome: VARCHAR
  - parentesco: VARCHAR
  - data_nascimento: DATE

- Produto
  - id: BIGINT PK
  - marca: VARCHAR
  - metal: VARCHAR
  - gema1..gema5: VARCHAR NULL (até 5 gemas descritas)
  - peso: DECIMAL(10,3) (gramas)
  - quilates: DECIMAL(10,3)
  - dimensoes: VARCHAR (formato AxLxP em mm)
  - preco_custo: DECIMAL(12,2)
  - preco_venda: DECIMAL(12,2)
  - data_cadastro: TIMESTAMP

- Pedido
  - id: BIGINT PK
  - cliente_id: FK -> Cliente(id)
  - data_pedido: TIMESTAMP
  - status: VARCHAR
  - observacoes: TEXT NULL
  - NOTA: Totais e impostos não serão persistidos — calcular em runtime

- ItemPedido
  - id: BIGINT PK
  - pedido_id: FK -> Pedido(id)
  - produto_id: FK -> Produto(id)
  - quantidade: INT
  - preco_unitario: DECIMAL(12,2)

- Pagamento
  - id: BIGINT PK
  - pedido_id: FK -> Pedido(id)
  - forma: VARCHAR (ex: CARTAO, DINHEIRO, TRANSFERENCIA)
  - valor: DECIMAL(12,2)
  - data_pagamento: TIMESTAMP

Relacionamentos:
- Cliente 1:N Pedido
- Pedido 1:N ItemPedido
- Produto 1:N ItemPedido
- Cliente 1:N Dependente
- Pedido 1:1..N Pagamento

Regras / Observações de implementação:
- Campos calculados (ex.: total do pedido, impostos) são sempre computados em memória, não armazenados.
- Produtos permitem até 5 gemas descritivas (gema1..gema5). Se desejado, migrar para tabela `produto_gema` no futuro.
- Dimensões armazenadas como string `AxLxP` por simplicidade; pode ser normalizado para colunas separadas.

Esta é a modelagem inicial — detalhes serão refinados ao implementar cada US.