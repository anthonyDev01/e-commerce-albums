export type InfoAlbum = {
    title: string;
    img: string;
    details: string;
};

export interface Album {
    albumType?: string;
    artists?: Artist[];
    externalUrls?: { externalUrls: { additionalProp1: string } };
    id?: string;
    images: Image[];
    image_url: string;
    name?: string;
    releaseDate?: string;
    deleted_at?: string;
    type?: string;
    value?: number;
    user_id?: string;
}

//utils

export interface Image {
    height: number;
    url: string;
}

export interface Artist {
    externalUrls?: { externalUrls: { additionalProp1: string } };
    href?: string;
    id?: string;
    name?: string;
    type?: string;
    uri?: string;
}
