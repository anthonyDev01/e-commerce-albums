import { fireEvent, render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import { AuthProvider } from "../context/AuthContext";
import Wallet from "../pages/Wallet";

describe("Wallet component", () => {
    test("renders correctly", () => {
        render(
            <MemoryRouter>
                <AuthProvider>
                    <Wallet />
                </AuthProvider>
            </MemoryRouter>
        );

        expect(screen.getByText("Minha Carteira")).toBeInTheDocument();
        expect(screen.getByText("Total de Pontos")).toBeInTheDocument();
        expect(screen.getByText("Saldo Total")).toBeInTheDocument();

        const saldoButton = screen.getByText("Saldo Total");
        fireEvent.click(saldoButton);
    });
});
