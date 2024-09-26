import { ReactNode } from "react";

interface InputProps extends React.HTMLProps<HTMLInputElement> {
    className?: string;
    type: "text" | "email" | "password";
    name: "name" | "password" | "email";
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
    children: ReactNode;
}

export function Input({ className, type, name, children }: InputProps) {
    return (
        <div className="flex flex-col gap-1 justify-center w-11/12 s640:w-[400px]">
            <label htmlFor={name}>{children}</label>
            <input
                name={name}
                type={type}
                className={
                    className
                        ? className
                        : " w-full h-14 rounded-xl border border-solid border-[#CBCAD7] px-2 text-sm "
                }
            />
        </div>
    );
}
