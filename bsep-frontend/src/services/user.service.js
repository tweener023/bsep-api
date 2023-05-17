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
    //const headers = {
    //  Authorization: `Bearer ${token}`
    //};
   //console.log("User is " + token);
    return axios.put(API_URL + profileId +'/activate', {headers:authHeader()});
  }

  
  getEngineerSkills(profileId, token){
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return axios.get(API_URL + profileId + '/skill', {headers});
  }

  
  getEngineerProjects(profileId, token){
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return axios.get(API_URL + profileId + '/project', {headers});
  }

  deleteSkill(skillId){
    return axios.put(API_URL + skillId + '/deleteSkill', {headers: authHeader()});
  }

  updateSkill(skillId, updatedSkill){
    console.log(updatedSkill);
    return axios.put(API_URL + skillId + '/editSkill', updatedSkill,{headers: authHeader()});
  }

  addSkill( newSkill, userId){
    console.log(newSkill);
    return axios.post(API_URL + userId + '/addSkill', newSkill,{headers: authHeader()});
  }
}

export default new UserService();
