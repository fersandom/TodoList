<script setup>
import { useTodoStore } from '../stores/todoStore'
import { useAuthStore } from '../stores/authStore'
import { useRouter } from 'vue-router';

const router = useRouter();

const todoStore = useTodoStore();
const authStore = useAuthStore()

authStore.checkAuth();

console.log(authStore.isAuthenticated);

</script>

<template>
  <RouterView />
  <div class="container">
    <div class="level">
      <h1 class="title is-1">Todos con un backend</h1>
      <button @click="authStore.logout()" class="level-right button is-danger">Logout</button>
    </div>

    <form action="">
      <input v-model="todoStore.formData.todoItem" type="text" placeholder="Your todo" class="input">
      <button v-on:click.prevent="todoStore.addTodo(todoStore.formData)" class="button is-link">Add todo</button>
    </form>

    <table class="table is-hoverable mt-5">
      <thead>
        <th>ID</th>
        <th>Item</th>
        <th>Finished?</th>
        <th>Finish</th>
        <th>Delete</th>
      </thead>
      <tbody>
        <tr v-for="item in todoStore.todos" :key="item.todoId">
          <td>{{ item.todoId }}</td>
          <td>{{ item.todoItem }}</td>
          <td>{{ item.isComplete }}</td>
          <td><button @click="todoStore.finishTodo(item.todoId)" class="button is-small is-success">Finish todo</button>
          </td>
          <td><button @click="todoStore.deleteTodo(item.todoId)" class="button is-small is-danger">Remove</button></td>
        </tr>
      </tbody>
    </table>

  </div>
</template>

<style></style>
