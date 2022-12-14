import React, { useState } from 'react';
import { IAuthContext } from '../types/AuthContext';

const authContextDefaults: IAuthContext = {
  token: '',
  isLoggedIn: true,
  login: (token: string) => { },
  logout: () => { },
};

const AuthContext = React.createContext<IAuthContext>(authContextDefaults);

export const AuthContextProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [token, setToken] = useState<string | null>(
    localStorage.getItem('token')
  );

  const [userIsLoggedIn, setUserIsLoggedIn] = useState<boolean>(
    token !== '' && token !== undefined && token !== null ? true : false
  );

  const logoutHandler = () => {
    setUserIsLoggedIn(false);
    setToken(null);

    localStorage.removeItem('token');
  };

  const loginHandler = (user: any) => {
    setUserIsLoggedIn(true);
    setToken(user!.token);

    localStorage.setItem('token', user.token);
    localStorage.setItem('username', user.user.username);
    localStorage.setItem('id', user.user.id);
  };

  const contextValue: IAuthContext = {
    token: token,
    isLoggedIn: userIsLoggedIn,
    login: loginHandler,
    logout: logoutHandler,
  };
  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
};

export default AuthContext;
