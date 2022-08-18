import { createPortal } from 'react-dom';

import gLogo from '/images/google.png';
import mailLogo from '/images/emailIcon.jpg';
import closeBtn from '/images/closeBtn.jpg';

import signupCss from './Signup.module.css';

import axios from 'axios';

// componentDidMount() {

//     const recipeUrl = 'http://localhost:8080/api/recipes';

//     const postBody = {

//         type: "hot",

//         limit: 10

//     };

//     const requestMetadata = {

//         method: 'POST',

//         headers: {

//             'Content-Type': 'application/json'

//         },

//         body: JSON.stringify(postBody)

//     };


//     fetch(recipeUrl, requestMetadata)
//         .then(res => res.json())
//         .then(recipes => {
//             this.setState({ recipes });
//         });

// }

// class Something extends Component {

var registerState = {
    fullName: "",
    email: "",
    password: ""
};

var fullNameRegisterHandleInput = event => {
    // setState({[event.target.name]: event.target.value})  
    registerState.fullName = event.target.value
};

var emailRegisterHandleInput = event => {
    // setState({[event.target.name]: event.target.value})  
    registerState.email = event.target.value
};

var passwordRegisterHandleInput = event => {
    // setState({[event.target.name]: event.target.value})  
    registerState.password = event.target.value
};


let Signup = ({ setAuth }) => {
    let loginDiv = <div className={signupCss.outerDiv}>
        <div className={signupCss.modal}>
            <div className={signupCss.header}>
                <span className={signupCss.ttl}>Signup</span>
                <span className={signupCss.closeBtn} onClick={() => setAuth({ closed: true, login: false, signup: false })}>
                    <img className={signupCss.closeBtnImg} src={closeBtn} alt="close button" />
                </span>
            </div>
            <div className={signupCss.lgBox}>
                <input className={signupCss.inpBox} name="fullName" type="text" placeholder='Full Name' onChange={fullNameRegisterHandleInput} />
                <input className={signupCss.inpBox} name="email" type="email" placeholder='Email' onChange={emailRegisterHandleInput} />
                <input className={signupCss.inpBox} name="password" type="password" placeholder='Password' onChange={passwordRegisterHandleInput} />
                {/* <span className={signupCss.termsTxt}>
                    <input type="checkbox" name="accpect" id="accpect" className={signupCss.checkBox} />
                    <span>
                        I agree to Zomato's <a href="" className={signupCss.termaAnchor}>Terms of Service, Privacy Policy</a> and <a href="" className={signupCss.termaAnchor}>Content Policies</a>
                    </span>
                </span> */}
                <button className={signupCss.btn} onClick={() => {
                    const registerUrl = 'http://localhost:8080/register';

                    const registerData = {
                        fullName: registerState.fullName,
                        email: registerState.email,
                        password: registerState.password
                    };
                    
                    axios
                          .post(registerUrl, registerData)
                          .then(data => {
                            setAuth({ closed: true, login: false, signup: false });
                            alert("Successful Registration");
                          })
                          .catch(error => {
                            //something
                          });
                }}>Create Account</button>
            </div>
            {/* <div className={signupCss.orBreak}><span className={signupCss.orBreakText}>or</span></div>
            <div className={signupCss.socialSignupBox}>
                <img className={signupCss.icon} src={gLogo} alt="google login" />
                Continue with Google
            </div> */}
            <hr className={signupCss.break} />
            <div className={signupCss.newToZomato}>Already have an account? <div className={signupCss.createAcc} onClick={() => setAuth({ closed: false, login: true, signup: false })} >Log in</div></div>
        </div>
    </div>
    return createPortal(loginDiv, document.getElementById('modal'));
}

// }

export default Signup;