import { useState } from "react";
import { Album } from "../../models/Album";
import Button from "../Button";
import DarkBackGround from "../DarkBackGround";
import closeIcon from "./assets/close.svg";
import ButtonConfirmDelete from "../ButtonConfirmDelete";
import { api, getAuthorization } from "../../services/apiService";
import { toast } from "react-toastify";

interface DeleteAlbumModalProps {
    album: Album | undefined;
    onClick: () => void;
}

const DeleteAlbumModal = ({ album, onClick }: DeleteAlbumModalProps) => {
    const [confirmDelete, setConfirmDelete] = useState<boolean>(false);

    const handleConfirmDelete = () => {
        setConfirmDelete(!confirmDelete);
    };

    const deleteAlbum = () => {
        const toastId = toast.loading("Deletando Album...");
        api.delete(`/albums/remove/${album?.id}`, getAuthorization())
            .then(() => {
                toast.dismiss(toastId);
                toast.success("Album deletado com sucesso!");
                onClick();
            })
            .catch(() => {
                toast.dismiss(toastId);
                toast.error("Erro ao apagar album!");
                onClick();
            });
    };

    return (
        <DarkBackGround>
            <div className="relative flex flex-col justify-around items-center w-full max-w-[500px] bg-white h-[306px] s440:rounded-[20px]">
                <h2 className="text-xl font-bold">Deletar Album</h2>
                <h3 className="text-lg font-semibold">{album?.name}</h3>

                {confirmDelete ? (
                    <ButtonConfirmDelete
                        onClick={onClick}
                        deleteAlbum={deleteAlbum}
                    />
                ) : (
                    <div className="w-10/12 h-[40px]">
                        <Button
                            className="bg-red-500 text-white"
                            onClick={handleConfirmDelete}
                        >
                            Deletar
                        </Button>
                    </div>
                )}

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

export default DeleteAlbumModal;
