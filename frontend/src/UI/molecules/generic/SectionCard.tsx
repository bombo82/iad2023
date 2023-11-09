import {Card} from "react-bootstrap";
import React, {ReactNode} from "react";

type SectionCardProps = {
  readonly children: ReactNode,
  readonly title: string
}

export default function SectionCard(props: Readonly<SectionCardProps>) {
  return (
    <Card>
      <Card.Header>{props.title}</Card.Header>
      <Card.Body>
        {props.children}
      </Card.Body>
    </Card>
  );
}
