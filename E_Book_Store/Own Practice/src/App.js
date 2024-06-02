import './App.css';
import { ToastContainer } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";
import Header from './Components/Header/Header';
import Footer from './Components/Footer/Footer';
import { BrowserRouter } from 'react-router-dom';
import MainNavigation from './Components/MainNavigation';
import { AuthWrapper } from './Context/auth.context';



// import { createContext } from 'react';

// const data = {
//   name : "",
//   age : 0,
// }

// export const authContext = createContext(data);

// const setUser = ( userDetail ) => {
//   data.name = userDetail.name;
// }

// const values = {
//   name : "Herin",
//   age : 10,
// }




function App() {

  return (
    <>
      {/* <authContext.Provider value={values}> */}
      <BrowserRouter>
        <ToastContainer />
        <AuthWrapper>
          <Header />
          <div className='flex-display'>
            <MainNavigation />
          </div>
          <br />
          <Footer />
        </AuthWrapper>
      </BrowserRouter>
      {/* </authContext.Provider> */}
    </>
  );
}

export default App;
