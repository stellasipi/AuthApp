import React, { Component } from 'react'
import PropTypes from 'prop-types';
import axios from 'axios';

class SubPage extends Component {

    static contextTypes = {
        fetchURL: PropTypes.string
    }

    state={
        page:''
    }

    componentDidMount() {
        document.cookie = `JSESSIONID=${sessionStorage.getItem('session')}`
        //user deatails
        axios.get(this.context.fetchURL + 'pages/'+this.props.subpage, {
            headers: {
                'content-Type': 'application/json',
                "Cache-Control": "no-cache",
                "Cookie": document.cookie
            },
            credentials: "same-origin",
            withCredentials: true
        })
            .then(
                res => this.setState({ page: res.data })
            )
            .catch((error) => { console.log(error) });
    }


    render() {
        if(this.state.page.title){
        return (
            <div style={componentStyle}>
                <h2 style={textStyle}>{this.state.page.title}</h2>
                <div style={textStyle}>{this.state.page.message}</div>
            </div>
        )
        }
        return(
            <div style={componentStyle}>
                <h3 style={textStyle}>Ehhez a felülethez nincs jogosultságod</h3>
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

const textStyle = {
    margin: '1% 1%',
    padding: '1% 1%'
}

SubPage.propTypes={
    subpage: PropTypes.string.isRequired
}

export default SubPage;
