import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Recaptcha from 'react-recaptcha';
import {Redirect} from 'react-router-dom';

class Login extends Component {
    state = {
        username: '',
        password: '',
        verified: false
    }

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onSubmit = (e) => {
        if (this.props.loginAttempt >= 3) {
            e.preventDefault();

            //pass fields
            this.props.login(this.state.username, this.state.password, this.state.verified);

            //clear fields
            this.setState({ username: '', password: '', verified: false });

        } else {
            e.preventDefault();
            //pass fields
            this.props.login(this.state.username, this.state.password, this.state.verified);

            //clear fields
            this.setState({ username: '', password: '', verified: false });
        }
    }

    recaptchaLoaded = () => {
        console.log('Captcha loaded succesfully');
    }

    verifyCallback = (response) => {
        this.setState({ verified: true })
    }

    render() {
        if (this.props.session.jsessionId) {
            return <Redirect to='/'/>
          }
        return (
            <form style={formStyle} onSubmit={this.onSubmit}>
                <h2 style={headerStyle}>Bejelentkezés</h2>
                <div className="form-group" style={inputStyle}>
                    <label htmlFor="username">Username:</label>
                    <input type="text" className="form-control" id="username" placeholder="Enter username" name="username" value={this.state.username} onChange={this.onChange} />
                </div>
                <div className="form-group" style={inputStyle}>
                    <label htmlFor="password">Password:</label>
                    <input type="password" className="form-control" id="password" placeholder="Enter password" name="password" value={this.state.password} onChange={this.onChange} />
                </div>
                {this.props.loginAttempt >= 3 ?
                    <div style={inputStyle}>
                        <Recaptcha
                            sitekey="6Lf-bqYZAAAAAKh0EAPP8FeBQRIlRYplaU_YllWh"
                            render="explicit"
                            onloadCallback={this.recaptchaLoaded}
                            verifyCallback={this.verifyCallback}
                        />
                    </div>
                    : <div></div>}
                <button type="submit" className="btn btn-primary" style={inputStyle}>Submit</button>
            </form>
        )
    }
}

const formStyle = {
    display: 'flex',
    flexDirection: 'column',
    borderRadius: '8px',
    backgroundColor: '#f4f4f4',
    textAlign: 'center',
    margin: '10% 20%'
}

const inputStyle = {
    flex: '1',
    margin: '16px 16px',
    padding: '1% 1%',
    alignSelf: 'center',
    width: '80%'
}

const headerStyle = {
    margin: '1% 1%',
    padding: '1% 1%'
}

Login.propTypes = {
    login: PropTypes.func.isRequired,
    loginAttempt: PropTypes.number.isRequired,
    session: PropTypes.any.isRequired 
}

export default Login;
