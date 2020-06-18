import React, { Component } from 'react';
import { BrowserRouter, Route, Redirect, Switch } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import PropTypes from 'prop-types';
import axios from 'axios';
import swal from 'sweetalert';
import './App.css';
import PrivateRoute from "./components/PrivateRoute";
import Header from "./components/layouts/Header";
import Login from "./components/pages/Login";
import Home from "./components/pages/Home";
import SubPage from "./components/pages/SubPage";
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
    return { fetchURL }
  }

  state = {
    loginAttempt: 0,
    session: ''
  }

  // componentDidMount() {
  //   this.interval = setInterval(() => this.setState({ time: Date.now() }), 1000);
  // }
  // componentWillUnmount() {
  //   console.log("Timer");
  // }

  login = (username, password, verified) => {
    if (this.state.loginAttempt < 3 || (this.state.loginAttempt >= 3 && verified)) {
      axios.post(fetchURL + 'login', {
        username,
        password
      })
        .then(
          res => this.setState({
            session: res.data
          },
            () => (sessionStorage.setItem('session', this.state.session.jsessionId))
            /*,() => (swal("Siker", "Sikeresen bejelentkeztél", "success"))*/
          )
        )
        .catch(() => {
          this.setState({ loginAttempt: this.state.loginAttempt + 1 }, () => (swal("Upsz!", "A felhasználónév és/vagy a jelszó nem megfelelő.", "error")))
        });
    } else {
      swal("Upsz!", "Igazold magad, hogy nem vagy robot!", "warning")
    }
  }


  render() {
    if (this.state.session.jsessionId) {
      return <Redirect to={{
        pathname: '/'
      }}
      />
    }
    return (
      <BrowserRouter>
        <Header />
        <Container>
          <Switch>
            <PrivateRoute path="/" exact component={() => <Home />} />
            <PrivateRoute path="/admin" exact component={() => <SubPage subpage="admin" />} />
            <PrivateRoute path="/loggedInUser" exact component={() => <SubPage subpage="loggedInUser" />} />
            <PrivateRoute path="/contentEditor" exact component={() => <SubPage subpage="contentEditor" />} />
            <Route path='/login' component={() => <Login login={this.login} loginAttempt={this.state.loginAttempt} />} />
            <Route exact component={() => <NotFound />} />
          </Switch>
        </Container>
      </BrowserRouter>
    )
  };
}

export default App;
