import { Link } from 'react-router-dom'

import css from './AdminCard.module.css'

import axios from 'axios'

import rightArrow from '/icons/right-arrow.png'


let CollectionsCard = ({ textSrc, link }) => {
    return <div className={css.card}  >
        <div className={css.cardImg} >
            {textSrc}
            </div>
        {/* <div className={css.details}>
            <div className={css.title}>{title}</div>
            <div className={css.placesTxt}> <span className={css.count}> {places} </span><span className={css.places}>Places</span>  <span className={css.rightArrowBox} >
                <img className={css.rightArrow} src={rightArrow} alt="right arrow" />
            </span></div>
        </div> */}
    </div>
}



// let CollectionsCard = ({ textSrc, link }) => {
//     return <Link to={link} className={css.card}  onClick={() => {
//         const adminViewCustUrl = "http://localhost:8080/admin/customers";
        
//         const JWT = localStorage.getItem("auth")
//         const authorizationToken = 'Bearer '.concat(JWT)
//         axios
//             .get(adminViewCustUrl, {
//                 headers: { Authorization: authorizationToken }
//             })
//             .then(response => {
//                 console.log(response.data)
//             })
//             .catch((error) => {
//                 console.log("error: " + error)
//             })
//     }} >
//         <div className={css.cardImg} >
//             {textSrc}
//             </div>
//         {/* <div className={css.details}>
//             <div className={css.title}>{title}</div>
//             <div className={css.placesTxt}> <span className={css.count}> {places} </span><span className={css.places}>Places</span>  <span className={css.rightArrowBox} >
//                 <img className={css.rightArrow} src={rightArrow} alt="right arrow" />
//             </span></div>
//         </div> */}
//     </Link>
// }

export default CollectionsCard;