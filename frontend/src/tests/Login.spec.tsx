import { fireEvent, render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import { AuthProvider } from "../context/AuthContext";
import Login from "../pages/Login";

describe("Register component", () => {
    test("renders correctly", () => {
        render(
            <BrowserRouter>
                <AuthProvider>
                    <Login />
                </AuthProvider>
            </BrowserRouter>
        );

        expect(screen.getByText("Acesse sua conta")).toBeInTheDocument();

        const inputEmail = screen.getByLabelText("Email");
        expect(inputEmail).toBeInTheDocument();
        fireEvent.change(inputEmail);

        const inputPassword = screen.getByLabelText("Password");
        expect(inputPassword).toBeInTheDocument();
        fireEvent.change(inputPassword);

        const buttonCreate = screen.getByText("Entrar");
        expect(buttonCreate).toBeInTheDocument();
        fireEvent.click(buttonCreate);

        const buttonChangePage = screen.getByText("Inscrever-se");
        expect(buttonChangePage).toBeInTheDocument();
        fireEvent.click(buttonChangePage);
        expect(window.location.pathname).toBe("/register");
    });
});
