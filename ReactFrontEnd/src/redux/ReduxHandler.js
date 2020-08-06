import { createStore, combineReducers } from 'redux';

import loginReducer from './LoginReducer';
import userReducer from './UserReducer';



const rootReducer = combineReducers({
    login: loginReducer,
    user: userReducer
})

const store = createStore(rootReducer);
export default store;