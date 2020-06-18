import React, { Component } from 'react'
import axios from 'axios'
import PropTypes from 'prop-types'

class Home extends Component {

    fetchURL = 'http://localhost:8080/';

    state = {
      userDetails:''
    }

    

    componentDidMount(){
        axios.get(this.fetchURL+'user/details', {
            // headers: {
            //     Cookie: 'JSESSIONID=6A0AC7361076C11FE6D67843B16A1543'// + this.props.session.jsessionId //the token is a variable which holds the token
            // }
            withCredentials: true
           })
        .then(res=>this.setState({userDetails: res.data}))
    }

    render() {
        return (
            <div>
                Home
            </div>
        )
    }
}

Home.propTypes={
    session: PropTypes.object.isRequired
}

export default Home;
