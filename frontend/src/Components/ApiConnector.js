import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'https://www.alphavantage.co/query'
})

export const getDailyChartForSymbol = (symbol) => {
  // console.log(symbol)
  return axiosInstance.get('', {
    params:{
      function: 'TIME_SERIES_DAILY',
      symbol,
      apikey: 'JM0HCE3A9YO9E60Y'
    }
  })
}