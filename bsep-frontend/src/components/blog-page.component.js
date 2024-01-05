import React, { useEffect, useState } from 'react';
import '../styles/BlogPage.css';

const BlogsComponent = () => {
  const [blogs, setBlogs] = useState([]);

  useEffect(() => {
    fetch('https://localhost:443/api/blogs')
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(data => setBlogs(data))
      .catch(error => console.error('Error fetching blogs:', error));
  }, []);
  
  return (
    <div className="blogs-container">
      <div className="header">
        <h1>Blogs</h1>
      </div>
      {blogs.map(blog => (
        <div key={blog.id} className="blog">
          <h2>{blog.title}</h2>
          <p>{blog.content}</p>
        </div>
      ))}
    </div>
  );
};

export default BlogsComponent;
