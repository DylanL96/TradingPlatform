import axios from 'axios';

export const authenticateUser = (formData) => {
  return axios.post('http://localhost:8080/authenticate', formData)
  .then(response => response.data)
  .catch(error => {
    throw error;
  });
};
