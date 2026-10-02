CREATE TABLE administrador (
    id VARCHAR(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE cliente (
    id VARCHAR(36) PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE configuracoes (
    id VARCHAR(36) PRIMARY KEY,
    horario_funcionamento VARCHAR(255) NOT NULL
);

CREATE TABLE categoria (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE produto (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    id_categoria VARCHAR(36) NOT NULL,
    imagem VARCHAR(255),
    CONSTRAINT fk_produto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id) ON DELETE CASCADE
);

CREATE TABLE cliente_endereco (
    id VARCHAR(36) PRIMARY KEY,
    id_cliente VARCHAR(36) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    tipo VARCHAR(50) NOT NULL, -- (Residencial / Trabalho / Outros)
    CONSTRAINT fk_endereco_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE cliente_telefone (
    id VARCHAR(36) PRIMARY KEY,
    id_cliente VARCHAR(36) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    CONSTRAINT fk_telefone_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE pedido (
    id VARCHAR(36) PRIMARY KEY,
    id_cliente VARCHAR(36) NOT NULL,
    preco_total DECIMAL(10,2) NOT NULL,
    tipo_entrega VARCHAR(50) NOT NULL, -- (Retirada/Entrega)
    endereco VARCHAR(255), -- opcional
    forma_pagamento VARCHAR(50) NOT NULL,
    horario_criacao DATETIME NOT NULL,
    horario_saida DATETIME,
    horario_finalizacao DATETIME,
    telefone VARCHAR(20) NOT NULL,
    status VARCHAR(50) NOT NULL,
    motivo_cancelamento TEXT,
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE produto_pedido (
    id VARCHAR(36) PRIMARY KEY,
    id_pedido VARCHAR(36) NOT NULL,
    id_produto VARCHAR(36) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_prod_ped_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id) ON DELETE CASCADE,
    CONSTRAINT fk_prod_ped_produto FOREIGN KEY (id_produto) REFERENCES produto(id) ON DELETE CASCADE
);

CREATE TABLE reserva (
    id VARCHAR(36) PRIMARY KEY,
    id_cliente VARCHAR(36) NOT NULL,
    horario DATETIME NOT NULL,
    quantidade_pessoas INT NOT NULL,
    tipo_evento VARCHAR(100),
    motivo_cancelamento TEXT,
    CONSTRAINT fk_reserva_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE
);

-- Insert sample admin
INSERT INTO administrador (id, email, senha) VALUES ('123e4567-e89b-12d3-a456-426614174000', 'admin@nonna.com', 'admin123');

-- Insert sample clients
INSERT INTO cliente (id, cpf, nome, sobrenome, email, senha) VALUES 
('223e4567-e89b-12d3-a456-426614174001', '111.111.111-11', 'João', 'Silva', 'joao@example.com', 'senha123'),
('323e4567-e89b-12d3-a456-426614174002', '222.222.222-22', 'Maria', 'Souza', 'maria@example.com', 'senha123');

-- Sample category
INSERT INTO categoria (id, nome) VALUES ('423e4567-e89b-12d3-a456-426614174003', 'Massas');

-- Sample product
INSERT INTO produto (id, nome, descricao, preco, id_categoria, imagem) VALUES 
('523e4567-e89b-12d3-a456-426614174004', 'Espaguete à Bolonhesa', 'Massa com molho bolonhesa clássico', 35.00, '423e4567-e89b-12d3-a456-426614174003', 'url_imagem_aqui');

