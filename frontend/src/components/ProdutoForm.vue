<template>
  <Form @submit="onSubmit" :validation-schema="schema" v-slot="{ errors }">
    <div class="mb-3">
      <label for="nome" class="form-label">Nome</label>
      <Field
        name="nome"
        type="text"
        class="form-control"
        :class="{ 'is-invalid': errors.nome }"
        v-model="formData.nome"
      />
      <div class="invalid-feedback">{{ errors.nome }}</div>
    </div>

    <div class="mb-3">
      <label for="descricao" class="form-label">Descrição</label>
      <Field
        name="descricao"
        type="text"
        class="form-control"
        :class="{ 'is-invalid': errors.descricao }"
        v-model="formData.descricao"
      />
      <div class="invalid-feedback">{{ errors.descricao }}</div>
    </div>

    <div class="mb-3">
      <label for="responsavel" class="form-label">Responsável</label>
      <Field
        name="responsavel"
        type="text"
        class="form-control"
        :class="{ 'is-invalid': errors.responsavel }"
        v-model="formData.responsavel"
      />
      <div class="invalid-feedback">{{ errors.responsavel }}</div>
    </div>

    <div class="mb-3">
      <label for="categoria" class="form-label">Categoria</label>
      <Field
        name="categoria"
        type="text"
        class="form-control"
        :class="{ 'is-invalid': errors.categoria }"
        v-model="formData.categoria"
      />
      <div class="invalid-feedback">{{ errors.categoria }}</div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
  </Form>
</template>

<script>
import { Form, Field } from 'vee-validate';
import * as yup from 'yup';

export default {
  name: 'ProdutoForm',
  components: {
    Form,
    Field
  },
  props: {
    produto: {
      type: Object,
      default: () => ({
        nome: '',
        descricao: '',
        responsavel: '',
        categoria: ''
      })
    }
  },
  data() {
    return {
      formData: { ...this.produto },
      schema: yup.object({
        nome: yup.string().required('Nome é obrigatório'),
        descricao: yup.string().max(1000, 'Descrição deve ter no máximo 1000 caracteres'),
        responsavel: yup.string().required('Responsável é obrigatório'),
        categoria: yup.string().required('Categoria é obrigatória')
      })
    };
  },
  methods: {
    onSubmit(values) {
      this.$emit('submit', values);
    }
  },
  watch: {
    produto: {
      handler(newVal) {
        this.formData = { ...newVal };
      },
      deep: true
    }
  }
};
</script> 