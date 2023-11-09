import React, {useEffect, useState} from "react";
import SectionCard from "../molecules/generic/SectionCard";
import XpCardTable from "../molecules/specializated/XpCardTable";
import {EmitXpCardForm} from "../molecules/specializated/EmitXpCardForm";
import Stack from "react-bootstrap/Stack";
import {fetchXpCardList, XpCardSummaryData} from "../../services/XpCardsService";

type XpCardsProps = {
  readonly onXpCardSelect: (cardId: string) => void
}

export default function XpCards(props: Readonly<XpCardsProps>) {
  const [xpCardList, setXpCardList] = useState<XpCardSummaryData[]>([]);

  useEffect(() => {
    fetchXpCardList((response) => setXpCardList(response))
  }, []);

  return (
    <SectionCard title="List of XP cards">
      <Stack gap={4}>
        <EmitXpCardForm onEmitNewXpCard={() => alert("Emit New XP Card")}></EmitXpCardForm>
        <XpCardTable xpCards={xpCardList} onXpCardSelect={props.onXpCardSelect}></XpCardTable>
      </Stack>
    </SectionCard>
  );
}
