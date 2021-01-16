import React from "react";
import facade from "./apiFacade";
import UserNavigator from "./userNavigator";
import AdminNavigator from "./adminNavigator";

const ValidateRoleSite = (props) => {
  const role = facade.getRoles();
  console.log(role);

  if (role === "admin" && props.loggedIn) {
    return (
      <>
        <AdminNavigator />
      </>
    );
  } else if (role === "user" && props.loggedIn) {
    return (
      <>
        <UserNavigator />
      </>
    );
  } else {
    return <></>;
  }
};

export default ValidateRoleSite;
