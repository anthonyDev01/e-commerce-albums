import { Link } from "react-router-dom";
import Button from "../Button";
import React, { useState } from "react";
import useAuth from "../../hooks/useAuth";
import avatar from "./assets/avatar.svg";

interface LoginToggleProps extends React.HTMLProps<HTMLDivElement> {
    openMenu: boolean;
}

const LoginToggle = ({ openMenu }: LoginToggleProps) => {
    const { token, logout } = useAuth();
    const [openButton, setOpenButton] = useState<boolean>(false);

    const handleButton = () => {
        setOpenButton(!openButton);
    };

    return (
        <div
            className={`bg-[#ffffff91] backdrop-blur-xl absolute top-[76px]  h-[20vh] max ${
                openMenu ? "hidden" : "flex"
            } w-full p-3 flex-col items-center justify-between gap-3 s440:flex-row s440:h-[11.5vh] s440:px-24 s540:gap-16 s840:flex s840:static s840:gap-3 s840:h-[47.58px] s840:p-0 s840:w-[412px] s840:bg-transparent s840:backdrop-blur-none`}
        >
            {!token ? (
                <>
                    <Button className="bg-sysmap_black text-white text-xl">
                        <Link to="/login">Entrar</Link>
                    </Button>
                    <Button className="bg-sysmap_ligth text-black text-xl">
                        <Link to="/register">Inscrever-se</Link>
                    </Button>
                </>
            ) : (
                <div className="w-full flex justify-around items-center">
                    <Link to="/MyDiscs" className="text-white">
                        Meus Discos
                    </Link>
                    <Link to="/wallet" className="text-white">
                        Carteira
                    </Link>

                    <div className="relative flex justify-center items-center">
                        <img
                            onClick={handleButton}
                            className="cursor-pointer"
                            src={avatar}
                            alt=""
                        />
                        {openButton && (
                            <div
                                onClick={logout}
                                className="absolute text-white w-24 -bottom-10 cursor-pointer bg-red-500 flex justify-center items-center rounded-lg"
                            >
                                Sair
                            </div>
                        )}
                    </div>
                </div>
            )}
        </div>
    );
};

export default LoginToggle;
