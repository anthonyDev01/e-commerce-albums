import { ReactNode } from "react";

interface ButtonProps {
    onclick?: () => void;
    className: string
    children: ReactNode
}

const Button = ({ className, onclick, children }: ButtonProps) => {
    return (
        <div
            className={`${className} w-full h-full flex items-center justify-center rounded-[32px] font-semibold cursor-pointer`}
            onClick={onclick}
        >
            <span>{children}</span>
        </div>
    );
};

export default Button;
