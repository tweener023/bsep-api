import React, { Component } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import AuthService from "../services/auth.service";

export default class BoardEngineer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      skills: [],
      error: null
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();

    console.log(JSON.stringify(currentUser));
    const  profileId  = currentUser.id;
    console.log(JSON.stringify(profileId));

    UserService.getEngineerSkills(profileId, currentUser.accessToken).then(
      (response) => {
        this.setState({
          skills: response.data,
          error: null
        });
      },
      (error) => {
        this.setState({
          skills: [],
          error:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }
  render() {
    const { skills, error } = this.state;
  
    return (
      <div className="container">
        <header className="jumbotron">
          {error ? (
            <div className="alert alert-danger" role="alert">
              Error: {error.message}
            </div>
          ) : (
            <ul>
              {skills.map((skill) => (
                <li key={skill.skillId}>
                  {skill.skillName} - Level {skill.skillLevel}
                </li>
              ))}
            </ul>
          )}
        </header>
      </div>
    );
  }
}