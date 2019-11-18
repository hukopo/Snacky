import * as React from "react";
import { AddCardButton } from "../AddCardButton/AddCardButton";
import { AddCardModal } from "../AddCardModal/AddCardModal";
import { Board } from "../Board/Board";
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
        <AddCardButton
          onClick={() => this.setState({ addCardModalIsOpen: true })}
        />
        {this.state.addCardModalIsOpen && <AddCardModal />}
        <Board />
      </>
    );
  }
}
