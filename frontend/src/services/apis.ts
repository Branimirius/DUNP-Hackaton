import { axiosInstance } from "../config/axios-instance";
import { urls } from "../utils/urls";

export const loginUser = (loginCredentials: {
  username: string;
  password: string;
}) => {
  return axiosInstance.post(urls.login, loginCredentials);
};
