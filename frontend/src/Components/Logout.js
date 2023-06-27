import React from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Perform logout actions here
    // For example, clear user session, remove tokens, etc.
    // You can also make an API request to revoke the user's authentication token

    // After logout, navigate to the desired page
    navigate('/');
  };

  return (
    <button onClick={handleLogout}>Logout</button>
  );
};

export default Logout;
