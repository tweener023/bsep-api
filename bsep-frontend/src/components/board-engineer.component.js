import React, { Component } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import AuthService from "../services/auth.service";
import "../styles/Engineer.css";
import AddSkill from "./add-skill.component";

export default class BoardEngineer extends Component {
  constructor(props) {
    super(props);

    this.state = {
      skills: [],
      loading: true,
      content: "",
      newSkillName: "", // New skill name input field
      newSkillLevel: "", // New skill level input field
      showAddSkill: false,
      currentUser: AuthService.getCurrentUser(),
    };

    this.handleEditSkill = this.handleEditSkill.bind(this);
    this.handleDeleteSkill = this.handleDeleteSkill.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.handleOpenEditDialog = this.handleOpenEditDialog.bind(this);
    this.handleCloseEditDialog = this.handleCloseEditDialog.bind(this);
    this.handleClickOnAddSkill = this.handleClickOnAddSkill.bind(this);
    this.handleCloseAddSkill = this.handleCloseAddSkill.bind(this);
    this.fetchSkills = this.fetchSkills.bind(this);
  }

  componentDidMount() {
    document.addEventListener("mousedown", this.handleClickOutsideDialog);

    const currentUser = AuthService.getCurrentUser();

    console.log(JSON.stringify(currentUser));
    const profileId = currentUser.id;
    console.log(JSON.stringify(profileId));

    UserService.getEngineerSkills(profileId, currentUser.accessToken).then(
      (response) => {
        this.setState({
          skills: response.data,
          error: null,
          loading: false,
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

  componentWillUnmount() {
    document.removeEventListener("mousedown", this.handleClickOutsideDialog);
  }

  handleClickOutsideDialog = (event) => {
    const dialog = document.querySelector(".dialog-card");
    if (dialog && !dialog.contains(event.target)) {
      this.handleCloseEditDialog();
    }
  };

  handleEditSkill(skillId) {
    const currentUser = AuthService.getCurrentUser();
    const { newSkillName, newSkillLevel } = this.state;

    if (!newSkillName || !newSkillLevel) {
      // Display an error message or take appropriate action
      console.log("Please provide both a new skill name and skill level.");
      return;
    }

    const updatedSkill = {
      skillName: newSkillName,
      skillLevel: newSkillLevel,
      user: currentUser,
      skillId: skillId,
    };

    UserService.updateSkill(skillId, updatedSkill)
      .then((response) => {
        // Handle successful update
        console.log("Skill updated successfully:", response.data);
        // Update the skills list if needed
        this.setState((prevState) => ({
          skills: prevState.skills.map((skill) =>
            skill.skillId === skillId ? { ...skill, ...updatedSkill } : skill
          ),
          editingSkillId: null,
          newSkillName: "",
          newSkillLevel: "",
        }));
      })
      .catch((error) => {
        // Handle error
        console.log("Error updating skill:", error);
      });
  }

  handleOpenEditDialog(skillId) {
    const { skills } = this.state;
    const skillToEdit = skills.find((skill) => skill.skillId === skillId);

    if (skillToEdit) {
      this.setState({
        editingSkillId: skillId,
        newSkillName: skillToEdit.skillName,
        newSkillLevel: skillToEdit.skillLevel,
      });
    }
  }

  handleCloseEditDialog() {
    this.setState({
      editingSkillId: null,
      newSkillName: "",
      newSkillLevel: "",
    });
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value,
    });
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

  handleClickOnAddSkill() {
    this.setState({
      showAddSkill: true,
    });
  }

  handleCloseAddSkill() {
    this.setState({
      showAddSkill: false,
    });
  }

  fetchSkills() {
    const currentUser = AuthService.getCurrentUser();
    const profileId = currentUser.id;

    UserService.getEngineerSkills(profileId, currentUser.accessToken)
      .then((response) => {
        // Handle successful fetch
        const skills = response.data;
        this.setState({
          skills: skills,
          error: null,
        });
      })
      .catch((error) => {
        // Handle error
        console.log("Error fetching skills:", error);
        this.setState({
          skills: [],
          error: "Failed to fetch skills.",
        });
      });
  }

  render() {
    const {
      skills,
      loading,
      content,
      editingSkillId,
      newSkillName,
      newSkillLevel,
      showAddSkill,
    } = this.state;

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
                      onClick={() => this.handleOpenEditDialog(skill.skillId)}
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
            {showAddSkill && (
              <div
                className="add-skill-overlay"
                onClick={this.handleCloseAddSkill}
              >
                <div
                  className="add-skill-card"
                  onClick={(e) => e.stopPropagation()}
                >
                  <AddSkill
                    currentUser={this.state.currentUser}
                    fetchSkills={this.fetchSkills}
                    onClose={this.handleCloseAddSkill}
                  />
                </div>
              </div>
            )}

            <button
              className="btn btn-success mt-2"
              onClick={this.handleClickOnAddSkill}
            >
              Add Skill
            </button>
            {/* Edit Skill Dialog */}
            {editingSkillId && (
              <div className="dialog-overlay">
                <div className="dialog-card">
                  <h2>Edit Skill</h2>
                  <input
                    type="text"
                    name="newSkillName"
                    placeholder="New Skill Name"
                    value={newSkillName}
                    onChange={this.handleChange}
                  />
                  <input
                    type="text"
                    name="newSkillLevel"
                    placeholder="New Skill Level"
                    value={newSkillLevel}
                    onChange={this.handleChange}
                  />
                  <button
                    className="btn btn-primary"
                    onClick={() => this.handleEditSkill(editingSkillId)}
                  >
                    Save
                  </button>
                  <button
                    className="btn btn-secondary"
                    onClick={this.handleCloseEditDialog}
                  >
                    Cancel
                  </button>
                </div>
              </div>
            )}
          </>
        )}

        <h1 className="headProjects">Projects</h1>
      </div>
    );
  }
}
