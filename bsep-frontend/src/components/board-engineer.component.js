import React, { Component } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import AuthService from "../services/auth.service";
import "../styles/Engineer.css";


  
export default class BoardEngineer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      skills: [],
      loading: true,
      content: "",
    };

    this.handleAddSkill = this.handleAddSkill.bind(this);
    this.handleEditSkill = this.handleEditSkill.bind(this);
    this.handleDeleteSkill = this.handleDeleteSkill.bind(this);
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
          error: null, 
          loading:false,
        });
      },
      (error) => {
        this.setState({
          skills: [],
          error:
            (error.response && error.response.data) ||
            error.message ||
            error.toString(),
            loading: false,
        });

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }
  handleAddSkill() {
    // TODO: implement add skill functionality
    console.log("Add skill clicked");
  }

  handleEditSkill(skillId) {
    // TODO: implement edit skill functionality
    console.log(`Edit skill ${skillId} clicked`);
  }

  handleDeleteSkill(skillId) {
    UserService.deleteSkill(skillId)
    .then(() => {
      // Skill deleted successfully
      // Update the state to reflect the deleted skill
      this.setState((prevState) => ({
        skills: prevState.skills.filter((skill) => skill.skillId !== skillId),
      }));
    })
    .catch((error) => {
      // Handle error
      console.log("Error deleting skill:", error);
    });
    console.log(`Delete skill ${skillId} clicked`);
  }

  render() {
    const { skills, loading, content } = this.state;

    return (
      <div className="container">
        <h1 className="head">Skills</h1>
        {loading && <p>Loading skills...</p>}
        {content && <p>{content}</p>}
        {!loading && !content && (
          <>
            <div className="card-group">
              {skills.map((skill) => (
                <div className="card" key={skill.skillId}>
                  <div className="card-body">
                    <h5 className="card-title">{skill.skillName}</h5>
                    <p className="card-text">Skill level: {skill.skillLevel}</p>
                    <button
                      className="btn btn-primary mr-2"
                      onClick={() => this.handleEditSkill(skill.skillId)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => this.handleDeleteSkill(skill.skillId)}
                    >
                      Delete
                    </button>
                  </div>
                </div>
              ))}
            </div>
            <button className="btn btn-success mt-2" onClick={this.handleAddSkill}>
              Add Skill
            </button>
          </>
        )}
      </div>
    );
  }
}