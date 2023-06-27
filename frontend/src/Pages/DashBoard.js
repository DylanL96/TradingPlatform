import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const Dashboard = () => {
  const [portfolioData, setPortfolioData] = useState([]);
  const userID = useParams().userId;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/stocks/${userID}/portfolio`);
        const responseData = response.data;
        setPortfolioData(responseData.stocks);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h2>User Dashboard</h2>
      <h3>Portfolio</h3>
      {Object.keys(portfolioData).length > 0 ? (
        <ul>
          {Object.keys(portfolioData).map((key) => (
            <li key={key}>
              <strong>{key}: </strong>
              {portfolioData[key]}
            </li>
          ))}
        </ul>
      ) : (
        <p>No portfolio data available.</p>
      )}
    </div>
  );
};

export default Dashboard;