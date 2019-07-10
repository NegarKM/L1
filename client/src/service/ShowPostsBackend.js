import axios from 'axios'

const url = 'http://localhost:8080';

class ShowPostsBackend {
    retrieveAllPosts() {
            return axios.get(`${url}/posts?user=${encodeURIComponent(user)}`
        );
    }
}

export default new ShowPostsBackend()
