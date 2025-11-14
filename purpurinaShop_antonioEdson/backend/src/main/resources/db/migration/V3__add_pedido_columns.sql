-- Migration V3: Adicionar colunas faltantes em pedido

ALTER TABLE pedido ADD COLUMN IF NOT EXISTS data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
