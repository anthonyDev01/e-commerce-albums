import { ToastContainer } from "react-toastify";
import Dashboard from "./pages/Dashboard";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import "react-toastify/dist/ReactToastify.css";
import { AuthProvider } from "./context/AuthContext";
import ProtectedRoute from "./routes/ProtectedRoute";
import MyDiscs from "./pages/MyDiscs";
import Wallet from "./pages/Wallet";

function App() {
    return (
        <>
            <ToastContainer position="top-right" autoClose={1800} />

            <Router>
                <AuthProvider>
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />

                        <Route element={<ProtectedRoute />}>
                            <Route path="/dashboard" element={<Dashboard />} />
                            <Route path="/myDiscs" element={<MyDiscs />} />
                            <Route path="/wallet" element={<Wallet />} />
                        </Route>
                    </Routes>
                </AuthProvider>
            </Router>
        </>
    );
}

export default App;
