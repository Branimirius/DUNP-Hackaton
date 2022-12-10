import classes from './NavigationBar.module.css';
import { NavLink, useHistory } from 'react-router-dom';

const NavigationBar = () => {
  const history = useHistory();
  return (
    <nav className={classes.navBar}>
      <NavLink to="/welcome" className={classes.appName}>
        Novi Pazar
      </NavLink>
      <NavLink to="/newLocation" activeClassName={classes.active}>
        Add Location
      </NavLink>
      <NavLink to="/map" activeClassName={classes.active}>
        City Map
      </NavLink>
      <NavLink to="/login" activeClassName={classes.active}>
        Log in
      </NavLink>
      <section className={classes.registerButton} onClick={() => { history.push('/register') }}>
        <p className={classes.newAccount}>New Account</p>
      </section>
    </nav>
  );
};

export default NavigationBar;
