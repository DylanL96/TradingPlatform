import React from 'react';
import { Link } from 'react-router-dom';
import '../Styles/HomePage.css';

const HomePage = () => {
  return (
    <div className="homepage-container">
      <nav className="homepage-nav">
        <ul className="homepage-nav-list">
          <li className="homepage-nav-item">
            <Link to="/login" className="homepage-nav-link">Login</Link>
          </li>
        </ul>
      </nav>
      <h1 className="homepage-heading">Home Page</h1>
    </div>
  );
};

export default HomePage;
