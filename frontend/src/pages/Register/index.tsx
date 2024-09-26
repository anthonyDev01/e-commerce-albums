import Button from "../../components/Button";
import { Input } from "../../components/Input";
import Logo from "../../components/Logo";

const Register = () => {
    return (
        <main className="w-screen h-screen bg-[url('./assets/bg-1.jpg')] bg-cover bg-center flex items-center justify-center">
            <div className="bg-[#FCFCFC] w-11/12 h-[623px] flex items-center justify-center flex-col gap-8 rounded-3xl s640:w-[544px]">
                <Logo />
                <h2 className="font-bold text-xl s640:text-[32px]">
                    Criar conta
                </h2>

                <Input name="name" type="text">
                    Nome Completo
                </Input>

                <Input name="email" type="email">
                    Email
                </Input>
                <Input name="password" type="password">
                    Password
                </Input>
                <div className="w-11/12 h-14 s640:w-[400px]">
                    <Button className="text-white bg-sysmap_black text-[22px] font-medium">
                        Entrar
                    </Button>
                </div>

                <p className="text-[#686677] text-xs s640:text-base">
                    Já tem uma conta?{" "}
                    <a
                        href="#"
                        className="font-medium text-black underline text-xs  s640:text-base"
                    >
                        Entrar
                    </a>
                </p>
            </div>
        </main>
    );
};

export default Register;
