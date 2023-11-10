import axios from "axios";
import {XpCardSummaryType} from "../types/XpCardSummaryType";

export function fetchXpCardList(callback: (response: XpCardSummaryType[]) => void) {
  axios({
    method: 'get',
    url: 'http://localhost:8080/',
    responseType: 'json'
  }).then(response => callback(response.data));
}
