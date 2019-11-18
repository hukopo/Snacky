import * as React from "react";
import { AddCardModal } from "../AddCardModal/AddCardModal";
import { Board } from "../Board/Board";
import { Button } from "../Button/Button";
import "./App.less";

interface AppState {
  addCardModalIsOpen: boolean;
}

export class App extends React.Component<void, AppState> {
  state = {
    addCardModalIsOpen: false
  };

  render() {
    return (
      <>
        <Button
          text="Добавить карточку"
          onClick={() => this.setState({ addCardModalIsOpen: true })}
        />
        {this.state.addCardModalIsOpen && <AddCardModal />}
        <Board />
      </>
    );
  }
}
