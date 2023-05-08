import React, { Component } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" }
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    console.log(currentUser);
    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true })
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
      <div className="container">
        {(this.state.userReady) ?
        <div>
        <header className="jumbotron">
          <h3>
            <strong>{currentUser.username}</strong> Profile
          </h3>
        </header>
        <p>
          <strong>Token:</strong>{" "}
          {currentUser.accessToken.substring(0, 20)} ...{" "}
          {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
        </p>
        <p>
          <strong>Id:</strong>{" "}
          {currentUser.id}
        </p>
        <p>
          <strong>Email:</strong>{" "}
          {currentUser.email}
        </p>
        <p>
          <strong> First name:</strong>{" "}
          {currentUser.firstName}
        </p>
        <p>
          <strong>Last name:</strong>{" "}
          {currentUser.lastName}
        </p>
        <p>
          <strong> Address:</strong>{" "}
          {currentUser.address}
        </p>
        <p>
          <strong>City:</strong>{" "}
          {currentUser.city}
        </p>
        <p>
          <strong>Country:</strong>{" "}
          {currentUser.country}
        </p>
        <p>
          <strong>Phone number:</strong>{" "}
          {currentUser.phoneNumber}
        </p>
        <p>
          <strong>Title:</strong>{" "}
          {currentUser.title}
        </p>
        <p>
          <strong>Profile activated:</strong>{" "}
          {currentUser.approved.toString()}
        </p>
        <strong>Authorities:</strong>
        <ul>
          {currentUser.roles &&
            currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
        </ul>
      </div>: null}
      </div>
    );
  }
}
