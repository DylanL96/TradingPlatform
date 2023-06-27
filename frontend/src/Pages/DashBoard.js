import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, Link } from 'react-router-dom';
import BuyStock from '../Components/BuyStock';
import SellStock from '../Components/SellStock';
import '../Styles/DashBoard.css'

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
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h2 className="dashboard-heading">User Dashboard</h2>
        <Link to="/" className="dashboard-logout-link">Logout</Link>
      </header>
      <section className="dashboard-section">
        <h3 className="dashboard-subheading">Portfolio</h3>
        <table className="table dashboard-portfolio-table">
          <thead>
            <tr>
              <th>Symbol</th>
              <th>Quantity</th>
            </tr>
          </thead>
          <tbody>
            {Object.keys(portfolioData).length > 0 ? (
              Object.keys(portfolioData).map((key) => (
                <tr key={key}>
                  <td>{key}</td>
                  <td>{portfolioData[key]}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="2">No portfolio data available.</td>
              </tr>
            )}
          </tbody>
        </table>
        <BuyStock userID={userID}/>
        <SellStock userID={userID}/>
      </section>
    </div>
  );
};

export default Dashboard;
