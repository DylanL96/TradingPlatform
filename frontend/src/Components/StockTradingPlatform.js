import React, { useEffect, useState } from 'react';

const StockTradingPlatform = () => {
  // const API_BASE_URL = 'http://localhost:8080/api/stocks';
  // const [stocks, setStocks] = useState([]);
  // const [selectedStock, setSelectedStock] = useState('');
  // const [quantity, setQuantity] = useState(0);

  // useEffect(() => {
  //   fetchStocks();
  // }, []);

  // const fetchStocks = () => {
  //   getStocks()
  //     .then(response => {
  //       setStocks(response.data);
  //     })
  //     .catch(error => {
  //       console.log('Error fetching stocks:', error);
  //     });
  // };

  // const handleBuyStock = () => {
  //   const stockToBuy = stocks.find(stock => stock.symbol === selectedStock);
  //   if (!stockToBuy) {
  //     console.log('Stock not found');
  //     return;
  //   }

  //   const buyRequest = {
  //     stockId: stockToBuy.id,
  //     quantity: quantity
  //   };

  //   buyStock(userId, buyRequest)
  //     .then(response => {
  //       console.log('Stock bought successfully');
  //       // Perform any necessary updates or refresh data
  //     })
  //     .catch(error => {
  //       console.log('Error buying stock:', error);
  //     });
  // };

  // const handleSellStock = () => {
  //   // Similar logic as handleBuyStock, but using the sellStock() function
  // };

  return (
    <div>
      <h1>Stock Trading Platform</h1>
      {/* <select value={selectedStock} onChange={event => setSelectedStock(event.target.value)}>
        <option value="">Select a stock</option>
        {stocks.map(stock => (
          <option key={stock.id} value={stock.symbol}>{stock.name}</option>
        ))}
      </select>
      <input type="number" value={quantity} onChange={event => setQuantity(event.target.value)} />
      <button onClick={handleBuyStock}>Buy</button>
      <button onClick={handleSellStock}>Sell</button> */}
    </div>
  );
};

export default StockTradingPlatform;
