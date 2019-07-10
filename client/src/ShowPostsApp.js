import React, { Component } from 'react';
import './ShowPostsApp.css';
import ShowPostsBackend from './service/ShowPostsBackend';

class ShowPostsApp extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        };
    }

    refresh() {
        ShowPostsBackend.retrieveAllPosts().then(response => {
               this.setState({
                   data: response
               })
            })
    };

    componentDidMount() {
        console.log('componentDidMount');
        this.refresh();
    }


    render() {
        if (!this.state.data) {
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
                        <li key = {idx}>
                            <div className="PostsText">
                                <p>{d.text}</p>
                            </div>
                            <div className="PostsDetails">
                                <p>posted by {d.username} on {d.timestamp}</p>
                            </div>
                        </li>
                    )
                })}
            </div>
        );
    }
}

export default ShowPostsApp;