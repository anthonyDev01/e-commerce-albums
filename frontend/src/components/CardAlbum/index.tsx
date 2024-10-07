import React from "react";
import { Album, InfoAlbum } from "../../models/Album";

interface CardAlbumProps extends React.HTMLProps<HTMLDivElement> {
    setInfoAlbum?: React.Dispatch<React.SetStateAction<InfoAlbum>>;
    dataAlbum?: Album;
}

const CardAlbum = ({ dataAlbum, ...rest }: CardAlbumProps) => {
    const imageUrl =
        Array.isArray(dataAlbum?.images) && dataAlbum?.images[0]?.url
            ? dataAlbum.images[0].url
            : dataAlbum?.image_url;

    return (
        <div
            {...rest}
            style={{
                backgroundImage: `url(${imageUrl})`,
            }}
            className=" bg-center bg-cover bg-no-repeat w-[263px] h-[285px] flex justify-around items-center flex-col rounded-[4.64px] cursor-pointer  shadow-cardShadow"
        >
            <h3 className="flex justify-center items-center text-white font-bold bg-background-title s740:text-[37.55px]">
                {dataAlbum?.name}
            </h3>
            <span className="text-white font-bold w-full flex justify-end pr-4 bg-background-title s740:text-[27.86px]">
                R$ {dataAlbum?.value}
            </span>
        </div>
    );
};

export default CardAlbum;
