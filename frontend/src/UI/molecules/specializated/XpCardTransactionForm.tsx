import {Button, Col, Form, FormGroup, Row} from "react-bootstrap";
import {useState} from "react";

type XpCardTransactionFormProps = {
  readonly enabled: boolean,
  readonly onGainPoints: () => void,
  readonly onRedeemPoints: () => void
}

export function XpCardTransactionForm(props: Readonly<XpCardTransactionFormProps>) {
  const [validated, setValidated] = useState(false);

  const onButtonClick = (event: any, action: string) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
      setValidated(true);
    } else {
      alert(action)
      setValidated(false);
    }
  };

  return (
    <Form noValidate
          validated={validated}
          onSubmit={(event) => onButtonClick(event, "gain")}
          onReset={(event) => onButtonClick(event, "redeem")}
    >
      <fieldset disabled={props.enabled}>
        <Row>
          <FormGroup as={Col} md={"2"}>
            <Form.Label>XP Points</Form.Label>
            <Form.Control type="number" min={1} required/>
            <Form.Control.Feedback type="invalid">
              This field is mandatory.
            </Form.Control.Feedback></FormGroup>
          <FormGroup as={Col} md={"8"}>
            <Form.Label>Reason</Form.Label>
            <Form.Control type="text" required/>
            <Form.Control.Feedback type="invalid">
              This field is mandatory.
            </Form.Control.Feedback>
          </FormGroup>
          <Col md={"2"} className="d-grid gap-2">
            <Button type={"submit"} variant={"success"}>Gain points</Button>
            <Button type={"reset"} variant={"danger"}>Redeem points</Button>
          </Col>
        </Row>
      </fieldset>
    </Form>
  );
}
