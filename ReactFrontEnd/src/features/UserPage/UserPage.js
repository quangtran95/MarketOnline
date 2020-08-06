import React, {useState, useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
// import PropTypes from 'prop-types';

import { Table, Alert } from 'reactstrap';

import userApi from '../../api/userApi';
import { Redirect } from 'react-router-dom';

function UserPage() {
    const [loading, setLoading] = useState(true);
    const auth = useSelector(state => state.login.auth);
    const userLoginId = useSelector(state => state.login.userLoginId);
    const {/* id,  */firstName, lastName, address, phoneNumber} = useSelector(state => state.user.userData);
    const dispatch = useDispatch();
    

    useEffect(() => {
        userApi.getUserById(userLoginId)
        .then(respone => {
            if(respone.data){
                console.log(respone.data);
                setLoading(false);
                dispatch({
                    type: 'LOAD_USER_INFO',
                    payLoad: respone.data,
                })
            }
        })
        .catch(err => {
            if (err.response ) {
                if(err.response.status === 403){

                }
            }
            else {
                console.error(err);
            }
        })
    }, [])

    if(!auth){
        return <Redirect to="/"/>
    }

    if(loading){
        return (
            <h1>Loading data</h1>
        );
    }

    return(
        <Table>
            <tbody>
                <tr>
                    <td>First Name</td>
                    <td>{firstName}</td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td>{lastName}</td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td>{phoneNumber}</td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>{address}</td>
                </tr>
            </tbody>
        </Table>
    );

}

export default UserPage;