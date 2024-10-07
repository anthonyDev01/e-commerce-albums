import { useEffect, useState } from "react";
import InfoAlbumCard from "../../components/InfoAlbumCard";
import NavBar from "../../components/NavBar";
import { api, getAuthorization } from "../../services/apiService";
import useAuth from "../../hooks/useAuth";
import { Album } from "../../models/Album";
import ListAlbums from "../../components/ListAlbums";
import DeleteAlbumModal from "../../components/DeleteAlbumModal";

const MyDiscs = () => {
    const { userId } = useAuth();
    const [albums, setAlbums] = useState<Album[]>([]);
    const [album, setAlbum] = useState<Album>();
    const [albumAmount, setAlbumAmount] = useState<number>(0);
    const [totalInvested, setTotalInvested] = useState<string>("0.00");
    const [openModal, setOpenModal] = useState<boolean>(false);
    const [updateList, setUpdateList] = useState<boolean>(false);

    useEffect(() => {
        const fechDiscs = () => {
            api.get<Album[]>(
                `/albums/my-collection/${userId}`,
                getAuthorization()
            )
                .then((response) => {
                    setAlbums(response.data);
                    calculateInvestedValue(response.data);
                    calculeteAmountAlbum(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        };

        const calculateInvestedValue = (data: Album[]) => {
            let value = 0;

            data?.forEach((album) => {
                if (album && !("deleted_at" in album)) {
                    value += album.value ?? 0;
                }
            });

            setTotalInvested(value.toFixed(2));
        };

        const calculeteAmountAlbum = (data: Album[]) => {
            let value = 0;

            data?.forEach((album) => {
                if (album && !("deleted_at" in album)) {
                    value++;
                }
            });

            setAlbumAmount(value);
        };

        fechDiscs();
    }, [updateList]);

    const handleModal = (album: Album) => {
        setOpenModal(!openModal);
        setAlbum(album);
    };

    return (
        <>
            <NavBar />
            <main className="w-full min-h-screen flex flex-col items-center justify-center gap-12 bg-[#19181F]">
                <div className="flex flex-col justify-center  min-h-[550px] gap-4 w-10/12  ">
                    <h2 className="text-white text-3xl">Meus Discos</h2>
                    <div className="flex flex-col gap-3 s640:flex-row">
                        <InfoAlbumCard TypeCard="total" label="Total de Albums">
                            {albumAmount}
                        </InfoAlbumCard>
                        <InfoAlbumCard TypeCard="value" label="Valor Investido">
                            R$ {totalInvested}
                        </InfoAlbumCard>
                    </div>
                </div>

                <div className="w-10/12 flex justify-end">
                    <ListAlbums
                        className="justify-start"
                        dataAlbum={albums}
                        myAlbums={albums}
                        onClick={handleModal}
                    />
                </div>

                {openModal && (
                    <DeleteAlbumModal
                        album={album}
                        onClick={() => {
                            setOpenModal(false);
                            setUpdateList((prev) => !prev);
                        }}
                    />
                )}
            </main>
        </>
    );
};

export default MyDiscs;
