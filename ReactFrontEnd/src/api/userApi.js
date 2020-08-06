import axiosClient from './axiosClient';

const userApi = {
    getUserById: function(userId){
        if(userId){
            const url = `/user/get/${userId}`;
            return axiosClient.get(url, {});
        }
        else {
            return Promise.reject("error");
        }
    }
}

export default userApi;