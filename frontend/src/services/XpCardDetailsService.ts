import axios from "axios";

export type XpCardDetailsData = {
  readonly cardId: string,
  readonly currentPoints: number
  readonly note: string
  readonly xpCardTransactions: XpCardTransactionData[]
}

export type XpCardTransactionData = {
  readonly points: number,
  readonly reason: string,
  readonly date: string
}

export function fetchXpCardDetails(cardId: string, action: (response: XpCardDetailsData) => void) {
  axios({
    method: 'get',
    url: `http://localhost:8080/${cardId}`,
    responseType: 'json'
  }).then(response => {
    action(response.data);
  });
}
