import axios from 'axios';

require('dotenv-safe').config();

const port = process.env.PORT || 9000;
const BASE_URL = process.env.SERVER || `http://localhost:${port}`;
const TIME_OUT = 3000;
const HEADERS = { 'X-Custom-Header': 'foobar' };

//   baseURL: 'https://some-domain.com/api/',
//   timeout: 1000,
//   headers: {'ACCEPT': 'multipart/form-data'}

export const defaultInstance = axios.create({
  baseURL: BASE_URL,
  timeout: TIME_OUT,
  headers: HEADERS
});
