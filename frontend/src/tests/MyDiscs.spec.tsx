import { render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";

import { AuthProvider } from "../context/AuthContext";
import MyDiscs from "../pages/MyDiscs";

describe("MyDiscs component", () => {
    test("renders correctly", () => {
        render(
            <MemoryRouter>
                <AuthProvider>
                    <MyDiscs />
                </AuthProvider>
            </MemoryRouter>
        );

        expect(screen.getByText("Meus Discos")).toBeInTheDocument();
        expect(screen.getByText("Total de Albums")).toBeInTheDocument();
        expect(screen.getByText("Valor Investido")).toBeInTheDocument();
    });
});
