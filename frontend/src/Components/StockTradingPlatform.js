import React from 'react';

const StockTradingPlatform = ({data}) => {

  return (
    <div>
      <h1>Stock Data</h1>
      {data && (
        <ul>
          {Object.entries(data).map(([symbol, quantity]) => (
            <li key={symbol}>
              Symbol: {symbol}, Quantity: {quantity}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default StockTradingPlatform;
