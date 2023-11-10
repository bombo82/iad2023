import axios from "axios";
import {XpCardDetailsType} from "../types/XpCardDetailsType";
import {XpCardSummaryType} from "../types/XpCardSummaryType";
import {EmitXpCardType} from "../types/EmitXpCardType";

export function fetchXpCardList(callback: (response: XpCardSummaryType[]) => void) {
  axios({
    method: 'get',
    url: 'http://localhost:8080/',
    responseType: 'json'
  }).then(response => callback(response.data));
}

export function emitXpCard(data: EmitXpCardType, callback: (response: XpCardDetailsType) => void) {
  axios({
    method: 'POST',
    url: 'http://localhost:8080/',
    responseType: 'json',
    data: data
  }).then(response => callback(response.data))
}
