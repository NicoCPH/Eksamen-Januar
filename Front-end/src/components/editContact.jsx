import React, { useEffect, useState } from "react";
import { GetContactUrl } from "../sites";
import facade from "./apiFacade";
import { Form, Button } from "react-bootstrap";

const url = GetContactUrl;
const EditContact = () => {
  const initialValue = {
    name: "",
    email: "",
    company: "",
    jobtitle: "",
    phone: ""
  };

    const [contact, setContact] = useState({});
    const [find, setFind] = useState("");
    const [submit, setSubmit] = useState("");
    const [editcontact, setEditcontact] = useState(initialValue)
    
    
    
    useEffect(() => {
        getContact()
    }, [submit]);

const getContact = async() => {
        await fetch(url+submit, facade.makeOptions("GET", true))
            .then((res) => res.json())
            .then((data) => {
                setContact(data);
    })};
    

    const handleChange = (event) => {
      const target = event.target;
      const value = target.value;
      setFind(value)
    };

    const handleEditChanges = (event) => {
      const target = event.target;
      const value = target.value;
      const name = target.name;
      setEditcontact({...editcontact,[name]: value});
    };

    const handleEditSubmit = (event) => {
      event.preventDefault();
      window.alert(JSON.stringify(editcontact));
      facade.editContact(submit, editcontact.name,editcontact.email,editcontact.company,editcontact.jobtitle,editcontact.phone);
    }

    const handleSubmit = (event) => {
      event.preventDefault();
      setSubmit(find)
    }

  return (
    <div>
      <h2>Find Contact by Email</h2>
      <Form onSubmit={handleSubmit}>
      <Form.Group controlId="formBasicEmail">
      <Form.Label>Email</Form.Label>
      <Form.Control name="email" placeholder="Enter Email" onChange={handleChange}/>
      </Form.Group>
      <Button variant="dark" type="submit" >Find Me</Button>
      </Form>
      <Contacts
      contact={contact}/>
      <div>
      <h2>Edit contact</h2>
      <Form onChange={handleEditChanges} onSubmit={handleEditSubmit}>
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
      <p>{JSON.stringify(editcontact)}</p>
      </div>
      </div>
  );
};

const Contacts = ({contact}) => {
    return(

        <div>
            <h1>contact</h1>
        <ul><li>{contact.name}</li> <li>{contact.email}</li>  <li>{contact.company} </li> <li>{contact.jobtitle}</li> <li>{contact.phone}</li> </ul>
        </div>
    )
}

export default EditContact;
