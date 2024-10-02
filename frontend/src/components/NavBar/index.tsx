import Logo from "../Logo";
import { useState } from "react";
import QuickMenu from "../QuickMenu";
import LoginToggle from "../LoginToggle";

const NavBar = () => {
    const [openMenu, setOpenMenu] = useState<boolean>(false);

    const toggleMenu = () => {
        setOpenMenu(!openMenu);
        console.log(openMenu);
    };

    return (
        <nav className="fixed w-screen h-[75px] flex justify-around items-center backdrop-blur-[50px] bg-[#FFFFFF4D] s840:justify-between s840:px-[100px]">
            <Logo text="BootPlay" />

            <LoginToggle openMenu={openMenu} />

            <QuickMenu openMenu={openMenu} toggleMenu={toggleMenu} />
        </nav>
    );
};

export default NavBar;
