import React, { Component } from 'react';
import './ShowPostsApp.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LoginComponent from './LoginComponent'
import ShowPostsBackend from './service/ShowPostsBackend';
import PostComponent from './PostComponent';

class ShowPostsApp extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        };

        this.refreshAll = this.refreshAll.bind(this);
    }

    refresh() {
        console.log('refresh!!!!');
        ShowPostsBackend.retrieveAllPosts().then(response => {
                if (response.hasError) {
                    this.setState({error: response.error})
                } else {
                   this.setState({
                       data: response.data
                   })
                }
            })
    };

    componentDidMount() {
        console.log('componentDidMount');
        this.refresh();
    }

    refreshAll() {
        console.log('refreshAll called');
        this.setState({changed: true});
    }

    render() {
        if (!this.state.error && !this.state.data) {
            return (
                <div>
                    Add some posts!
                </div>
            );
        }
        return (
            <div>
                {this.state.data.map(function (d, idx) {
                    return (
                        <div>
                        <PostComponent key={d.text} data={d} ref={idx}/>
                        </div>
                    )
                }, this)}
            </div>
        );
    }
}

export default ShowPostsApp;