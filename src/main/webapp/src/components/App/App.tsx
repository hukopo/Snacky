import * as React from "react";
import { AddCardModal } from "../AddCardModal/AddCardModal";
import { Board } from "../Board/Board";
import { Button } from "../Button/Button";
import cn from "./App.less";

interface AppState {
  addCardModalIsOpen: boolean;
}

export class App extends React.Component<{}, AppState> {
  state = {
    addCardModalIsOpen: false
  };

  render() {
    return (
      <>
        <div className={cn("position")}>
          <Button
            text="Добавить карточку"
            onClick={() => this.setState({ addCardModalIsOpen: true })}
          />
        </div>
        {this.state.addCardModalIsOpen && (
          <AddCardModal
            close={() => this.setState({ addCardModalIsOpen: false })}
          />
        )}
        <Board />
      </>
    );
  }
}
