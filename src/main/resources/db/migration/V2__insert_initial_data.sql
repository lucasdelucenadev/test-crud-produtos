-- Inserindo dados iniciais de exemplo
INSERT INTO produtos (nome, descricao, responsavel, tipo, data_criacao)
VALUES 
    ('Notebook Dell XPS', 'Notebook Dell XPS 13 polegadas, 16GB RAM, 512GB SSD', 'João Silva', 'Eletrônicos', CURRENT_TIMESTAMP),
    ('Monitor LG 27"', 'Monitor LG 27 polegadas, 4K, HDR', 'Maria Santos', 'Eletrônicos', CURRENT_TIMESTAMP),
    ('Teclado Mecânico', 'Teclado mecânico RGB, switches blue', 'Pedro Oliveira', 'Periféricos', CURRENT_TIMESTAMP),
    ('Mouse Gamer', 'Mouse gamer com 6 botões programáveis', 'Ana Costa', 'Periféricos', CURRENT_TIMESTAMP),
    ('Headset Bluetooth', 'Headset Bluetooth com cancelamento de ruído', 'Carlos Lima', 'Áudio', CURRENT_TIMESTAMP); 