import axios from 'axios'

const url = 'http://localhost:8080';

class ShowPostsBackend {
    retrieveAllPosts() {
        return axios.get(`${url}/getPostsByUser?user=${localStorage.getItem("username")}`)
        .then(res => {
            return {hasError: false, data: res.data}
        })
        .catch( (error) => {
            if (error.response) {
                console.log(error);
                return {hasError: true, error: error};
            }
        });
    }
}

export default new ShowPostsBackend()
