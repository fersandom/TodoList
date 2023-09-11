import { defineStore } from "pinia";
import axios from "axios";
import { ref } from "vue";
import { useRouter } from "vue-router";


export const useAuthStore = defineStore("auth", {

    state: () => ({
        authObject: ref({}),
        formData: ref({
            username: "",
            password: ""
        }),
        isAuthenticated:ref(false),
        router:useRouter()
    }),

    actions: {
        async login() {
            const response = await axios.post("http://localhost:8080/api/auth/login", this.formData)
            this.authObject = response.data;
            console.log(this.authObject);
            this.isAuthenticated=true;
            this.router.push('/')
        },
        async register() {
            const response = await axios.post("http://localhost:8080/api/auth/register", this.formData)
            this.authObject = response.data;
            console.log(this.authObject);
        },

        logout(){
            this.authObject = {},
            this.isAuthenticated = false;
            console.log(this.isAuthenticated);
            this.router.push("/auth")
        },

        checkAuth(){
            if(!this.isAuthenticated){
                this.router.push("/auth")
            }
        }
    }
});
