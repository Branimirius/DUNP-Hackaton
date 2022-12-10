import { urls } from "../utils/urls";
import axios from "axios";

export const loginUser = (loginCredentials: {
  username: string;
  password: string;
}) => {
  return axios.post(urls.login, loginCredentials);
};

export const createLocation = (newLocation: {
  category: string;
  latitude: number;
  longitude: number;
  description: string;
}) => {
  return axios.post(urls.createLocation, newLocation);
};
