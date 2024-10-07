import { forwardRef, ReactNode } from "react";

interface InputProps extends React.HTMLProps<HTMLInputElement> {
    children: ReactNode;
}

export const Input = forwardRef<HTMLInputElement, InputProps>(
    ({ children, ...rest }, ref) => {
        return (
            <div className="flex flex-col gap-1 justify-center w-11/12 s640:w-[400px]">
                <label htmlFor={rest.id as string}>{children}</label>
                <input
                    ref={ref}
                    {...rest}
                    className={`w-full h-14 rounded-xl border border-solid border-[#CBCAD7] px-2 text-sm`}
                />
            </div>
        );
    }
);
