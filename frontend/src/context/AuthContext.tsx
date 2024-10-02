import { jwtDecode } from "jwt-decode";
import { createContext, ReactNode, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

interface AuthContextProps {
    children: ReactNode;
}

interface AuthContextType {
    token: string | null;
    login: (token: string) => void;
    logout: () => void;
}

interface Token {
    sub: string;
    iat: number;
    exp: number;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

const AuthProvider = ({ children }: AuthContextProps) => {
    const [token, setToken] = useState<string | null>(
        localStorage.getItem("token")
    );
    const navigate = useNavigate();

    useEffect(() => {
        if (token) {
            try {
                const decodedToken = jwtDecode<Token>(token);

                if (decodedToken.exp * 1000 < Date.now()) {
                    logout();
                }
            } catch (error) {
                logout();
            }
        }
    }, []);

    const login = (token: string) => {
        localStorage.setItem("token", token);
        setToken(token);

        navigate("/dashboard");
    };

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
        navigate("/login");
    };

    return (
        <AuthContext.Provider value={{ token, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export { AuthProvider, AuthContext };
