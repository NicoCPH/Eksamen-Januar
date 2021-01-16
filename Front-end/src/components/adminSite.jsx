import React, { useState } from "react";
import facade from "./apiFacade";
import { AdminUrlUserCount } from "./../sites";

const url = AdminUrlUserCount;

const AdminSite = () => {
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

export default AdminSite;
