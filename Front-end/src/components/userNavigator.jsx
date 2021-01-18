import React from "react";
import { NavDropdown} from "react-bootstrap";
import { Link } from "react-router-dom";

const UserNavigator = () => {
  return (
    <NavDropdown title="Contact" id="basic-nav-dropdown">
    <NavDropdown.Item as={Link} to="/CreateContact">
     Create Contact
    </NavDropdown.Item>
    <NavDropdown.Item as={Link} to="/Contacts">
      All Contacts
    </NavDropdown.Item>
  </NavDropdown>
  );
};

export default UserNavigator;
