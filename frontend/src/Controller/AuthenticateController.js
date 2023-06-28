import axios from 'axios';

export const authenticateUser = async (formData) => {
  try {
    const response = await axios.post('http://localhost:8080/authenticate', formData);
    return response.data;
  } catch (error) {
    throw error;
  }
};
