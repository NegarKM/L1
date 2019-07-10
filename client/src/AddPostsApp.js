import React, { Component } from 'react';
import AddPostsBackend from './service/AddPostsBackend';
import ShowPostsApp from './ShowPostsApp';
import './AddPostsApp.css';

class AddPostsApp extends Component {
    constructor(props) {
        super(props);

        this.state = {
            text : '',
        };

    }

    clickedForPost(thisForm) {
        console.log('clicked ' + thisForm.refs.textBox.value);
        AddPostsBackend.addPost(thisForm.refs.textBox.value).then(response => {
            this.setState({
                text: response
            })

            this.refs.showPost.refresh();

        })
    }

    render() {
        return (
            <div>
                <div>
                    <p>
                        <input ref="textBox" type="text" className="PostTextArea" maxLength = "256"/>
                        <button onClick={ (e) => { this.clickedForPost(this); } }>POST</button>
                    </p>
                </div>

                <div>
                    <ShowPostsApp ref="showPost"/>
                </div>
            </div>
        );
    }
}

export default AddPostsApp;