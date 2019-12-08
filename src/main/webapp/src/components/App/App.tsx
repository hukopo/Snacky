import * as React from "react";
import { Board } from "../Board/Board";
import { Button } from "../Button/Button";
import { Logout } from "../Logout/Logout";
import cn from "./App.less";
import CardModal from "../CardModal";

enum CardModalState {
    openForEdit,
    openForAdding,
    close
}

interface AppState {
  cardModalState: CardModalState
  editableCard: CardDto | undefined
}

export class App extends React.Component<{}, AppState> {
  state = {
    cardModalState: CardModalState.close,
    editableCard: undefined
  };

  modalClose = () => this.setState({ cardModalState: CardModalState.close, editableCard: undefined });
  onEditCard = (card: CardDto) => {
    this.setState({ editableCard: card});
    this.setState({ cardModalState: CardModalState.openForEdit });
  };

  render() {
    return (
      <>
        <Logout />
        <div className={cn("position")}>
          <Button
            text="Добавить карточку"
            onClick={() => this.setState({ cardModalState: CardModalState.openForAdding })}
          />
        </div>
        {this.state.cardModalState === CardModalState.openForAdding && (
          <CardModal edit={false} close={this.modalClose}/>
        )}
        {this.state.cardModalState === CardModalState.openForEdit && (
          <CardModal edit={true} close={this.modalClose} card={this.state.editableCard} />
        )}
        <Board onEditCard={this.onEditCard} />
      </>
    );
  }
}
