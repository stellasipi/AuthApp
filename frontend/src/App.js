import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
//import { browserHistory } from 'react-router';
import PropTypes from 'prop-types';
import axios from 'axios';
import './App.css';
import Header from "./components/layouts/Header";
//import Alert from "./components/layouts/Alert";
import Login from "./components/pages/Login";
import Home from "./components/pages/Home";

const fetchURL = 'http://localhost:8080/';


class App extends Component {

  static childContextTypes = {
    fetchURL: PropTypes.string
  }

  getChildContext() {
    return { fetchURL }
  }

  state = {
    session: '',
    loginAttempt: 0
  }

  login=(username, password)=>{
    axios.post(fetchURL + 'login', {
      username,
      password
    })
    .then(
      res => this.setState({ session: res.data })
    )
    .catch(error => {
      console.log(error)
      //<Alert />
      //this.state.loginAttempt+=1
    });
  }

  render() {
    return (
    <Router>
      <div className="App">
        <Header />
        <Route exact path="/" render={props => (
          <React.Fragment>
            <Home session={this.state.session}/>
          </React.Fragment>
        )} />
        <Route path="/login" render={props => (
          <React.Fragment>
            <Login login={this.login}/>
          </React.Fragment>
        )} />
      </div>
    </Router>
    )
  };
}

export default App;
