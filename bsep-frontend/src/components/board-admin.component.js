import React, { Component } from "react";

import UserService from "../services/user.service";
import AuthService from "../services/auth.service";
import EventBus from "../common/EventBus";

export default class BoardAdmin extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
      unactivatedProfiles: [],
    };
  }

  componentDidMount() {
    UserService.getAdminBoard().then(
      (response) => {
        this.setState({
          content: response.data,
        });
      },
      (error) => {
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString(),
        });

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );

    UserService.getUnactivatedProfiles().then(
      (response) => {
        this.setState({
          unactivatedProfiles: response.data,
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }

  activateProfile(profile) {
    const currentUser = AuthService.getCurrentUser();

   // console.log("user je " + JSON.stringify(currentUser));
    UserService.activateProfile(profile.id, currentUser.accessToken).then(
      (response) => {
        // remove the activated profile from the state
        const updatedProfiles = this.state.unactivatedProfiles.filter(
          (p) => p.id !== profile.id
        );
        this.setState({
          unactivatedProfiles: updatedProfiles,
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          <h3>Unactivated profiles</h3>
        <table className="table">
          <thead>
            <tr>
              <th>Username</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Address</th>
              <th>City</th>
              <th>Country</th>
              <th>Phone Number</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {this.state.unactivatedProfiles.map((profile) => (
              <tr key={profile.id}>
                <td>{profile.username}</td>
                <td>{profile.firstName}</td>
                <td>{profile.lastName}</td>
                <td>{profile.address}</td>
                <td>{profile.city}</td>
                <td>{profile.country}</td>
                <td>{profile.phoneNumber}</td>
                <td>
                  <button
                    className="btn btn-primary"
                    onClick={() => this.activateProfile(profile)}
                  >
                    Activate
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        </header>
        
      </div>
    );
  }
}
