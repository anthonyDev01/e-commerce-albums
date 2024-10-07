import { render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import { AuthProvider } from "../context/AuthContext";

describe("Dashboard component", () => {
    test("renders correctly", () => {
        render(
            <MemoryRouter>
                <AuthProvider>
                    <Dashboard />
                </AuthProvider>
            </MemoryRouter>
            
        );

        expect(
            screen.getByText("A história da música não pode ser esquecida!")
        ).toBeInTheDocument();

        expect(
            screen.getByText("Sucessos que marcaram o tempo!!!")
        ).toBeInTheDocument();
    });
});
