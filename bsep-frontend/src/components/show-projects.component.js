import React, { Component } from "react";
import UserService from "../services/user.service";
import AuthService from "../services/auth.service";
import EventBus from "../common/EventBus";

export default class ShowProject extends Component {
  constructor(props) {
    super(props);

    this.state = {
      projects: [],
      loading: true,
      error: null,
      editingProjectId: null,
      newProjectDescription: "",
    };
  }

  componentDidMount() {
    document.addEventListener("mousedown", this.handleClickOutsideDialog);

    const currentUser = AuthService.getCurrentUser();

    const profileId = currentUser.id;

    UserService.getEngineerProjects(profileId, currentUser.accessToken).then(
      (response) => {
        this.setState({
          projects: response.data,
          error: null,
          loading: false,
        });
      },
      (error) => {
        this.setState({
          projects: [],
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

  
  handleEditProject(projectId) {
    const currentUser = AuthService.getCurrentUser();
    const {newProjectDescription } = this.state;

    if (!newProjectDescription) {
      // Display an error message or take appropriate action
      console.log("Please provide both a new skill name and skill level.");
      return;
    }

    const updatedProject = {
      projectDescription: newProjectDescription,
      user: currentUser,
      projectId: projectId,
    };

    UserService.updateProject(projectId, updatedProject)
      .then((response) => {
        // Handle successful update
        console.log("Project updated successfully:", response.data);
        // Update the skills list if needed
        this.setState((prevState) => ({
          projects: prevState.projects.map((project) =>
            project.projectId === projectId ? { ...project, ...updatedProject } : project
          ),
          editingProjectId: null,
          newProjectDescription: "",
        }));
      })
      .catch((error) => {
        // Handle error
        console.log("Error updating skill:", error);
      });
  }

  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  handleCloseEditDialog = () => {
    this.setState({
      editingProjectId: null,
      newProjectDescription: "",
    });
  };

  render() {
    const {
      projects,
      loading,
      error,
      editingProjectId,
      newProjectDescription,
    } = this.state;

    return (
      <div>
        <h1 className="headProjects">Projects</h1>
        {loading && <p>Loading projects...</p>}
        {error && <p>{error}</p>}
        {!loading && !error && (
          <div className="card-group">
            {projects.map((project) => (
              <div className="card" key={project.projectId}>
                <div className="card-body">
                  <h5 className="card-title">{project.projectName}</h5>
                  <p className="card-text">Description: {project.projectDescription}</p>
                  {/* Add any additional project details as needed */}

                  {/* Edit PROJECTS Dialog */}
                  {editingProjectId === project.projectId && (
                    <div className="dialog-overlay">
                      <div className="dialog-card">
                        <h2>Edit Project</h2>
                        <p>Project Name: {project.projectName}</p>
                        <input
                          type="text"
                          name="newProjectDescription"
                          placeholder="New Project Description"
                          value={newProjectDescription}
                          onChange={this.handleChange}
                        />
                        <button
                          className="btn btn-primary"
                          onClick={() => this.handleEditProject(project.projectId)}
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

                  <button
                    className="btn btn-primary"
                    onClick={() =>
                      this.setState({
                        editingProjectId: project.projectId,
                        newProjectDescription: project.projectDescription,
                      })
                    }
                  >
                    Edit
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    );
  }
}
