import axios from 'axios';

const url = 'http://localhost:8080'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

class AuthenticationBackend {
    executeBasicAuthenticationService(username, password) {
        return axios.get(`${url}/basicauth`,
            { headers: { authorization: this.createBasicAuthToken(username, password) } })
    }

    createBasicAuthToken(username, password) {
        localStorage.setItem("username", username);
        return 'Basic ' + window.btoa(username + ":" + password)
    }

    registerSuccessfulLogin(username, password) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    }

   isUserLoggedIn() {
       let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
       if (user === null) return false
       return true
   }

   setupAxiosInterceptors(token) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }
}

export default new AuthenticationBackend()