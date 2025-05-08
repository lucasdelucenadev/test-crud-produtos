<template>
  <div class="container mt-4">
    <h2>Lista de Produtos</h2>
    <div class="mb-3">
      <button class="btn btn-primary" @click="showForm = true">
        Novo Produto
      </button>
    </div>

    <div class="table-responsive">
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Responsável</th>
            <th>Categoria</th>
            <th>Data Criação</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="produto in produtos" :key="produto.id">
            <td>{{ produto.id }}</td>
            <td>{{ produto.nome }}</td>
            <td>{{ produto.descricao }}</td>
            <td>{{ produto.responsavel }}</td>
            <td>{{ produto.categoria }}</td>
            <td>{{ formatDate(produto.dataCriacao) }}</td>
            <td>
              <button class="btn btn-sm btn-info me-2" @click="editarProduto(produto)">
                Editar
              </button>
              <button class="btn btn-sm btn-danger" @click="confirmarExclusao(produto)">
                Excluir
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Paginação -->
    <nav v-if="totalPages > 1">
      <ul class="pagination justify-content-center">
        <li class="page-item" :class="{ disabled: currentPage === 0 }">
          <a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)">Anterior</a>
        </li>
        <li v-for="page in totalPages" :key="page" class="page-item" :class="{ active: currentPage === page - 1 }">
          <a class="page-link" href="#" @click.prevent="changePage(page - 1)">{{ page }}</a>
        </li>
        <li class="page-item" :class="{ disabled: currentPage === totalPages - 1 }">
          <a class="page-link" href="#" @click.prevent="changePage(currentPage + 1)">Próxima</a>
        </li>
      </ul>
    </nav>

    <!-- Modal de Formulário -->
    <div class="modal fade" :class="{ show: showForm }" tabindex="-1" :style="{ display: showForm ? 'block' : 'none' }">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ produtoEditando ? 'Editar Produto' : 'Novo Produto' }}</h5>
            <button type="button" class="btn-close" @click="showForm = false"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="salvarProduto">
              <div class="mb-3">
                <label class="form-label">Nome</label>
                <input type="text" class="form-control" v-model="produtoForm.nome" required>
              </div>
              <div class="mb-3">
                <label class="form-label">Descrição</label>
                <textarea class="form-control" v-model="produtoForm.descricao"></textarea>
              </div>
              <div class="mb-3">
                <label class="form-label">Responsável</label>
                <input type="text" class="form-control" v-model="produtoForm.responsavel" required>
              </div>
              <div class="mb-3">
                <label class="form-label">Categoria</label>
                <input type="text" class="form-control" v-model="produtoForm.categoria">
              </div>
              <div class="text-end">
                <button type="button" class="btn btn-secondary me-2" @click="showForm = false">Cancelar</button>
                <button type="submit" class="btn btn-primary">Salvar</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="modal-backdrop fade" :class="{ show: showForm }" :style="{ display: showForm ? 'block' : 'none' }"></div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ProdutoList',
  data() {
    return {
      produtos: [],
      currentPage: 0,
      pageSize: 10,
      totalPages: 0,
      showForm: false,
      produtoEditando: null,
      produtoForm: {
        nome: '',
        descricao: '',
        responsavel: '',
        categoria: ''
      }
    };
  },
  created() {
    this.carregarProdutos();
  },
  methods: {
    async carregarProdutos() {
      try {
        const response = await axios.get(`http://localhost:8080/api/produtos?page=${this.currentPage}&size=${this.pageSize}`);
        this.produtos = response.data.content;
        this.totalPages = response.data.totalPages;
      } catch (error) {
        console.error('Erro ao carregar produtos:', error);
        alert('Erro ao carregar produtos');
      }
    },
    formatDate(date) {
      return new Date(date).toLocaleDateString();
    },
    changePage(page) {
      if (page >= 0 && page < this.totalPages) {
        this.currentPage = page;
        this.carregarProdutos();
      }
    },
    editarProduto(produto) {
      this.produtoEditando = produto;
      this.produtoForm = { ...produto };
      this.showForm = true;
    },
    async salvarProduto() {
      try {
        if (this.produtoEditando) {
          await axios.put(`http://localhost:8080/api/produtos/${this.produtoEditando.id}`, this.produtoForm);
        } else {
          await axios.post('http://localhost:8080/api/produtos', this.produtoForm);
        }
        this.showForm = false;
        this.produtoEditando = null;
        this.produtoForm = {
          nome: '',
          descricao: '',
          responsavel: '',
          categoria: ''
        };
        this.carregarProdutos();
      } catch (error) {
        console.error('Erro ao salvar produto:', error);
        alert('Erro ao salvar produto');
      }
    },
    async confirmarExclusao(produto) {
      if (confirm(`Tem certeza que deseja excluir o produto "${produto.nome}"?`)) {
        try {
          await axios.delete(`http://localhost:8080/api/produtos/${produto.id}`);
          this.carregarProdutos();
        } catch (error) {
          console.error('Erro ao excluir produto:', error);
          alert('Erro ao excluir produto');
        }
      }
    }
  }
};
</script>

<style scoped>
.modal {
  background-color: rgba(0, 0, 0, 0.5);
}
</style> 