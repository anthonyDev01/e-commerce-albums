import Logo from "../../components/Logo";
import { Input } from "../../components/Input";
import Button from "../../components/Button";
import { Link } from "react-router-dom";
import { useForm, SubmitHandler } from "react-hook-form";
import { AuthResponse, User } from "../../models/User";
import { api, getHeaders } from "../../services/apiService";
import { useState } from "react";
import { toast } from "react-toastify";
import useAuth from "../../hooks/useAuth";

const Login = () => {
    const { register, handleSubmit } = useForm<User>();
    const [loading, setLoading] = useState<boolean>(false);
    const { login } = useAuth();

    const onSubmit: SubmitHandler<User> = (data) => {
        console.log(data);
        setLoading(true);
        const toastId = toast.loading("Acessando sua conta...");

        api.post<AuthResponse>(
            "/users/auth",
            JSON.stringify(data),
            getHeaders()
        )
            .then((response) => {
                toast.dismiss(toastId);
                toast.success("Login feito com sucesso!");
                setTimeout(() => {
                    login(response.data.token);
                    setLoading(false);
                }, 2500);
            })
            .catch((error) => {
                console.log(error);
                setLoading(false);
                toast.dismiss(toastId);
                toast.error("Algo deu errado!");
            });
    };

    return (
        <main className="w-screen h-screen bg-background-home bg-cover bg-center ">
            <div className="w-screen h-screen backdrop-blur-[20px] flex items-center justify-center">
                <form
                    onSubmit={handleSubmit(onSubmit)}
                    className="bg-[#FCFCFC] w-11/12 h-[522px] flex items-center justify-center flex-col gap-8 rounded-3xl s640:w-[544px]"
                >
                    <Logo />
                    <h2 className="font-bold text-xl s640:text-[32px]">
                        Acesse sua conta
                    </h2>
                    <Input {...register("email")} type="email" required>
                        Email
                    </Input>
                    <Input {...register("password")} type="password" required>
                        Password
                    </Input>
                    <div className="w-11/12 h-14 s640:w-[400px]">
                        <Button
                            disabled={loading}
                            className={`text-white bg-sysmap_black text-[22px] font-medium ${
                                loading ? "opacity-50" : ""
                            }`}
                        >
                            Entrar
                        </Button>
                    </div>

                    <p className="text-[#686677] text-xs s640:text-base">
                        Ainda não tem uma conta?{" "}
                        <Link
                            to="/register"
                            className="font-medium text-black underline text-xs  s640:text-base"
                        >
                            Inscrever-se
                        </Link>
                    </p>
                </form>
            </div>
        </main>
    );
};

export default Login;
