import axios from 'axios';

const url = 'http://localhost:8080';

const headers = {
  'Accept': 'application/json',
  'Content-Type': 'application/json',
  'X-Requested-With': 'XMLHttpRequest'
};

class AddPostsBackend {
    addPost(text, cityName) {
        return axios.post(`${url}/createPost`, {
            text: text,
            username: localStorage.getItem("username"),
            cityName: cityName
        })
        .then(res => {
             return res.data
        })
        .then(data => {
            console.log(data);
            return data;
        });
    };
}

export default new AddPostsBackend()