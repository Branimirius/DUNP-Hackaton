import { Switch, Route, Redirect } from 'react-router-dom';
import './App.css';
import NavigationBar from './components/NavigationBar/NavigationBar';
import CityMapPage from './pages/CityMapPage/CityMapPage';
import { HomePage } from './pages/HomePage/HomePage';
import { LoginPage } from './pages/LoginPage/LoginPage';
import { NewLocationPage } from './pages/NewLocationPage/NewLocationPage';
import { RegistrationPage } from './pages/RegistrationPage/RegistrationPage';

export default function App() {
  return (
    <>
      <NavigationBar />

      <Switch>

        <Route path="/" exact>
          <Redirect to="/welcome" />
        </Route>

        <Route path="/welcome">
          <HomePage />
        </Route>

        <Route path="/newLocation">
          <NewLocationPage />
        </Route>

        <Route path="/map">
          <CityMapPage />
        </Route>

        <Route path="/login">
          <LoginPage />
        </Route>

        <Route path="/register">
          <RegistrationPage />
        </Route>

        <Route path="*">
          <Redirect to="/" />

        </Route>
      </Switch>
    </>
  );
}