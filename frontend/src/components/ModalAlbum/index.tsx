import { Album } from "../../models/Album";
import Button from "../Button";
import DarkBackGround from "../DarkBackGround";
import closeButton from "./assets/close.svg";
import { api, getAuthorization } from "../../services/apiService";
import useAuth from "../../hooks/useAuth";
import { toast } from "react-toastify";

interface ModalAlbumProps extends React.HTMLProps<HTMLDivElement> {
    selectedAlbum: Album | undefined;
    onClick: () => void;
}

const ModalAlbum = ({ selectedAlbum, onClick }: ModalAlbumProps) => {
    const { userId } = useAuth();

    const getArtists = () => {
        let artistsString = "";
        selectedAlbum?.artists?.forEach((artist) => {
            artistsString += artist.name + ", ";
        });
        return artistsString;
    };

    function formatDate(isoDate: string | undefined) {
        if (!isoDate) return null;

        const date = new Date(isoDate);
        const day = String(date.getUTCDate()).padStart(2, "0");
        const month = String(date.getUTCMonth() + 1).padStart(2, "0");
        const year = date.getUTCFullYear();

        return ` ${day}/${month}/${year}`;
    }

    const buyAlbum = () => {
        const updatedSelectedAlbum = {
            album: {
                ...selectedAlbum,
            },
            user_id: userId,
        };

        const toastId = toast.loading("Comprando Album...");

        api.post(
            "/albums/sale",
            JSON.stringify(updatedSelectedAlbum),
            getAuthorization()
        )
            .then(() => {
                toast.dismiss(toastId);
                toast.success("Album comprado com sucesso");
            })
            .catch((error) => {
                console.log(error.response.data);
                toast.dismiss(toastId);

                const errorMessage = error.response?.data?.message;

                if (
                    errorMessage ===
                    "The album entered already belongs to the user"
                )
                    toast.error("Você já possui esse álbum!");
                else if (
                    errorMessage ===
                    "Wallet does not have funds to complete the transaction!"
                )
                    toast.error("Você não possui saldo suficiente!");
                else
                    toast.error(
                        "Ocorreu um erro durante o processo de compra!"
                    );
            });
    };

    return (
        <DarkBackGround>
            <div className="relative bg-white flex justify-center items-center w-[607px] h-[306.74px] rounded-[20px] overflow-hidden z-50">
                <div
                    className="h-full w-2/4 bg-cover bg-center bg-no-repeat"
                    style={{
                        backgroundImage: `url(${selectedAlbum?.images[0].url})`,
                    }}
                ></div>

                <div className="h-full w-2/4 flex flex-col justify-around items-center">
                    <h3 className="font-bold text-xl">{selectedAlbum?.name}</h3>

                    <div className="flex flex-col w-3/4 gap-3 text-sm ">
                        <span className="text-black text-sm ">
                            <span className="font-semibold">Artistas: </span>
                            {getArtists()}
                        </span>
                        <div>
                            <span className="font-semibold">Preço: </span>
                            <span>R$ {selectedAlbum?.value}</span>
                        </div>
                        <div>
                            <span className="font-semibold">
                                Data de Lançamento:
                            </span>
                            <span className="text-sm">
                                {formatDate(selectedAlbum?.releaseDate)}
                            </span>
                        </div>
                    </div>
                    <div className="w-[258.85px] h-[41.42px]">
                        <Button
                            className="bg-[#FBBC05] text-white s740:text-[22px]"
                            onClick={buyAlbum}
                        >
                            Comprar
                        </Button>
                    </div>
                </div>

                <img
                    className="absolute right-2 top-2 cursor-pointer"
                    src={closeButton}
                    onClick={onClick}
                />
            </div>
        </DarkBackGround>
    );
};

export default ModalAlbum;
