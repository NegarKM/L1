import React, { Component } from 'react';
import AddPostsBackend from './service/AddPostsBackend';

class AddPostsApp extends Component {
    constructor() {
        super();

        this.state = {
            text : ''
        };
    }

    clickedForPost(thisForm) {
        console.log('clicked ' + thisForm.refs.textBox.value);
        AddPostsBackend.addPost(thisForm.refs.textBox.value).then(response => {
            this.setState({
                text: response
            })
        })
    }

    render() {
        return (
            <div>
                <div>
                    <p>
                        <input ref="textBox" type="text"/>
                        <button onClick={ (e) => { this.clickedForPost(this); } }>POST</button>
                    </p>
                </div>
                <div>
                    <p>
                        {this.state.text}
                    </p>
                </div>
            </div>
        );
    }
}

export default AddPostsApp;