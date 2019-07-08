import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import HelloWorldApp from './HelloWorldApp';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<HelloWorldApp />, document.getElementById('root'));
registerServiceWorker();