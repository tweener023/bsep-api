import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getEngineerBoard() {
    return axios.get(API_URL + 'engineer', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  getUnactivatedProfiles(){
    return axios.get(API_URL + 'unactivated', {headers: authHeader()});
  }

  activateProfile(profileId, token){
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return axios.put(API_URL + profileId +'/activate', {headers});
  }
}

export default new UserService();
