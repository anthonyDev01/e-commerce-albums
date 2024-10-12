import logo from "../../../public/assets/logo.png";

interface LogoProps {
    text?: string;
}

const Logo = ({ text }: LogoProps) => {
    return (
        <div className="flex items-center gap-[7px] cursor-pointer">
            <img src={logo} alt="" />
            <span className="text-white font-bold text-[17px] font-comics_neue">
                {text}
            </span>
        </div>
    );
};

export default Logo;
