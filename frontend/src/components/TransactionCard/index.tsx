import { Transaction } from "../../models/Transaction";

interface TransactionCardProps extends React.HTMLProps<HTMLDivElement> {
    transaction: Transaction;
    handleOpenTransaction?: (transaction: Transaction) => void;
}

const TransactionCard = ({
    transaction,
    handleOpenTransaction,
}: TransactionCardProps) => {
    function formatDate(dateString?: string) {
        if (!dateString) {
            return "Invalid date";
        }
        const date = new Date(dateString);

        const day = date.getUTCDate();

        const month = date.toLocaleString("pt-BR", { month: "short" });

        return `${day} ${month}`;
    }

    return (
        <div
            onClick={() => handleOpenTransaction?.(transaction)}
            className="flex w-full h-28 p-4 border-b-[1px] cursor-pointer"
        >
            <img src="" alt="" />
            <div className="w-full flex flex-col justify-center gap-2">
                <div className="flex justify-between w-full">
                    <h2 className="text-white font-semibold">
                        Compra Efetuada
                    </h2>
                    <span className="text-gray-300">
                        {formatDate(transaction?.created_at)}
                    </span>
                </div>
                <span className="text-gray-300">{transaction?.album_name}</span>
                <span className="text-gray-300">
                    R$ {transaction.value?.toFixed(2)}
                </span>
            </div>
        </div>
    );
};

export default TransactionCard;
