import React, { useEffect, useState } from "react";
import { GetAllContactsUrl } from "../sites";
import facade from "./apiFacade";

const url = GetAllContactsUrl;

const AllContact = () => {
  const [contacts, setContacts] = useState([]);

 
  useEffect(() => {
    fetch(url, facade.makeOptions("GET", true))
            .then((res) => res.json())
            .then((data) => {
            setContacts(data.all)
             } )}, []);

             const EmailAndNames = contacts.map((data) => 
                <li key={data.email}>
                  {data.name} - {data.email}
              </li>
           );
  return (
    <div>
      <h3>Navn og Email</h3>
     <ul>{EmailAndNames}</ul>
    </div>
  );
};

export default AllContact;
