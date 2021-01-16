import React from "react";
import { Nav, NavItem } from "react-bootstrap";
import { Link } from "react-router-dom";

const UserNavigator = () => {
  return (
    <NavItem href="/UserSite">
      <Nav.Link as={Link} to="/UserSite">
        User
      </Nav.Link>
    </NavItem>
  );
};

export default UserNavigator;
