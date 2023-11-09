import {Table} from "react-bootstrap";
import React, {ReactNode} from "react";

type BaseTableProps = {
  readonly children: ReactNode,
  readonly colHeaders: string[]
}

export default function BaseTable(props: Readonly<BaseTableProps>) {
  return <Table striped bordered hover>
    <thead>
    <tr>
      {props.colHeaders.map(colHeader => <th key={colHeader}>{colHeader}</th>)}
    </tr>
    </thead>
    <tbody>
    {props.children}
    </tbody>
  </Table>
}
