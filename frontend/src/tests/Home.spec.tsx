import { AuthProvider } from "../context/AuthContext";
import { fireEvent, render, screen } from "@testing-library/react";
import Home from "../pages/Home";
import { MemoryRouter } from "react-router-dom";

describe("Home component", () => {
    test("renders correctly ", () => {
        render(
            <MemoryRouter>
                <AuthProvider>
                    <Home />
                </AuthProvider>
            </MemoryRouter>
        );

        expect(
            screen.getByText("A história da música não pode ser esquecida!")
        ).toBeInTheDocument();

        expect(
            screen.getByText(
                "Crie já sua conta e curta os sucessos que marcaram os tempos no Vinil."
            )
        ).toBeInTheDocument();

        const buttons = screen.getAllByText("Inscrever-se");
        expect(buttons).toHaveLength(2);
        fireEvent.click(buttons[0]);

        expect(buttons[0]).toBeInTheDocument();
        expect(buttons[1]).toBeInTheDocument();

        const enterButton = screen.getByText("Entrar");
        fireEvent.click(enterButton);
        expect(enterButton).toBeInTheDocument();
    });
});
