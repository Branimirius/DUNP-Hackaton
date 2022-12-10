import { urls } from "../utils/urls";
import axios from "axios";

export const loginUser = (loginCredentials: {
  username: string;
  password: string;
}) => {
  return axios.post(urls.login, loginCredentials);
};

export const registerUser = (newUser: {
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  repeatPassword: string;
}) => {
  return axios.post(urls.register, newUser);
};

export const createLocation = (newLocation: {
  userId: number;
  category: string;
  latitude: number;
  longitude: number;
  description: string;
}) => {
  return axios.post(urls.createLocation, newLocation);
};

const config = {
  headers: {
    "Content-Type": "multipart/form-data; boundary=AaB03x",
  },
};

export const uploadImage = (locationId: number, imageData: any) => {
  return axios.post(
    `${urls.createLocation}/${locationId}/image`,
    { image: imageData },
    config
  );
};

export const getAllLocations = async () => {
  return axios.get(`${urls.getLocations}`);
};
