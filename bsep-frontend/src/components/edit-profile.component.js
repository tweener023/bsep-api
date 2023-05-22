import React, { Component } from "react";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";

export default class EditProfileForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      currentUser: AuthService.getCurrentUser(),
      username: "",
      firstName: "",
      lastName: "",
      address: "",
      city: "",
      country: "",
      phoneNumber: "",
      title: "",
      errorMessage: ""
    };
  }

  componentDidMount() {
    const { currentUser } = this.state;
    this.setState({
      username: currentUser.username,
      firstName: currentUser.firstName,
      lastName: currentUser.lastName,
      address: currentUser.address,
      city: currentUser.city,
      country: currentUser.country,
      phoneNumber: currentUser.phoneNumber,
      title: currentUser.title
    });
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleSubmit = (event) => {
    event.preventDefault();
    const { currentUser, username, firstName, lastName, address, city, country, phoneNumber, title } = this.state;

    UserService.updateUser(currentUser.id, { username, firstName, lastName, address, city, country, phoneNumber, title })
      .then(() => {
        window.location.reload();
      })
      .catch((error) => {
        this.setState({ errorMessage: error.response.data.message });
      });
  };

  render() {
    return (
      <form onSubmit={this.handleFormSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            className="form-control"
            id="username"
            name="username"
            value={this.state.username}
            onChange={this.handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="firstName">First Name:</label>
          <input
            type="text"
            className="form-control"
            id="firstName"
            name="firstName"
            value={this.state.firstName}
            onChange={this.handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name:</label>
          <input
            type="text"
            className="form-control"
            id="lastName"
            name="lastName"
            value={this.state.lastName}
            onChange={this.handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="address">Address:</label>
          <input
            type="text"
            className="form-control"
            id="address"
            name="address"
            value={this.state.address}
            onChange={this.handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="city">City:</label>
          <input
            type="text"
            className="form-control"
            id="city"
            name="city"
            value={this.state.city}
            onChange={this.handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="country">Country:</label>
          <input
            type="text"
            className="form-control"
            id="country"
            name="country"
            value={this.state.country}
            onChange={this.handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="phoneNumber">Phone Number:</label>
          <input
            type="text"
            className="form-control"
            id="phoneNumber"
            name="phoneNumber"
            value={this.state.phoneNumber}
            onChange={this.handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="title">Title:</label>
          <input
            type="text"
            className="form-control"
            id="title"
            name="title"
            value={this.state.title}
            onChange={this.handleInputChange}
          />
        </div>
        <button type="submit" className="btn btn-primary">Save Changes</button>
      </form>
    );
  }
}  