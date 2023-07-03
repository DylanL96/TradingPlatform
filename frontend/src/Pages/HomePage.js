import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import {API_KEY3} from '../utils/config';
import '../Styles/HomePage.css';

const HomePage = () => {
  const navigate = useNavigate();
  const [search, setSearch] = useState('');
  const newsURL = `https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=${API_KEY3}`;
  const [newsData, setNewsData] = useState([]);

  const getNews = async () => {
    try {
      // get financial quote
      const responseQuote = await axios.get(newsURL);
      setNewsData(responseQuote.data.articles);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getNews();
  }, []);

  const handleChange = event => {
    setSearch(event.target.value);
  };

  const handleClick = e => {
    navigate(`/${search}`);
  };

  return (
    <div className="homepage-container">
      <nav className="homepage-nav">
        <div className="homepage-nav-logo">
          <Link to="/" className="homepage-nav-link">Logo</Link>
        </div>
        <ul className="homepage-nav-list">
          <li className="homepage-nav-item">
            <Link to="/login" className="homepage-nav-link">Login</Link>
          </li>
          <li className="homepage-nav-item">
            <Link to="/stocks" className="homepage-nav-link">Stocks</Link>
          </li>
          <li className="homepage-nav-item">
            <Link to="/news" className="homepage-nav-link">Portfolio</Link>
          </li>
        </ul>
      </nav>
      <h1 className="homepage-heading">Home Page</h1>
      <div className="homepage-content">
        <input
          type="text"
          value={search}
          onChange={handleChange}
          placeholder="Search for a Stock i.e. TSLA"
        />
        <button onClick={handleClick}>Go</button>
      </div>
      <div className="news-grid">
        {newsData.map((news, index) => (
          <div key={index} className="news-card">
            <img src={news.urlToImage} alt={news.title} className="news-image" />
            <h3 className="news-title">{news.title}</h3>
            <p className="news-description">{news.description}</p>
            <a href={news.url} target="_blank" rel="noopener noreferrer" className="news-link">Read More</a>
          </div>
        ))}
      </div>
    </div>


  );
};

export default HomePage;
