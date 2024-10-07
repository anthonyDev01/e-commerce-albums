import Logo from "../Logo";
import { useState } from "react";
import QuickMenu from "../QuickMenu";
import LoginToggle from "../LoginToggle";
import useAuth from "../../hooks/useAuth";
import { Link } from "react-router-dom";

const NavBar = () => {
    const [openMenu, setOpenMenu] = useState<boolean>(true);

    const { token } = useAuth();

    const toggleMenu = () => {
        setOpenMenu(!openMenu);
    };

    return (
        <nav className="fixed w-screen h-[75px] flex justify-around items-center backdrop-blur-[50px] bg-[#FFFFFF4D] s840:justify-between s840:px-[100px]">
            <Link to={token ? "/dashboard" : "/"}>
                <Logo text="BootPlay" />
            </Link>

            <LoginToggle openMenu={openMenu} />

            <QuickMenu openMenu={openMenu} toggleMenu={toggleMenu} />
        </nav>
    );
};

export default NavBar;
