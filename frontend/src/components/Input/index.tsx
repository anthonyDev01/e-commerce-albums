import { forwardRef, ReactNode } from "react";

interface InputProps extends React.HTMLProps<HTMLInputElement> {
    className?: string;
    type: "text" | "email" | "password";

    children: ReactNode;
}

export const Input = forwardRef<HTMLInputElement, InputProps>(
    ({ className, type, children, ...rest }, ref) => {
        return (
            <div className="flex flex-col gap-1 justify-center w-11/12 s640:w-[400px]">
                <label>{children}</label>
                <input
                    ref={ref}
                    type={type}
                    {...rest}
                    className={
                        className
                            ? className
                            : "w-full h-14 rounded-xl border border-solid border-[#CBCAD7] px-2 text-sm"
                    }
                />
            </div>
        );
    }
);
