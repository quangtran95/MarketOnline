import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { Formik, Form, FastField } from 'formik';
import { FormGroup, Container, Button } from 'reactstrap';
import * as Yup from 'yup';
import {Redirect, Link} from 'react-router-dom';

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
    }

    const validationSchema = Yup.object().shape({
      username: Yup.string().required('This field is required!'),
      password: Yup.string().required('This field is required!'),
    })
  
    const submitForm = (formValues) => {
      loginApi.login(formValues.username, formValues.password)
      .then(respone => {
        if(respone.data){
          dispatch({
            type: 'LOGIN',
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
            // console.log({values, errors, touched});
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

                {/* <FastField
                  name="options"
                  component={SelectField}

                  label="Options"
                  placeholder="options"
                  options={[
                    {value: 1, label: 'AAAA'},
                    {value: 2, label: 'BBBBB'},
                    {value: 3, label: 'CCCC'},
                  ]}
                /> */}

                {/* <FastField
                  name="randomPhoto"
                  component={RandomPhotoField}

                  label="Random Photo"
                />   */}

                <FormGroup>
                  <Button color="primary" type="submit">Submit</Button>
                  <Link to="/signup">Sign Up</Link>
                </FormGroup>
              </Form>
            )
          }}
        </Formik>
    );
  }
  
  export default LoginPage;