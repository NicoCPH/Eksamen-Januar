import React from "react";
import { Nav, NavItem } from "react-bootstrap";
import { Link } from "react-router-dom";

const AdminNavigator = () => {
  return (
    <NavItem href="/AdminSite">
      <Nav.Link as={Link} to="/AdminSite">
        Admin
      </Nav.Link>
    </NavItem>
  );
};

export default AdminNavigator;
