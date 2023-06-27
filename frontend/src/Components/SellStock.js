import React, { useState } from 'react';
import axios from 'axios';

const SellStock = ({userID}) => {
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
    <div>
      <h2>Sell Stock</h2>
      <form>
        <div>
          <label>Stock Symbol:</label>
          <input type="text" value={symbol} onChange={handleSymbolChange} />
        </div>
        <div>
          <label>Quantity:</label>
          <input type="number" value={quantity} onChange={handleQuantityChange} />
        </div>
        <button type="button" onClick={handleSellStock}>Buy</button>
      </form>
    </div>
  );
};

export default SellStock;
