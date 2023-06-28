import React from 'react'

const StockFilter = ({search, handleChange}) => {
  return (
    <div className='test'>
      <input value={search} onChange={handleChange} placeholder="Search Stock"/>
    </div>
  )
}

export default StockFilter