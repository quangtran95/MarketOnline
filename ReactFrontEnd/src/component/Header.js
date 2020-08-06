import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {Col, Container, Row, Button, FormGroup} from 'reactstrap';
import { Link, NavLink } from 'react-router-dom';

import '../assets/styles/Header.scss';



function Header() {
    let auth = useSelector(state => state.login.auth);
    const dispatch = useDispatch();

    const onLogOutHandler = () => {
        dispatch({
            type: 'LOGOUT',
            payLoad: null,
          });
    }
    return (
        <header className="header">
            <Container>
                <Row className="justify-content-between">
                    <Col xs="auto">
                        <a
                            className="header__link header__title"
                            rel="noopener noreferrer"
                        >Quang Tran</a>
                    </Col>
                    <Col xs="auto">
                        <FormGroup>
                            <NavLink to='/home'>Home</NavLink>
                            {auth && <NavLink to='/user'>User</NavLink> }
                            {auth ? <Button onClick={onLogOutHandler} color="primary" type="button">Log out</Button> : <NavLink to='/login'>Login</NavLink>}
                        </FormGroup>
                        
                    </Col>
                </Row>
            </Container>
        </header>
    );
}

export default Header;
