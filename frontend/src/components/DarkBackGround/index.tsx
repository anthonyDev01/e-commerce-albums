import React, { ReactNode } from "react";

interface DarkBackGroundProps extends React.HTMLProps<HTMLDivElement> {
    children: ReactNode;
}

const DarkBackGround = ({ children }: DarkBackGroundProps) => {
    return (
        <div className="fixed top-0 w-screen h-screen backdrop-blur-[2px] bg-[rgb(0 0 0 / 0.1)] flex justify-center items-center">
            {children}
        </div>
    );
};

export default DarkBackGround;
