export type XpCardDetailsType = {
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
