import { ReactNode } from "react";

interface ButtonProps extends React.HTMLProps<HTMLButtonElement> {
    className: string;
    children: ReactNode;
}

const Button = ({ className, children, disabled, ...rest }: ButtonProps) => {
    return (
        <button
            {...rest}
            disabled={disabled}
            type="submit"
            className={`${className}  w-full h-full flex items-center justify-center rounded-[32px] font-semibold ${
                disabled ? "cursor-not-allowed" : "cursor-pointer"
            }`}
        >
            <span>{children}</span>
        </button>
    );
};

export default Button;
