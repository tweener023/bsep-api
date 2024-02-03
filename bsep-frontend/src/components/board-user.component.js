import React, { Component } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import '../styles/UserOrders.css'

export default class BoardUser extends Component {
  constructor(props) {
    super(props);

    this.state = {
      orders: [],
      error: ""
    };
  }

  componentDidMount() {
    const userId = JSON.parse(localStorage.getItem("user")).id;
    const accessToken = this.getJwt();

    UserService.getUserOrders(userId, accessToken)
      .then(response => {
        this.setState({
          orders: response.data
        });
      })
      .catch(error => {
        const errorMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        this.setState({
          error: errorMessage
        });

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      });
  }

  getJwt = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    return user ? user.accessToken : null;
  };

  render() {
    const { orders, error } = this.state;
  
    return (
      <div className="board-user-container">
        <header className="board-user-jumbotron">
          <h3 className="board-user-h3">My Orders</h3>
          {error ? (
            <div className="board-user-error">Error: {error}</div>
          ) : (
            <div>
              {orders.map(order => (
                <div key={order.id} className="board-user-order-container">
                  <h4 className="board-user-order-heading">{order.manufacturerOfGuitar} - {order.modelOfGuitar}</h4>
                  <div className="board-user-order-info">
                    <div>Year: {order.yearOfProduction}</div>
                    <div>Price: {order.price}</div>
                    <div>State: {order.stateOfGuitar}</div>
                    <div>Type: {order.typeOfGuitar}</div>
                    <div>Magnets: {order.typeOfMagnets}</div>
                    <div>Tuners: {order.tuners}</div>
                    <div>Wood: {order.typeOfWood}</div>
                    <div>Description: {order.description}</div>
                  </div>
                </div>
              ))}
            </div>
          )}
        </header>
      </div>
    );
  }
}
