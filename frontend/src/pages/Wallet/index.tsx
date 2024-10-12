import { useEffect, useState } from "react";
import InfoAlbumCard from "../../components/InfoAlbumCard";
import NavBar from "../../components/NavBar";
import TransactionCard from "../../components/TransactionCard";
import { api, getAuthorization } from "../../services/apiService";
import useAuth from "../../hooks/useAuth";
import { Transaction } from "../../models/Transaction";
import { User } from "../../models/User";
import { WalletReponse } from "../../models/Wallet";
import CreditModal from "../../components/CreditModal";
import TransactionModal from "../../components/TransactionModal";

const Wallet = () => {
    const { userId } = useAuth();
    const [wallet, setWallet] = useState<WalletReponse>();
    const [transactions, setTransactions] = useState<Transaction[]>();
    const [transaction, setTransaction] = useState<Transaction | undefined>();
    const [openModal, setOpenModal] = useState<boolean>(false);
    const [openTransaction, setOpenTransaction] = useState<boolean>(false);

    useEffect(() => {
        const fechTransactions = () => {
            api.get(
                `/transactions/users/transactions/${userId}`,
                getAuthorization()
            )
                .then((response) => {
                    setTransactions(response.data);
                    console.log(response.data);
                })
                .catch((error) => {
                    console.log(error.response);
                });
        };

        const fechWalletId = () => {
            api.get<User>(`/users/${userId}`, getAuthorization())
                .then((response) => {
                    fechBalance(response.data.wallet_id);
                })
                .catch((error) => {
                    console.log(error);
                });
        };

        const fechBalance = (walletId: string | undefined) => {
            api.get<WalletReponse>(`/wallet/${walletId}`, getAuthorization())
                .then((response) => {
                    setWallet(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        };

        fechWalletId();

        fechTransactions();
    }, [openModal]);

    const handleOpenModal = () => {
        setOpenModal(!openModal);
    };

    const handleOpenTransaction = (transaction: Transaction | undefined) => {
        setOpenTransaction(!openTransaction);
        setTransaction(transaction);
        console.log(transaction);
        
    };

    return (
        <>
            <NavBar />
            <main className="w-full min-h-screen flex flex-col items-center gap-16 justify-center bg-[#19181F]">
                <div className="flex flex-col justify-end items-center min-h-[340px] gap-4 w-10/12 s640:items-start">
                    <h2 className="text-white text-3xl">Minha Carteira</h2>
                    <div className="flex flex-col justify-center  gap-3 s640:flex-row">
                        <InfoAlbumCard TypeCard="total" label="Total de Pontos">
                            {wallet?.points}
                        </InfoAlbumCard>
                        <InfoAlbumCard
                            TypeCard="value"
                            label="Saldo Total"
                            onClick={handleOpenModal}
                        >
                            R$ {wallet?.balance?.toFixed(2)}
                        </InfoAlbumCard>
                    </div>
                </div>

                <div className="w-10/12 h-[500px] flex flex-col py-5 justify-start items-center overflow-y-auto s640:w-3/6">
                    {transactions?.map((data) => (
                        <TransactionCard
                            key={data.id}
                            transaction={data}
                            handleOpenTransaction={handleOpenTransaction}
                        />
                    ))}
                </div>

                {openModal && (
                    <CreditModal
                        onClick={handleOpenModal}
                        walletId={wallet?.wallet_id}
                    />
                )}

                {openTransaction && (
                    <TransactionModal
                        transaction={transaction}
                        onClick={() => setOpenTransaction(false)}
                    />
                )}
            </main>
        </>
    );
};

export default Wallet;
