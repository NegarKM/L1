import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';

import HelloWorldApp from './HelloWorldApp';
import AddPostsApp from './AddPostsApp';

ReactDOM.render(<HelloWorldApp />, document.getElementById('root'));
ReactDOM.render(<AddPostsApp />, document.getElementById('addPosts'));
registerServiceWorker();