import { IoIosClose, IoIosMenu } from "react-icons/io";

interface QuickMenuPorps {
    openMenu: boolean;
    toggleMenu: () => void;
}

const QuickMenu = ({ openMenu, toggleMenu }: QuickMenuPorps) => {
    return openMenu ? (
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
    );
};

export default QuickMenu;
