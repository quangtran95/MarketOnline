import axiosClient from './axiosClient';

const loginApi = {
    login: function(username, password){
        // const params = {
        //     username: username,
        //     password: password,
        // };
        const url = `/login/${username}/${password}`;
        return axiosClient.get(url, {});
    },

    signUp: function(username, password){
        // const params = {
        //     username: username,
        //     password: password,
        // };
        const url = `/signUp/${username}/${password}`;
        return axiosClient.get(url, {});
    }
}

export default loginApi;