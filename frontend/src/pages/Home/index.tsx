import Button from "../../components/Button";
import MainTitle from "../../components/MainTitle";
import NavBar from "../../components/NavBar";

const Home = () => {
    return (
        <>
            <NavBar />
            <main className="w-screen h-screen bg-background-home bg-cover bg-center flex justify-center items-center gap-6 text-white s640:justify-normal">
                <div className="flex justify-center gap-[24px] flex-col w-11/12 s640:w-[400px] s640:ml-[50px] s740:w-[600px] s940:ml-[100px] s940:w-[701px]">
                    <MainTitle className="text-2xl s440:text-3xl s740:text-4xl s940:text-[64px] s940:leading-none" />
                    <h2 className="w-10/12 s440:text-xl s740:text-2xl">
                        Crie já sua conta e curta os sucessos que marcaram os
                        tempos no Vinil.
                    </h2>

                    <div className="w-[269px] h-16 ">
                        <Button className="bg-sysmap_ligth text-black text-xl">
                            Inscrever-se
                        </Button>
                    </div>
                </div>
            </main>
        </>
    );
};

export default Home;
