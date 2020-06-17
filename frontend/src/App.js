import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './App.css';

class App extends Component {
  render() {
    return (
    <Router>
      <div className="App">
      <h4>Ide jön HEADER</h4>
        <Route exact path="/" render={props => (
          <React.Fragment>
            <h4>Ide jön a login</h4>
          </React.Fragment>
        )} />
        <Route path="/details" render={props => (
          <React.Fragment>
            <h4>Ide meg a bejelentkezés</h4>
          </React.Fragment>
        )} />
      </div>
    </Router>
    )
  };
}

export default App;
