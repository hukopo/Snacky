import * as React from "react";
import cn from "./ModalWindow.less";

interface ModalWindowProps {
  close: () => void;
}

export class ModalWindow extends React.Component<ModalWindowProps> {
  render() {
    return (
      <div className={cn("modal")}>
        <span onClick={this.props.close} className={cn("close")}>
          &times;
        </span>
        {this.props.children}
      </div>
    );
  }
}
