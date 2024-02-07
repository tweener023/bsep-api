import React, { useState, useEffect } from 'react';
import ImageGallery from './image-gallery.component';
import GuitarInfo from './guitar-info.component';  
import '../styles/GalleryStyles.css'; 

import {api_url} from '../common/environment';

const GalleryModal = ({ guitarId, onClose, onOrder }) => {
  const [images, setImages] = useState([]);
  const [selectedImageIndex, setSelectedImageIndex] = useState(null);
  const [guitarInfo, setGuitarInfo] = useState(null);
  const [ordered, setOrdered] = useState(false); 


  useEffect(() => {
     fetch(api_url + `horder/guitars/${guitarId}`)
     .then((response) => response.json())
     .then((data) => setGuitarInfo(data))
     .catch((error) => console.error('Error fetching guitar info:', error));

    fetch(api_url + `horder/images/${guitarId}/getAllPhotos`)
      .then((response) => response.json())
      .then((data) => setImages(data))
      .catch((error) => console.error('Error fetching guitar photos:', error));
  }, [guitarId]);

  const openImage = (index) => {
    setSelectedImageIndex(index);
  };

  const closeImage = () => {
    setSelectedImageIndex(null);
  };
  const handleOrder = () => {
    setOrdered(true); 
    onOrder();
    onClose();
  };

  return (
    <div className="modal d-block" tabIndex="-1" role="dialog" style={{ maxHeight: '100vh', overflowY: 'auto' }}>
      <div className="modal-dialog" role="document">
        <div className="modal-content">
          <div className="modal-header">
            <button type="button" className="close" data-dismiss="modal" aria-label="Close" onClick={onClose}>
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div className="modal-body">
            <div className="d-flex justify-content-center align-items-center">
              <ImageGallery images={images} onImageClick={openImage} />
            </div>
            {guitarInfo && <GuitarInfo guitar={guitarInfo}  onOrder={() => handleOrder()} />} 
          </div>
        </div>
      </div>
      {selectedImageIndex !== null && (
        <div className="modal d-block" tabIndex="-1" role="dialog">
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal" aria-label="Close" onClick={closeImage}>
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div className="modal-body" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <img
                  src={images[selectedImageIndex].urlPath}
                  alt="Enlarged"
                  className="img-fluid"
                  style={{ maxWidth: '100%', maxHeight: '100%', objectFit: 'contain' }}
                />
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default GalleryModal;
