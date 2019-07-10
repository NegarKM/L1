import axios from 'axios'

const url = 'http://localhost:8080';

class ShowPostsBackend {
    retrieveAllPosts() {
        return axios.get(`${url}/getPosts?user=${localStorage.getItem("username")}`)
        .then(res => {
            return res.data
        })
        .catch( (error) => {
            if (error.response) {
                console.log(error);
                return null;
            }
        });
    }
}

export default new ShowPostsBackend()
