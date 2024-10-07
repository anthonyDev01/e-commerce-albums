import { fireEvent, render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import { AuthProvider } from "../context/AuthContext";
import Register from "../pages/Register";

describe("Register component", () => {
    test("renders correctly", () => {
        render(
            <BrowserRouter>
                <AuthProvider>
                    <Register />
                </AuthProvider>
            </BrowserRouter>
        );

        expect(screen.getByText("Criar conta")).toBeInTheDocument();

        const inputName = screen.getByLabelText("Nome Completo");
        expect(inputName).toBeInTheDocument();
        fireEvent.change(inputName);

        const inputEmail = screen.getByLabelText("Email");
        expect(inputEmail).toBeInTheDocument();
        fireEvent.change(inputEmail);

        const inputPassword = screen.getByLabelText("Password");
        expect(inputPassword).toBeInTheDocument();
        fireEvent.change(inputPassword);

        const buttonCreate = screen.getByText("Criar Conta");
        expect(buttonCreate).toBeInTheDocument();
        fireEvent.click(buttonCreate);

        const buttonChangePage = screen.getByText("Entrar");
        expect(buttonChangePage).toBeInTheDocument();
        fireEvent.click(buttonChangePage);
        expect(window.location.pathname).toBe("/login");
    });
});
