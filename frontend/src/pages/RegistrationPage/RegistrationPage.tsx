import { useRef } from 'react'
import { useHistory } from 'react-router-dom';
import { registerUser } from '../../services/apis';
import classes from '../LoginPage/Form.module.css'

export const RegistrationPage = () => {

    const username = useRef<HTMLInputElement>(null);
    const firstName = useRef<HTMLInputElement>(null);
    const lastName = useRef<HTMLInputElement>(null);
    const email = useRef<HTMLInputElement>(null);
    const password = useRef<HTMLInputElement>(null);
    const repeatPassword = useRef<HTMLInputElement>(null);

    const history = useHistory();

    const registerUserHandler = async (event: React.FormEvent) => {
        event.preventDefault();

        const response = await registerUser({
            username: username.current!.value, firstName: password.current!.value,
            lastName: lastName.current!.value, email: email.current!.value, password: password.current!.value, repeatPassword: repeatPassword.current!.value
        })

        console.log(response.data);

        history.push('/login')
    }

    return (<div className={classes.context}>
        <div>
            <h1 className={classes.heading}>Explore Novi Pazar</h1>
        </div>
        <div className={classes.container}>
            <div className={classes.title}>Registration</div>
            <form onSubmit={registerUserHandler}>
                <div className={classes.user__details}>
                    <div className={classes.input__box}>
                        <span className={classes.details}>First name</span>
                        <input type="text" placeholder="First name" required ref={firstName} />
                    </div>
                    <div className={classes.input__box}>
                        <span className={classes.details}>Last name</span>
                        <input type="text" placeholder="Last name" required ref={lastName} />
                    </div>
                    <div className={classes.input__box}>
                        <span className={classes.details}>Email</span>
                        <input type="email" placeholder="imeiprezime@gmail.com" required ref={email} />
                    </div>
                    <div className={classes.input__box}>
                        <span className={classes.details}>Username</span>
                        <input type="text" placeholder="Username" required ref={username} />
                    </div>
                    <div className={classes.input__box}>
                        <span className={classes.details}>Password</span>
                        <input type="password" placeholder="********" required ref={password} />
                    </div>
                    <div className={classes.input__box}>
                        <span className={classes.details}>Confirm Password</span>
                        <input type="password" placeholder="********" required ref={repeatPassword} />
                    </div>

                </div>
                <div className={classes.button}>
                    <input type="submit" value="Register" />
                </div>
            </form>
        </div>
    </div>
    )
}