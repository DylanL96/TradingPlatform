import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_KEY } from '../utils/config';
import { useParams } from 'react-router-dom';
import '../Styles/IndividualStockPage.css';

const IndividualStock = () => {
  const [individualStock, setIndividualStock] = useState([]);
  const [stockQuote, setStockQuote] = useState('');
  const params = useParams();
  const stockURL = `https://finnhub.io/api/v1/stock/metric?symbol=${params.stock}&metric=all&token=${API_KEY}`;
  const stockQuoteURL = `https://finnhub.io/api/v1/quote?symbol=${params.stock}&token=${API_KEY}`;

  const getIndividualStock = async () => {
    try {
      // get financial metrics
      const responseMetric = await axios.get(stockURL);
      setIndividualStock(responseMetric.data.metric);

      // get financial quote
      const responseQuote = await axios.get(stockQuoteURL);
      setStockQuote(responseQuote.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getIndividualStock();
  }, []);

  if (Object.keys(individualStock).length === 0) {
    return <div className="individual-stock">NO STOCK</div>;
  } else {
    return (
      <div className="individual-stock">
        <h1 className="stock-name">{params.stock}</h1>
        <div className="stock-info">
          <div className="stock-price">
            <h3>Current Stock Price</h3>
            <p>{stockQuote.c}</p>
          </div>
          <div className="stock-metrics">
            <div className="metric-item">
              <p>Gross Margin TTM</p>
              <p>{individualStock.grossMarginTTM}</p>
            </div>
            <div className="metric-item">
              <p>Revenue Growth Over 5 Years</p>
              <p>{individualStock.revenueGrowth5Y}</p>
            </div>
            <div className="metric-item">
              <p>Free Cash Flow TTM</p>
              <p>{individualStock.freeCashFlowTTM}</p>
            </div>
            <div className="metric-item">
              <p>Beta</p>
              <p>{individualStock.beta}</p>
            </div>
            <div className="metric-item">
              <p>Gross Margin TTM</p>
              <p>{individualStock.grossMarginTTM}</p>
            </div>
            <div className="metric-item">
              <p>Revenue Growth Over 5 Years</p>
              <p>{individualStock.revenueGrowth5Y}</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
};

export default IndividualStock;
