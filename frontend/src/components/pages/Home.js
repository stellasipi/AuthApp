import React, { Component } from 'react'
import axios from 'axios'
import PropTypes from 'prop-types'

class Home extends Component {

    static contextTypes = {
        fetchURL: PropTypes.string
    }

    state = {
        userDetails: ''
        
    }

    componentDidMount() {
        document.cookie = `JSESSIONID=${sessionStorage.getItem('session')}`
        //user deatails
        axios.get(this.context.fetchURL + 'user/details', {
            headers: {
                'content-Type': 'application/json',
                "Cache-Control": "no-cache",
                "Cookie": document.cookie
            },
            credentials: "same-origin",
            withCredentials: true
        })
            .then(
                res => this.setState({ userDetails: res.data })
            )
            .catch((error) => { console.log(error) });
    }

    rolesss = []

    render() {
        if (this.state.userDetails.roles) {
            this.rolesss = this.state.userDetails.roles.map((role) => { return <li key={role.id}>{role.description}</li> });
        }
        return (
            <div style={componentStyle}>
                <strong>Felhasználónév:</strong>
                {this.state.userDetails.username}<br />
                <strong>Csoportok:</strong>
                {this.rolesss}
                <strong>Utolsó belépés:</strong>
                {this.state.userDetails.lastLogin}
            </div>
        )
    }
}

const componentStyle = {
    display: 'flex',
    flexDirection: 'column',
    borderRadius: '8px',
    backgroundColor: '#f4f4f4',
    textAlign: 'left',
    margin: '10% 20%',
    padding: '1% 1%'
}

export default Home;
