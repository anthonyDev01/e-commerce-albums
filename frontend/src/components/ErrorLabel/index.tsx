import React from "react";
import { FieldError } from "react-hook-form";

interface ErrorLabelProps extends React.HTMLProps<HTMLParagraphElement> {
    fieldError: FieldError | undefined;
}

const ErrorLabel = ({ fieldError }: ErrorLabelProps) => {
    return (
        fieldError && (
            <p className="text-sm text-red-700">
                {fieldError.type === "required"
                    ? "Campo obrigatório!"
                    : fieldError.type === "minLength" &&
                      "O mínimo de caracteres é 8!"}
            </p>
        )
    );
};

export default ErrorLabel;
