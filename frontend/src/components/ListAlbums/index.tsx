import React from "react";
import CardAlbum from "../CardAlbum";
import { Album, InfoAlbum } from "../../models/Album";

interface ListAlbumsProps {
    onClick?: (album: Album) => void;
    setInfoAlbum?: React.Dispatch<React.SetStateAction<InfoAlbum>>;
    dataAlbum: Album[];
    myAlbums?: Album[];
    className?: string;
}

const ListAlbums = ({
    onClick,
    dataAlbum,
    className,
    myAlbums,
}: ListAlbumsProps) => {
    return (
        <section className="w-full flex flex-col">
            <div
                className={`flex ${
                    className ? className : "justify-center "
                } items-center flex-wrap gap-4 p-2`}
            >
                {myAlbums
                    ? myAlbums
                          .filter((data) => !("deleted_at" in data))
                          .map((data) => (
                              <CardAlbum
                                  onClick={() => onClick?.(data)}
                                  key={data.id}
                                  dataAlbum={data}
                              />
                          ))
                    : dataAlbum.map((data) => (
                          <CardAlbum
                              onClick={() => onClick?.(data)}
                              key={data.id}
                              dataAlbum={data}
                          />
                      ))}
            </div>
        </section>
    );
};

export default ListAlbums;
