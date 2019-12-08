import * as React from "react";
import cn from "./logout.less";
export class Logout extends React.Component {
  render() {
    return (
      <a className={cn("logout")} href={"/login"} onClick={() => fetch("/logout", {method: "POST"})}>
        logout
      </a>
    );
  }
}
