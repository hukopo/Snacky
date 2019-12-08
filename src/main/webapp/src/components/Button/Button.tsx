import * as React from "react";
import cn from "./Button.less";

interface ButtonProps {
  onClick: () => void;
  text: string;
}

export class Button extends React.Component<ButtonProps> {
  render() {
    return (
      <div onClick={this.props.onClick} className={cn("button")}>
        {this.props.text}
      </div>
    );
  }
}
