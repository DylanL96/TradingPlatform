import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_KEY } from '../utils/config';
import { useParams } from "react-router-dom";
import Chart from '../Components/Chart';

const IndividualStock = () => {
  const [individualStock, setIndividualStock] = useState([]);
  const [stockQuote, setStockQuote] = useState('');
  const params = useParams();
  const stockURL = `https://finnhub.io/api/v1/stock/metric?symbol=${params.stock}&metric=all&token=${API_KEY}`;
  const stockQuoteURL = `https://finnhub.io/api/v1/quote?symbol=${params.stock}&token=${API_KEY}`;

  const getIndividualStock = async () => {
    try{
      // get financial metrics
      const responseMetric = await axios.get(stockURL);
      setIndividualStock(responseMetric.data.metric);
      
      // get financial quote
      const responseQuote = await axios.get(stockQuoteURL);
      setStockQuote(responseQuote.data);
    } catch(error){
      console.log(error);
    }
  }
  
  useEffect(() => {
    getIndividualStock();
  }, []);

  if(Object.keys(individualStock).length === 0){
    return (
      <div>NO STOCK</div>
    )
  } else {
    return (
      <div>
        <h1>{params.stock}</h1>
        <h3>Current Stock Price</h3>
        <p>{stockQuote.c}</p>
        <Chart search={params.stock}/>
        <div>
          <p>Gross Margin TTM</p>
          <p>{individualStock.grossMarginTTM}</p>
  
          <p>Revenue Growth Over 5 Years</p>
          <p>{individualStock.revenueGrowth5Y}</p>
  
          <p>Free Cash Flow TTM</p>
          <p>{individualStock.freeCashFlowTTM}</p>
  
          <p>Beta</p>
          <p>{individualStock.beta}</p>
  
          <p>Gross Margin TTM</p>
          <p>{individualStock.grossMarginTTM}</p>
  
          <p>Revenue Growth Over 5 Years</p>
          <p>{individualStock.revenueGrowth5Y}</p>
        </div>
        
      </div>
    )
  }




}

export default IndividualStock