import React, { Component } from 'react'
import axios from 'axios'
import PropTypes from 'prop-types'
import "bootstrap/js/src/collapse.js"

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

    pageTitles = []

    render() {
        if (this.state.pages) {
            this.pageTitles = this.state.pages.map((page) => {
                return <a key={page.id} className="nav-item nav-link" href={'/' + page.name}>{page.title}</a>
            });
        }
        if (sessionStorage.getItem('session')) {
            return (
                <header>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <a className="navbar-brand" style={headerStyle} href="/">AuthApp</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="true" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div className="navbar-nav">
                            {this.pageTitles}
                        </div>
                        <button type="button" className="btn btn-outline-dark">Kijelentkezés</button>
                    </div>
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