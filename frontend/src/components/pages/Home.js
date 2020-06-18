import React, { Component } from 'react'
import axios from 'axios'
import PropTypes from 'prop-types'

class Home extends Component {

    static contextTypes = {
        fetchURL: PropTypes.string,
        //session: PropTypes.object
    }

    state = {
      userDetails:''
    }

    componentDidMount(){
        document.cookie=`JSESSIONID=${sessionStorage.getItem('session')}`
        //axios.defaults.withCredentials = true;
        axios.get(this.context.fetchURL+'user/details', {
            headers: {
                'content-Type': 'application/json',
                "Cache-Control": "no-cache",
                "Cookie": document.cookie
                //Authorization: `JSESSIONID=AED67599AF546A8020ABC8B79623E8F0`
            },
            credentials: "same-origin",
            withCredentials: true
           })
        .then(res=>this.setState({userDetails: res.data}),console.log(sessionStorage.getItem('session')))
        .catch((error)=>{console.log("bad :(")})
    }

    render() {
        return (
            <div>
                Home <br/>
                Felhasználónév: {this.state.userDetails.username}
            </div>
        )
    }
}

export default Home;
