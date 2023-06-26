import './App.css';
import StockTradingPlatform from './Components/StockTradingPlatform';
import axios from 'axios';
import { useEffect, useState } from 'react';

const API_BASE_URL = 'http://localhost:8080/api/stocks/2/portfolio';

const App = () => {
  const [data, setData] = useState();

  useEffect(() => {
    const loadAsyncStuff = async () => {
      try {
        const response = await axios.get(API_BASE_URL);
        const jsonData = response.data;

        setData(jsonData);
        console.log(jsonData);
      } catch (error) {
        console.log(error);
      }
    };

    loadAsyncStuff();
  }, []);

  return (
    <div className="App">
      <StockTradingPlatform data={data} />
    </div>
  );
};

export default App;
