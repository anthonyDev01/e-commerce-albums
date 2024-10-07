import { Link, useNavigate } from "react-router-dom";
import Button from "../../components/Button";
import { Input } from "../../components/Input";
import Logo from "../../components/Logo";
import { useForm, SubmitHandler } from "react-hook-form";
import { SignUpResponse, User } from "../../models/User";
import { api, getHeaders } from "../../services/apiService";
import { useState } from "react";
import { toast } from "react-toastify";
import ErrorLabel from "../../components/ErrorLabel";

const Register = () => {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<User>();
    const [loading, setLoading] = useState<boolean>(false);
    const navigate = useNavigate();

    const onSubmit: SubmitHandler<User> = (data) => {
        const toastId = toast.loading("Criando sua conta...");
        setLoading(true);

        api.post<SignUpResponse>(
            "/users/signUp",
            JSON.stringify(data),
            getHeaders()
        )
            .then((response) => {
                toast.dismiss(toastId);
                toast.success("Conta criado com sucesso");
                localStorage.setItem("wallet_id", response.data.wallet_id);
                setTimeout(() => {
                    navigate("/login");
                    setLoading(false);
                }, 2500);
            })
            .catch((error) => {
                console.log(error);
                toast.dismiss(toastId);

                const errorMessage = error.response.data.message;
                if (errorMessage == "User already exists")
                    toast.error("Usuario já existe!");
                else toast.error("Email ou senha inválidos!");

                setTimeout(() => {
                    setLoading(false);
                }, 2500);
            });
    };

    return (
        <main className="w-screen h-screen bg-[url('./assets/bg-1.jpg')] bg-cover bg-center flex items-center justify-center">
            <form
                onSubmit={handleSubmit(onSubmit)}
                className="bg-[#FCFCFC] w-11/12 h-[623px] flex items-center justify-center flex-col gap-8 rounded-3xl s640:w-[544px]"
            >
                <Logo />
                <h2 className="font-bold text-xl s640:text-[32px]">
                    Criar conta
                </h2>

                <div className="w-full flex flex-col items-center justify-center">
                    <Input
                        id="name"
                        {...register("name", { required: true, minLength: 3 })}
                        type="text"
                    >
                        Nome Completo
                    </Input>
                    <ErrorLabel fieldError={errors?.name} />
                </div>

                <div className="w-full flex flex-col items-center justify-center">
                    <Input
                        id="email"
                        {...register("email", { required: true })}
                        type="email"
                    >
                        Email
                    </Input>
                    <ErrorLabel fieldError={errors?.email} />
                </div>

                <div className="w-full flex flex-col items-center justify-center">
                    <Input
                        id="password"
                        {...register("password", {
                            required: true,
                            minLength: 8,
                        })}
                        type="password"
                    >
                        Password
                    </Input>
                    <ErrorLabel fieldError={errors?.password} />
                </div>

                <div className="w-11/12 h-14 s640:w-[400px]">
                    <Button
                        disabled={loading}
                        className={`text-white bg-sysmap_black text-[22px] font-medium ${
                            loading ? "opacity-50" : ""
                        }`}
                    >
                        Criar Conta
                    </Button>
                </div>

                <p className="text-[#686677] text-xs s640:text-base">
                    Já tem uma conta?
                    <Link
                        to="/login"
                        className="font-medium text-black underline text-xs  s640:text-base"
                    >
                        Entrar
                    </Link>
                </p>
            </form>
        </main>
    );
};

export default Register;
