import { Transaction } from "../../models/Transaction";
import DarkBackGround from "../DarkBackGround";
import closeIcon from "./assets/close.svg";

interface TransactionModalProps {
    onClick: () => void;
    transaction: Transaction | undefined;
}

const TransactionModal = ({ onClick, transaction }: TransactionModalProps) => {
    function formatDate(dateString: string | undefined): string | null {
        if (!dateString) {
            return null;
        }

        const date = new Date(dateString);

        if (isNaN(date.getTime())) {
            return null;
        }

        const options: Intl.DateTimeFormatOptions = {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            hour12: false,
        };

        const formattedDate = date
            .toLocaleString("pt-BR", options)
            .replace(",", "");

        return formattedDate;
    }

    return (
        <DarkBackGround>
            <div className="relative bg-white flex flex-col justify-center p-4 gap-5 h-[550px] w-1/3 rounded-[20px] shadow-black">
                <h2 className="font-bold text-xl">{transaction?.album_name}</h2>
                <h3 className="font-semibold text-lg text-gray-800 ">
                    Aritista: {transaction?.artist_name}
                </h3>
                <h4 className="text-lg font-semibold">
                    $ {transaction?.value?.toFixed(2)}
                </h4>
                <span className="text-gray-600 font-medium">
                    {formatDate(transaction?.created_at)}
                </span>

                <div className=" w-1/3 flex justify-between items-center">
                    <span className="w-2/4 text-gray-600 font-medium ">
                        Pontos Ganhos:
                        <span className="font-medium text-black">
                            {transaction?.points_earned}
                        </span>
                    </span>
                </div>

                <div className="w-full flex flex-col gap-2 justify-center items-center">
                    <img
                        className="w-3/4 h-[200px] max-w-[400px]"
                        src={transaction?.image_url}
                        alt=""
                    />
                    <a
                        href={transaction?.spotify_url}
                        target="_blank"
                        className="w-10/12 flex cursor-pointer font-medium text-blue-800"
                    >
                        Ouvir agora
                    </a>
                </div>

                <img
                    className="absolute top-3 right-3 cursor-pointer"
                    src={closeIcon}
                    onClick={onClick}
                    alt=""
                />
            </div>
        </DarkBackGround>
    );
};

export default TransactionModal;
