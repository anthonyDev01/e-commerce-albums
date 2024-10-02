import { Link } from "react-router-dom";
import Button from "../Button";
import React from "react";
import useAuth from "../../hooks/useAuth";
import avatar from "./assets/avatar.svg";

interface LoginToggleProps extends React.HTMLProps<HTMLDivElement> {
    openMenu: boolean;
}

const LoginToggle = ({ openMenu }: LoginToggleProps) => {
    const { token } = useAuth();

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
                    <a href="" className="text-white">
                        Meus Discos
                    </a>
                    <a href="" className="text-white">
                        Carteira
                    </a>

                    <img src={avatar} alt="" />
                </div>
            )}
        </div>
    );
};

export default LoginToggle;
