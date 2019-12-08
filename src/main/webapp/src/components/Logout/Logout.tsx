import * as React from "react";

export class Logout extends React.Component {
  render() {
    return (
      <div>
        {/* <form id="logoutForm" method="POST" action="${contextPath}/logout">
          <input
            type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"
          />
        </form>
        <a onClick={document.forms["logoutForm"].submit()}>Logout</a> */}
      </div>
    );
  }
}
