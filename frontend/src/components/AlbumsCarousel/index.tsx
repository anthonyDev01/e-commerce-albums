import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/swiper-bundle.css";
import { Album } from "../../models/Album";
import CardAlbum from "../CardAlbum";
import { Autoplay } from "swiper/modules";
import { useEffect, useState } from "react";

interface AlbumsCarouselProps {
    dataAlbum: Album[];
    onClick?: (album: Album) => void;
}

const AlbumsCarousel = ({ dataAlbum, onClick }: AlbumsCarouselProps) => {
    const [slidePerView, setSlidePerView] = useState(1);

    useEffect(() => {
        const handlResize = () => {
            const width = window.innerWidth;

            if (width >= 1100) {
                setSlidePerView(4);
            } else if (width >= 940) {
                setSlidePerView(3);
            } else if (width >= 860) {
                setSlidePerView(2);
            } else if (width >= 540) {
                setSlidePerView(1);
            }
        };
        handlResize();

        window.addEventListener("resize", handlResize);

        return () => {
            window.removeEventListener("resize", handlResize);
        };
    }, []);

    return (
        <div className="flex flex-col  h-[485px]  -z-0">
            <h2 className="p-[30px] text-white text-[48px] font-bold">
                Trends
            </h2>
            <Swiper
                className="w-full flex justify-start items-center"
                modules={[Autoplay]}
                spaceBetween={50}
                slidesPerView={slidePerView}
                loop={true}
                speed={500}
                autoplay={{
                    delay: 1500,
                }}
            >
                {dataAlbum.map((data) => (
                    <SwiperSlide
                        key={data.id}
                        className=" flex justify-center items-center"
                    >
                        <CardAlbum
                            dataAlbum={data}
                            onClick={() => onClick?.(data)}
                        />
                    </SwiperSlide>
                ))}
            </Swiper>
        </div>
    );
};
export default AlbumsCarousel;
