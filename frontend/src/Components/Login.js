import React, { useState } from 'react';
import axios from 'axios';
import isEmpty from 'validator/lib/isEmpty';
import isEmail from 'validator/lib/isEmail';
import { useNavigate } from 'react-router-dom';
import '../Styles/Login.css';

const Login = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    errorMessage: false,
    loading: false
  });

  const { email, password, errorMessage, loading } = formData;

  const handleChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
      errorMessage: ''
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Sign in form submitted');

    if (isEmpty(email) || isEmpty(password)) {
      setFormData({
        ...formData,
        errorMessage: 'All fields are required'
      });
    } else if (!isEmail(email)) {
      setFormData({
        ...formData,
        errorMessage: 'Invalid email'
      });
    } else {
      setFormData({ ...formData, loading: true });

      axios
        .post('http://localhost:8080/authenticate', formData)
        .then((response) => {
          console.log(response);
          const userId = response.data.userID;
          navigate(`/dashboard/${userId}`);
        })
        .catch((error) => {
          console.log(error);
          setFormData({
            ...formData,
            errorMessage: 'Invalid email or password. Try again.'
          });
        });
    }
  };

  const showSigninForm = () => (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h2 className="login-heading">Log in</h2>
        <div className="form-group">
          <label htmlFor="exampleInputEmail1">Email address</label>
          <input
            name="email"
            type="email"
            className="form-control"
            id="exampleInputEmail1"
            aria-describedby="emailHelp"
            placeholder="Enter email"
            onChange={handleChange}
          />
          <small id="emailHelp" className="form-text text-muted">
            We'll never share your email with anyone else.
          </small>
        </div>
        <div className="form-group">
          <label htmlFor="exampleInputPassword1">Password</label>
          <input
            name="password"
            type="password"
            className="form-control"
            id="exampleInputPassword1"
            placeholder="Password"
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="btn btn-primary login-button">
          Submit
        </button>
      </form>
    </div>
  );

  return (
    <div>
      {loading}
      {errorMessage}
      {showSigninForm()}
    </div>
  );
};

export default Login;
