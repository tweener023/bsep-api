import React, { Component } from "react";
import UserService from "../services/user.service";
import "../styles/AddSkill.css";



export default class AddSkill extends Component {
  constructor(props) {
    super(props);

    this.state = {
      newSkillName: "",
      newSkillLevel: "",
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.addSkill = this.addSkill.bind(this);
  }

  handleInputChange(event) {
    const { name, value } = event.target;
    this.setState({
      [name]: value,
    });
  }

  addSkill() {
    const { newSkillName, newSkillLevel } = this.state;

    if (!newSkillName || !newSkillLevel) {
      // Display an error message or take appropriate action
      console.log("Please provide both a skill name and skill level.");
      return;
    }

    const { currentUser, onClose } = this.props;
    const newSkill = {
      skillName: newSkillName,
      skillLevel: newSkillLevel,
      user: {
        id: currentUser.id,
        username: currentUser.username,
        email: currentUser.email,
        // Rest of the user details
      },
    };

    UserService.addSkill(newSkill)
      .then((response) => {
        // Handle successful skill addition
        console.log("Skill added successfully:", response.data);
        // Reset the input fields
        this.setState({
          newSkillName: "",
          newSkillLevel: "",
        });
        // Fetch the updated skills list or update it manually
        this.props.fetchSkills();
        onClose();
      })
      .catch((error) => {
        // Handle error
        console.log("Error adding skill:", error);
      });
  }
  render() {
    const { newSkillName, newSkillLevel } = this.state;
    const { onClose } = this.props;
  
    return (
      <div className="add-skill-card-overlay" onClick={onClose}>
        <div className="add-skill-card" onClick={(e) => e.stopPropagation()}>
          <h3>Add Skill</h3>
          <div className="form-group">
            <label htmlFor="newSkillName">Skill Name</label>
            <input
              type="text"
              className="form-control"
              id="newSkillName"
              name="newSkillName"
              value={newSkillName}
              onChange={this.handleInputChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="newSkillLevel">Skill Level</label>
            <select
              className="form-control"
              id="newSkillLevel"
              name="newSkillLevel"
              value={newSkillLevel}
              onChange={this.handleInputChange}
            >
              <option value="">Select a level</option>
              <option value="Beginner">Beginner</option>
              <option value="Intermediate">Intermediate</option>
              <option value="Advanced">Advanced</option>
              <option value="Expert">Expert</option>
            </select>
          </div>
          <button className="btn btn-primary" onClick={this.addSkill}>
            Add Skill
          </button>
        </div>
      </div>
    );
  }
}  