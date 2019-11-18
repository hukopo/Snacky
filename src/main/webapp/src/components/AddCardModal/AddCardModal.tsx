import * as React from "react";
import { Button } from "../Button/Button";
import { ModalWindow, ModalWindowProps } from "../ModalWindow/ModalWindow";

interface AddCardModalProps extends ModalWindowProps {}
//interface HTMLEventType extends HTMLInputElement, HTMLTextAreaElement {}

interface AddCardModalState {
  title: string;
  description: string;
  place: string;
  members: string;
  tags: string;
  startDate: string;
  endDate: string;
}

export class AddCardModal extends React.Component<
  AddCardModalProps,
  AddCardModalState
> {
  sendCard = async (): Promise<void> => {
    const state = this.state;
    const splitedTags = state.tags
      .split(" ")
      .map(t => ({ name: t } as TagModel));
    const card: CardDto = {
      //creator: { userName: "", email: "" },
      title: state.title,
      description: { content: state.description },
      tags: splitedTags,
      members: [],
      startDate: state.startDate,
      endDate: state.startDate,
      place: {
        name: "place",
        longitude: 1.2,
        latitude: 90.9
      }
    };

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    await fetch("/cards/add", {
      headers: myHeaders,
      method: "POST",
      body: JSON.stringify(card)
    });
  };

  handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {
      target: { id, value }
    } = event;
    this.setState({ [id]: value } as Pick<
      AddCardModalState,
      keyof AddCardModalState
    >);
  };

  handleTextAreaChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const {
      target: { id, value }
    } = event;
    this.setState({ [id]: value } as Pick<
      AddCardModalState,
      keyof AddCardModalState
    >);
  };

  render() {
    return (
      <ModalWindow close={this.props.close}>
        <div>
          <label htmlFor="title">название</label>
          <input onChange={this.handleInputChange} id="title" />
        </div>
        <div>
          <label htmlFor="description">описание</label>
          <textarea onChange={this.handleTextAreaChange} id="description" />
        </div>
        <div>
          <label htmlFor="members">участники</label>
          <input onChange={this.handleInputChange} id="members" />
        </div>
        <div>
          <label htmlFor="tags">тэги</label>
          <input id="tags" />
        </div>
        <div>
          <label htmlFor="place">место</label>
          <input onChange={this.handleInputChange} id="place" />
        </div>
        <div>
          <label htmlFor="startDate">Дата начала</label>
          <input
            onChange={this.handleInputChange}
            type="datetime-local"
            id="startDate"
          />
        </div>
        <div>
          <label htmlFor="endDate">Дата конца</label>
          <input
            onChange={this.handleInputChange}
            //for debug            onChange={e => console.log(e.target.value)}
            type="datetime-local"
            id="endDate"
          />
        </div>

        <Button text="Добавить карточку" onClick={this.sendCard} />
      </ModalWindow>
    );
  }
}
