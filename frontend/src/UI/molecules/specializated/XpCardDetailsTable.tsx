import React from "react";
import BaseTable from "../generic/BaseTable";
import {XpCardTransactionData} from "../../../services/XpCardDetailsService";

type XpCardDetailsProps = {
  readonly xpCardTransactions: XpCardTransactionData[]
}

export default function XpCardDetailsTable(props: Readonly<XpCardDetailsProps>) {

  return (
    <BaseTable colHeaders={["Points", "Reason", "Date"]}>
      {props.xpCardTransactions.map((xpCardTransaction, index) => (
        <tr key={index} onClick={() => {
        }}>
          <td>{xpCardTransaction.points}</td>
          <td>{xpCardTransaction.reason}</td>
          <td>{xpCardTransaction.date}</td>
        </tr>
      ))}
    </BaseTable>
  );
}
