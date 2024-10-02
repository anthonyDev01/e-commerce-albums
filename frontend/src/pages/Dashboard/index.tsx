import { useState } from "react";
import ListAlbums from "../../components/ListAlbums";
import MainTitle from "../../components/MainTitle";
import NavBar from "../../components/NavBar";
import searchIcon from "./assets/search.svg";
import { Album } from "../../models/Album";
import ModalAlbum from "../../components/ModalAlbum";
import { api, getParamsSearch } from "../../services/apiService";

const Dashboard = () => {
    const [openModal, setOpenModal] = useState<boolean>(false);
    const [search, setSearch] = useState<string>("");
    const [dataAlbum, setDataAlbum] = useState<Album[]>([]);
    const [selectedAlbum, setSelectedAlbum] = useState<Album | undefined>();

    const handleModal = (album: Album) => {
        setOpenModal(!openModal);
        setSelectedAlbum(album);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSearch(e.target.value);
    };

    const closeModal = () => {
        setOpenModal(false);
    };

    console.log(selectedAlbum);

    const searchAlbum = () => {
        if (search.length > 0) {
            api.get<Album[]>("albums/all", getParamsSearch(search))
                .then((response) => {
                    setDataAlbum(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    };

    return (
        <>
            <NavBar />
            <main className="w-full bg-[#19181F] flex-grow min-h-screen flex justify-between flex-col">
                <div className="bg-background-dashboard bg-cover bg-no-repeat bg-center flex flex-col w-full min-h-[550px] justify-center">
                    <MainTitle className="text-lg text-white w-3/4 s440:text-2xl s640:ml-7 s640:text-[39px] s640:leading-none s640:w-[504px] " />
                    <h2 className="text-white text-sm mt-2 ml-7 s440:text-base s640:text-2xl">
                        Sucessos que marcaram o tempo!!!
                    </h2>
                </div>

                <div className="w-full flex justify-center items-center h-[56px] mb-4">
                    <div className="flex items-center justify-around h-full w-11/12 max-w-[448px] rounded-xl overflow-hidden ring-1 ring-[#CBCAD7]">
                        <input
                            className="w-10/12 h-full bg-transparent outline-none text-white"
                            type="text"
                            onChange={handleInputChange}
                        />
                        <img
                            src={searchIcon}
                            onClick={searchAlbum}
                            alt=""
                            className="mr-2 cursor-pointer"
                        />
                    </div>
                </div>

                <ListAlbums onClick={handleModal} dataAlbum={dataAlbum} />

                {openModal && (
                    <ModalAlbum
                        selectedAlbum={selectedAlbum}
                        onClick={closeModal}
                    />
                )}
            </main>
        </>
    );
};

export default Dashboard;
