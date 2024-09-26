/** @type {import('tailwindcss').Config} */
export default {
    content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        extend: {
            colors: {
                sysmap_black: "#010B0F",
                sysmap_ligth: "#9EE2FF",
            },
            fontFamily: {
                comics_neue: ["Comic-Neue", "cursive"],
            },
            screens: {
                s340: "340px",
                s440: "440px",
                s540: "540px",
                s640: "640px",
                s740: "740px",
                s840: "840px",
                s940: "940px",
                s1040: "1040px",
                s1140: "1140px",
                s1240: "1240px",
                s1340: "1340px",
                s1440: "1440px",
                s1540: "1540px",
                s1640: "1640px",
                s1740: "1740px",
                s1840: "1840px",
                s1940: "1940px",
            },
        },
    },
    plugins: [],
};
