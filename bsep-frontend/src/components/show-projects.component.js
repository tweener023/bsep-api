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
    };
  }

  componentDidMount() {
    document.addEventListener("mousedown", this.handleClickOutsideDialog);

    const currentUser = AuthService.getCurrentUser();

    console.log(JSON.stringify(currentUser));
    const profileId = currentUser.id;
    console.log(JSON.stringify(profileId));

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


  render() {
    const { projects, loading, error } = this.state;

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
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    );
  }
}
