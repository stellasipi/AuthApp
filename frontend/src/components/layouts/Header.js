import React, { Component } from 'react'
import axios from 'axios'
import PropTypes from 'prop-types'

class Header extends Component {

    static contextTypes = {
        fetchURL: PropTypes.string
    }

    state = {
        pages: []
    }

    componentDidMount() {
        //pages
        if (sessionStorage.getItem('session')) {
            axios.get(this.context.fetchURL + 'user/pages', {
                headers: {
                    'content-Type': 'application/json',
                    "Cache-Control": "no-cache",
                    "Cookie": document.cookie
                },
                credentials: "same-origin",
                withCredentials: true
            })
                .then(
                    res => this.setState({ pages: res.data })
                )
                .catch((error) => { console.log(error) });
        }
    }

    render() {
        if (sessionStorage.getItem('session')) {
            return (
                <header>
                    <nav className="navbar navbar-light bg-light">
                        <a className="navbar-brand" style={headerStyle} href="/">AuthApp</a>
                    </nav>
                </header>
            )
        }

        return (
            <header>
                <nav className="navbar navbar-light bg-light">
                    <a className="navbar-brand" style={headerStyle} href="/">AuthApp</a>
                </nav>
            </header>
        )
    }
}

const headerStyle = {
    color: '#545554'
}

export default Header;