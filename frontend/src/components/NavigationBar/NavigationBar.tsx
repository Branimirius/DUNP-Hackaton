import classes from './NavigationBar.module.css';
import { NavLink, useHistory } from 'react-router-dom';
import { useContext } from 'react';
import AuthContext from '../../store/auth-context';

const NavigationBar = () => {
  const history = useHistory();
  const { isLoggedIn, logout } = useContext(AuthContext);
  return (
    <nav className={classes.navBar}>
      <NavLink to="/welcome" className={classes.appName}>
        Novi Pazar
      </NavLink>
      {isLoggedIn && <NavLink to="/newLocation" activeClassName={classes.active}>
        Add Location
      </NavLink>}
      <NavLink to="/map" activeClassName={classes.active}>
        City Map
      </NavLink>
      {!isLoggedIn && <NavLink to="/login" activeClassName={classes.active}>
        Log in
      </NavLink>}
      {!isLoggedIn && <section className={classes.registerButton} onClick={() => { history.push('/register') }}>
        <p className={classes.newAccount}>New Account</p>
      </section>}
      {isLoggedIn && <section className={classes.registerButton} onClick={() => { logout(); history.push('/login') }}>
        <p className={classes.newAccount}>Log out</p>
      </section>}
    </nav>
  );
};

export default NavigationBar;
