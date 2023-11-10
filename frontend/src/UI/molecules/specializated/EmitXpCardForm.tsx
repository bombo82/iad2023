import {Button, Col, Form, FormGroup, Row} from "react-bootstrap";
import {useRef, useState} from "react";
import {EmitXpCardType} from "../../../types/EmitXpCardType";

type EmitXpCardFormProps = {
  readonly onEmitNewXpCard: (data: EmitXpCardType) => void
}

export function EmitXpCardForm(props: Readonly<EmitXpCardFormProps>) {
  const formRef = useRef<HTMLFormElement | null>(null);
  const [isFormValidated, setIsFormValidated] = useState(false);
  const [initialPoints, setInitialPoints] = useState<number>();
  const [note, setNote] = useState<string>();

  function handleClick(action: (data: EmitXpCardType) => void) {
    const form = formRef.current;
    if (form?.checkValidity()) {
      action({initialPoints: initialPoints!, note: note!});
      setIsFormValidated(false)
      form?.reset();
    } else {
      setIsFormValidated(true)
    }
  }

  return (
    <Form noValidate validated={isFormValidated} ref={formRef}>
      <Row>
        <FormGroup as={Col} md={"2"}>
          <Form.Label>Initial XP Points</Form.Label>
          <Form.Control
            type="number"
            min={1}
            required
            onChange={event => setInitialPoints(parseInt(event.target.value))}/>
          <Form.Control.Feedback type="invalid">
            This field is mandatory.
          </Form.Control.Feedback>
        </FormGroup>
        <FormGroup as={Col} md={"8"}>
          <Form.Label>Note</Form.Label>
          <Form.Control
            type="text"
            required
            onChange={event => setNote(event.target.value)}/>
          <Form.Control.Feedback type="invalid">
            This field is mandatory.
          </Form.Control.Feedback>
        </FormGroup>
        <Col md={"2"} className="d-grid gap-2">
          <Button type={"button"} onClick={(event) => handleClick(props.onEmitNewXpCard)}>
            New XP Card
          </Button>
        </Col>
      </Row>
    </Form>
  );
}
