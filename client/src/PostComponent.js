import React, { Component } from 'react'
import './ShowPostsApp.css';

class PostComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            text: props.data.text,
            username: props.data.username,
            timestamp: props.data.timestamp
        }
    }

    refresh() {
        console.log('refresh ' + this.props.key);
        this.setState({
                    text: this.props.data.text,
                    username: this.props.data.username,
                    timestamp: this.props.data.timestamp,
                    nothing: 'nothing'
                    }
        )
    }

    render() {
        return (
            <div>
                <div className="PostsText">
                    <p>{this.state.text}</p>
                </div>
                <div className="PostsDetails">
                    <p>posted by {this.state.username} on {this.state.timestamp}</p>
                </div>
                <div>
                    <button onClick={ (e) => { this.clickedForReply(this); } }>REPLY</button>
                </div>
            </div>
        );
    }
}

export default PostComponent;