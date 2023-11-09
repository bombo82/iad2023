import React from "react";
import {Navbar} from "react-bootstrap";
import "./header.scss"

export default function Header() {
  return (
    <Navbar sticky={"top"} className={"bg-secondary"}>
      <Navbar.Brand>Xp Cards by Manuela & Gianni</Navbar.Brand>
    </Navbar>
  );
}
