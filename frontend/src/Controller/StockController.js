import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/stocks';

export const getStocks = () => {
  return axios.get(API_BASE_URL);
};

export const buyStock = (userId, stock) => {
  return axios.post(`${API_BASE_URL}/${userId}/buy`, stock);
};

export const sellStock = (userId, stock) => {
  return axios.post(`${API_BASE_URL}/${userId}/sell`, stock);
};
