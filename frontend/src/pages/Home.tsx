import React, {useState} from "react";
import Header from "../UI/organisms/header/Header";
import XpCardList from "../UI/organisms/XpCards";
import XpCardDetails from "../UI/organisms/xpCardDetails/XpCardDetails";
import Stack from "react-bootstrap/Stack";

export default function Home() {
  const [selectedXpCard, setSelectedXpCard] = useState<string>()

  return (
    <>
      <Header></Header>
      <Stack gap={3} className={"container main-container"}>
        <XpCardList onXpCardSelect={(cardId) => setSelectedXpCard(cardId)}></XpCardList>
        <XpCardDetails selectedXpCard={selectedXpCard}></XpCardDetails>
      </Stack>
    </>
  )
}
