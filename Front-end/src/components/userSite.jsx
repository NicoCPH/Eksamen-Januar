import React, { useState } from "react";
import facade from "./apiFacade";
import { UserUrlUserCount } from "./../sites";
import { Form, Button } from "react-bootstrap";

const url = UserUrlUserCount;

const UserSite = () => {
  const [count, setCount] = useState("");
  return (
    <>
      <p>Number of users on this site: {count}</p>
      <button
        onClick={() =>
          fetch(url, facade.makeOptions("GET", true))
            .then((res) => res.json())
            .then((data) => setCount(data))
        }
      >
        Hente antal bruger
      </button>
    </>
  );
};

const AddUser = () => {
  
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
    <h2>Add New User</h2>
    <Form onChange={handleChange} onSubmit={handleSubmit}>
    <Form.Group controlId="formBasicEmail">
    <Form.Label>Name</Form.Label>
    <Form.Control name="fname" placeholder="Enter Name" />
    </Form.Group>
    <Form.Group controlId="formBasicEmail">
    <Form.Label>Email</Form.Label>
    <Form.Control name="Email" placeholder="Email" />
    </Form.Group>
    <Form.Group controlId="formBasicEmail">
    <Form.Label>Company</Form.Label>
    <Form.Control name="Company" placeholder="Enter Company" />
    </Form.Group>
    <Form.Group controlId="formBasicPassword">
    <Form.Label>Jobtitle</Form.Label>
    <Form.Control name="Jobtitle" placeholder="Jobtitle" />
    </Form.Group>
    <Form.Group controlId="formBasicPassword">
    <Form.Label>Phone</Form.Label>
    <Form.Control name="Phone" placeholder="Phone" />
    </Form.Group>
    <Button variant="dark" type="submit">Add Me</Button>
    </Form>
    <p>{JSON.stringify(contact)}</p>
    </div>
);
};
export default UserSite;
