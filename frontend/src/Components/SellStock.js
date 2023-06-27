import React, { useState } from 'react';
import axios from 'axios';
import '../Styles/SellStock.css';

const SellStock = ({ userID }) => {
  const [symbol, setSymbol] = useState('');
  const [quantity, setQuantity] = useState('');

  const handleSymbolChange = (event) => {
    setSymbol(event.target.value);
  };

  const handleQuantityChange = (event) => {
    setQuantity(event.target.value);
  };

  const handleSellStock = () => {
    const payload = {
      symbol,
      quantity
    };

    axios.post(`http://localhost:8080/api/stocks/${userID}/sell`, payload)
      .then(response => {
        console.log(response);
        // Handle the response
        setSymbol('');
        setQuantity('');
        console.log("successfully sold stock");
      })
      .catch(error => {
        console.log(error);
        // Handle the error
      });
  };

  return (
    <div className="sell-stock-container">
      <h2 className="sell-stock-heading">Sell Stock</h2>
      <form>
        <div className="form-group">
          <label className="form-label">Stock Symbol:</label>
          <input className="form-input" type="text" value={symbol} onChange={handleSymbolChange} />
        </div>
        <div className="form-group">
          <label className="form-label">Quantity:</label>
          <input className="form-input" type="number" value={quantity} onChange={handleQuantityChange} />
        </div>
        <button className="sell-button" type="button" onClick={handleSellStock}>Sell</button>
      </form>
    </div>
  );
};

export default SellStock;
