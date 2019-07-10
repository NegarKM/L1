import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationBackend from './service/AuthenticationBackend';

class AuthenticatedRoute extends Component {
    render() {
        if (AuthenticationBackend.isUserLoggedIn()) {
            return <Route {...this.props} />
        } else {
            return <Redirect to="/login" />
        }
    }
}

export default AuthenticatedRoute