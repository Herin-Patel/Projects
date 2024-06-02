import React from 'react';
import './Pages.css';
import { useFormik } from "formik";
import * as Yup from 'yup';
import Button from '@material-ui/core/Button';
import authService from "../Services/auth.service";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { FormControl, MenuItem, TextField } from "@material-ui/core";
import { Select } from '@mui/material';
// import Button from '@mui/material/Button';
// import { FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material';




import { emphasize, styled } from '@mui/material/styles';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Chip from '@mui/material/Chip';
import HomeIcon from '@mui/icons-material/Home';
import AppRegistrationOutlinedIcon from '@mui/icons-material/AppRegistrationOutlined';
import NavigateNextIcon from '@mui/icons-material/NavigateNext';

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
}); // TypeScript only: need a type cast here because https://github.com/Microsoft/TypeScript/issues/26591

function handleClick(event) {
    event.preventDefault();
    console.info('You clicked a breadcrumb.');
}




function Register() {

    const navigate = useNavigate();

    const roleList = [
        { id: 2, name: "buyer" },
        { id: 3, name: "seller" }
    ];





    // const [ roleList, setRoleList ] = useState( [] );

    // useEffect( () => {
    //     if( roleList.length )
    //     {
    //         return;
    //     }
    //     getRoles();
    // }, [roleList]);

    // const getRoles = () => {
    //     userService.getAllRoles().then( (res) => {
    //         setRoleList(res);
    //     });
    // };




    // Pass the useFormik() hook initial form values and a submit function that will be called when the form is submitted
    const formik = useFormik(
        {
            initialValues: {
                firstName: '',
                lastName: '',
                email: '',
                roleId: 0,
                id: 0,
                password: '',
                confirmpass: '',
            },



            validationSchema: Yup.object(
                {
                    firstName: Yup.string()
                        .min(2, 'Too Short!')
                        .max(15, 'Must be 15 characters or less')
                        .required('First name is required'),

                    lastName: Yup.string()
                        .min(2, 'Too Short!')
                        .max(20, 'Must be 20 characters or less')
                        .required('Last name is required'),

                    email: Yup.string()
                        .email('Invalid email address')
                        .required('Email is necessary'),

                    password: Yup.string()
                        .min(2, 'Too Short!')
                        .max(15, 'Must be 15 characters or less')
                        .required('Password is required'),

                    confirmpass: Yup.string()
                        .oneOf([Yup.ref('password')], 'Your passwords do not match.')
                        .required('Please retype your password.'),

                }
            ),

            onSubmit: (values) => {
                //alert(JSON.stringify(values, null, 2));
                console.log("Data submitted", values);

                delete values.id;
                delete values.confirmpass;
                authService.create(values).then((res) => {
                    navigate("/Login");
                    toast.success("Successfully registered");
                });
            },
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
                                label="Register"
                                icon={<AppRegistrationOutlinedIcon fontSize="medium" />}
                            />
                        </Breadcrumbs>
                    </div>
                </div>


                <h1 className='center-align'>
                    Register or Create an Account
                </h1>
                <div className='container-line'></div>
                <br />



                <form onSubmit={formik.handleSubmit} className="display-flex">
                    <div className='box'>
                        <h3>
                            Personal Information
                        </h3>
                        <div className='container-line'></div>

                        <br />
                        <label htmlFor="firstName">
                            Enter First Name :
                        </label>
                        <TextField
                            id="firstName"
                            name="firstName"
                            type="text"
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            value={formik.values.firstName}
                            fullWidth
                        />
                        {formik.touched.firstName && formik.errors.firstName ?
                            <div style={{
                                color: "red",
                                fontSize: 15,
                                fontWeight: 490,
                            }}>
                                {formik.errors.firstName}
                            </div> : null}
                        <br />
                        <br />
                        <label htmlFor="lastName">
                            Enter Last Name :
                        </label>
                        <TextField
                            id="lastName"
                            name="lastName"
                            type="text"
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            value={formik.values.lastName}
                            fullWidth
                        />
                        {formik.touched.lastName && formik.errors.lastName ?
                            <div style={{
                                color: "red",
                                fontSize: 15,
                                fontWeight: 490,
                            }}>
                                {formik.errors.lastName}
                            </div> : null}
                        <br />
                        <br />

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

                        <FormControl
                            className="dropdown-wrapper"
                            variant="outlined"
                        >
                            <label htmlFor="roleId">
                                Role of User :
                            </label>
                            <Select
                                name="roleId"
                                id={"roleId"}
                                onChange={formik.handleChange}
                            >
                                {roleList.length > 0 &&
                                    roleList.map((role) => (
                                        <MenuItem value={role.id} key={"name" + role.id}>
                                            {role.name}
                                        </MenuItem>
                                    ))
                                }

                            </Select>

                        </FormControl>
                    </div>

                    <div className='box'>

                        <h3>
                            Login Information
                        </h3>
                        <div className='container-line'></div>

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

                        <label htmlFor="confirmpass">
                            Confirm password :
                        </label>
                        <TextField
                            id="confirmpass"
                            name="confirmpass"
                            type="password"
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            value={formik.values.confirmpass}
                            fullWidth
                        />
                        {formik.touched.confirmpass && formik.errors.confirmpass ?
                            <div style={{
                                color: "red",
                                fontSize: 15,
                                fontWeight: 490,
                            }}>
                                {formik.errors.confirmpass}
                            </div> : null}
                        <br />
                        <br />

                        <Button color="secondary" variant="contained" type="submit">
                            Register
                        </Button>
                    </div>
                </form>
            </div>
        </>
    )
}

export default Register;