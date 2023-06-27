import React from 'react';
import Login from './Components/Login';
import { Router, Route, Routes } from 'react-router';
// Import the UserDashboard component
import DashBoard from './Pages/DashBoard';
import HomePage from './Pages/HomePage';

const App = () => {


  return (
      <Routes>
        <Route path="/" element={<Login/>}/>
        {/* <Route path="/login" element={<Login/>}/> */}
        <Route exact path="/dashboard/:userId" element={<DashBoard/>} />
      </Routes>
  );
};

export default App;
