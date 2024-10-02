import React from "react";
import CardAlbum from "../CardAlbum";
import { Album, InfoAlbum } from "../../models/Album";

interface ListAlbumsProps {
    onClick: (album: Album) => void;
    setInfoAlbum?: React.Dispatch<React.SetStateAction<InfoAlbum>>;
    dataAlbum: Album[];
}

const ListAlbums = ({ onClick, dataAlbum }: ListAlbumsProps) => {
    return (
        <section className="w-full flex flex-grow flex-col">
            <div className="w-full flex items-center justify-center">
                <h2 className="text-white text-[48px] w-11/12 ">Trends</h2>
            </div>

            <div className="flex justify-around items-center flex-wrap gap-4 p-2">
                {dataAlbum.map((data) => (
                    <CardAlbum onClick={() => onClick(data)} dataAlbum={data} />
                ))}
            </div>
        </section>
    );
};

export default ListAlbums;
