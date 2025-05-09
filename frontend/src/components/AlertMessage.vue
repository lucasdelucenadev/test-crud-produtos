<template>
  <div v-if="show" :class="['alert', `alert-${type}`, 'alert-dismissible', 'fade', 'show']" role="alert">
    {{ message }}
    <button type="button" class="btn-close" @click="close" aria-label="Close"></button>
  </div>
</template>

<script>
export default {
  name: 'AlertMessage',
  props: {
    message: {
      type: String,
      required: true
    },
    type: {
      type: String,
      default: 'info',
      validator: (value) => ['success', 'danger', 'warning', 'info'].includes(value)
    },
    duration: {
      type: Number,
      default: 5000
    }
  },
  data() {
    return {
      show: true
    };
  },
  mounted() {
    if (this.duration > 0) {
      setTimeout(() => {
        this.close();
      }, this.duration);
    }
  },
  methods: {
    close() {
      this.show = false;
      this.$emit('close');
    }
  }
};
</script>

<style scoped>
.alert {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
  min-width: 300px;
  max-width: 500px;
}
</style> 