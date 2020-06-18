import React from 'react'

function Header() {
    return (
        <header>
            <nav className="navbar navbar-light bg-light">
                <a className="navbar-brand" style={headerStyle} href="/">AuthApp</a>
            </nav>
        </header>
    )
}

const headerStyle = {
    color: '#545554'
}

export default Header;
