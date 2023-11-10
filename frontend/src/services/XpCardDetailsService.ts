import axios from "axios";
import {XpCardDetailsType} from "../types/XpCardDetailsType";
import {GainPointsType} from "../types/GainPointsType";
import {RedeemPointsType} from "../types/RedeemPointsType";

export function fetchXpCardDetails(cardId: string, callback: (response: XpCardDetailsType) => void) {
  axios({
    method: 'get',
    url: `http://localhost:8080/${cardId}`,
    responseType: 'json'
  }).then(response => callback(response.data));
}

export function gainPoints(cardId: string, data: GainPointsType, callback: (response: XpCardDetailsType) => void) {
  axios({
    method: 'POST',
    url: `http://localhost:8080/${cardId}/addPoints`,
    responseType: 'json',
    data: data
  }).then(response => callback(response.data))
}

export function redeemPoints(cardId: string, data: RedeemPointsType, callback: (response: XpCardDetailsType) => void) {
  axios({
    method: 'POST',
    url: `http://localhost:8080/${cardId}/redeemPoints`,
    responseType: 'json',
    data: data
  }).then(response => callback(response.data))
}

