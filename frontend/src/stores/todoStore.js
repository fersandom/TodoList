import { defineStore } from "pinia";
import axios from "axios";
import { ref } from "vue";

export const useTodoStore = defineStore("todos", {
    state: () => ({
        todos: ref([]),
        formData: ref({
            todoItem: ""
        })
    }),

    actions: {
        async getTodos() {
            const response = await axios.get("http://localhost:8080/api/todo/");
            this.todos = response.data;
        },

        async addTodo() {
            await axios.post("http://localhost:8080/api/todo/create", this.formData)
            .then(function(response){
                console.log(response);
            });

            this.newTodo = "";
            this.getTodos();
        },

        async finishTodo(id) {
            await axios.patch(`http://localhost:8080/api/todo/isfinished/${id}`);
            this.getTodos();
        },

        async deleteTodo(id){
            await axios.delete(`http://localhost:8080/api/todo/delete/${id}`)
            this.getTodos();
        }
    }
})