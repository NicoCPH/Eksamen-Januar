import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { Navbar, Nav, NavItem, NavDropdown } from "react-bootstrap";
import Login from "./login";
import DailyFun from "./dailyFun";
import Home from "./home";
import Readme from "./readMe";
import facade from "./apiFacade";
import ValidateRoleSite from "./validateRoleSite";
import UserSite from "./userSite";
import AdminSite from "./adminSite";

const NavBarIO = () => {
  const [loggedIn, setLoggedIn] = useState(false);

  return (
    <>
      <Router>
        <Header loggedIn={loggedIn} />
        <div>
          <Content setLoggedIn={setLoggedIn} loggedIn={loggedIn} />
        </div>
      </Router>
    </>
  );
};

const Header = (props) => {
  return (
    <>
      <div>
        <Navbar bg="dark" variant="dark">
          <Navbar.Brand as={Link} to="/">
            CA-3 Gruppe 5
          </Navbar.Brand>
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">
              <NavItem href="/">
                <Nav.Link as={Link} to="/">
                  Home
                </Nav.Link>
              </NavItem>
              <NavItem href="/Readme">
                <Nav.Link as={Link} to="/Readme">
                  Readme
                </Nav.Link>
              </NavItem>
              <NavItem href="/DailyFun">
                <Nav.Link as={Link} to="/DailyFun">
                  DailyFun
                </Nav.Link>
              </NavItem>
              <ValidateRoleSite loggedIn={props.loggedIn} />
              <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                <NavDropdown.Item as={Link} to="/">
                  Home
                </NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/Readme">
                  Readme
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>
            <Nav>
              <NavItem href="/Login">
                <Nav.Link as={Link} to="/Login">
                  <IsLoggedIn loggedIn={props.loggedIn} />
                </Nav.Link>
              </NavItem>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
      </div>
    </>
  );
};

const Content = (props) => {
  return (
    <Switch>
      <Route exact path="/" component={Home} />
      <Route path="/Readme" component={Readme} />
      <Route path="/DailyFun" component={DailyFun} />
      <Route path="/AdminSite" component={AdminSite} />
      <Route path="/UserSite" component={UserSite} />
      <Route path="/Login">
        <Login setLoggedIn={props.setLoggedIn} loggedIn={props.loggedIn} />
      </Route>
      <Route path="*" component={NoMatch} />
    </Switch>
  );
};

const NoMatch = () => {
  return <p>There was no match</p>;
};

const IsLoggedIn = (props) => {
  if (props.loggedIn) {
    const userName = facade.getUserName();
    console.log("IsLoggedIn(), ", userName);
    return <>{userName}</>;
  } else {
    return <>Login</>;
  }
};

export default NavBarIO;
