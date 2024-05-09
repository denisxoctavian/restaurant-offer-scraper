import { Restaurant } from "./restaurant";

export interface Menu {
    id: number,
    name: string,
    price: number,
    restaurantId: Restaurant,
    picture: ArrayBuffer,
    date: string
}