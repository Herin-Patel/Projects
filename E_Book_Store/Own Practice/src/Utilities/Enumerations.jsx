// import Books from '../Pages/Books';
// import Register from "../Pages/Register";
// import Log_In from "../Pages/Login";
// import Categories from "../Pages/Categories";
// import Update_Profile from "../Pages/Update Profile";
// import Users from "../Pages/Users";



export const RoutePaths = {
  Login: "/Login",
  Register: "/Register",
  User: "/Users",
  EditUser: "/edit-user/:id",
  Categories: "/Categories",
  EditCategory: "/edit-category/:id",
  AddCategory: "/add-category",
  Books: "/Books",
  EditBook: "/edit-book/:id",
  AddBook: "/add-book",
  BookListing: "/",
  Cart: "/Cart",
  UpdateProfile: "/Update Profile",
};

// export const RoutePaths = {
//     Login: Log_In,
//     Register: Register,
//     User: Users,
//     EditUser: "/edit-user/:id",
//     Categories: Categories,
//     EditCategory: "/edit-category/:id",
//     AddCategory: "/add-category",
//     Books: Books,
//     EditBook: "/edit-book/:id",
//     AddBook: "/add-book",
//     BookListing: "/",
//     Cart: "/Cart",
//     UpdateProfile: Update_Profile,
//   };
  
  export const Role = {
    Admin: 1,
    Seller: 2,
    Buyer: 3,
  };
  