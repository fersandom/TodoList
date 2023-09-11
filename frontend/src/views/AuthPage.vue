<script setup>
import { useAuthStore } from '../stores/authStore'
import { ref } from 'vue';
const authStore = useAuthStore();
const isLogin = ref(true);
</script>

<template>
    <div class="container level notification">
        <h2 v-if="isLogin" class="title">Don't have an account?</h2>
        <h2 v-if="!isLogin" class="title">Already have an account?</h2>
        <span @click="isLogin = !isLogin" class="level-right subtitle pointer">Click here</span>
    </div>

    <div class="container mt-5">

        <form action="">
            <div class="field">
                <label for="username" class="label">Username</label>
                <div class="control">
                    <input v-model="authStore.formData.username" class="input" id="username" type="text" placeholder="username">
                </div>
            </div>
            <div class="field">
                <label  for="password" class="label">Password</label>
                <div class="control">
                    <input v-model="authStore.formData.password" class="input" id="password" type="password" placeholder="password">
                </div>
            </div>
            <button v-on:click.prevent="authStore.login()" v-if="isLogin" class="button is-medium is-info">Login</button>
            <button v-on:click.prevent="authStore.register()" v-if="!isLogin" class="button is-medium is-info">Register</button>

        </form>
    </div>
</template>



<style lang="scss" scoped>
.pointer {
    cursor: pointer;
}
</style>