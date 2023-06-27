import React, { useState } from 'react';

const BuyStock = () => {
  const [symbol, setSymbol] = useState('');
  const [quantity, setQuantity] = useState('');

  const handleSymbolChange = (event) => {
    setSymbol(event.target.value);
  };

  const handleQuantityChange = (event) => {
    setQuantity(event.target.value);
  };

  const handleBuyStock = () => {
    setSymbol('');
    setQuantity('');
  };

  return (
    <div>
      <h2>Buy Stock</h2>
      <form>
        <div>
          <label>Stock Symbol:</label>
          <input type="text" value={symbol} onChange={handleSymbolChange} />
        </div>
        <div>
          <label>Quantity:</label>
          <input type="number" value={quantity} onChange={handleQuantityChange} />
        </div>
        <button type="button" onClick={handleBuyStock}>Buy</button>
      </form>
    </div>
  );
};

export default BuyStock;
