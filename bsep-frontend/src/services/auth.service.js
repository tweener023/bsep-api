import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password, firstName, lastName, address, city, country, phoneNumber, title, isApproved) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password, 
      firstName,
      lastName,
      address,
      city,
      country,
      phoneNumber,
      title,
      isApproved
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
  getJwt(){
    const user = JSON.parse(localStorage.getItem("user"));
    return user ? user.token : null;
  }
}

export default new AuthService();
