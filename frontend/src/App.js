import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import PropTypes from 'prop-types';
import axios from 'axios';
import swal from 'sweetalert';
import './App.css';
import PrivateRoute from "./components/PrivateRoute";
import Header from "./components/layouts/Header";
import Login from "./components/pages/Login";
import Home from "./components/pages/Home";
import NotFound from "./components/pages/NotFound";

const fetchURL = 'http://localhost:8080/';


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      loginAttempt: 0,
      session: ''
    }
  }

  static childContextTypes = {
    fetchURL: PropTypes.string
  }

  getChildContext() {
    return { fetchURL}
  }

  state = {
    loginAttempt: 0,
    session: ''
  }

  login = (username, password) => {
    axios.post(fetchURL + 'login', {
      username,
      password
    })
      .then(
        res => this.setState({ session: res.data }, () => (sessionStorage.setItem('session', this.state.session.jsessionId)))
      )
      .catch(error => {
        swal("Upsz!", /*`error: ${error}`*/"A felhasználónév és/vagy a jelszó nem megfelelő.", "error")
      });
  }



  render() {
    if (this.state.session.jsessionId) {
      return <Redirect to={{
        pathname: '/'
      }}
      />
    }
    return (
      <Router>
        <Header/>
        <Container>
          <Switch>
            <PrivateRoute path="/" exact component={() => <Home />} />
            <Route path='/login' component={() => <Login login={this.login} />} />
            <Route exact component={() => <NotFound />} />
          </Switch>
        </Container>
      </Router>
    )
  };
}

export default App;
