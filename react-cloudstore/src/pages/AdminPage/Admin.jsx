import css from './Admin.module.css'

import rightArrow from '/icons/right-arrow.png'
import CathTheMatachImg from '/images/cathcthematch.jpg'
import NewInTownImg from '/images/newintown.jpg'
import TrendingThisWeekImg from '/images/trendingthisweek.jpg'
import CallingBarHoppersImg from '/images/callingallbarhoppers.jpg'

import CollectionsCard from '../../utils/Cards/card2/AdminCard'

import Navbar from '../../components/Navbars/NavigationBar/NavigationBar'
import { Link } from 'react-router-dom'

import admincss from './../../utils/Cards/card2/AdminCard.module.css'

import axios from 'axios'

let Collections = () => {

    return <div className={css.outerDiv}>
        <div className={css.title}>Admin Page</div>
        <div className={css.tagLine}>
            <span className={css.desc}>Perform administrative operations on shops and customers</span>
            {/* <span className={css.collectionPlacesTag}>All collections in Hyderabad <span className={css.rightArrowBox}><img className={css.rightArrow} src={rightArrow} alt="right arrow" /></span></span> */}
        </div>
        <div className={css.cards}>
            {/* <CollectionsCard  title="Catch the Match" places="30" /> */}
            
            {/* <CollectionsCard textSrc={"View All Customers"} title="New In Town" places="19"
                onClick={() => {
                    const adminViewCustUrl = "http://localhost:8080/admin/customers";
                    
                    const JWT = localStorage.getItem("auth")
                    const authorizationToken = 'Bearer '.concat(JWT)
                    axios
                        .get(adminViewCustUrl, {
                            headers: { Authorization: authorizationToken }
                        })
                        .then(response => {
                            console.log(response.data)
                        })
                        .catch((error) => {
                            console.log("error: " + error)
                        })
                }}
            /> */}

                <div className={admincss.card} onClick={() => {
                    const adminViewCustUrl = "http://localhost:8080/admin/customers";
                    
                    const JWT = localStorage.getItem("auth")
                    const authorizationToken = 'Bearer '.concat(JWT)
                    axios
                        .get(adminViewCustUrl, {
                            headers: { Authorization: authorizationToken }
                        })
                        .then(response => {
                            console.log(response.data)
                        })
                        .catch((error) => {
                            console.log("error: " + error)
                        })
                }} >
                    <div className={admincss.cardImg} >
                        {"View All Customers"}
                        </div>
                    {/* <div className={css.details}>
                        <div className={css.title}>{title}</div>
                        <div className={css.placesTxt}> <span className={css.count}> {places} </span><span className={css.places}>Places</span>  <span className={css.rightArrowBox} >
                            <img className={css.rightArrow} src={rightArrow} alt="right arrow" />
                        </span></div>
                    </div> */}
                 </div>

            <CollectionsCard textSrc={"Edit Customer Details"} title="Trending This Week" places="30" link={'/get-the-app'} />
            
            
            <CollectionsCard textSrc={"View Customer Log"} title="Calling all Bar Hoppers" places="30" link={'/get-the-app'} />
            
            {/* <CollectionsCard textSrc={"Disable customer"} title="Calling all Bar Hoppers" places="30" link={'/get-the-app'} /> */}

            <div className={admincss.card} onClick={() => {
                    const adminViewCustUrl = "http://localhost:8080/admin/customers";
                    
                    const emails = ["jerin@gmail.com"]

                    const JWT = localStorage.getItem("auth")
                    const authorizationToken = 'Bearer '.concat(JWT)
                    axios
                        .delete(adminViewCustUrl, 
                            {
                            headers: { Authorization: authorizationToken },
                            data: {emails}
                            // data: '["ashish@gmail.com"]'
                        })
                        .then(response => {
                            console.log(response.data)
                        })
                        .catch((error) => {
                            console.log("error: " + error)
                        })
                }} >
                    <div className={admincss.cardImg} >
                        {"Disable Customers"}
                        </div>
                    {/* <div className={css.details}>
                        <div className={css.title}>{title}</div>
                        <div className={css.placesTxt}> <span className={css.count}> {places} </span><span className={css.places}>Places</span>  <span className={css.rightArrowBox} >
                            <img className={css.rightArrow} src={rightArrow} alt="right arrow" />
                        </span></div>
                    </div> */}
                 </div>

            {/* <CollectionsCard textSrc={"View All Shops"} title="Calling all Bar Hoppers" places="30" link={'/get-the-app'} /> */}
            
            <div className={admincss.card} onClick={() => {
                    const adminViewCustUrl = "http://localhost:8080/admin/shops";
                    
                    const JWT = localStorage.getItem("auth")
                    const authorizationToken = 'Bearer '.concat(JWT)
                    axios
                        .get(adminViewCustUrl, {
                            headers: { Authorization: authorizationToken }
                        })
                        .then(response => {
                            console.log(response.data)
                        })
                        .catch((error) => {
                            console.log("error: " + error)
                        })
                }} >
                    <div className={admincss.cardImg} >
                        {"View All Shops"}
                        </div>
                    {/* <div className={css.details}>
                        <div className={css.title}>{title}</div>
                        <div className={css.placesTxt}> <span className={css.count}> {places} </span><span className={css.places}>Places</span>  <span className={css.rightArrowBox} >
                            <img className={css.rightArrow} src={rightArrow} alt="right arrow" />
                        </span></div>
                    </div> */}
                 </div>

            <CollectionsCard textSrc={"Edit Shop Details"} title="New In Town" places="19" link={'/get-the-app'} />
            <CollectionsCard textSrc={"View Shop Log"} title="Trending This Week" places="30" link={'/get-the-app'} />
            
            {/* <CollectionsCard textSrc={"Disable Shop"} title="Calling all Bar Hoppers" places="30" link={'/get-the-app'} /> */}
            
            <div className={admincss.card} onClick={() => {
                    const adminViewCustUrl = "http://localhost:8080/admin/shops";
                    
                    const emails = ["tharavaadu@gmail.com"]

                    const JWT = localStorage.getItem("auth")
                    const authorizationToken = 'Bearer '.concat(JWT)
                    axios
                        .delete(adminViewCustUrl, 
                            {
                            headers: { Authorization: authorizationToken },
                            data: {emails}
                            // data: '["ashish@gmail.com"]'
                        })
                        .then(response => {
                            console.log(response.data)
                        })
                        .catch((error) => {
                            console.log("error: " + error)
                        })
                }} >
                    <div className={admincss.cardImg} >
                        {"Disable Shops"}
                        </div>
                    {/* <div className={css.details}>
                        <div className={css.title}>{title}</div>
                        <div className={css.placesTxt}> <span className={css.count}> {places} </span><span className={css.places}>Places</span>  <span className={css.rightArrowBox} >
                            <img className={css.rightArrow} src={rightArrow} alt="right arrow" />
                        </span></div>
                    </div> */}
                 </div>

            <Link to={'/'} className={css.disableCustDiv} >
                {/* <CollectionsCard textSrc={"Go to HomePage"} title="Calling all Bar Hoppers" places="30"  /> */}
                
                <div className={admincss.card}  >
                    <div className={admincss.cardImg} >
                        {"Go to HomePage"}
                        </div>
                    {/* <div className={css.details}>
                        <div className={css.title}>{title}</div>
                        <div className={css.placesTxt}> <span className={css.count}> {places} </span><span className={css.places}>Places</span>  <span className={css.rightArrowBox} >
                            <img className={css.rightArrow} src={rightArrow} alt="right arrow" />
                        </span></div>
                    </div> */}
                 </div>

            </Link>
        
        </div>
    </div>
}

export default Collections;