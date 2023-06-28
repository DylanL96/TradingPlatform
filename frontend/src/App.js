import React from 'react';
import Login from './Components/Login';
import {Route, Routes } from 'react-router';
// Import the UserDashboard component
import DashBoard from './Pages/DashBoard';
import HomePage from './Pages/HomePage';
import IndividualStock from './Pages/IndividualStockPage';

const App = () => {


  return (
      <Routes>
        <Route path="/" element={<HomePage/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route exact path="/dashboard/:userId" element={<DashBoard/>} />
        <Route path ='/:stock' element={<IndividualStock/>}/>
      </Routes>
  );
};

export default App;
