// GuitarInfo component
import React from 'react';
import '../styles/GuitarInfoStyles.css'; // Import the CSS file

const GuitarInfo = ({ guitar }) => {
  return (
    <div className="guitar-info-container">
      <h3 className="guitar-title">{`${guitar.manufacturerOfGuitar} ${guitar.modelOfGuitar}`}</h3>
      <div className="guitar-details">
        <p><strong>Year of Production:</strong> {guitar.yearOfProduction}</p>
        <p><strong>Price:</strong> ${guitar.price}</p>
        <p><strong>State of Guitar:</strong> {guitar.stateOfGuitar}</p>
        <p><strong>Type of Guitar:</strong> {guitar.typeOfGuitar}</p>
        <p><strong>Type of Magnets:</strong> {guitar.typeOfMagnets}</p>
        <p><strong>Tuners:</strong> {guitar.tuners}</p>
        <p><strong>Type of Wood:</strong> {guitar.typeOfWood}</p>
        <p><strong>Description:</strong> {guitar.description}</p>
      </div>
    </div>
  );
};

export default GuitarInfo;
