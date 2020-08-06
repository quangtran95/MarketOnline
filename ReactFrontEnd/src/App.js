import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  // Link,
  // Redirect,
  // useHistory,
  // useLocation
} from "react-router-dom";

import { Container } from 'reactstrap';

import Header from './component/Header';
import HomePage from './features/HomePage/HomePage';
import LoginPage from './features/LoginPage/LoginPage';
import UserPage from './features/UserPage/UserPage';
import SignUpPage from './features/LoginPage/SignUpPage';

function App() {
  return (
    <div>
      <Router>
        <Header></Header>
        <Container>
          <Switch>
            <Route exact path="/"><HomePage/></Route>
            <Route exact path="/home"><HomePage/></Route>
            <Route exact path="/login"><LoginPage/></Route>
            <Route exact path="/user"><UserPage/></Route>
            <Route exact path="/signup"><SignUpPage/></Route>
          </Switch>
        </Container>
      </Router>

    </div>
  );
}

export default App;
