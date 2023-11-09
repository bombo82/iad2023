import {Button, Col, Form, FormGroup, Row} from "react-bootstrap";
import {useState} from "react";

type EmitXpCardFormProps = {
  readonly onEmitNewXpCard: () => void
}

export function EmitXpCardForm(props: Readonly<EmitXpCardFormProps>) {
  const [validated, setValidated] = useState(false);

  const onButtonClick = (event: any) => {
    const form = event.currentTarget;
    console.log(form)
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
      setValidated(true);
    } else {
      props.onEmitNewXpCard();
      setValidated(false);
    }
  };

  return (
    <Form noValidate validated={validated} onSubmit={onButtonClick}>
      <Row>
        <FormGroup as={Col} md={"2"}>
          <Form.Label>Initial XP Points</Form.Label>
          <Form.Control type="number" min={1} required/>
          <Form.Control.Feedback type="invalid">
            This field is mandatory an must be greater than zero.
          </Form.Control.Feedback></FormGroup>
        <FormGroup as={Col} md={"8"}>
          <Form.Label>Note</Form.Label>
          <Form.Control type="text" required/>
          <Form.Control.Feedback type="invalid">
            This field is mandatory.
          </Form.Control.Feedback>
        </FormGroup>
        <Col md={"2"} className="d-grid gap-2">
          <Button type={"submit"}>New XP Card</Button>
        </Col>
      </Row>
    </Form>
  );
}
