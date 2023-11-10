import React, {useEffect, useState} from "react";
import SectionCard from "../molecules/generic/SectionCard";
import XpCardDetailsTable from "../molecules/specializated/XpCardDetailsTable";
import {fetchXpCardDetails, gainPoints, redeemPoints} from "../../services/XpCardDetailsService";
import Stack from "react-bootstrap/Stack";
import {XpCardTransactionForm} from "../molecules/specializated/XpCardTransactionForm";
import {XpCardDetailsType} from "../../types/XpCardDetailsType";

type XpCardDetailsProps = {
  readonly selectedXpCard?: string
}

export default function XpCardDetails(props: Readonly<XpCardDetailsProps>) {
  const [xpCardDetails, setXpCardDetails] = useState<XpCardDetailsType>();

  useEffect(() => {
    if (props.selectedXpCard) {
      fetchXpCardDetails(props.selectedXpCard, (response) => setXpCardDetails(response))
    }
  }, [props.selectedXpCard]);

  return (
    <SectionCard title="XP Card Details">
      <Stack gap={4}>
        <XpCardTransactionForm enabled={!props.selectedXpCard}
                               onGainPoints={(data) => gainPoints(props.selectedXpCard!, data, () => fetchXpCardDetails(props.selectedXpCard!, (response) => setXpCardDetails(response)))}
                               onRedeemPoints={(data) => redeemPoints(props.selectedXpCard!, data, () => fetchXpCardDetails(props.selectedXpCard!, (response) => setXpCardDetails(response)))}/>
        <Stack direction={"horizontal"} gap={3}>
          <XpCardDetailsTable xpCardTransactions={xpCardDetails?.xpCardTransactions ?? []}></XpCardDetailsTable>
          <div>
            {xpCardDetails?.xpCardTransactions
              .map(xpCardTransaction => xpCardTransaction.points)
              .reduce((sum, current) => sum + current, 0)}
          </div>
        </Stack>
      </Stack>
    </SectionCard>
  );
}
