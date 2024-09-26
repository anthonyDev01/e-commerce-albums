interface MainTitleProps{
    fontSize: string
}

const MainTitle = ({fontSize}: MainTitleProps) => {
    return <h1 className={`w-full ${fontSize} font-bold`}>A história da música não pode ser esquecida!</h1>;
};

export default MainTitle;
