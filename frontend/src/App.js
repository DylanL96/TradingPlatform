import './App.css';
import StockTradingPlatform from './Components/StockTradingPlatform';
import axios from 'axios';
import { useEffect, useState } from 'react';

const API_BASE_URL = 'http://localhost:8080/api/stocks/4/portfolio';

const App = () => {
  const [data, setData] = useState();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(API_BASE_URL);
        const jsonData = response.data;

        setData(jsonData);
        setLoading(false);
      } catch (error) {
        console.log(error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  } 

  return (
    <div className="App">
      {data ? (
        <StockTradingPlatform data={data.stocks} />
      ) : (
        <div>No data available</div>
      )}
    </div>
  );
};

export default App;
