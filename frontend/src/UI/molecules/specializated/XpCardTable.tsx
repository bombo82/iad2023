import React from "react";
import BaseTable from "../generic/BaseTable";
import {XpCardSummaryType} from "../../../types/XpCardSummaryType";

type XpCardTableProps = {
  readonly xpCards: XpCardSummaryType[]
  readonly onXpCardSelect: (cardId: string) => void
}

export default function XpCardTable(props: Readonly<XpCardTableProps>) {
  return (
    <BaseTable colHeaders={["Card ID", "Note"]}>
      {props.xpCards.map(xpCard => (
        <tr key={xpCard.cardId} onClick={() => {
          props.onXpCardSelect(xpCard.cardId);
        }}>
          <td>{xpCard.cardId}</td>
          <td>{xpCard.note}</td>
        </tr>
      ))}
    </BaseTable>
  );
}
