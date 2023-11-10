import {Button, Col, Form, FormGroup, Row} from "react-bootstrap";
import {useRef, useState} from "react";
import {GainPointsType} from "../../../types/GainPointsType";
import {RedeemPointsType} from "../../../types/RedeemPointsType";

type XpCardTransactionFormProps = {
  readonly enabled: boolean,
  readonly onGainPoints: (data: GainPointsType) => void,
  readonly onRedeemPoints: (data: RedeemPointsType) => void
}

export function XpCardTransactionForm(props: Readonly<XpCardTransactionFormProps>) {
  const formRef = useRef<HTMLFormElement | null>(null);
  const [isFormValidated, setIsFormValidated] = useState(false);
  const [points, setPoints] = useState<number>();
  const [reason, setReason] = useState<string>();

  function handleClick(action: (data: GainPointsType | RedeemPointsType) => void) {
    const form = formRef.current;
    if (form?.checkValidity()) {
      action({points: points!, reason: reason!});
      setIsFormValidated(false)
      form?.reset();
    } else {
      setIsFormValidated(true)
    }
  }

  return (
    <Form noValidate validated={isFormValidated} ref={formRef}>
      <fieldset disabled={props.enabled}>
        <Row>
          <FormGroup as={Col} md={"2"}>
            <Form.Label>XP Points</Form.Label>
            <Form.Control
              type="number"
              min={1}
              required
              onChange={event => setPoints(parseInt(event.target.value))}/>
            <Form.Control.Feedback type="invalid">
              This field is mandatory.
            </Form.Control.Feedback>
          </FormGroup>
          <FormGroup as={Col} md={"8"}>
            <Form.Label>Reason</Form.Label>
            <Form.Control
              type="text"
              required
              onChange={event => setReason(event.target.value)}/>
            <Form.Control.Feedback type="invalid">
              This field is mandatory.
            </Form.Control.Feedback>
          </FormGroup>
          <Col md={"2"} className="d-grid gap-2">
            <Button type={"button"} variant={"success"} onClick={(event) => handleClick(props.onGainPoints)}>
              Gain points
            </Button>
            <Button type={"button"} variant={"danger"} onClick={(event) => handleClick(props.onRedeemPoints)}>
              Redeem points
            </Button>
          </Col>
        </Row>
      </fieldset>
    </Form>
  );
}
