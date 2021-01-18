import React, { useState, useEffect } from "react";
import facade from "./apiFacade";
import { Form, Button } from "react-bootstrap";

const CreateContact = () => {
  
    const initialValue = {
      name: "",
      email: "",
      company: "",
      jobtitle: "",
      phone: ""
    };
    const [contact, setContact] = useState(initialValue);
  
    const handleChange = (event) => {
      const target = event.target;
      const value = target.value;
      const name = target.name;
      setContact({...contact,[name]: value});
    };
  
    const handleSubmit = (event) => {
      event.preventDefault();
      window.alert(JSON.stringify(contact));
      facade.addContact(contact.name,contact.email,contact.company,contact.jobtitle,contact.phone);
    }
  return (
    <div>
      <h2>Create new contact</h2>
      <Form onChange={handleChange} onSubmit={handleSubmit}>
      <Form.Group controlId="formBasicEmail">
      <Form.Label>Name</Form.Label>
      <Form.Control name="name" placeholder="Enter Name" />
      </Form.Group>
      <Form.Group controlId="formBasicEmail">
      <Form.Label>Email</Form.Label>
      <Form.Control name="email" placeholder="Email" />
      </Form.Group>
      <Form.Group controlId="formBasicEmail">
      <Form.Label>Company</Form.Label>
      <Form.Control name="company" placeholder="Enter Company" />
      </Form.Group>
      <Form.Group controlId="formBasicPassword">
      <Form.Label>Jobtitle</Form.Label>
      <Form.Control name="jobtitle" placeholder="Jobtitle" />
      </Form.Group>
      <Form.Group controlId="formBasicPassword">
      <Form.Label>Phone</Form.Label>
      <Form.Control name="phone" placeholder="Phone" />
      </Form.Group>
      <Button variant="dark" type="submit">Add Me</Button>
      </Form>
      <p>{JSON.stringify(contact)}</p>
      </div>
  );}

  export default CreateContact;