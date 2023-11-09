import axios from "axios";

export type XpCardSummaryData = {
  readonly cardId: string,
  readonly currentPoints: number,
  readonly note: string
}

export function fetchXpCardList(action: (response: XpCardSummaryData[]) => void) {
  axios({
    method: 'get',
    url: 'http://localhost:8080/',
    responseType: 'json'
  }).then(response => {
    action(response.data);
  });
}
