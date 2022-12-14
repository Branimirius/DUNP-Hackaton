import { urls } from "../utils/urls";
import { axiosInstance } from "../config/axios-instance";
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
  userId: string;
  category: string;
  latitude: number;
  longitude: number;
  description: string;
}) => {
  return axiosInstance.post(urls.createLocation, newLocation);
};

const config = {
  headers: {
    "Content-Type": "multipart/form-data; boundary=AaB03x",
  },
};

export const uploadImage = (locationId: number, imageData: any) => {
  return axiosInstance.post(
    `${urls.createLocation}/${locationId}/image`,
    { image: imageData },
    config
  );
};

export const checkImageCategory = (imageData: any) => {
  return axios.post(`http://localhost:5000/image`, imageData);
};

export const getAllLocations = async () => {
  return axios.get(`${urls.getLocations}`);
};

export const createComment = (comment: any) => {
  return axiosInstance.post(urls.addComment, comment);
};

export const getLocationComments = async (locationId: number) => {
  return axios.get(`${urls.getLocationComments}/${locationId}/comments`);
};

export const getLocationImage = async (locationId: number) => {
  return axios.get(`${urls.getLocationImage}/${locationId}`, {
    responseType: "blob",
  });
};

export const filterLocations = (filters: any) => {
  return axios.post(urls.filterLocations, filters);
};

export const deleteLocation = (locationId: any) => {
  return axiosInstance.delete(`${urls.deleteLocation}/${locationId}`);
};
