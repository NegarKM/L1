import React, { Component } from 'react';
import './HelloWorldApp.css';
import * as Backend from './service/HelloWorldBackend';

class HelloWorldApp extends Component {
    constructor(props) {
        super(props);

        this.state = {
            greetings : ''
        }
    }

    componentDidMount() {
        console.log('componentDidMount');
        Backend.getJson().then(response => {
            this.setState({
                greetings: response
            })
        })
    }

    render() {
        return (
            <div className="HelloWorldApp">
                <p className="HelloWorldApp-intro">
                    {this.state.greetings}
                </p>
            </div>
        );
    }
}

export default HelloWorldApp;
