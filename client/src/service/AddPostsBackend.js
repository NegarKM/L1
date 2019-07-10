import axios from 'axios';

const url = 'http://localhost:8080';

const headers = {
  'Accept': 'application/json',
  'Content-Type': 'application/json',
  'X-Requested-With': 'XMLHttpRequest'
};

class AddPostsBackend {
    addPost(value) {
        return axios.get(`${url}/echo?text=${encodeURIComponent(value)}&user=${localStorage.getItem("username")}`
                 )
            .then(res => {
                 return res.data
            })
            .then(data => {
                console.log(data);
                return data.text;
            });
    };
}

export default new AddPostsBackend()