import axios from "axios";

export const api = axios.create({
    baseURL: "http://167.99.7.170:8081/api/",
});

export function getHeaders() {
    return {
        headers: { "Content-Type": "application/json" },
    };
}

export function getParamsSearch(value: string) {
    const token = localStorage.getItem("token");
    return {
        params: {
            search: value,
        },
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };
}

export function getAuthorization() {
    const token = localStorage.getItem("token");

    return {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    };
}
