import Button from "../Button";

interface ButtonConfirmDeleteProps {
    onClick: () => void;
    deleteAlbum: () => void;
}

const ButtonConfirmDelete = ({
    onClick,
    deleteAlbum,
}: ButtonConfirmDeleteProps) => {
    return (
        <div className="w-10/12 h-[40px] gap-3 flex justify-around">
            <div className="w-2/4">
                <Button className="bg-red-500 text-white" onClick={deleteAlbum}>
                    Confirmar
                </Button>
            </div>
            <div className="w-2/4">
                <Button className="bg-[#FBBC05] text-white" onClick={onClick}>
                    Cancelar
                </Button>
            </div>
        </div>
    );
};

export default ButtonConfirmDelete;
