import axios from "axios";
import {XpCardDetailsType} from "../types/XpCardDetailsType";

export function fetchXpCardDetails(cardId: string, callback: (response: XpCardDetailsType) => void) {
  axios({
    method: 'get',
    url: `http://localhost:8080/${cardId}`,
    responseType: 'json'
  }).then(response => callback(response.data));
}
