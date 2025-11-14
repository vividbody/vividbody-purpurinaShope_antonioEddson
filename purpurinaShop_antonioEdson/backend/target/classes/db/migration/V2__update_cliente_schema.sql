-- Migration V2: Alterar tabela cliente para incluir campos específicos

ALTER TABLE cliente ADD COLUMN rua VARCHAR(255);
ALTER TABLE cliente ADD COLUMN bairro VARCHAR(255);
ALTER TABLE cliente ADD COLUMN numero VARCHAR(50);
ALTER TABLE cliente ADD COLUMN cidade VARCHAR(255);
ALTER TABLE cliente ADD COLUMN email VARCHAR(255);
ALTER TABLE cliente ADD COLUMN telefone VARCHAR(20);
ALTER TABLE cliente ADD COLUMN data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Manter compatibilidade removendo colunas antigas se necessário
-- ALTER TABLE cliente DROP COLUMN endereco;
-- ALTER TABLE cliente DROP COLUMN contato;
