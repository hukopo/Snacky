import * as React from "react";
import cn from "./Button.less";

interface ButtonProps {
  onClick: () => void;
  text: string;
}

export class Button extends React.Component<ButtonProps> {
  render() {
    return (
      <div className={cn("position")}>
        <div onClick={this.props.onClick} className={cn("add-button")}>
          {this.props.text}
        </div>
      </div>
    );
  }
}
