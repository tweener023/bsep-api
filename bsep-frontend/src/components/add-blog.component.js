import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import styles from '../styles/CreateBlogs.module.css';

const CreateBlogsComponent = () => {
  const [formData, setFormData] = useState({
    title: '',
    content: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('https://localhost:443/api/blogs', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        // Handle successful blog creation if needed
        console.log('Blog created successfully:', data);
        toast.success('Blog created successfully.', {
          position: "top-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        setFormData({ title: '', content: '' }); // Clear input data
      })
      .catch(error => {
        console.error('Error creating blog:', error);
        toast.error('Error creating blog.', {
          position: "top-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      });
  };
 
  return (
    <div className={styles.createBlogsContainer}>
      <h2 className={styles.createBlogsTitle}>Create a Blog</h2>
      <form className={styles.createBlogsForm} onSubmit={handleSubmit}>
        <div>
          <label className={styles.createBlogsLabel} htmlFor="title">Title:</label>
          <input
            type="text"
            id="title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            className={styles.createBlogsInput}
            required
          />
        </div>
        <div>
          <label className={styles.createBlogsLabel} htmlFor="content">Content:</label>
          <textarea
            id="content"
            name="content"
            value={formData.content}
            onChange={handleChange}
            rows={8}
            className={styles.createBlogsTextarea}
            required
          />
        </div>
        <div>
          <button type="submit" className={styles.createBlogsButton}>Submit</button>
        </div>
      </form>
      <ToastContainer />
    </div>
  );
};

export default CreateBlogsComponent;
