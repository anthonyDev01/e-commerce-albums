import React, { ReactNode } from "react";
import fileVideo from "./assets/file-video.svg";
import dolarIcon from "./assets/dollar-sign.svg";
import pointsIcon from "./assets/estrela.png";

interface InfoAlbumCardProps extends React.HTMLProps<HTMLDivElement> {
    TypeCard: "total" | "value" | "points";
    label: string;
    children: ReactNode;
    onClick?: () => void;
}

const InfoAlbumCard = ({
    TypeCard,
    children,
    label,
    onClick,
}: InfoAlbumCardProps) => {
    return (
        <div
            className="w-[237px] h-[87px] bg-white flex items-center justify-around rounded-[10px] shadow-cardShadow cursor-pointer"
            onClick={onClick}
        >
            <div className="w-[40px] h-[40px] flex justify-center items-center bg-black rounded-full">
                <img
                    src={
                        TypeCard === "total"
                            ? fileVideo
                            : TypeCard === "points"
                            ? pointsIcon
                            : dolarIcon
                    }
                    alt=""
                />
            </div>

            <div className="flex flex-col">
                <span className="font-medium text-base">{label}</span>
                <span className="font-medium text-lg">{children}</span>
            </div>
        </div>
    );
};

export default InfoAlbumCard;
