import * as React from "react";
import { Button } from "../Button/Button";
import { ModalWindow } from "../ModalWindow/ModalWindow";

interface AddCardModalState {
  title: string;
  description: string;
  place: string;
  members: string;
}

export class AddCardModal extends React.Component {
  sendCard = async (): Promise<void> => {
    const card: CardDto = {
      creator: { username: "1", email: "1@1.ru" },
      title: document.getElementById("title").value,
      description: { content: document.getElementById("description").value },
      members: [],
      startDate: new Date(),
      endDate: new Date(),
      place: {
        name: "place",
        longitude: 1.2,
        latitude: 90.9
      }
    };

    await fetch("http://localhost:8080/cards/add", {
      method: "POST",
      body: JSON.stringify(card)
    });
    //const title = document.getElementById("title").value;
  };

  render() {
    return (
      <ModalWindow close={() => this.setState({ addCardModalIsOpen: false })}>
        <div>
          <label htmlFor="title">название</label>
          <input id="title" />
        </div>
        <div>
          <label htmlFor="description">описание</label>
          <textarea id="description" />
        </div>
        <div>
          <label htmlFor="members">участники</label>
          <input id="members" />
        </div>
        {/* <div>
          <label htmlFor="tags">тэги</label>
          <input id="tags" />
        </div> */}
        <div>
          <label htmlFor="place">место</label>
          <input id="place" />
        </div>

        <Button text="Добавить карточку" onClick={this.sendCard} />
      </ModalWindow>
    );
  }
}
