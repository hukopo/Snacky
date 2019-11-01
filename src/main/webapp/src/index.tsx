import * as React from "react";
import { render } from "react-dom";
// tslint:disable-next-line: no-import-side-effect
import "reset-css";

import { App } from "./components/App/App";
import "./index.less";


const Root = () => (
        <App />
);

render(<Root />, document.getElementById("root"));
