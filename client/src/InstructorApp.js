import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import AuthenticatedRoute from './AuthenticatedRoute';
import LoginComponent from './LoginComponent';
import AddPostsApp from './AddPostsApp';

class InstructorApp extends Component {
   render() {
        return (
            <Router>
                <Switch>
                    <Route path="/" exact component={LoginComponent} />
                    <Route path="/login" exact component={LoginComponent} />
                    <AuthenticatedRoute path="/addPosts" exact component={AddPostsApp} />
                </Switch>
            </Router>
        )
    }
}

export default InstructorApp