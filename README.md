------------------------
COMO USAR ESTE PROJETO
------------------------

# Como executar as dependencia para o projeto
    1. execute na raiz do projeto onde esta o docker compose:
    --> docker compose up -d
    2. para parar execute
    --> docker compose down
    3. para ver mais informações use o docker ps ou docker inspect
    4. OPCIONAL MAS IMPORTANTE:
    você pode adicionar o pgadmin como serviço, escolha a imagem certa
    e as variaveis de ambiente e as portas mapeadas, exemplo do meu:

   pgadmin:
    image: dpage/pgadmin4
    container_name: pgadmin
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@admin.com
      PGADMIN_DEFAULT_PASSWORD: admin1234
    ports:
      - "5050:80"
    depends_on:
      - postgres
 

# Como compilar e executar e empacotar o projeto para war
    1. mvn clean compile
    2. mvn exec:java -Dexec.mainClass="com.atacadao.util.Teste"
    3. mvn clean package --também instala as dependencias

# como exportar e importar o banco de dados para o projeto
    1. abra a pasta BD que tera um backup do banco de dados estoque em 
    formato sql, nunca exclua ele!
    --> docker exec -t <container_do_postgres> pg_dump -U <usuario_do_banco> <nome_do_banco> > <nome_do_banco>.sql
    SEGUINDO OS DADOS QUE ESTÃO NO DOCKER COMPOSE IA FICAR:
    --> docker exec -t postgres pg_dump -U admin estoque > estoque_dump.sql
*
    2. como não há volumes entao deve ser feito importações do banco de dados que esta na pasta db
    ou outro backup que voce fez dele, para isso o comando:

    --> docker exec -i postgres psql -U admin -d estoque < bd/dump_estoque.sql 
    'lembre-se de estar na raiz do projeto'

    --ou use o pgadmin para importar os dados ou exportar mas para isso adicione o pgadmin
    como serviço no docker compose como sugerido antes

--criação de tabelas

-- Tabela principal com os dados comuns de todos os usuários
CREATE TABLE usuario (
    cpf CHAR(11) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(60) NOT NULL,
    celular VARCHAR(15),
    salario NUMERIC(10, 2)
);

-- Tabela de gerentes, cada gerente está associado a um CPF de usuário
CREATE TABLE gerente (
    id SERIAL PRIMARY KEY,
    cpf_usuario CHAR(11) UNIQUE NOT NULL,
    bonificacao NUMERIC(6, 2),
    FOREIGN KEY (cpf_usuario) REFERENCES usuario(cpf) ON DELETE CASCADE
);

-- Tabela de funcionários, vinculados a um usuário e a um gerente
CREATE TABLE funcionario (
    id SERIAL PRIMARY KEY,
    cpf_usuario CHAR(11) UNIQUE NOT NULL,
    id_gerente INTEGER NOT NULL,
    cargo VARCHAR(50),
    FOREIGN KEY (cpf_usuario) REFERENCES usuario(cpf),
    FOREIGN KEY (id_gerente) REFERENCES gerente(id)
);

-- Tabela de produtos, vinculados ao gerente que os cadastrou
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    quantidade INTEGER NOT NULL,
    id_gerente INTEGER NOT NULL,
    FOREIGN KEY (id_gerente) REFERENCES gerente(id)
);

CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    cnpj CHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    senha VARCHAR(60) NOT NULL
);

--inserts

INSERT INTO admin (cnpj, nome, email, telefone, senha)
VALUES ('12345678000199', 'Admin Master ATAC', 'admin@atac.com', '11999990000', 'admin1234');

INSERT INTO usuario (cpf, nome, email, senha, celular, salario) VALUES
('12345678900', 'Carlos Gerente', 'carlos@empresa.com', '1234', '11999999999', 7000.00),
('23456789001', 'Luciana Supervisora', 'luciana@empresa.com', '2345', '11888888888', 7500.00),
('34567890123', 'Bruno Func', 'bruno@empresa.com', '3456', '11777777777', 3200.00),
('45678901234', 'Ana Func', 'ana@empresa.com', '4567', '11666666666', 3000.00);

INSERT INTO gerente (cpf_usuario, bonificacao) VALUES
('12345678900', 20.00), -- Carlos
('23456789001', 0.00); -- Luciana

INSERT INTO funcionario (cpf_usuario, id_gerente, cargo) VALUES
('34567890123', 1, 'caixa'),
('45678901234', 2, 'repositor');

INSERT INTO produto (nome, valor, quantidade, id_gerente) VALUES
('Arroz 5kg', 22.50, 100, 1),
('Feijão 1kg', 7.80, 200, 1),
('Óleo de soja 900ml', 6.50, 150, 2),
('Açúcar 1kg', 4.90, 180, 2);

