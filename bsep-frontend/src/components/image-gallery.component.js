// ImageGallery component
import React from 'react';

const ImageGallery = ({ images, onImageClick }) => {
  return (
    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
      {images.map((image, index) => (
        <div key={image.id} className="col">
          <div className="position-relative">
            <img
              src={image.urlPath}
              alt={`Thumbnail ${image.id}`}
              className="cursor-pointer img-fluid rounded-lg"
              onClick={() => onImageClick(index, image.urlPath)}
            />
          </div>
        </div>
      ))}
    </div>
  );
};

export default ImageGallery;
