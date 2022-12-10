import classes from './NavigationBar.module.css';
import { NavLink } from 'react-router-dom';

const NavigationBar = () => {
  return (
    <>
      <nav className={classes.navBar}>
        <NavLink to="/welcome" className={classes.appName}>
          Novi Pazar
        </NavLink>
        <NavLink to="/map" activeClassName={classes.active}>
          City Map
        </NavLink>
        <NavLink to="/login" activeClassName={classes.active}>
          Log in
        </NavLink>
        <section className={classes.registerButton}>
          <p className={classes.newAccount}>New Account</p>
        </section>
      </nav>
    </>
  );
};

export default NavigationBar;
