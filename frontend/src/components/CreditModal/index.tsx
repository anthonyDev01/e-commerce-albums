import { Input } from "../Input";
import Button from "../Button";
import DarkBackGround from "../DarkBackGround";
import closeIcon from "./assets/close.svg";
import { api, getAuthorization } from "../../services/apiService";
import { useState } from "react";
import { WalletReponse } from "../../models/Wallet";
import { toast } from "react-toastify";

interface CreditModalProps {
    onClick?: () => void;
    walletId: string | undefined;
}

const CreditModal = ({ onClick, walletId }: CreditModalProps) => {
    const [credit, setCredit] = useState<string>();

    const handleCredit = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCredit(e.target.value); // Atualiza o estado com o valor atual do input
    };

    const addBalance = () => {
        if (Number(credit) > 0) {
            console.log(walletId);

            api.post<WalletReponse>(
                "/wallet/credit",
                {
                    balance: Number(credit),
                    wallet_id: walletId,
                },
                getAuthorization()
            )
                .then((response) => {
                    console.log(response.data);
                    toast.success("Credito adicionado com sucesso!");
                    if (onClick) {
                        onClick();
                    }
                })
                .catch((error) => {
                    console.log(error);
                    toast.error("Algo deu errado durante o processo!");
                });
        } else {
            toast.error("O credito precisa ser maior que 0!");
        }
    };

    return (
        <DarkBackGround>
            <div className="relative w-full flex flex-col justify-around items-center gap-3 h-[306px] bg-white s540:rounded-[20px] s540:w-[400px]">
                <h2 className="text-xl font-semibold">Adicionar Credito</h2>
                <div className="w-10/12 flex justify-center items-center">
                    <Input type="number" onChange={handleCredit}>
                        Quantidade
                    </Input>
                </div>

                <div className="w-10/12 h-14">
                    <Button
                        className="bg-[#FBBC05] text-white"
                        onClick={addBalance}
                    >
                        Adicionar
                    </Button>
                </div>

                <img
                    className="absolute top-2 right-3 cursor-pointer"
                    onClick={onClick}
                    src={closeIcon}
                    alt=""
                />
            </div>
        </DarkBackGround>
    );
};

export default CreditModal;
