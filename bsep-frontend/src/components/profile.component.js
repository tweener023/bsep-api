import React, { Component } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import "../styles/EditProfile.css";

export default class Profile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      editing: false,
      redirect: null,
      userReady: false,
      currentUser: { username: "" },
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true });
  }

  handleEdit = () => {
    this.setState({ editing: true });
  };

  handleSave = async () => {
    const token = AuthService.getJwt();
    // console.log("evo ga token " + token);
    const { currentUser } = this.state;
    //console.log(currentUser);
    try {
      const response = await fetch("http://localhost:8080/api/users", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(currentUser),
      });
      if (!response.ok) {
        throw new Error(response.statusText);
      }
      this.setState({ editing: false });
    } catch (error) {
      console.error(error);
    }
  };

  handleChange = (event) => {
    const { currentUser } = this.state;
    this.setState({
      currentUser: { ...currentUser, [event.target.name]: event.target.value },
    });
  };

  render() {
    const { redirect, userReady, editing } = this.state;

    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    }

    const { currentUser } = this.state;

    return (
      <div className="container">
        {this.state.userReady ? (
          <div>
            <header className="jumbotron">
              <h3 className="usernameH3">
                <strong>{currentUser.firstName} {currentUser.lastName} </strong>
              </h3>
              <div className="info-card">
                <p>
                  <strong>Password: </strong>
                  {editing ? (
                    <input
                      className="inputPassword"
                      type="password"
                      name="password"
                      value={currentUser.password}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.email
                  )}
                </p>
                <p>
                  <strong>First Name: </strong>
                  {editing ? (
                    <input
                      type="text"
                      name="firstName"
                      value={currentUser.firstName}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.firstName
                  )}
                </p>
                <p>
                  <strong>Lastname: </strong>
                  {editing ? (
                    <input
                      type="text"
                      name="lastName"
                      value={currentUser.lastName}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.lastName
                  )}
                </p>
                <p>
                  <strong>Address: </strong>
                  {editing ? (
                    <input
                      type="text"
                      name="address"
                      value={currentUser.address}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.address
                  )}
                </p>
                <p>
                  <strong>City: </strong>
                  {editing ? (
                    <input
                      type="text"
                      name="city"
                      value={currentUser.city}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.city
                  )}
                </p>
                <p>
                  <strong>Country: </strong>
                  {editing ? (
                    <input
                      type="text"
                      name="country"
                      value={currentUser.country}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.country
                  )}
                </p>
                <p>
                  <strong>Phone number: </strong>
                  {editing ? (
                    <input
                      type="text"
                      name="phoneNumber"
                      value={currentUser.phoneNumber}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.phoneNumber
                  )}
                </p>
    
                <p>
                  <strong>Title: </strong>
                  {editing ? (
                    <input
                      type="text"
                      name="title"
                      value={currentUser.title}
                      onChange={this.handleChange}
                    />
                  ) : (
                    currentUser.title
                  )}
                </p>

              </div>

              {editing ? (
                <>
                  <div className="button-container">
                    <button className="btnSave" onClick={this.handleSave}>
                      Save
                    </button>
                    <button
                      className="btnCancel"
                      onClick={() => this.setState({ editing: false })}
                    >
                      Cancel
                    </button>
                  </div>
                </>
              ) : (
                <button className="btnChange" onClick={this.handleEdit}>
                  Change info
                </button>
              )}
            </header>
          </div>
        ) : null}
      </div>
    );
  }
}
