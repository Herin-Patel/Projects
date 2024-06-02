import React from 'react';
import './Footer.css';
import { linkedin_logo } from '../../Assets/Images/Footer Images/Footer images';
import { github_logo } from '../../Assets/Images/Footer Images/Footer images';
import { instagram_logo } from '../../Assets/Images/Footer Images/Footer images';
import { facebook_logo } from '../../Assets/Images/Footer Images/Footer images';

function Footer() {
    return (
        <>
            <div className='box-line'></div>
            <br/>
            <div className='footer-bg'>
                <div className='icons'>
                    <div className='items' id='item1'>
                        <img src={linkedin_logo} alt='E-book store Logo.png' className='images-align'></img>
                    </div>
                    <div className='items' id='item2'>
                        <img src={github_logo} alt='E-book store Logo.png' className='images-align'></img>
                    </div>
                    <div className='items' id='ittem3'>
                        <img src={instagram_logo} alt='E-book store Logo.png' className='images-align'></img>
                    </div>
                    <div className='items' id='item4'>
                        <img src={facebook_logo} alt='E-book store Logo.png' className='images-align'></img>
                    </div>
                </div>
                <div className='icons'>
                    © Made by Group 3 : Herin, Darshan, KD, Jainik
                </div>
            </div>
            <br/>
            <div className='box-line'></div>
        </>
    )
}

export default Footer;