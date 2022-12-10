import { axiosInstance } from "../config/axios-instance";
import { urls } from "../utils/urls";
import axios from "axios";

export const loginUser = (loginCredentials: {
  username: string;
  password: string;
}) => {
  return axios.post(urls.login, loginCredentials);
};
