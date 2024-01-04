// Home.js
import React, { Component } from "react";
import GuitarCard from "./guitar-card.component"; 

export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
      guitars: [] 
    };
  }

  componentDidMount() {
    fetch("https://localhost:443/api/horder/guitars/all")
      .then(response => response.json())
      .then(data => {
        this.setState({
          guitars: data
        });
      })
      .catch(error => console.error("Error fetching guitars:", error));
  }

  render() {
    const { guitars } = this.state;

    return (
      <div className="container home-container">
        <div className="row">
          {guitars.map(guitar => (
            <div key={guitar.id} className="col-md-4">
              <GuitarCard guitar={guitar} />
            </div>
          ))}
        </div>
      </div>
    );
  }
}
