CREATE TABLE categories (
    id SERIAL PRIMARY KEY;
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY;
    name VARCHAR(255) NOT NULL,
    description TEXT;
    price DECIMAL(10, 2) NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO categories(name) VALUES
('Eletrónicos'),
('Roupas'),
('Alimentos'),
('Móveis'),
('Livros');

INSERT INTO products (name, description, price, category_id) VALUES
('Notebook Dell', 'Notebook ultraleve com processador i7', 4500.00, 1),
('Smartphone Samsung', 'Celular top de linha com câmera tripla', 2500.00, 1),
('Camiseta Básica', 'Camiseta branca de algodão', 39.90, 2),
('Calça Jeans', 'Calça jeans azul clara', 129.90, 2),
('Arroz Tipo 1', 'Pacote de arroz de 5kg', 25.50, 3),
('Feijão Preto', 'Pacote de feijão preto de 1kg', 8.90, 3),
('Sofá Retrátil', 'Sofá retrátil de couro sintético', 1500.00, 4),
('Cadeira de Escritório', 'Cadeira ergonômica giratória', 450.00, 4),
('Dom Casmurro', 'Romance clássico de Machado de Assis', 29.90, 5),
('O Pequeno Príncipe', 'Livro infantil de Antoine de Saint-Exupéry', 35.90, 5);