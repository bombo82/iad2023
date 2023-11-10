import React, {useEffect, useState} from "react";
import SectionCard from "../molecules/generic/SectionCard";
import XpCardTable from "../molecules/specializated/XpCardTable";
import {EmitXpCardForm} from "../molecules/specializated/EmitXpCardForm";
import Stack from "react-bootstrap/Stack";
import {emitXpCard, fetchXpCardList} from "../../services/XpCardsService";
import {XpCardSummaryType} from "../../types/XpCardSummaryType";

type XpCardsProps = {
  readonly onXpCardSelect: (cardId: string) => void
}

export default function XpCards(props: Readonly<XpCardsProps>) {
  const [xpCardList, setXpCardList] = useState<XpCardSummaryType[]>([]);

  useEffect(() => {
    fetchXpCardList((response) => setXpCardList(response))
  }, []);

  return (
    <SectionCard title="List of XP cards">
      <Stack gap={4}>
        <EmitXpCardForm
          onEmitNewXpCard={(data) => emitXpCard(data, () => fetchXpCardList((response) => setXpCardList(response)))}/>
        <XpCardTable xpCards={xpCardList} onXpCardSelect={props.onXpCardSelect}/>
      </Stack>
    </SectionCard>
  );
}
