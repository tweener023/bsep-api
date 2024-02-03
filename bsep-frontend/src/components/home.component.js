import React, { Component } from "react";
import GuitarCard from "./guitar-card.component";

export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
      guitars: [],
      filteredGuitars: [],
      searchQuery: "",
      minPrice: "",
      maxPrice: "",
    };
  }

  componentDidMount() {
   this.fetchGuitars();
  }

  fetchGuitars() {
    fetch("https://localhost:443/api/horder/guitars/all")
      .then(response => response.json())
      .then(data => {
        this.setState({
          guitars: data,
          filteredGuitars: data,
        });
      })
      .catch(error => console.error("Error fetching guitars:", error));
  }


  handleSearchChange = (event) => {
    const searchQuery = event.target.value.toLowerCase();
    this.setState({ searchQuery }, this.filterGuitars);
  };

  handleMinPriceChange = (event) => {
    const minPrice = event.target.value;
    this.setState({ minPrice }, this.filterGuitars);
  };

  handleMaxPriceChange = (event) => {
    const maxPrice = event.target.value;
    this.setState({ maxPrice }, this.filterGuitars);
  };

  handleOnOrderRefresh = () => {
    console.log("refetch and refresh state here");
    
    this.fetchGuitars();
    window.location.reload(); 
  }

  filterGuitars = () => {
    const { guitars, searchQuery, minPrice, maxPrice } = this.state;

    const filteredGuitars = guitars.filter(guitar => {
      const manufacturerMatches = guitar.manufacturerOfGuitar && guitar.manufacturerOfGuitar.toLowerCase().includes(searchQuery);
      const modelMatches = guitar.modelOfGuitar && guitar.modelOfGuitar.toLowerCase().includes(searchQuery);
      const priceInRange =
        (!minPrice || guitar.price >= parseFloat(minPrice)) &&
        (!maxPrice || guitar.price <= parseFloat(maxPrice));
  
      return (manufacturerMatches || modelMatches) && priceInRange;
    });
  
    this.setState({ filteredGuitars });
  };
  

  render() {
    const { filteredGuitars } = this.state;

    return (
      <div className="container home-container">
        <div className="row">
          <div className="col-md-12 mb-3">
            <form>
              <div className="form-row">
                <div className="form-group col-md-4">
                  <label htmlFor="search">Search:</label>
                  <input
                    type="text"
                    className="form-control"
                    id="search"
                    placeholder="Search by title"
                    onChange={this.handleSearchChange}
                  />
                </div>
                <div className="form-group col-md-4">
                  <label htmlFor="minPrice">Min Price:</label>
                  <input
                    type="number"
                    className="form-control"
                    id="minPrice"
                    placeholder="Min Price"
                    onChange={this.handleMinPriceChange}
                  />
                </div>
                <div className="form-group col-md-4">
                  <label htmlFor="maxPrice">Max Price:</label>
                  <input
                    type="number"
                    className="form-control"
                    id="maxPrice"
                    placeholder="Max Price"
                    onChange={this.handleMaxPriceChange}
                  />
                </div>
              </div>
            </form>
          </div>

          {filteredGuitars.map(guitar => (
            <div key={guitar.id} className="col-md-4">
              <GuitarCard guitar={guitar} onOrderRefresh={this.handleOnOrderRefresh}/>
            </div>
          ))}
        </div>
      </div>
    );
  }
}
