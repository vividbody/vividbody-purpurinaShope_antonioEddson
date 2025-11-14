-- Migration inicial: cria tabelas básicas para Cliente, Dependente, Produto, Pedido, ItemPedido, Pagamento

CREATE TABLE cliente (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  genero VARCHAR(50),
  endereco VARCHAR(512),
  contato VARCHAR(255)
);

CREATE TABLE dependente (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  cliente_id BIGINT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  parentesco VARCHAR(100),
  data_nascimento DATE,
  CONSTRAINT fk_dependente_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE produto (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  marca VARCHAR(255),
  metal VARCHAR(100),
  gema1 VARCHAR(255),
  gema2 VARCHAR(255),
  gema3 VARCHAR(255),
  gema4 VARCHAR(255),
  gema5 VARCHAR(255),
  peso DECIMAL(10,3),
  quilates DECIMAL(10,3),
  dimensoes VARCHAR(100),
  preco_custo DECIMAL(12,2),
  preco_venda DECIMAL(12,2),
  data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pedido (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  cliente_id BIGINT NOT NULL,
  data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(50),
  observacoes VARCHAR(1000),
  CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE RESTRICT
);

CREATE TABLE item_pedido (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  pedido_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  quantidade INT NOT NULL,
  preco_unitario DECIMAL(12,2) NOT NULL,
  CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
  CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE RESTRICT
);

CREATE TABLE pagamento (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  pedido_id BIGINT NOT NULL,
  forma VARCHAR(50),
  valor DECIMAL(12,2) NOT NULL,
  data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_pagamento_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE
);
