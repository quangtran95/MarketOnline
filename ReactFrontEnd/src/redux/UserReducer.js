const initialState = {
    userData: {
        id: null,
        firstName: '',
        lastName: '',
        address: '',
        phoneNumber: '',
    },
}

const userReducer = (state = initialState, action) => {
    switch(action.type){
        case 'LOAD_USER_INFO':{
            if(action.payLoad){
                return {
                    ...state,
                    userData: action.payLoad,
                }
            }
            break;
        }
        default:
            return state;
    }
}

export default userReducer;