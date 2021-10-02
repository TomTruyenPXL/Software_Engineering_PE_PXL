import axios from 'axios';

const BASE_URL = "http://localhost:8888";

export default class API {
    static async register(email, password) {
        return await axios.post(`${BASE_URL}/register`, {
            email, password
        });
    }

    static async login(email, password) {
        return await axios.post(`${BASE_URL}/login`, {
            email, password
        });
    }
}