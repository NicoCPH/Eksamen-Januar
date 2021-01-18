import React, { useEffect, useState } from "react";
import { DeleteContactUrl } from "../sites";
import facade from "./apiFacade";
import { Form, Button } from "react-bootstrap";

const url = DeleteContactUrl;
const DeleteContact = () => {
    const [contact, setContact] = useState({});
    const [find, setFind] = useState("");
    const [submit, setSubmit] = useState("");
    
    
    
    
    useEffect(() => {
        getContact()
    }, [submit]);

const getContact = async() => {
        await fetch(url+submit, facade.makeOptions("DELETE", true))
            .then((res) => res.json())
            .then((data) => {
                setContact(data);
                console.log(contact)
    })};
    

    const handleChange = (event) => {
      const target = event.target;
      const value = target.value;
      setFind(value)
    };

    const handleSubmit = (event) => {
      event.preventDefault();
      setSubmit(find)
    }

  return (
    <div>
      <h2>Delete Contact by Email</h2>
      <Form onSubmit={handleSubmit}>
      <Form.Group controlId="formBasicEmail">
      <Form.Label>Email</Form.Label>
      <Form.Control name="email" placeholder="Enter Email" onChange={handleChange}/>
      </Form.Group>
      <Button variant="dark" type="submit" >Find Me</Button>
      </Form>
      <Contacts
      contact={contact}/>
     
      </div>
  );
};

const Contacts = ({contact}) => {
    return(

        <div>
            <h1>Denne Contact er slettet</h1>
        <ul><li>{contact.name}</li> <li>{contact.email}</li>  <li>{contact.company} </li> <li>{contact.jobtitle}</li> <li>{contact.phone}</li> </ul>
        </div>
    )
}


export default DeleteContact;
