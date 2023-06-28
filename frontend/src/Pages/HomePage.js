import React, {useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../Styles/HomePage.css';
import StockFilter from '../Components/StockFilter';

const HomePage = () => {
  const navigate = useNavigate();
  const [search, setSearch] = useState('');

  // keeps track of the changes into the input
  const handleChange = event => {
    // console.log(event.target.value)
    setSearch(event.target.value);
  };
  
  const handleClick = e => {
    navigate(`/${search}`);
  }

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
      <StockFilter search={search} handleChange={handleChange}/> 
        <button onClick={handleClick}>Click me</button>
    </div>
  );
};

export default HomePage;
