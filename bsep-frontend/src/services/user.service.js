import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'https://localhost:443/api/test/';

class UserService {
  getPublicContent() {
    return axios.get( 'https://localhost:443/api/horder/guitars/all');
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
   console.log("User is " + token);
    return axios.put(API_URL + profileId +'/activate', {headers:authHeader()});
  }

  giveCrudPermissions(profileId, token){
    const headers = {
      Authorization: `Bearer ${token}`
    };
   console.log("User is " + token);
    return axios.put(API_URL + profileId +'/giveCrudPermissions', {headers:authHeader()});
  }

  unauthorize(profileId, token){
    const headers = {
      Authorization: `Bearer ${token}`
    };
   console.log("User is " + token);
    return axios.put(API_URL + profileId +'/unauthorize', {headers:authHeader()});
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

  
  updateProject(projectId, updatedProject){
    console.log(updatedProject);
    return axios.put(API_URL + projectId + '/editProject', updatedProject,{headers: authHeader()});
  }

  addSkill( newSkill, userId){
    console.log(newSkill);
    return axios.post(API_URL + userId + '/addSkill', newSkill,{headers: authHeader()});
  }
  
  updateUser(userId, updatedUser){
    console.log(updatedUser);
    return axios.put(API_URL + userId + '/editUser', updatedUser,{headers: authHeader()});
  }

  getUserOrders(userId, accessToken) {
    console.log("this is users id " + userId);
    const headers = {
      Authorization: `Bearer ${accessToken}`
    };

    return axios.get(`https://localhost:443/api/horder/guitars/${userId}/orders`, { headers });
  }

}

export default new UserService();
