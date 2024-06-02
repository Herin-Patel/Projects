import React, { useState } from 'react';
import { ts_logo } from '../../Assets/Images/Images';
import { eb_logo } from '../../Assets/Images/Images';
import './Header.css';
import Button from '@mui/material/Button';
import bookService from '../../Services/book.service';
import { List, ListItem, TextField } from '@mui/material';
// import { BrowserRouter } from 'react-router-dom';
// import MainNavigation from '../MainNavigation';
import SearchIcon from '@mui/icons-material/Search';


function Header() {




    
    // const open = false;
    const [query, setquery] = useState(" ");
    const [bookList, setbookList] = useState([]);
    const [openSearchResult, setopenSearchResult] = useState(false);

    // const openMenu = () => {
    //     document.body.classList.toggle("open-menu");
    // };

    const searchBook = async () => {
        const res = await bookService.searchBook(query);
        setbookList(res);
    };

    const search = () => {
        document.body.classList.add("search-results-open");
        searchBook();
        setopenSearchResult(true);
    };






    return (
        <>
            <img src={ts_logo} alt='TatvaSoft Logo.png' className='ts-logo-align left-pad'></img>
            <div className='top-pad'>
                <div className='header-bg'>
                    <div className='item' id='item1'>
                        <img src={eb_logo} alt='E-book store Logo.png' className='top-pad eb-logo-align'></img>
                    </div>
                    <div className='item' id='item2'>
                        <h1 className='eb-title'>
                            E-book Store
                        </h1>
                    </div>
                    <div className='item' id='item3'>
                        <Button variant="contained" href='/Home'>
                            Home
                        </Button>
                        &nbsp;
                        <Button variant="contained" href='/Login'>
                            Login
                        </Button>
                        &nbsp;
                        <Button variant="contained" href='/Register'>
                            Register
                        </Button>
                        &nbsp;
                        <Button variant="contained" href='/Users'>
                            Users
                        </Button>
                        &nbsp;
                        <Button variant="contained" href='/Categories'>
                            Categories
                        </Button>
                        &nbsp;
                        <Button variant="contained" href='/Books'>
                            Books
                        </Button>
                        &nbsp;
                        <Button variant="contained" href='/Update Profile'>
                            Update Profile
                        </Button>
                        <br />

                    </div>
                </div>
            </div>
            <br />
            <div className='box-line'></div>
            <br />
    


            {/* Search box with cancel button */}
            <div className='center-align search-bar'> 
            <div className='search-box'>   
            <TextField
                placeholder="What are you looking for ..."
                id="text"
                name="text"
                variant="outlined"
                value={query}
                onChange={(e) => setquery(e.target.value)}
                fullWidth
            />
            
                {/* Display the search results */}
                {openSearchResult && (
                    <>
                        <div>
                            {bookList?.length === 0 && (
                                <p style={{color:'red', paddingLeft:'10px'}}>
                                    <b>
                                    No product found
                                    </b>
                                </p>
                            )}
                        </div>

                        <List>
                            { bookList?.length > 0 && bookList.map( (item, i) =>
                                    {
                                        return(
                                                    <ListItem key={i}>
                                                        <span>
                                                            {item.name}
                                                        </span>
                                                        <p>
                                                            {item.description}
                                                        </p>
                                                        <span>
                                                            {item.price}
                                                        </span>
                                                    </ListItem>
                                                );
                                    }
                                )
                            }
                        </List>

                    </>
                )}
            </div>   
            &nbsp;
            &nbsp;
            {/* <div className='search-button'> */}
            <Button
                type="submit"
                variant='contained'
                color="secondary"
                disableElevation
                onClick={search}
                startIcon={<SearchIcon />}
                className='search-button'
                // icon={<SearchIcon fontSize="medium" />}
            >
                {/* <Breadcrumbs
                    icon={<SearchIcon fontSize="medium" />}
                ></Breadcrumbs> */}
                Search
            </Button>
            {/* </div> */}
            </div>
            <br/>



            <div className='box-line'></div>
            <br />
            {/* <br/>
            <BrowserRouter>
                <MainNavigation />
            </BrowserRouter> */}
        </>
    )
}

export default Header;