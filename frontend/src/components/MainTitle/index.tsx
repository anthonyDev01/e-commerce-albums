interface MainTitleProps {
    className: string;
}

const MainTitle = ({ className }: MainTitleProps) => {
    return (
        <h1 className={`w-full ${className} font-bold`}>
            A história da música não pode ser esquecida!
        </h1>
    );
};

export default MainTitle;
