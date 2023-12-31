import React from "react";
import BaseTable from "../generic/BaseTable";
import {XpCardTransactionData} from "../../../types/XpCardDetailsType";

type XpCardDetailsProps = {
  readonly className?: string
  readonly xpCardTransactions: XpCardTransactionData[]
}

export default function XpCardDetailsTable(props: Readonly<XpCardDetailsProps>) {

  return (
    <BaseTable className={props.className} colHeaders={["XP Points", "Reason", "Date"]}>
      {props.xpCardTransactions.map(xpCardTransaction => (
        <tr key={xpCardTransaction.date} onClick={() => {
        }}>
          <td>{xpCardTransaction.points}</td>
          <td>{xpCardTransaction.reason}</td>
          <td>{xpCardTransaction.date}</td>
        </tr>
      ))}
    </BaseTable>
  );
}
