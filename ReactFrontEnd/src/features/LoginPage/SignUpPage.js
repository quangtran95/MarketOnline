import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { Formik, Form, FastField } from 'formik';
import { FormGroup, Container, Button } from 'reactstrap';
import * as Yup from 'yup';
import {Redirect} from 'react-router-dom';

//Api
import loginApi from '../../api/loginApi';

//Component
import InputField from '../../component/InputField';

function LoginPage() {
    const auth = useSelector(state => state.login.auth);
    const dispatch = useDispatch();

    if(auth){
      return (
        <h1>You logged in</h1>
      );
    }

    const initialValues = {
      username: 'tom',
      password: '123',
      confirmPassword: '123'
    }

    const validationSchema = Yup.object().shape({
      username: Yup.string().required('This field is required!'),
      password: Yup.string().required('This field is required!'),
      confirmPassword: Yup.string().oneOf([Yup.ref('password'), null], 'Passwords must match'),
    })
  
    const submitForm = (formValues) => {
      loginApi.login(formValues.username, formValues.password)
      .then(respone => {
        if(respone.data){
          dispatch({
            type: 'SIGNUP',
            payLoad: respone.data,
          })
        }
        console.log(respone);
        
        
      }).catch(err => {
        if (err.response ) {
          if(err.response.status === 401){
            console.log("login error 401");
          }
        }
        else {
          console.error(err.response);
        }
      });
    }
    return (
        <Formik 
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={values => submitForm(values)}
        > 
          {formikProps => {
            const {values, errors, touched} = formikProps;
            return(
              <Form>
                <FastField
                  name="username"
                  component={InputField}

                  label="Username"
                  placeholder="user name"
                />
                
                <FastField
                  name="password"
                  component={InputField}

                  type="password"
                  label="Password"
                  placeholder="password"
                />

                <FastField
                  name="confirmPassword"
                  component={InputField}

                  type="password"
                  label="Confirm Password"
                  placeholder="password"
                />

                <FormGroup>
                  <Button color="primary" type="submit">Sign Up</Button>
                </FormGroup>
              </Form>
            )
          }}
        </Formik>
    );
  }
  
  export default LoginPage;