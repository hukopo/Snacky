import * as React from "react";
import cn from "./AddCardButton.less";

interface AddCardButtonProps {
  onClick: () => void;
}

export class AddCardButton extends React.Component<AddCardButtonProps> {
  render() {
    return (
      <div className={cn("position")}>
        <div onClick={this.props.onClick} className={cn("add-button")}>
          Добавить карточку
        </div>
      </div>
    );
  }
}
