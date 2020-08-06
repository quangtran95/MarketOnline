import axios from 'axios';
import queryString from 'query-string';

const axiosClient = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json',
    },
    paramsSerializer: params => queryString.stringify(params),
});


axiosClient.interceptors.request.use((config) => {
    config.headers.Authorization = localStorage.getItem("JWT_AUTHENTICATION_TOKEN");
    return config;
});

axiosClient.interceptors.response.use((response) => {
    return response;
}, (error) => {
    console.log(`Error:${error}`);
    return Promise.reject(error);
});

export default axiosClient;