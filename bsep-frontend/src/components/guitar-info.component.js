import React from 'react';
import { useState } from 'react';
import AuthService from "../services/auth.service";
import '../styles/GuitarInfoStyles.css'; 

const GuitarInfo = ({ guitar, onClose, onOrder }) => {
  
   const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));
  };

  const userHasUserRole = () => {
    const currentUser = getCurrentUser();
    return currentUser && currentUser.roles.includes('ROLE_USER');
  };

 
   const getJwt = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    return user ? user.accessToken : null;
  };

  const handleOrderClick = () => {
    const guitarId = guitar.id;
    const userId = JSON.parse(localStorage.getItem("user")).id;
  
    const jwtToken = getJwt();

    fetch(`https://localhost:443/api/horder/guitars/order/${guitarId}/user/${userId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}` 
      },
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      console.log('Guitar ordered successfully!');
      onOrder();
    })
    .catch(error => {
      console.error('There was a problem ordering the guitar:', error);
    });
  };

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
        {userHasUserRole() && <button onClick={handleOrderClick}>Order this guitar</button>}
      </div>
    </div>
  );
};

export default GuitarInfo;
