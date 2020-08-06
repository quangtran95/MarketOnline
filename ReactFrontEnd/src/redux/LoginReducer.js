const initialState = {
    auth: false,
    userLoginId: null,
}

const loginReducer = (state = initialState, action) => {
    switch(action.type){
        case 'LOGIN':{
            if(action.payLoad){
                localStorage.setItem("JWT_AUTHENTICATION_TOKEN", action.payLoad.token);
                return {
                    ...state,
                    auth: true,
                    userLoginId: action.payLoad.userId,
                }
            }
            break;
        }
        case 'LOGOUT': {
            localStorage.setItem("JWT_AUTHENTICATION_TOKEN", "");
            return {
                ...state,
                auth: false
            }
        }  
        default:
            return state;
    }
}

export default loginReducer;