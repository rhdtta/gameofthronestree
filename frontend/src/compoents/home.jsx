import { Link, NavLink, Outlet } from "react-router-dom";
import backgroundImages from '../assets/backdrop.jpg';
import icon from '../assets/icon.png';
export default function Home() {
    const styles = {
            backgroundImage: `url(${backgroundImages})`,
            backgroundPosition: 'center',
            backgroundSize: 'cover',
            // backgroundRepeat: 'no-repeat',
    };
    return (
        <>  
            <div className="main-page" style={styles}>
                <div className="menu">
                    <Link to="/">
                        <img src={icon} className="icon-main"/>
                    </Link>
                </div>
                <div id="graph" >
                    <Outlet/>
                </div>
            </div>
        </>
    )
}