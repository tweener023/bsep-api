import React from 'react';
import { useState } from 'react';
import AuthService from "../services/auth.service";
import '../styles/GuitarInfoStyles.css'; 

const GuitarInfo = ({ guitar }) => {
  
   const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));
  };

  const userHasUserRole = () => {
    const currentUser = getCurrentUser();
    return currentUser && currentUser.roles.includes('ROLE_USER');
  };

 
   const getJwt = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    console.log('User object:', user); // Log the user object
    return user ? user.accessToken : null;
  };

  const handleOrderClick = () => {
    const guitarId = guitar.id;
    const userId = JSON.parse(localStorage.getItem("user")).id;
  
    // Get JWT token
    const jwtToken = getJwt();
    console.log('JWT Token:', jwtToken); // Log the JWT token
  
    // Log request details
    console.log('Request URL:', `https://localhost:443/api/order/guitars/order/${guitarId}/user/${userId}`);
    console.log('Request Headers:', {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${jwtToken}`
    });
  
    // Send request with JWT token
    fetch(`https://localhost:443/api/horder/guitars/order/${guitarId}/user/${userId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}` // Use locally defined getJwt function
      },
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // Handle success response
      console.log('Guitar ordered successfully!');
    })
    .catch(error => {
      // Handle error
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
