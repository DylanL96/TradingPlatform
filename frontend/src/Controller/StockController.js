import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const buyStock = (userID, symbol, quantity) => {
  const payload = {
    symbol,
    quantity
  };

  return axios.post(`${API_BASE_URL}/stocks/${userID}/buy`, payload)
    .then(response => {
      console.log(response);
      console.log("successfully bought stock");
      return response.data; // Return the response data if needed
    })
    .catch(error => {
      console.log(error);
      throw error; // Throw the error to handle it in the component
    });
};

export const sellStock = (userID, symbol, quantity) => {
  const payload = {
    symbol,
    quantity
  };

  return axios.post(`${API_BASE_URL}/stocks/${userID}/sell`, payload)
    .then(response => {
      console.log(response);
      console.log("successfully sold stock");
      return response.data; // Return the response data if needed
    })
    .catch(error => {
      console.log(error);
      throw error; // Throw the error to handle it in the component
    });
};