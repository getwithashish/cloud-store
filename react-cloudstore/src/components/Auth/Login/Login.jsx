import {useState} from "react";
import { createPortal } from 'react-dom';

import gLogo from '/images/google.png';
import mailLogo from '/images/emailIcon.jpg';
import closeBtn from '/images/closeBtn.jpg';

import loginCss from './Login.module.css';

import EnterOTP from '../../Auth/EnterOTP/EnterOTP'

import axios from 'axios'

var loginState = {
    email: "",
    password: ""
};

var emailLoginHandleInput = event => {
    loginState.email = event.target.value
};

var passwordLoginHandleInput = event => {
    loginState.password = event.target.value
};


let Login = ({ setAuth, setLoggedIn }) => {
    const [phone, setPhone] = useState();

    let [otpModal, setOTPModal] = useState(false)

    let loginDiv = !otpModal ? <div className={loginCss.outerDiv}>
        <div className={loginCss.modal}>
            <div className={loginCss.header}>
                <span className={loginCss.ttl}>Login</span>
                <span className={loginCss.closeBtn} onClick={() => setAuth({ closed: true, login: false, signup: false })}>
                    <img className={loginCss.closeBtnImg} src={closeBtn} alt="close button" />
                </span>
            </div>
            <div className={loginCss.lgBox}>
                {/* <input className={loginCss.phoneInp} type="tel" placeholder='Phone number ...' onChange={(e) => setPhone(e.target.value)} /> */}
                <input className={loginCss.inpBox} name="email" type="email" placeholder='Email' onChange={emailLoginHandleInput} />
                <input className={loginCss.inpBox} name="password" type="password" placeholder='Password' onChange={passwordLoginHandleInput} />
                
                <div className={loginCss.forgotPass}>Forgot Password</div>
                
                {/* <button  className={phone?.length === 10 ? [loginCss.btn, loginCss.Sbtn].join(" ") : loginCss.btn} onClick={()=> phone?.length === 10 ? setOTPModal(true) : ""}>Send OTP</button> */}
                <button className={loginCss.btn} onClick={() => {

                    
                    
                    const loginUrl = 'http://localhost:8080/user/login';
                    const loginData = {
                        email: loginState.email,
                        password: loginState.password
                    };

                    axios
                          .post(loginUrl, loginData)
                          .then(data => {
                              let responseBody = data.data;
                              localStorage.setItem("auth", responseBody)
                              if(responseBody != ""){
                                setAuth({ closed: true, login: false, signup: false })
                                alert("Successfully logged in")
                                location.reload(true)
                              }
                              else{
                                  alert("Cannot log in! Wrong Credentials")
                              }
                            // alert(responseBody);
                          })
                          .catch(error => {
                            //something
                          });
                
            }
                
                }>Login</button>
            </div>
            {/* <div className={loginCss.orBreak}><span className={loginCss.orBreakText}>or</span></div> */}
            {/* <div className={loginCss.socialSignupBox}>
                <img className={loginCss.icon} src={mailLogo} alt="email signup" />
                Continue with Email
            </div> */}
            {/* <div className={loginCss.socialSignupBox}>
                <img className={loginCss.icon} src={gLogo} alt="google signup" />
                Continue with Google
            </div> */}
            <hr className={loginCss.break} />
            <div className={loginCss.newToZomato}>New to Cloud Store? <div className={loginCss.createAcc} onClick={() => setAuth({ closed: false, login: false, signup: true })}>Create Account</div></div>
        </div>
    </div> :  <EnterOTP setModal={setOTPModal} setLoggedIn={setLoggedIn} setAuth={setAuth} />
    return createPortal(loginDiv, document.getElementById('modal'));
}

export default Login;