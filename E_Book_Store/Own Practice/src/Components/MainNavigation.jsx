import React from "react";
import { Routes, Route } from "react-router-dom";
import Books from '../Pages/Books';
import Register from "../Pages/Register";
import Log_In from "../Pages/Login";
import Categories from "../Pages/Categories";
import Update_Profile from "../Pages/Update Profile";
import Users from "../Pages/Users";
import { useAuthContext } from "../Context/auth.context";
import { RoutePaths } from "../Utilities/Enumerations";


const MainNavigation = () => {
    const AuthContext = useAuthContext();
    return (
        <>
            <Routes>
                <Route path={RoutePaths.Books} Component={Books} />
                <Route path={RoutePaths.Categories} Component={Categories} />
                <Route path={RoutePaths.Login} Component={Log_In} />
                <Route path={RoutePaths.Register} Component={Register} />
                <Route path={RoutePaths.UpdateProfile} Component={Update_Profile} />
                <Route path={RoutePaths.User} Component={Users} />
            </Routes>
            {/* <Routes>
                <Route path='/Books' Component={Books} />
                <Route path='/Categories' Component={Categories} />
                <Route path={RoutePaths.Login} Component={Log_In} />
                <Route path='/Register' Component={Register} />
                <Route path='/Update Profile' Component={Update_Profile} />
                <Route path='/Users' Component={Users} />
            </Routes> */}
        </>
    )
}

export default MainNavigation;



