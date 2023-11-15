import React from "react";

import "./giftCard.scss"

type GiftCardProps = {
  readonly points: number | undefined
}

export default function GiftCard(props: Readonly<GiftCardProps>) {
  return (
    <div className="giftcard">
      <div className="giftcard-cover">
        <h1>
          eXtreme Programming Gift Card
        </h1>
      </div>
      <div className="giftcard-footer">
        <h2>
          {props.points} XP Points
        </h2>
      </div>
    </div>
  );
}
