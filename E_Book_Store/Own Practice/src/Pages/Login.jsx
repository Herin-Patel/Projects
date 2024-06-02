import React from 'react';
import './Pages.css';
import { useFormik } from "formik";
import * as Yup from 'yup';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import authService from '../Services/auth.service';
import { toast } from 'react-toastify';
// import authService from "../Services/auth.service";
// import { toast } from "react-toastify";
// import { useNavigate } from 'react-router-dom';
import { useAuthContext } from '../Context/auth.context';






import { emphasize, styled } from '@mui/material/styles';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Chip from '@mui/material/Chip';
import HomeIcon from '@mui/icons-material/Home';
// import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import LoginIcon from '@mui/icons-material/Login';
import NavigateNextIcon from '@mui/icons-material/NavigateNext';
// import { authContext } from '../App';

const StyledBreadcrumb = styled(Chip)(({ theme }) => {
    const backgroundColor =
        theme.palette.mode === 'light'
            ? theme.palette.grey[100]
            : theme.palette.grey[800];
    return {
        backgroundColor,
        height: theme.spacing(5),
        color: theme.palette.text.primary,
        fontWeight: theme.typography.fontWeightRegular,
        '&:hover, &:focus': {
            backgroundColor: emphasize(backgroundColor, 0.2),
        },
        '&:active': {
            boxShadow: theme.shadows[1],
            backgroundColor: emphasize(backgroundColor, 0.12),
        },
    };
});

function handleClick(event) {
    event.preventDefault();
    console.info('You clicked a breadcrumb.');
}







function Log_In() {


    // const myData = useContext( authContext);

    // console.log("myData", myData );



    // const navigate = useNavigate();
    const AuthContext = useAuthContext();

    // Pass the useFormik() hook initial form values and a submit function that will be called when the form is submitted
    const formik = useFormik(
        {
            initialValues: {
                email: '',
                password: '',
            },



            validationSchema: Yup.object(
                {
                    email: Yup.string()
                        .email('Invalid email address')
                        .required('Email is necessary'),

                    password: Yup.string()
                        .min(2, 'Too Short!')
                        .max(15, 'Must be 15 characters or less')
                        .required('Password is required'),

                }
            ),

            onSubmit: (values) => {
                //alert(JSON.stringify(values, null, 2));
                console.log("Log-In Data submitted", values);
                authService.login(values).then(
                    (res) => {
                        toast.success("Login successfully");
                        console.log("Result", res);
                        AuthContext.setUser(res);
                    }
                );

            }
        });

    return (
        <>
            <div className='container'>
                <div className='center-align'>
                    <div role="presentation" onClick={handleClick}>
                        <Breadcrumbs separator={<NavigateNextIcon fontSize="small" />} aria-label="breadcrumb">
                            <StyledBreadcrumb
                                component="a"
                                href="#"
                                label="Home"
                                icon={<HomeIcon fontSize="medium" />}
                            />
                            <StyledBreadcrumb
                                component="a"
                                href="#"
                                label="Login"
                                icon={<LoginIcon fontSize="medium" />}
                            />
                        </Breadcrumbs>
                    </div>
                </div>



                <h1 className='center-align'>
                    Login or Create an Account
                </h1>
                <div className='container-line'></div>
                <br />

                <div className="display-flex">
                    <div className='box'>
                        <h3>
                            New Customer
                        </h3>
                        <div className='container-line'></div>
                        <p>
                            Registration is free and easy
                        </p>
                        <ul>
                            <li>Faster checkout</li>
                            <li>Give multiple shipping addresses</li>
                            <li>View and track orders and more</li>
                        </ul>

                        <Button color="secondary" variant="contained" type="submit">
                            Create an Account
                        </Button>
                    </div>
                    <div className='box'>

                        <h3>
                            Registered Customers
                        </h3>
                        <div className='container-line'></div>
                        <br />

                        <form onSubmit={formik.handleSubmit}>

                            <label htmlFor="email">
                                Enter Email address :
                            </label>
                            <TextField
                                id="email"
                                name="email"
                                type="email"
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                value={formik.values.email}
                                fullWidth
                            />
                            {formik.touched.email && formik.errors.email ?
                                <div style={{
                                    color: "red",
                                    fontSize: 15,
                                    fontWeight: 490,
                                }}>
                                    {formik.errors.email}
                                </div> : null}
                            <br />
                            <br />

                            <label htmlFor="password">
                                Password :
                            </label>
                            <TextField
                                id="password"
                                name="password"
                                type="password"
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                value={formik.values.password}
                                fullWidth
                            />
                            {formik.touched.password && formik.errors.password ?
                                <div style={{
                                    color: "red",
                                    fontSize: 15,
                                    fontWeight: 490,
                                }}>
                                    {formik.errors.password}
                                </div> : null}
                            <br />
                            <br />

                            <Button color="secondary" variant="contained" type="submit">
                                Log In
                            </Button>

                        </form>
                    </div>
                </div>
                <br/>


            </div>
        </>
    )
}

export default Log_In;