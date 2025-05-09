CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(1000),
    responsavel VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    data_modificacao TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE
);

-- Índices
CREATE INDEX idx_produtos_nome ON produtos(nome);
CREATE INDEX idx_produtos_tipo ON produtos(tipo);
CREATE INDEX idx_produtos_deleted ON produtos(deleted);

-- Comentários
COMMENT ON TABLE produtos IS 'Tabela de produtos do sistema';
COMMENT ON COLUMN produtos.id IS 'Identificador único do produto';
COMMENT ON COLUMN produtos.nome IS 'Nome do produto';
COMMENT ON COLUMN produtos.descricao IS 'Descrição detalhada do produto';
COMMENT ON COLUMN produtos.responsavel IS 'Responsável pelo produto';
COMMENT ON COLUMN produtos.tipo IS 'Categoria/tipo do produto';
COMMENT ON COLUMN produtos.data_criacao IS 'Data de criação do registro';
COMMENT ON COLUMN produtos.data_modificacao IS 'Data da última modificação';
COMMENT ON COLUMN produtos.deleted IS 'Flag de exclusão lógica'; 