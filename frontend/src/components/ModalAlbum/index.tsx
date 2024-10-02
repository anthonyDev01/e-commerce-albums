import React from "react";
import { Album } from "../../models/Album";
import Button from "../Button";
import DarkBackGround from "../DarkBackGround";
import closeButton from "./assets/close.svg";

interface ModalAlbumProps extends React.HTMLProps<HTMLDivElement> {
    selectedAlbum: Album | undefined;
    onClick: () => void;
}

const ModalAlbum = ({ selectedAlbum, onClick }: ModalAlbumProps) => {
    return (
        <DarkBackGround>
            <div className="relative bg-white flex justify-center items-center w-[607px] h-[306.74px] rounded-[20px] overflow-hidden">
                <div
                    className="h-full w-2/4 bg-cover bg-center bg-no-repeat"
                    style={{
                        backgroundImage: `url(${selectedAlbum?.images[0].url})`,
                    }}
                ></div>

                <div className="h-full w-2/4 flex flex-col justify-around items-center">
                    <h3>{selectedAlbum?.name}</h3>
                    <p>Detalhes do album...</p>
                    <div className="w-[258.85px] h-[41.42px]">
                        <Button className="bg-[#FBBC05] text-white s740:text-[22px]">
                            Comprar
                        </Button>
                    </div>
                </div>

                <img
                    className="absolute right-2 top-2 cursor-pointer"
                    src={closeButton}
                    onClick={onClick}
                />
            </div>
        </DarkBackGround>
    );
};

export default ModalAlbum;
