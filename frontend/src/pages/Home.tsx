import React, {useState} from "react";
import Header from "../UI/organisms/Header";
import XpCardList from "../UI/organisms/XpCards";
import XpCardDetails from "../UI/organisms/XpCardDetails";
import Stack from "react-bootstrap/Stack";

export default function Home() {
  const [selectedXpCard, setSelectedXpCard] = useState<string>()

  return (
    <Stack gap={3}>
      <Header></Header>
      <div className={"container"}>
        <XpCardList onXpCardSelect={(cardId) => setSelectedXpCard(cardId)}></XpCardList>
        <XpCardDetails selectedXpCard={selectedXpCard}></XpCardDetails>
      </div>
    </Stack>
  );
}
