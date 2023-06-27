import React, { useState } from 'react';
import axios from 'axios';
import '../Styles/BuyStock.css';

const BuyStock = ({ userID }) => {
  const [symbol, setSymbol] = useState('');
  const [quantity, setQuantity] = useState('');

  const handleSymbolChange = (event) => {
    setSymbol(event.target.value);
  };

  const handleQuantityChange = (event) => {
    setQuantity(event.target.value);
  };

  const handleBuyStock = () => {
    const payload = {
      symbol,
      quantity
    };

    axios.post(`http://localhost:8080/api/stocks/${userID}/buy`, payload)
      .then(response => {
        console.log(response);
        // Handle the response
        setSymbol('');
        setQuantity('');
        console.log("successfully bought stock");
      })
      .catch(error => {
        console.log(error);
        // Handle the error
      });
  };

  return (
    <div className="buy-stock-container">
      <h2 className="buy-stock-heading">Buy Stock</h2>
      <form>
        <div className="form-group">
          <label className="form-label">Stock Symbol:</label>
          <input
            type="text"
            value={symbol}
            onChange={handleSymbolChange}
            className="form-input"
          />
        </div>
        <div className="form-group">
          <label className="form-label">Quantity:</label>
          <input
            type="number"
            value={quantity}
            onChange={handleQuantityChange}
            className="form-input"
          />
        </div>
        <button type="button" onClick={handleBuyStock} className="buy-button">Buy</button>
      </form>
    </div>
  );
};

export default BuyStock;
