import React, { createContext, useContext, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
// import Shared from "../Utilities/Shared-common";
// import { RoutePaths } from "../Utilities/Enumerations";





const initialUserValue = {
  id: 0,
  email: "",
  firstName: "",
  lastName: "",
  roleId: 0,
  role: "",
  password: "",
};


const initialState = {
  setUser: () => { },
  user: initialUserValue,
  signOut: () => { },
}

const authContext = createContext(initialState);








const Role = {
  Admin: 1,
  Seller: 2,
  Buyer: 3,
};



const LocalStorageKeys = {
  USER: "user",
};

const NavigationItems = [
  {
    name: "Users",
    route: "/Users",
    access: [Role.Admin],
  },
  {
    name: "Categories",
    route: "/Categories",
    access: [Role.Admin],
  },
  {
    name: "Books",
    route: "/Books",
    access: [Role.Admin, Role.Seller],
  },
  {
    name: "Update Profile",
    route: "/Update Profile",
    access: [Role.Admin, Role.Buyer, Role.Seller],
  },
];


const hasAccess = (pathname, user) => {
  const navItem = NavigationItems.find((navItem) =>
    pathname.includes(navItem.route)
  );

  if (navItem) {
    return (
      !navItem.access ||
      !!(navItem.access && navItem.access.includes(user.roleId))
    );
  }

  return true;
};





export const AuthWrapper = ({ children }) => {


  const [user, _setUser] = useState(initialUserValue);

  const navigate = useNavigate();

  const { pathname } = useLocation();

  const setUser = (user) => {
    localStorage.setUser(LocalStorageKeys.USER, JSON.stringify(user));
    _setUser(user);
  };

  const signOut = () => {
    localStorage.removeItem(LocalStorageKeys.USER);
    _setUser(initialUserValue);
    navigate("/Login");
  }


  useEffect(() => {
    // const str = JSON.parse(localStorage.getItem(LocalStorageKeys.USER)) || initialUserValue;
    const str = JSON.stringify(localStorage.getItem(LocalStorageKeys.USER)) || initialUserValue;

    if (str.id) {
      _setUser(str);
    }
    if (!str.id) {
      // navigate("/Books");
    }

  }, []);

  useEffect(() => {
    if (pathname === "/Login" && user.id) {
      navigate("/Books");
    }

    if (!user.id) {
      return;
    }

    const access = LocalStorageKeys.USER.hasAccess(pathname, user);

    if (!access) {
      toast.warning("Sorry, you are not authorized to access this page");
      navigate("/Books");
      return;
    }

  },
    // eslint-disable-next-line import/no-anonymous-default-export
    [user, pathname]);




  const value = {
    user,
    setUser,
    signOut,
  };


  return <authContext.Provider value={value}>
    {children}
  </authContext.Provider>

}


export const useAuthContext = () => {

  return useContext(authContext);
}
