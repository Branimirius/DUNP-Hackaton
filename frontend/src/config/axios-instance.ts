import axios from "axios";

const axiosInstance = axios.create();

axiosInstance.interceptors.request.use(function (config: any) {
  const token = localStorage.getItem("token");
  config!.headers!.Authorization = `Bearer ${token}`;

  return config;
});

export { axiosInstance };
