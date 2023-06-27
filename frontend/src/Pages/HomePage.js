import React from 'react'
import BuyStock from '../Components/BuyStock'
import SellStock from '../Components/SellStock'

const HomePage = () => {
  return (
    <div>
      <h1>Home Page</h1>
      <BuyStock/>
      <SellStock/>
    </div>
  )
}

export default HomePage