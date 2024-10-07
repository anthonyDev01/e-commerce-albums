export default {
    transform: {
        "^.+\\.(js|jsx|ts|tsx)$": "babel-jest",
    },
    testEnvironment: "jest-environment-jsdom",
    setupFilesAfterEnv: ["<rootDir>/setupTests.ts"],
    moduleNameMapper: {
        "\\.(css|less|scss|sass)$": "identity-obj-proxy",
        "\\.(jpg|ico|jpeg|png|gif|eot|otf|webp|svg|ttf|woff|woff2|mp4|webm|wav|mp3|m4a|aac|oga)$":
            "<rootDir>/mocks/fileMock.js",
        "swiper/css": "swiper/swiper.min.css",
    },
    transformIgnorePatterns: ["node_modules/(?!swiper|ssr-window|dom7).*/"],
};
