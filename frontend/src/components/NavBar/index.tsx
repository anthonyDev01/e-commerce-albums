import { IoIosClose, IoIosMenu } from "react-icons/io";
import Button from "../Button";
import Logo from "../Logo";
import { useState } from "react";

const NavBar = () => {
    const [openMenu, setOpenMenu] = useState<boolean>(false);

    const toggleMenu = () => {
        setOpenMenu(!openMenu);
        console.log(openMenu);
    };

    return (
        <nav className="fixed w-screen h-[75px] flex justify-around items-center  backdrop-blur-[50px] bg-[#FFFFFF4D] s840:justify-between s840:px-[100px]">
            <Logo text="BootPlay" />

            <div
                className={`bg-[#ffffff91] backdrop-blur-xl absolute top-[76px]  h-[20vh] max ${
                    openMenu ? "hidden" : "flex"
                } w-full p-3 flex-col items-center justify-between gap-3 s440:flex-row s440:h-[11.5vh] s440:px-24 s540:gap-16 s840:flex s840:static s840:gap-3 s840:h-[47.58px] s840:p-0 s840:w-[412px] s840:bg-transparent s840:backdrop-blur-none`}
            >
                <Button className="bg-sysmap_black text-white text-xl">
                    Entrar
                </Button>
                <Button className="bg-sysmap_ligth text-black text-xl">
                    Inscrever-se
                </Button>
            </div>

            {openMenu ? (
                <IoIosMenu
                    size={49}
                    className="cursor-pointer s840:hidden"
                    onClick={toggleMenu}
                />
            ) : (
                <IoIosClose
                    size={49}
                    className="cursor-pointer s840:hidden"
                    onClick={toggleMenu}
                />
            )}
        </nav>
    );
};

export default NavBar;
