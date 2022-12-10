import classes from './Form.module.css';
import { useContext, useRef } from 'react';
import { loginUser } from '../../services/apis';
import AuthContext from '../../store/auth-context';
import { useHistory } from 'react-router-dom';

export const LoginPage = () => {
    const { login } = useContext(AuthContext);

    const history = useHistory();

    const userName = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);

    const loginUserHandler = async (event: React.FormEvent) => {
        event.preventDefault();

        const response = await loginUser({
            username: userName.current!.value,
            password: passwordRef.current!.value,
        });

        login(response.data);

        history.push('*')
    };
    return (
        <div className={classes.context}>
            <div>
                <h1 className={classes.heading}>Explore Novi Pazar</h1>
            </div>
            <div className={classes.container}>
                <div className={classes.title}>Login</div>
                <form onSubmit={loginUserHandler} className={classes.form}>
                    <div className={classes.user__details}>
                        <div className={classes.input__box}>
                            <span className={classes.details}>Username</span>
                            <input type="text" placeholder="First name" required ref={userName} />
                        </div>
                        <div className={classes.input__box}>
                            <span className={classes.details}>Password</span>
                            <input type={classes.password} placeholder="********" required ref={passwordRef} />
                        </div>
                    </div>
                    <div className={classes.button}>
                        <input type="submit" value="Login" />
                    </div>
                </form>
            </div>
        </div>
    )
};