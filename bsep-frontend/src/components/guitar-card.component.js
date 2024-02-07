import React, { useState, useEffect } from 'react';
import GalleryModal from './gallery-modal.component';


const GuitarCard = ({ guitar, onOrderRefresh}) => {
  const [guitarPhotoUrl, setGuitarPhotoUrl] = useState(null);
  const [showGallery, setShowGallery] = useState(false);
  const [ordered, setOrdered] = useState(false); 


  useEffect(() => {
    fetch(`https://localhost:443/api/horder/images/${guitar.id}/getTopImage`)
      .then((response) => response.json())
      .then((data) => {
        const photoUrl = data.urlPath;
        setGuitarPhotoUrl(photoUrl);
      })
      .catch((error) => console.error('Error fetching guitar photo:', error));
  }, [guitar.id]);

  const isAdmin = guitar.userId && guitar.userId === 1;

  const handleOrder = () => {
    setOrdered(true); 
    setShowGallery(false);
    onOrderRefresh();

  };


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

        {showGallery && (
          <GalleryModal
            guitarId={guitar.id}
            onClose={() => setShowGallery(false)} 
            onOrder={() => handleOrder()} 
          />
        )}
      </div>
    </div>
  );
};

export default GuitarCard;
