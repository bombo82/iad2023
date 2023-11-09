import Stack from "react-bootstrap/Stack";
import {Button, Form, FormGroup} from "react-bootstrap";
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
      <Stack gap={4}>
        <FormGroup>
          <Form.Label>Initial XP Points</Form.Label>
          <Form.Control type="number" min={1} required/>
          <Form.Control.Feedback type="invalid">
            This field is mandatory an must be greater than zero.
          </Form.Control.Feedback></FormGroup>
        <FormGroup>
          <Form.Label>Note</Form.Label>
          <Form.Control type="text" required/>
          <Form.Control.Feedback type="invalid">
            This field is mandatory.
          </Form.Control.Feedback>
        </FormGroup>
        <Button type={"submit"}>New XP Card</Button>
      </Stack>
    </Form>
  );
}
