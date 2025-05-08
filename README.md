# Sistema CRUD de Produtos

Este é um sistema de gerenciamento de produtos desenvolvido com Spring Boot (backend) e Vue.js (frontend).

## Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

### Frontend
- Vue.js 3
- Bootstrap 5
- Axios

## Requisitos

- Java 17
- Maven
- Node.js 18+
- Docker e Docker Compose (opcional)

## Executando com Docker Compose (Recomendado)

1. Clone o repositório:
```bash
git clone https://github.com/lucasdelucenadev/test-crud-produtos.git
cd test-crud-produtos
```

2. Execute o ambiente completo:
```bash
docker-compose up -d
```

A aplicação estará disponível em:
- Frontend: http://localhost
- Backend API: http://localhost:8080

## Executando Localmente

### Backend

1. Configure o PostgreSQL:
```sql
CREATE DATABASE produtos_db;
```

2. Configure as credenciais do banco de dados em `src/main/resources/application.properties`

3. Execute o projeto:
```bash
mvn spring-boot:run
```

### Frontend

1. Entre no diretório do frontend:
```bash
cd frontend
```

2. Instale as dependências:
```bash
npm install
```

3. Execute o servidor de desenvolvimento:
```bash
npm run dev
```

## Estrutura do Projeto

```
test-crud-produtos/
├── src/                    # Código fonte do backend
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/testcrudprodutos/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   └── test/
├── frontend/              # Código fonte do frontend
│   ├── src/
│   │   ├── components/
│   │   └── App.vue
│   └── package.json
├── Dockerfile            # Dockerfile do backend
├── docker-compose.yml    # Configuração do Docker Compose
└── pom.xml              # Dependências do Maven
```

## API Endpoints

- `GET /api/produtos` - Lista todos os produtos (com paginação)
- `GET /api/produtos/{id}` - Busca um produto por ID
- `POST /api/produtos` - Cria um novo produto
- `PUT /api/produtos/{id}` - Atualiza um produto existente
- `DELETE /api/produtos/{id}` - Remove um produto (soft delete)

## Funcionalidades

- CRUD completo de produtos
- Paginação na listagem
- Soft delete
- Interface responsiva
- Validação de dados
- Integração com PostgreSQL

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request 