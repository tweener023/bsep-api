// GuitarCard.js
import React, { useState, useEffect } from 'react';
import GalleryModal from './gallery-modal.component';


const GuitarCard = ({ guitar }) => {
  const [guitarPhotoUrl, setGuitarPhotoUrl] = useState(null);
  const [showGallery, setShowGallery] = useState(false);

  useEffect(() => {
    // Fetch guitar photo URL from your backend API
    fetch(`https://localhost:443/api/horder/images/${guitar.id}/getTopImage`)
      .then((response) => response.json())
      .then((data) => {
        // Extract the URL from the response
        const photoUrl = data.urlPath;
        setGuitarPhotoUrl(photoUrl);
      })
      .catch((error) => console.error('Error fetching guitar photo:', error));
  }, [guitar.id]);

  const isAdmin = guitar.user && guitar.user.id === 1;

  return (
    <div className="card bg-white rounded-lg shadow-lg overflow-hidden card-container">
      <div className="position-relative card-image" style={{ height: '200px', overflow: 'hidden' }}>
        {guitarPhotoUrl && (
          <img
            src={guitarPhotoUrl}
            alt={guitar.manufacturerOfGuitar}
            className="card-img-top w-100 h-100 object-cover rounded-top"
            style={{ objectFit: 'cover', height: '100%' }}
          />
        )}
      </div>
      <div className="card-body">
        <h2 className="card-title h5">{guitar.manufacturerOfGuitar}</h2>
        <h2 className="card-subtitle h5 text-muted">{guitar.modelOfGuitar}</h2>
        <h2 className="card-price text-danger">{guitar.price}</h2>

        <p className="card-description font-italic">{guitar.description}</p>
        {isAdmin ? (
          <button
            className="btn btn-primary card-button w-100"
            onClick={() => setShowGallery(true)}
          >
            See More
          </button>
        ) : (
          <button
            className="btn btn-secondary card-button w-100"
            disabled
          >
            Out of Stock
          </button>
        )}

        {/* Conditionally render the GalleryModal component */}
        {showGallery && (
          <GalleryModal
            guitarId={guitar.id}
            onClose={() => setShowGallery(false)} // Close the gallery
          />
        )}
      </div>
    </div>
  );
};

export default GuitarCard;
