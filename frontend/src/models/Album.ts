export type InfoAlbum = {
    title: string;
    img: string;
    details: string;
};

export interface Album {
    albumType?: string;
    artists?: [];
    externalUrls?: { externalUrls: {} };
    id?: string;
    images: Image[];
    name?: string;
    releaseDate?: string;
    type?: string;
    value?: number;
}

//utils

export interface Image {
    height: number;
    url: string;
}
