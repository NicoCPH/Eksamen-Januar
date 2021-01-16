import React, { useEffect, useState } from "react";
import { DailyFunUrl } from "./../sites";

const url = DailyFunUrl;

const DailyFun = () => {
  const [dailyFun, setDailyFun] = useState({});
  useEffect(() => {
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        setDailyFun(data);
      });
  }, []);
  return (
    <div>
      <h3>Bored</h3>
      <p>{dailyFun.boredActivity}</p>
      <p>{dailyFun.boredType}</p>
      <p>{dailyFun.boredParticipants}</p>
      <h3>Cat Fact</h3>
      <p>{dailyFun.catMessage}</p>
      <h3>My IP</h3>
      <p>{dailyFun.myIP}</p>
      <h3>People in Space</h3>
      <p>{dailyFun.spaceNumber}</p>
      <h3>What Does Trump Think</h3>
      <p>{dailyFun.trumpMessage}</p>
    </div>
  );
};

export default DailyFun;
