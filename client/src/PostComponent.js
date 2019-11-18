import React, { Component } from 'react'
import './ShowPostsApp.css';

class PostComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            text: props.data.text,
            username: props.data.username,
            timestamp: props.data.timestamp,
            cityName: props.data.cityVO == null ? null : props.data.cityVO.name,
            latitude: props.data.cityVO == null ? null : props.data.cityVO.latitude,
            longitude: props.data.cityVO == null ? null : props.data.cityVO.longitude,
            temperature: props.data.cityVO == null ? null : props.data.cityVO.temperature
        }
    }

    refresh() {
        console.log('refresh ' + this.props.key);
        this.setState({
                    text: this.props.data.text,
                    username: this.props.data.username,
                    timestamp: this.props.data.timestamp,
                    cityName: this.props.data.cityVO == null ? null : this.props.data.cityVO.name,
                    latitude: this.props.data.cityVO == null ? null : this.props.data.cityVO.latitude,
                    longitude: this.props.data.cityVO == null ? null : this.props.data.cityVO.longitude,
                    temperature: this.props.data.cityVO == null ? null : this.props.data.cityVO.temperature,
                    nothing: 'nothing'
                    }
        )
    }

    render() {
        if (this.state.cityName == null) {
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
        } else {
            return (
                <div>
                    <div className="PostsText">
                        <p>{this.state.text}</p>
                    </div>
                    <div className="PostsDetails">
                        <p>posted by {this.state.username} on {this.state.timestamp}</p>
                    </div>
                    <div className="CityDetails">
                        <p>posted from {this.state.cityName} : {this.state.latitude}    {this.state.longitude}   /   {this.state.temperature}</p>
                    </div>
                    <div>
                        <button onClick={ (e) => { this.clickedForReply(this); } }>REPLY</button>
                    </div>
                </div>
            );
        }
    }
}

export default PostComponent;