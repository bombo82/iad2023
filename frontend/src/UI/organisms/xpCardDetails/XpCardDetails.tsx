import React, {useEffect, useState} from "react";
import SectionCard from "../../molecules/generic/SectionCard";
import XpCardDetailsTable from "../../molecules/specializated/XpCardDetailsTable";
import {fetchXpCardDetails, gainPoints, redeemPoints} from "../../../services/XpCardDetailsService";
import Stack from "react-bootstrap/Stack";
import {XpCardTransactionForm} from "../../molecules/specializated/XpCardTransactionForm";
import {XpCardDetailsType} from "../../../types/XpCardDetailsType";
import GiftCard from "../../molecules/generic/giftCard/GiftCard";

import './xpCardDetails.scss';

type XpCardDetailsProps = {
  readonly selectedXpCard: string | undefined
}

export default function XpCardDetails(props: Readonly<XpCardDetailsProps>) {
  const [xpCardDetails, setXpCardDetails] = useState<XpCardDetailsType>();

  useEffect(() => {
    if (props.selectedXpCard) {
      fetchXpCardDetails(props.selectedXpCard, (response) => setXpCardDetails(response))
    }
  }, [props.selectedXpCard]);

  return (
    <SectionCard title={`XP Card Details: ${xpCardDetails?.cardId} - ${xpCardDetails?.note}`}>
      <Stack gap={4}>
        <XpCardTransactionForm enabled={!props.selectedXpCard}
                               onGainPoints={(data) => gainPoints(props.selectedXpCard!, data, () => fetchXpCardDetails(props.selectedXpCard!, (response) => setXpCardDetails(response)))}
                               onRedeemPoints={(data) => redeemPoints(props.selectedXpCard!, data, () => fetchXpCardDetails(props.selectedXpCard!, (response) => setXpCardDetails(response)))}/>
        <div className="details-container">
          <GiftCard points={xpCardDetails?.xpCardTransactions
            .map(xpCardTransaction => xpCardTransaction.points)
            .reduce((sum, current) => sum + current, 0)}/>
          <XpCardDetailsTable className={"cardTransaction"}
                              xpCardTransactions={xpCardDetails?.xpCardTransactions ?? []}></XpCardDetailsTable>
        </div>
      </Stack>
    </SectionCard>
  );
}
