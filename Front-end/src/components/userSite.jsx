import React, { useState } from "react";
import facade from "./apiFacade";
import { UserUrlUserCount } from "./../sites";

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

export default UserSite;
